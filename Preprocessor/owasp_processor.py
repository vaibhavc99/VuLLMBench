import os
import re
import shutil
import pandas as pd
from Preprocessor.obfuscation import obfuscate, rename
from Preprocessor.owasp_cache import CacheManager

class OwaspProcessor:
    """
    Process OWASP code examples.

    Parameters:
    - owasp_dir (str): The directory path where the OWASP dataset is located.
    - useCache (bool, optional): Flag indicating whether to use caching for processed examples. Defaults to True.
    - saveFiles (bool, optional): Flag indicating whether to save obfuscated files. Defaults to False.

    Attributes:
    - examples_dir (str): The directory path where the original code examples are located.
    - obfuscated_dir (str): The directory path where the obfuscated code examples will be saved.
    - groundTruth (str): The path to the file containing the ground truth.
    - cache_manager (CacheManager): An instance of the CacheManager class for managing caching.
    - useCache (bool): Flag indicating whether to use caching for processed examples.
    - saveFiles (bool): Flag indicating whether to save obfuscated files.
    - processing_options (dict): A dictionary containing the processing options.
    """

    def __init__(self, owasp_dir, useCache=True, saveFiles=False):
        self.examples_dir = os.path.join(owasp_dir, 'examples')
        self.obfuscated_dir = os.path.join(owasp_dir, 'obfuscated')
        self.groundTruth = os.path.join(owasp_dir, 'expectedresults-1.2.csv')
        self.cache_manager = CacheManager(owasp_dir)
        self.useCache = useCache
        self.saveFiles = saveFiles
        self.processing_options = None
        self.setup_obfuscated_dir()

    def setup_obfuscated_dir(self):
        """
        Creates the obfuscated directory if it doesn't exist.
        """
        if not os.path.exists(self.obfuscated_dir):
            os.makedirs(self.obfuscated_dir)

    def remove_multiline_comments(self, java_code):
        """
        Removes multiline comments from the given Java code.

        Parameters:
        - java_code (str): The Java code.

        Returns:
        - str: The Java code with multiline comments removed.
        """
        pattern = re.compile(r'/\*.*?\*/', re.DOTALL)
        cleaned_code = re.sub(pattern, '', java_code)
        return cleaned_code

    def remove_import_statements(self, java_code):
        """
        Removes import statements from the given Java code.

        Parameters:
        - java_code (str): The Java code.

        Returns:
        - str: The Java code with import statements removed.
        """
        lines = java_code.split('\n')
        cleaned_lines = [line for line in lines if not line.strip().startswith('import')]
        return '\n'.join(cleaned_lines)

    def remove_package_declarations(self, java_code):
        """
        Removes package declarations from the given Java code.

        Parameters:
        - java_code (str): The Java code.

        Returns:
        - str: The Java code with package declarations removed.
        """
        lines = java_code.split('\n')
        cleaned_lines = [line for line in lines if not line.strip().startswith('package')]
        return '\n'.join(cleaned_lines)

    def replace_benchmark_names(self, java_code):
        """
        Replaces benchmark related names in the given Java code with dummy names.

        Parameters:
        - java_code (str): The Java code.

        Returns:
        - str: The Java code with benchmark names replaced.
        """
        pattern = re.compile(r'\b(owasp|benchmark)\b', re.IGNORECASE)
        return pattern.sub(lambda match: self.replace_with_dummy(match, 'suite'), java_code)
    
    def replace_cwe_names(self, java_code):
        """
        Replaces CWE names in the given Java code with dummy names.

        Parameters:
        - java_code (str): The Java code.

        Returns:
        - str: The Java code with CWE names replaced.
        """
        cwe_names = [
            'pathtraver', 'hash', 'trustbound', 'crypto', 'cmdi', 
            'xss', 'securecookie', 'ldapi', 'weakrand', 'xpathi', 'sqli'
        ]
        pattern = re.compile(r'\b(' + '|'.join(cwe_names) + r')\b', re.IGNORECASE)
        return pattern.sub(lambda match: self.replace_with_dummy(match, 'cat'), java_code)

    def replace_with_dummy(self, match, prefix):
        """
        Replaces the matched keyword with a dummy name.

        Parameters:
        - match (re.Match): The matched keyword.
        - prefix (str): The prefix to be used for renaming.

        Returns:
        - str: The renamed name (prefix_{hash of keyword}).
        """
        keyword = match.group().lower()
        return rename(keyword, prefix)

    def obfuscate_code(self, java_code):
        """
        Obfuscates the given Java code.

        Parameters:
        - java_code (str): The Java code.

        Returns:
        - tuple: A tuple containing the obfuscated code and the new class name.
        """
        obfuscated_code, new_class_name = obfuscate(java_code, classes=True, methods=False, variables=True, parameters=True)
        return obfuscated_code, new_class_name

    def load_and_process_examples(self, processing_options=None):
        """
        Loads and processes the OWASP code examples.

        Parameters:
        - processing_options (dict, optional): A dictionary containing the processing options. Defaults to None.

        Returns:
        - pd.DataFrame: The processed examples as a pandas DataFrame.
        """
        self.processing_options = processing_options if processing_options else {'remove_multiline_comments': True}
        owasp_df = pd.read_csv(self.groundTruth, index_col='test_name')
        owasp_df.columns = owasp_df.columns.str.strip()

        table_name = self.generate_table_name()

        if self.useCache:
            if not self.cache_manager.is_cache_initialized(table_name):
                df = self.process_examples(owasp_df)
                self.cache_manager.process_and_cache_examples(df, table_name)

            return self.cache_manager.load_cached_examples(table_name)
        
        else:
            return self.process_examples(owasp_df)

    def generate_table_name(self):
        """
        Generates a table name based on the processing options.

        Returns:
        - str: The table name.
        """
        def acronym(key):
            return ''.join(word[0] for word in key.split('_'))

        option_names = [acronym(key) for key, value in self.processing_options.items() if value]
        table_name_suffix = "_".join(option_names)
        table_name = f"{self.cache_manager.cache_table_prefix}_{table_name_suffix}"
        return table_name

    def process_examples(self, owasp_df: pd.DataFrame):
        """
        Processes the OWASP code examples.

        Parameters:
        - owasp_df (pd.DataFrame): The DataFrame containing the OWASP code examples.

        Returns:
        - pd.DataFrame: The processed examples as a pandas DataFrame.
        """
        code_snippets = {}
        file_list = os.listdir(self.examples_dir)

        for filename in file_list:
            if filename.endswith(".java"):
                file_path = os.path.join(self.examples_dir, filename)
                with open(file_path, 'r', encoding='utf-8') as file:
                    java_code = file.read()

                java_code, new_class_name = self.apply_processing_steps(java_code)

                # Assuming the file name without extension matches the test name
                test_name = os.path.splitext(filename)[0]
                code_snippets[test_name] = java_code

                if new_class_name and self.saveFiles:
                    self.save_files(test_name, new_class_name, java_code)

        owasp_df['code_snippet'] = owasp_df.index.map(code_snippets)
        return owasp_df[['category', 'real_vulnerability', 'cwe', 'code_snippet']]

    def apply_processing_steps(self, java_code):
        """
        Applies the processing steps to the given Java code.

        Parameters:
        - java_code (str): The Java code.

        Returns:
        - tuple: A tuple containing the processed Java code and the new class name.
        """
        processing_steps = {
            'obfuscate_code': self.obfuscate_code,
            'remove_multiline_comments': self.remove_multiline_comments,
            'remove_import_statements': self.remove_import_statements,
            'remove_package_declarations': self.remove_package_declarations,
            'replace_benchmark_names': self.replace_benchmark_names,
            'replace_cwe_names': self.replace_cwe_names,
        }

        new_class_name = None
        for step, method in processing_steps.items():
            if self.processing_options.get(step, False):
                if step == 'obfuscate_code':
                    java_code, new_class_name = method(java_code)
                else:
                    java_code = method(java_code)
        
        return java_code, new_class_name

    def save_files(self, original_class_name, new_class_name, java_code):
        """
        Saves the obfuscated files.

        Parameters:
        - original_class_name (str): The original class name.
        - new_class_name (str): The new class name.
        - java_code (str): The obfuscated Java code.
        """
        obfuscated_java_path = os.path.join(self.obfuscated_dir, f"{new_class_name}.java")
        with open(obfuscated_java_path, 'w', encoding='utf-8') as file:
            file.write(java_code)

        original_xml_path = os.path.join(self.examples_dir, f"{original_class_name}.xml")
        new_xml_path = os.path.join(self.obfuscated_dir, f"{new_class_name}.xml")
        shutil.copyfile(original_xml_path, new_xml_path)


if __name__ == "__main__":
    owasp_dir = 'CodeExamples/OWASP'
    
    processing_options = {
        'obfuscate_code': True,
        'remove_multiline_comments': True,
        'remove_import_statements': False,
        'remove_package_declarations': False,
        'replace_benchmark_names': True,
        'replace_cwe_names': True
    }
    
    owasp = OwaspProcessor(owasp_dir, useCache=False, saveFiles=True)
    df = owasp.load_and_process_examples(processing_options)
    print(df.head())