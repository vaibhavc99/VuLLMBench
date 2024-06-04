import os
import re
import random
import shutil
import pandas as pd
from Preprocessor.obfuscation import obfuscate
from Preprocessor.owasp_cache import CacheManager

class OwaspProcessor:
    def __init__(self, data_dir, useCache=True, saveFiles=False):
        self.examples_dir = os.path.join(data_dir, 'owasp_code_examples')
        self.obfuscated_dir = os.path.join(data_dir, 'owasp_code_examples_obfuscated')
        self.groundTruth = os.path.join(data_dir, 'expectedresults-1.2.csv')
        self.dummy_name_map = {}
        self.cache_manager = CacheManager(data_dir)
        self.useCache = useCache
        self.saveFiles = saveFiles
        self.processing_options = None
        self.setup_obfuscated_dir()

    def setup_obfuscated_dir(self):
        if not os.path.exists(self.obfuscated_dir):
            os.makedirs(self.obfuscated_dir)

    def remove_multiline_comments(self, java_code):
        pattern = re.compile(r'/\*.*?\*/', re.DOTALL)
        cleaned_code = re.sub(pattern, '', java_code)
        return cleaned_code

    def remove_import_statements(self, java_code):
        lines = java_code.split('\n')
        cleaned_lines = [line for line in lines if not line.strip().startswith('import')]
        return '\n'.join(cleaned_lines)

    def remove_package_declarations(self, java_code):
        lines = java_code.split('\n')
        cleaned_lines = [line for line in lines if not line.strip().startswith('package')]
        return '\n'.join(cleaned_lines)

    def replace_benchmark_names(self, java_code):
        pattern = re.compile(r'\b(owasp|benchmark)\b', re.IGNORECASE)
        return pattern.sub(self.replace_with_dummy, java_code)
    
    def replace_cwe_names(self, java_code):
        cwe_names = [
            'pathtraver', 'hash', 'trustbound', 'crypto', 'cmdi', 
            'xss', 'securecookie', 'ldapi', 'weakrand', 'xpathi', 'sqli'
        ]
        pattern = re.compile(r'\b(' + '|'.join(cwe_names) + r')\b', re.IGNORECASE)
        return pattern.sub(self.replace_with_dummy, java_code)

    def replace_with_dummy(self, match):
        keyword = match.group().lower()
        if keyword not in self.dummy_name_map:
            self.dummy_name_map[keyword] = self.generate_random_name()
        return self.dummy_name_map[keyword]

    def generate_random_name(self):
        names = ['Alpha', 'Beta', 'Gamma', 'Delta', 'Epsilon']
        adjs = ['Quick', 'Bright', 'Silent', 'Clever', 'Brave']
        return f"{random.choice(adjs)}{random.choice(names)}{random.randint(100, 999)}"
    
    def obfuscate_code(self, java_code):
        obfuscated_code, new_class_name = obfuscate(java_code, classes=True, methods=False, variables=True, parameters=True)
        return obfuscated_code, new_class_name

    def load_and_process_examples(self, processing_options=None):
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
        def acronym(key):
            return ''.join(word[0] for word in key.split('_'))

        option_names = [acronym(key) for key, value in self.processing_options.items() if value]
        table_name_suffix = "_".join(option_names)
        table_name = f"{self.cache_manager.cache_table_prefix}_{table_name_suffix}"
        return table_name

    def process_examples(self, owasp_df: pd.DataFrame):
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
        obfuscated_java_path = os.path.join(self.obfuscated_dir, f"{new_class_name}.java")
        with open(obfuscated_java_path, 'w', encoding='utf-8') as file:
            file.write(java_code)

        original_xml_path = os.path.join(self.examples_dir, f"{original_class_name}.xml")
        new_xml_path = os.path.join(self.obfuscated_dir, f"{new_class_name}.xml")
        shutil.copyfile(original_xml_path, new_xml_path)


if __name__ == "__main__":
    data_dir = '../CodeExamples'
    
    processing_options = {
        'obfuscate_code': True,
        'remove_multiline_comments': True,
        'remove_import_statements': False,
        'remove_package_declarations': False,
        'replace_benchmark_names': True,
        'replace_cwe_names': True
    }
    
    owasp = OwaspProcessor(data_dir, useCache=True, saveFiles=True)
    df = owasp.load_and_process_examples(processing_options)
    print(df.head())