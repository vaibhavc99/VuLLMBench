import os
import pandas as pd
import re
import random
import sqlite3

class OWASP:
    def __init__(self, data_dir, useCache=True):
        self.examples_dir = os.path.join(data_dir, 'owasp_code_examples')
        self.groundTruth = os.path.join(data_dir, 'expectedresults-1.2.csv')
        self.dummy_name_map = {}
        self.db_path = os.path.join(data_dir, 'owasp_cache.db')
        self.cache_table = 'processed_examples'
        self.useCache = useCache

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
        pattern = re.compile(r'\b(BenchmarkTest\d{5}|owasp|benchmark)\b', re.IGNORECASE)
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

    def load_and_process_examples(self):
        owasp_df = pd.read_csv(self.groundTruth, index_col='test_name')
        owasp_df.columns = owasp_df.columns.str.strip()

        if self.useCache:
            if not self.is_cache_initialized():
                self.process_and_cache_examples(owasp_df)
            return self.load_cached_examples()
        else:
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

                java_code = self.remove_multiline_comments(java_code)
                java_code = self.remove_import_statements(java_code)
                java_code = self.remove_package_declarations(java_code)
                java_code = self.replace_benchmark_names(java_code)
                java_code = self.replace_cwe_names(java_code)

                # Assuming the file name without extension matches the test name
                test_name = os.path.splitext(filename)[0]
                code_snippets[test_name] = java_code

        owasp_df['code_snippet'] = owasp_df.index.map(code_snippets)
        return owasp_df[['category', 'real_vulnerability', 'cwe', 'code_snippet']]

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
    owasp = OWASP(data_dir)
    df = owasp.load_and_process_examples()
    print(df.head())