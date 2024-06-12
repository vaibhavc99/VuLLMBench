import config
from Preprocessor.owasp_processor import OwaspProcessor
from Preprocessor.stratification import get_stratified_sample
from PromptGenerator.generator import PromptGenerator
from LLMsInterface.llms import LLMs
from LLMsInterface.responseDB import LLMResponseDB
from ReportsGenerator.responseParser import ResponseParser
from ReportsGenerator.vulnEvaluator import VulnerabilityEvaluator
import pandas as pd
import logging
import json

class Controller:
    """
    Controller for managing the workflow of the benchmark.

    Parameters:
    - data_dir_path (str): The path to the directory containing the OWASP examples.
    - table_name (str): The name of the table to store the LLM responses in the database.
    - useCache (bool, optional): Flag indicating whether to use cached LLM responses. Defaults to True.

    Attributes:
    - preprocessor (OwaspProcessor): An instance of the OwaspProcessor class for preprocessing OWASP examples.
    - prompt_generator (PromptGenerator): An instance of the PromptGenerator class for generating prompts.
    - llm_interface (LLMs): An instance of the LLMs class for interacting with the LLM models.
    - response_parser (ResponseParser): An instance of the ResponseParser class for parsing LLM responses.
    - db (LLMResponseDB): An instance of the LLMResponseDB class for managing the LLM response database.
    - examples (pandas.DataFrame): The loaded OWASP examples.
    - table_name (str): The name of the table to store the LLM responses in the database.
    - useCache (bool): Flag indicating whether to use cached LLM responses.

    """

    def __init__(self, data_dir_path:str, table_name:str, useCache:bool=True):
        self.preprocessor = OwaspProcessor(data_dir_path)
        self.prompt_generator = PromptGenerator()
        self.llm_interface = LLMs(config)
        self.response_parser = ResponseParser()
        self.db = LLMResponseDB(config.DB_PATH)
        self.examples = None
        self.table_name = table_name
        self.useCache = useCache
        logging.basicConfig(level=logging.INFO)

    def load_examples(self, processing_options=None, stratification_options=None):
        """
        Load and preprocess the OWASP examples.

        Parameters:
        - processing_options (dict, optional): Options for preprocessing the examples. Defaults to None.
        - stratification_options (dict, optional): Options for stratifying the examples. Defaults to None.

        """
        examples = self.preprocessor.load_and_process_examples(processing_options)
        if stratification_options['stratify']:
            self.examples = get_stratified_sample(examples, stratification_options)
            logging.info(f"Loaded stratified sample of {len(self.examples)} OWASP examples.")
        else:
            self.examples = examples
            logging.info(f"Loaded {len(self.examples)} OWASP examples.")

    def get_prompt(self, code_snippet:str, prompt_type:str):
        """
        Generate a prompt for a given code snippet and prompt type.

        Parameters:
        - code_snippet (str): The code snippet to generate a prompt for.
        - prompt_type (str): The type of prompt to generate.

        Returns:
        - str: The generated prompt.

        """
        return self.prompt_generator.generate_prompt(code_snippet, prompt_type)
    
    def send_to_llm(self, model_names:list, prompt_type:str):
        """
        Send the examples to the LLMs and store the responses in the database.

        Parameters:
        - model_names (list): The names of the LLM models to query.
        - prompt_type (str): The type of prompt to use for querying the models.

        """
        if self.examples is None:
            logging.error("Examples not loaded.")
            return
        
        for index, row in self.examples.iterrows():
            prompt = self.get_prompt(row["code_snippet"], prompt_type)
            models_to_query = []

            for model_name in model_names:
                if not self.db.response_exists(self.table_name, index, model_name) or not self.useCache:
                    models_to_query.append(model_name)

            if models_to_query:
                responses = self.llm_interface.ask_llms(prompt, models_to_query)

                for model_name, response in responses.items():
                    self.db.insert_response(self.table_name, index, prompt, model_name, response)
                    logging.info(f"A response by {model_name} for {index} is stored in database.")

            else:
                logging.info(f"All responses for {index} already exist. Skipping LLM calls.")

    def generate_reports(self, prompt_type:str):
        """
        Generate reports based on the LLM responses.

        Parameters:
        - prompt_type (str): The type of prompt used for querying the models.

        """
        logging.info("Generating reports based on LLM responses.")

        responses = self.db.get_all_responses(self.table_name)
        df = self.response_parser.responses_to_dataframe(responses, prompt_type)

        evaluator = VulnerabilityEvaluator(df, self.examples)
        results = evaluator.evaluate_by_model()
        print(f"\nEvaluation Results: \n{results}")

        results_json = json.dumps(results, indent=4)
        with open(f'evaluation_results-{prompt_type}_prompt.json', 'w') as file:
            file.write(results_json)


if __name__ == '__main__':
    # prompt_types = ["simple", "vulnerability_specific", "explanatory_insights", "solution_oriented"]
    prompt_type = "simple"
    table_name = f"{prompt_type}_prompt_default"
    model_names = [config.GROQ_MODEL_LIST[0]]
    
    controller = Controller(config.DATA_DIR_PATH, table_name)
    controller.load_examples()
    controller.send_to_llm(model_names,prompt_type)
    controller.generate_reports(prompt_type)
    controller.db.close()
   
    logging.info("Done.")
