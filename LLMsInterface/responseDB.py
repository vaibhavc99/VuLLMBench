import sqlite3

class LLMResponseDB:
    def __init__(self, db_path):
        self.db = sqlite3.connect(db_path)
        self.cursor = self.db.cursor()

    def ensure_table_exists(self, table):
        table_creation_query = f"""
        CREATE TABLE IF NOT EXISTS {table} (
            test_name TEXT PRIMARY KEY);
        """
        self.cursor.execute(table_creation_query)
        self.db.commit()

    def ensure_column_exists(self, table, column):
        if not self.column_exists(table, column):
            self.cursor.execute(f"ALTER TABLE {table} ADD COLUMN {column} TEXT")
            self.db.commit()

    def column_exists(self, table, column):
        self.cursor.execute(f"PRAGMA table_info({table});")
        columns = [info[1] for info in self.cursor.fetchall()]
        return column.strip('"') in columns

    def insert_response(self, table, test_name, prompt, model_name, response):
        self.ensure_table_exists(table)
        model_column = f'"{model_name}"'
        prompt_str = f"SYSTEM_PROMPT: {prompt[0].content}\nUSER_PROMPT: {prompt[1].content}"

        self.ensure_column_exists(table, "prompt")
        self.ensure_column_exists(table, model_column)
        
        self.cursor.execute(f"SELECT {model_column} FROM {table} WHERE test_name = ?", (test_name,))

        row = self.cursor.fetchone()
        if row is None:
            self.cursor.execute(f"INSERT INTO {table} (test_name, prompt, {model_column}) VALUES (?, ?, ?)", (test_name, prompt_str, response))
        else:
            if row[0] is None:
                self.cursor.execute(f"UPDATE {table} SET prompt = ? WHERE test_name = ?", (prompt_str, test_name))
            self.cursor.execute(f"UPDATE {table} SET {model_column} = ? WHERE test_name = ?", (response, test_name))

        self.db.commit()
    
    def response_exists(self, table, test_name, model_name):
        try:
            model_column = f'"{model_name}"'
            if self.column_exists(table, model_column):
                self.cursor.execute(f"SELECT {model_column} FROM {table} WHERE test_name = ?", (test_name,))
                result = self.cursor.fetchone()
                return result is not None and result[0] is not None
            else:
                return False
        except Exception as e:
            print(e)
            return False
    
    def get_all_responses(self, table):
        self.cursor.execute(f"SELECT * FROM {table}")
        column_names = [description[0] for description in self.cursor.description][1:]
        rows = self.cursor.fetchall()
        return {row[0]: dict(zip(column_names, row[1:])) for row in rows}
    
    def get_responses_by_model(self, table, model_name):
        model_column = f'"{model_name}"'
        if self.column_exists(table, model_column):
            self.cursor.execute(f"SELECT test_name, {model_column} FROM {table}")
            rows = self.cursor.fetchall()
            return {row[0]: row[1] for row in rows if row[1] is not None}
        else:
            return {}

    def close(self):
        self.db.close()

if __name__ == '__main__':
    db_path = '../llms_responses.db'
    table_name = 'simple'
    db = LLMResponseDB(db_path)
    # print(db.response_exists("simple", "BenchmarkTest00001","llama3-70b-8192"))
    # db.ensure_table_exists(table_name)
    all_responses = db.get_all_responses(table_name)
    db.close()
    print(all_responses)
