import os
import sqlite3
import pandas as pd

class CacheManager:
    def __init__(self, data_dir):
        self.db_path = os.path.join(data_dir, 'owasp_cache.db')
        self.cache_table_prefix = 'processed_examples'

    def is_cache_initialized(self, table_name):
        conn = sqlite3.connect(self.db_path)
        cursor = conn.cursor()
        
        cursor.execute(f'''
            SELECT name FROM sqlite_master WHERE type='table' AND name='{table_name}';
        ''')
        
        table_exists = cursor.fetchone() is not None
        conn.close()
        
        return table_exists

    def load_cached_examples(self, table_name):
        conn = sqlite3.connect(self.db_path)
        query = f'''
            SELECT * FROM {table_name};
        '''
        cached_df = pd.read_sql_query(query, conn, index_col='test_name')
        conn.close()
        
        return cached_df

    def process_and_cache_examples(self, df, table_name):
        conn = sqlite3.connect(self.db_path)
        df.to_sql(table_name, conn, if_exists='replace', index=True)
        conn.close()
