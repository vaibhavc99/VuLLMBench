import os
import sqlite3
import pandas as pd

class CacheManager:
    def __init__(self, data_dir):
        """
        Initializes a CacheManager object.

        Parameters:
        - data_dir (str): The directory where the cache database will be stored.
        """
        self.db_path = os.path.join(data_dir, 'owasp_cache.db')
        self.cache_table_prefix = 'processed_examples'

    def is_cache_initialized(self, table_name):
        """
        Checks if a cache table is initialized in the cache database.

        Parameters:
        - table_name (str): The name of the cache table.

        Returns:
        - bool: True if the cache table exists, False otherwise.
        """
        conn = sqlite3.connect(self.db_path)
        cursor = conn.cursor()
        
        cursor.execute(f'''
            SELECT name FROM sqlite_master WHERE type='table' AND name='{table_name}';
        ''')
        
        table_exists = cursor.fetchone() is not None
        conn.close()
        
        return table_exists

    def load_cached_examples(self, table_name):
        """
        Loads cached examples from a cache table in the cache database.

        Parameters:
        - table_name (str): The name of the cache table.

        Returns:
        - pandas.DataFrame: The cached examples as a DataFrame.
        """
        conn = sqlite3.connect(self.db_path)
        query = f'''
            SELECT * FROM {table_name};
        '''
        cached_df = pd.read_sql_query(query, conn, index_col='test_name')
        conn.close()
        
        return cached_df

    def process_and_cache_examples(self, df, table_name):
        """
        Processes and caches examples in a cache table in the cache database.

        Parameters:
        - df (pandas.DataFrame): The examples to be cached as a DataFrame.
        - table_name (str): The name of the cache table.
        """
        conn = sqlite3.connect(self.db_path)
        df.to_sql(table_name, conn, if_exists='replace', index=True)
        conn.close()
