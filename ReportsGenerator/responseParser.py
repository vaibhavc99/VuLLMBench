import pandas as pd

class ResponseParser:
    def __init__(self, delimiter='|'):
        self.delimiter = delimiter

    def parse_response(self, response):
        if response is None:
            return {}
        
        parsed_data = {}
        try:
            parts = response.split(self.delimiter)
            for part in parts:
                key, value = part.strip().split(':', 1)
                parsed_data[key.strip()] = value.strip()

        except ValueError as e:
            print(f"Error parsing response: {response}. Error: {e}")
            # pass
        return parsed_data

    def responses_to_dataframe(self, responses, prompt_type):
        responses_list = []
        for test_name, results in responses.items():
            for model_name, response in results.items():
                parsed_response = self.parse_response(response)
                parsed_response['Test Name'] = test_name
                parsed_response['Model'] = model_name
                responses_list.append(parsed_response)

        df = pd.DataFrame(responses_list)
        df.set_index('Test Name', inplace=True)

        df['Predicted Vulnerability'] = df['Vulnerability'].str.strip().str.lower().map({'true': True, 'false': False})
        df['Predicted Vulnerability Type'] = df.get('Vulnerability Type', 'None')
        df['Predicted Vulnerability Name'] = df.get('Vulnerability Name', 'None')
        
        if prompt_type == 'explanatory_insights':
            df['Explanation'] = df.get('Explanation', 'None')
        if prompt_type == 'solution_oriented':            
            df['Solution'] = df.get('Solution', 'None')

        df.drop(columns=['Vulnerability', 'Vulnerability Type','Vulnerability Name'], inplace=True)

        return df
    
    def save(self, df, filepath):
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
