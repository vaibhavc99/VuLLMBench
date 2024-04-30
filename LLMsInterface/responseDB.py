import sqlite3

class LLMResponseDB:
    def __init__(self, table, db_path):
        self.db = sqlite3.connect(db_path)
        self.cursor = self.db.cursor()
        self.table = table
        self.ensure_table_exists()

    def ensure_table_exists(self):
        table_creation_query = f"""
        CREATE TABLE IF NOT EXISTS {self.table} (
            test_name TEXT PRIMARY KEY);
        """
        self.cursor.execute(table_creation_query)
        self.db.commit()

    def ensure_column_exists(self, model_name):
        if not self.column_exists(model_name):
            column = f'"{model_name}"'
            self.cursor.execute(f"ALTER TABLE {self.table} ADD COLUMN {column} TEXT")
            self.db.commit()
    
    def column_exists(self, model_name):
        column = f'"{model_name}"'
        self.cursor.execute(f"PRAGMA table_info({self.table});")
        columns = [info[1] for info in self.cursor.fetchall()]
        return column.strip('"') in columns

    def insert_response(self, test_name, model_name, response):
        self.ensure_column_exists(model_name)
        column = f'"{model_name}"'
        
        self.cursor.execute(f"SELECT {column} FROM {self.table} WHERE test_name = ?", (test_name,))
        if self.cursor.fetchone() is None:
            self.cursor.execute(f"INSERT INTO {self.table} (test_name, {column}) VALUES (?, ?)", (test_name, response))
        else:
            self.cursor.execute(f"UPDATE {self.table} SET {column} = ? WHERE test_name = ?", (response, test_name))

        self.db.commit()
    
    def response_exists(self, test_name, model_name):
        column = f'"{model_name}"'
        self.cursor.execute(f"SELECT {column} FROM {self.table} WHERE test_name = ?", (test_name,))
        result = self.cursor.fetchone()
        return result is not None and result[0] is not None
    
    def get_all_responses(self):
        self.cursor.execute(f"SELECT * FROM {self.table}")
        column_names = [description[0] for description in self.cursor.description][1:]
        rows = self.cursor.fetchall()
        return {row[0]: dict(zip(column_names, row[1:])) for row in rows}

    def close(self):
        self.db.close()

if __name__ == '__main__':
    db = LLMResponseDB('simple', '../llms_responses.db')
    all_responses = db.get_all_responses()
    db.close()
    print(all_responses)