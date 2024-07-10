import sqlite3
import json
from langchain_core.messages import SystemMessage, HumanMessage, AIMessage

class LLMResponseDB:
    """
    Database for storing LLM responses.

    Attributes:
    - db_path (str): The path to the SQLite database file.
    - db (sqlite3.Connection): The connection to the SQLite database.
    - cursor (sqlite3.Cursor): The cursor object for executing SQL queries.
    """

    def __init__(self, db_path):
        """
        Initializes a new instance of the LLMResponseDB class.

        Parameters:
        - db_path (str): The path to the SQLite database file.
        """
        self.db = sqlite3.connect(db_path)
        self.cursor = self.db.cursor()

    def ensure_table_exists(self, table):
        """
        Ensures that the specified table exists in the database. If the table does not exist, it creates it.

        Parameters:
        - table (str): The name of the table.
        """
        table_creation_query = f"""
        CREATE TABLE IF NOT EXISTS {table} (
            test_name TEXT PRIMARY KEY);
        """
        self.cursor.execute(table_creation_query)
        self.db.commit()

    def ensure_column_exists(self, table, column):
        """
        Ensures that the specified column exists in the specified table. If the column does not exist, it adds it to the table.

        Parameters:
        - table (str): The name of the table.
        - column (str): The name of the column.
        """
        if not self.column_exists(table, column):
            self.cursor.execute(f"ALTER TABLE {table} ADD COLUMN {column} TEXT")
            self.db.commit()

    def column_exists(self, table, column):
        """
        Checks if the specified column exists in the specified table.

        Parameters:
        - table (str): The name of the table.
        - column (str): The name of the column.

        Returns:
        - bool: True if the column exists, False otherwise.
        """
        self.cursor.execute(f"PRAGMA table_info({table});")
        columns = [info[1] for info in self.cursor.fetchall()]
        return column.strip('"') in columns

    def insert_response(self, table, test_name, prompt, model_name, response):
        """
        Inserts a response into the specified table.

        Parameters:
        - table (str): The name of the table.
        - test_name (str): The name of the test.
        - prompt (list): The list of messages for the response.
        - model_name (str): The name of the model.
        - response (str): The response.
        """
        self.ensure_table_exists(table)
        model_column = f'"{model_name}"'

        # Extract the message type and content from each message and convert to a JSON string
        prompt_contents = [{"type": type(message).__name__, "content": message.content} for message in prompt]
        prompt_json = json.dumps(prompt_contents)

        self.ensure_column_exists(table, "prompt")
        self.ensure_column_exists(table, model_column)
        
        self.cursor.execute(f"SELECT {model_column} FROM {table} WHERE test_name = ?", (test_name,))

        row = self.cursor.fetchone()
        if row is None:
            self.cursor.execute(f"INSERT INTO {table} (test_name, prompt, {model_column}) VALUES (?, ?, ?)", (test_name, prompt_json, response))
        else:
            if row[0] is None:
                self.cursor.execute(f"UPDATE {table} SET prompt = ? WHERE test_name = ?", (prompt_json, test_name))
            self.cursor.execute(f"UPDATE {table} SET {model_column} = ? WHERE test_name = ?", (response, test_name))

        self.db.commit()
    
    def response_exists(self, table, test_name, model_name):
        """
        Checks if a response exists in the specified table for the given test name and model name.

        Parameters:
        - table (str): The name of the table.
        - test_name (str): The name of the test.
        - model_name (str): The name of the model.

        Returns:
        - bool: True if the response exists, False otherwise.
        """
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
        """
        Retrieves all responses from the specified table.

        Parameters:
        - table (str): The name of the table.

        Returns:
        - dict: A dictionary containing all responses, where the keys are the test names and the values are dictionaries representing the responses.
        """
        self.cursor.execute(f"SELECT * FROM {table}")
        column_names = [description[0] for description in self.cursor.description][2:]
        rows = self.cursor.fetchall()
        return {row[0]: dict(zip(column_names, row[2:])) for row in rows}
    
    def get_response_by_index_and_model(self, table, index, model_name):
        """
        Retrieves the prompt and response for a specific index and model from the specified table.

        Parameters:
        - table (str): The name of the table.
        - index (int): The index of the example.
        - model_name (str): The name of the model.

        Returns:
        - dict: A dictionary containing the prompt and response, or None if not found.
        """
        model_column = f'"{model_name}"'
        if self.column_exists(table, model_column):
            self.cursor.execute(f"SELECT prompt, {model_column} FROM {table} WHERE test_name = ?", (index,))
            row = self.cursor.fetchone()
            if row:
                prompt_json, response = row
                prompt_contents = json.loads(prompt_json)
                prompt = []
                for item in prompt_contents:
                    if item['type'] == 'SystemMessage':
                        prompt.append(SystemMessage(content=item['content']))
                    elif item['type'] == 'HumanMessage':
                        prompt.append(HumanMessage(content=item['content']))
                    elif item['type'] == 'AIMessage':
                        prompt.append(AIMessage(content=item['content']))
                return {
                    'prompt': prompt,
                    'response': response
                }
        return None    

    def close(self):
        """
        Closes the database connection.
        """
        self.db.close()

if __name__ == '__main__':
    db_path = '../llms_responses.db'
    table_name = 'exp07_test_prompt_self_reflection'
    db = LLMResponseDB(db_path)
    # print(db.response_exists("simple", "BenchmarkTest00001","llama3-70b-8192"))
    # db.ensure_table_exists(table_name)
    all_responses = db.get_response_by_index_and_model(table_name, "BenchmarkTest01517","llama3-8b-8192")
    db.close()
    print(all_responses)
