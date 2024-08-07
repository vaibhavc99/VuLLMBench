import pandas as pd
import json
import re

class ResponseParser:
    """
    A class for parsing and processing LLM response data.
    """

    def parse_response(self, response):
        """
        Parses a response string and returns a dictionary with vulnerability details.
        {
          "vulnerability": <true or false>,
          "vulnerability_type": <CWE_number>,
          "vulnerability_name": <Name of CWE>
        }

        Parameters:
        - response (str): The response string to be parsed.

        Returns:
        - dict: A dictionary containing the parsed key-value pairs.

        """
        if response is None:
            return {}
        
        # Replace any case variations of True/False with lowercase true/false using regex
        response = re.sub(r'\btrue\b', 'true', response, flags=re.IGNORECASE)
        response = re.sub(r'\bfalse\b', 'false', response, flags=re.IGNORECASE)
        
        try:
            parsed_data = json.loads(response)
        except json.JSONDecodeError as e:
            print(f"Error parsing response: {response}. Error: {e}")
            return {}
        
        # Extract CWE if vulnerability_type is a string
        if 'vulnerability_type' in parsed_data and isinstance(parsed_data['vulnerability_type'], str):
            parsed_data['vulnerability_type'] = self.extract_cwe(parsed_data['vulnerability_type'])
        
        return parsed_data

    def extract_cwe(self, cwe_string):
        """
        Extracts the CWE number from a vulnerability_type in case of non-integer response.

        Parameters:
        - cwe_string (str): predicted vulnerability_type.

        Returns:
        - int: The extracted CWE number or None if no CWE is found.
        """
        match = re.search(r'(?i)cwe-?(\d+)|\b(\d+)\b', cwe_string)
        if match:
            cwe_number = match.group(1) if match.group(1) else match.group(2)
            return int(cwe_number)
        return None

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

        df['predicted_vulnerability'] = df.get('vulnerability', None)
        df['predicted_vulnerability_name'] = df.get('vulnerability_name', 'None')

        if prompt_type == 'vulnerability_names':
            df['predicted_cwe'] = 'None'
        else:
            df['predicted_cwe'] = df.get('vulnerability_type', 'None')

        if prompt_type == 'explanatory_insights':
            df['explanation'] = df.get('explanation', 'None')
        if prompt_type == 'solution_oriented':            
            df['solution'] = df.get('solution', 'None')

        df.drop(columns=['vulnerability', 'vulnerability_type', 'vulnerability_name'], inplace=True, errors='ignore')

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
            'llama3-8b-8192': '{"vulnerability": True, "vulnerability_type": 22, "vulnerability_name": "Path Traversal"}',
            'mixtral-8x7b-32768': '{"vulnerability": True, "vulnerability_type": "CWE-22", "vulnerability_name": "Path Traversal"}'
        },
        'BenchmarkTest00002': {
            'llama3-8b-8192': '{"vulnerability": true, "vulnerability_type": "CWE-22: Improper Limitation of a Path to a Predictable String within a Non-Canonical Path", "vulnerability_name": "Directory Traversal"}',
            'mixtral-8x7b-32768': '{"vulnerability": true, "vulnerability_type": 22, "vulnerability_name": "Path Traversal"}'
        }
    }

    parser = ResponseParser()
    df = parser.responses_to_dataframe(responses, "simple")
    print(df)
    parser.save(df, './response_df.csv')
