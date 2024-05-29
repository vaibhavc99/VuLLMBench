import os
import shutil
import pandas as pd
import re
import random
import sqlite3
import hashlib

class OWASP:
    def __init__(self, data_dir, useCache=True):
        self.examples_dir = os.path.join(data_dir, 'owasp_code_examples')
        self.obfuscated_dir = os.path.join(data_dir, 'owasp_code_examples_obfuscated')
        self.groundTruth = os.path.join(data_dir, 'expectedresults-1.2.csv')
        self.dummy_name_map = {}
        self.db_path = os.path.join(data_dir, 'owasp_cache.db')
        self.cache_table = 'processed_examples'
        self.useCache = useCache
        self.processing_options = None
        self.setup_obfuscated_dir()

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
    
    def obfuscate(self, java_code):
        patterns = {
            'variable': r'\b(?:int|float|double|char|byte|short|long|String|boolean|List|Map|Set|ArrayList|HashMap)\s+(\w+)[;=]'
        }
        obfuscated_code = java_code
        for type_, pattern in patterns.items():
            matches = re.findall(pattern, java_code)
            unique_matches = set(matches)

            for match in unique_matches:
                obfuscated_name = hashlib.md5(match.encode()).hexdigest()[:8]
                obfuscated_code = re.sub(r'\b' + match + r'\b', obfuscated_name, obfuscated_code)

        return obfuscated_code

    def load_and_process_examples(self, processing_options=None):
        owasp_df = pd.read_csv(self.groundTruth, index_col='test_name')
        owasp_df.columns = owasp_df.columns.str.strip()

        if self.useCache:
            if not self.is_cache_initialized():
                self.process_and_cache_examples(owasp_df)
            return self.load_cached_examples()
        else:
            self.processing_options = processing_options if processing_options else {'remove_multiline_comments': True}
            return self.process_examples(owasp_df)

    def is_cache_initialized(self):
        conn = sqlite3.connect(self.db_path)
        cursor = conn.cursor()
        
        cursor.execute(f'''
            SELECT name FROM sqlite_master WHERE type='table' AND name='{self.cache_table}';
        ''')
        
        table_exists = cursor.fetchone() is not None
        conn.close()
        
        return table_exists

    def process_and_cache_examples(self, owasp_df: pd.DataFrame):
        df = self.process_examples(owasp_df)
        
        conn = sqlite3.connect(self.db_path)
        df.to_sql(self.cache_table, conn, if_exists='replace', index=True)
        conn.close()

    def process_examples(self, owasp_df: pd.DataFrame):
        code_snippets = {}
        file_list = os.listdir(self.examples_dir)

        for filename in file_list:
            if filename.endswith(".java"):
                file_path = os.path.join(self.examples_dir, filename)
                with open(file_path, 'r', encoding='utf-8') as file:
                    java_code = file.read()

                java_code = self.apply_processing_steps(java_code)
                new_filename, java_code = self.rename_class_and_file(filename, java_code)

                # Assuming the file name without extension matches the test name
                test_name = os.path.splitext(filename)[0]
                code_snippets[test_name] = java_code

        owasp_df['code_snippet'] = owasp_df.index.map(code_snippets)
        return owasp_df[['category', 'real_vulnerability', 'cwe', 'code_snippet']]

    def apply_processing_steps(self, java_code):
        processing_steps = {
            'remove_multiline_comments': self.remove_multiline_comments,
            'remove_import_statements': self.remove_import_statements,
            'remove_package_declarations': self.remove_package_declarations,
            'replace_benchmark_names': self.replace_benchmark_names,
            'replace_cwe_names': self.replace_cwe_names,
            'obfuscate': self.obfuscate
        }

        for step, method in processing_steps.items():
            if self.processing_options.get(step, False):
                java_code = method(java_code)
        
        return java_code

    def rename_class_and_file(self, filename, java_code):
        class_name_pattern = re.compile(r'\bBenchmarkTest\d{5}\b')
        matches = class_name_pattern.findall(java_code)
        new_class_name = None
        
        for match in matches:
            if match not in self.dummy_name_map:
                self.dummy_name_map[match] = self.generate_random_name()
            new_class_name = self.dummy_name_map[match]
            java_code = java_code.replace(match, new_class_name)
        
        if new_class_name:
            new_filename = f"{new_class_name}.java"
            original_path = os.path.join(self.examples_dir, filename)
            obfuscated_path = os.path.join(self.obfuscated_dir, new_filename)
            shutil.copyfile(original_path, obfuscated_path)
            with open(obfuscated_path, 'w', encoding='utf-8') as file:
                file.write(java_code)
            return new_filename, java_code
        return filename, java_code

    def setup_obfuscated_dir(self):
        if not os.path.exists(self.obfuscated_dir):
            os.makedirs(self.obfuscated_dir)

    def load_cached_examples(self):
        conn = sqlite3.connect(self.db_path)
        query = f'''
            SELECT * FROM {self.cache_table};
        '''
        cached_df = pd.read_sql_query(query, conn, index_col='test_name')
        conn.close()
        
        return cached_df


if __name__ == "__main__":
    data_dir = '../CodeExamples'
    processing_options = {
        'remove_multiline_comments': True,
        'remove_import_statements': True,
        'remove_package_declarations': True,
        'replace_benchmark_names': False,
        'replace_cwe_names': False,
        'obfuscate': False
    }
    owasp = OWASP(data_dir, useCache=False)
    df = owasp.load_and_process_examples(processing_options)
    print(df.head())
