import pandas as pd

class ResponseParser:
    """
    A class for parsing and processing LLM response data.

    Parameters:
    - delimiter (str): The delimiter used to separate key-value pairs in the response string.

    Attributes:
    - delimiter (str): The delimiter used to separate key-value pairs in the response string.

    """

    def __init__(self, delimiter='|'):
        self.delimiter = delimiter

    def parse_response(self, response):
        """
        Parses a response string and returns a dictionary with vulnerability details.
        {Vulnerability: <True or False>, Vulnerability Type: <CWE_number>, Vulnerability Name: <Name of CWE>}

        Parameters:
        - response (str): The response string to be parsed.

        Returns:
        - dict: A dictionary containing the parsed key-value pairs.

        """
        if response is None:
            return {}
        
        parsed_data = {}
        try:
            parts = response.split(self.delimiter)
            for part in parts:
                if ':' in part:
                    key, value = part.strip().split(':', 1)
                    parsed_data[key.strip()] = value.strip()

        except ValueError as e:
            print(f"Error parsing response: {response}. Error: {e}")
            # pass
        return parsed_data

    def responses_to_dataframe(self, responses, prompt_type):
        """
        Converts a dictionary of responses into a pandas DataFrame.

        Parameters:
        - responses (dict): A dictionary of responses, where the keys are test names and the values are dictionaries of model names and response strings.
        - prompt_type (str): The type of prompt used to generate the responses.

        Returns:
        - pandas.DataFrame: A DataFrame containing the parsed responses.

        """
        responses_list = []
        for test_name, results in responses.items():
            for model_name, response in results.items():
                parsed_response = self.parse_response(response)
                parsed_response['Test Name'] = test_name
                parsed_response['Model'] = model_name
                responses_list.append(parsed_response)

        df = pd.DataFrame(responses_list)
        df.set_index('Test Name', inplace=True)

        df['predicted_vulnerability'] = df['Vulnerability'].str.strip().str.lower().map({'true': True, 'false': False})
        df['predicted_vulnerability_name'] = df.get('Vulnerability Name', 'None')

        if prompt_type == 'vulnerability_names':
            df['predicted_cwe'] = 'None'
        else:
            df['predicted_cwe'] = df.get('Vulnerability Type', 'None')

        if prompt_type == 'explanatory_insights':
            df['explanation'] = df.get('Explanation', 'None')
        if prompt_type == 'solution_oriented':            
            df['solution'] = df.get('Solution', 'None')

        df.drop(columns=['Vulnerability', 'Vulnerability Type','Vulnerability Name'], inplace=True, errors='ignore')

        return df
    
    def save(self, df, filepath):
        """
        Saves a DataFrame to a CSV file.

        Parameters:
        - df (pandas.DataFrame): The DataFrame to be saved.
        - filepath (str): The path to the output CSV file.

        """
        df.to_csv(filepath)
    
if __name__ == '__main__':
    responses = {
        'BenchmarkTest00001': {
            'llama3-8b-8192': 'Vulnerability: true | Vulnerability Type: CWE-22: Improper Limitation of a Path or Directory | Vulnerability Name: Path Traversal | Explanation: Path traversal vulnerability allows unauthorized file access.',
            'mixtral-8x7b-32768': "Vulnerability: TRUE | Vulnerability Type: CWE-22 | Vulnerability Name: Path Traversal | Solution: Validate and sanitize all user inputs to ensure the security of file paths."
        },
        'BenchmarkTest00002': {
            'llama3-8b-8192': 'Vulnerability: True | Vulnerability Type: CWE-22: Improper Limitation of a Path to a Predictable String within a Non-Canonical Path | Vulnerability Name: Directory Traversal | Explanation: This could potentially allow an attacker to access restricted files.',
            'mixtral-8x7b-32768': 'Vulnerability: True | Vulnerability Type: CWE-22 | Vulnerability Name: Path Traversal | Solution: Implement rigorous path normalization before processing user inputs.'
        }
    }

    parser = ResponseParser()
    df = parser.responses_to_dataframe(responses, "simple")
    print(df)
    # parser.save(df, './response_df.csv')
