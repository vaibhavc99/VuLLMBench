import os
import re
import pandas as pd
from PromptGenerator.cwe_lists import top_25_cwe_ids

class CVEFixesProcessor:
    """
    Process CVE fixes examples.

    Parameters:
    - cvefixes_dir (str): The directory path where the CVE fixes dataset is located.

    Attributes:
    - cvefixes_dir (str): The directory path where the CVE fixes dataset is located.
    - examples_csv_path (str): The path to the CSV file containing CVE fixes examples by file.
    - method_examples_csv_path (str): The path to the CSV file containing CVE fixes examples by method.
    - top_25_cwes (list): The top 25 CWE IDs.
    """

    def __init__(self, cvefixes_dir):
        self.cvefixes_dir = cvefixes_dir
        self.examples_csv_path = os.path.join(cvefixes_dir, 'Output/cvefixes_java.csv')
        self.method_examples_csv_path = os.path.join(cvefixes_dir, 'Output/cvefixes_java_method.csv')
        self.top_25_cwes = top_25_cwe_ids

    def remove_multiline_comments(self, java_code):
        """
        Removes multiline comments from the given Java code.

        Parameters:
        - java_code (str): The Java code.

        Returns:
        - str: The Java code with multiline comments removed.
        """
        if not isinstance(java_code, str):
            return java_code
        pattern = re.compile(r'/\*.*?\*/', re.DOTALL)
        cleaned_code = re.sub(pattern, '', java_code)
        return cleaned_code

    def load_and_process_examples(self, processing_options=None):
        """
        Loads and processes the CVE fixes examples based on the provided processing options.

        Parameters:
        - processing_options (dict, optional): Options for processing the examples. Should contain keys:
            - top_25_cwe (bool): Whether to filter by top 25 CWEs.
            - file_examples (bool): Whether to process file examples.
            - method_examples (bool): Whether to process method examples.

        Returns:
        - pd.DataFrame: The processed examples as a pandas DataFrame.
        """
        if processing_options is None:
            processing_options = {'top_25_cwe': True, 'file_examples': True, 'method_examples': False}

        if processing_options.get('file_examples', False):
            csv_path = self.examples_csv_path
        elif processing_options.get('method_examples', False):
            csv_path = self.method_examples_csv_path
        else:
            raise ValueError("processing_options must specify either 'file_examples' or 'method_examples' as True.")

        cvefixes_df = pd.read_csv(csv_path)
        cvefixes_df.columns = cvefixes_df.columns.str.strip()

        # Filter out rows where 'code' is NaN
        cvefixes_df = cvefixes_df.dropna(subset=['code'])

        processed_snippets = []
        cwe_ids = []
        for index, row in cvefixes_df.iterrows():
            java_code = row['code']
            processed_code = self.remove_multiline_comments(java_code)
            processed_snippets.append(processed_code)

            try:
                cwe_id = int(row['cwe_id'].split("-")[-1])
            except:
                # To handle NaN or invalid CWE IDs
                cwe_id = -1
            cwe_ids.append(cwe_id)

        cvefixes_df['code_snippet'] = processed_snippets
        cvefixes_df['cwe'] = cwe_ids

        if processing_options.get('top_25_cwe', True):
            top_cwes = [int(cwe.split("-")[-1].strip()) for cwe in self.top_25_cwes]
            cvefixes_df = cvefixes_df[cvefixes_df['cwe'].isin(top_cwes)]

        # cvefixes_df.reset_index(inplace=True)
        # cvefixes_df.rename(columns={'index': 'test_number'}, inplace=True)
        # return cvefixes_df[['test_number', 'cve_id', 'cwe', 'cwe_name', 'code_snippet', 'real_vulnerability']]

        return cvefixes_df[['cwe', 'cwe_name', 'real_vulnerability', 'cve_id', 'code_snippet']]

    def save_processed_examples(self, processed_df, examples_type):
        """
        Saves the processed DataFrame as a CSV file.

        Parameters:
        - processed_df (pd.DataFrame): The processed examples DataFrame.
        - examples_type (str): Type of examples processed ('file' or 'method').
        """
        processed_csv_path = os.path.join(self.cvefixes_dir, f'processed_cvefixes_{examples_type}.csv')
        processed_df.to_csv(processed_csv_path)

if __name__ == "__main__":
    cvefixes_dir = 'CodeExamples/CVEfixes_v1.0.7'
    processing_options = {
        'top_25_cwe': True,
        'file_examples': True,
        'method_examples': False
    }

    cve_processor = CVEFixesProcessor(cvefixes_dir)
    processed_df = cve_processor.load_and_process_examples(processing_options=processing_options)
    examples_type = 'file' if processing_options.get('file_examples', False) else 'method'
    cve_processor.save_processed_examples(processed_df, examples_type)
    print(processed_df.head())
