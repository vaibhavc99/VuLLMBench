import pandas as pd

class ResponseParser:
    def __init__(self, delimiter='|'):
        self.delimiter = delimiter

    def parse_response(self, response):
        parts = response.split(self.delimiter)
        parsed_data = {}
        
        for part in parts:
            key, value = part.strip().split(':', 1)
            parsed_data[key.strip()] = value.strip()
        
        return parsed_data

    def responses_to_dataframe(self, responses):
        responses_list = []
        for test_name, results in responses.items():
            for model_name, response in results.items():
                parsed_response = self.parse_response(response)
                parsed_response['Test Name'] = test_name
                parsed_response['Model'] = model_name
                responses_list.append(parsed_response)

        df = pd.DataFrame(responses_list)
        df.set_index('Test Name', inplace=True)

        df['Predicted Vulnerability'] = df['Vulnerability'].map({'True': True, 'False': False})
        df['Predicted Vulnerability Type'] = df.get('Vulnerability Type', 'None')
    
        df.drop(columns=['Vulnerability'], inplace=True)
        df.drop(columns=['Vulnerability Type'], inplace=True)

        return df
    
    def save(self, df, filepath):
        df.to_csv(filepath)
    
if __name__ == '__main__':
    responses = {
        'BenchmarkTest00001': {
            'llama3-8b-8192': 'Vulnerability: True | Vulnerability Type: CWE-22: Improper Limitation of a Path or Directory',
            'mixtral-8x7b-32768': "Vulnerability: True | Vulnerability Type: CWE-22 (Improper Limitation of a Pathname to a Restricted Directory ('Path Traversal'))."
        },
        'BenchmarkTest00002': {
            'llama3-8b-8192': 'Vulnerability: True | Vulnerability Type: CWE-22: Improper Limitation of a Path to a Predictable String within a Non-Canonical Path.',
            'mixtral-8x7b-32768': 'Vulnerability: True | Vulnerability Type: CWE-22 (Improper Limitation of a Pathname to a Restricted Directory (\'Path Traversal\')).'
        }
    }

    parser = ResponseParser()
    df = parser.responses_to_dataframe(responses)
    print(df)
    parser.save(df, './response_df.csv')
