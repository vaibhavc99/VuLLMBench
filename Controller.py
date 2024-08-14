import Utils.model_config as model_config
from Preprocessor.owasp_processor import OwaspProcessor
from Preprocessor.cvefixes_processor import CVEFixesProcessor
from Preprocessor.stratification import get_stratified_sample
from PromptGenerator.generator import PromptGenerator
from LLMsInterface.llms import LLMs
from LLMsInterface.responseDB import LLMResponseDB
from ReportsGenerator.responseParser import ResponseParser
from ReportsGenerator.vulnEvaluator import VulnerabilityEvaluator
import pandas as pd
from Utils.configure_logging import configure_logging
import json
import os

class Controller:
    """
    Controller for managing the workflow of the benchmark.

    Parameters:
    - data_dir_path (str): The path to the directory containing the dataset.
    - dataset_name (str): The name of the dataset to use.
    - table_name (str, optional): The name of the table to store the LLM responses in the database.
    - useCache (bool, optional): Flag indicating whether to use cached LLM responses. Defaults to True.
    - self_reflection (bool, optional): Flag indicating whether to perform self-reflection prompts. Defaults to False.
    - self_reflection_gt (bool, optional): Flag indicating whether to perform self-reflection prompts with ground truth. Defaults to False.

    Attributes:
    - preprocessor (object): An instance of the appropriate preprocessor class for the dataset.
    - prompt_generator (PromptGenerator): An instance of the PromptGenerator class for generating prompts.
    - llm_interface (LLMs): An instance of the LLMs class for interacting with the LLM models.
    - response_parser (ResponseParser): An instance of the ResponseParser class for parsing LLM responses.
    - db (LLMResponseDB): An instance of the LLMResponseDB class for managing the LLM response database.
    - examples (pandas.DataFrame): The loaded examples.
    - table_name (str): The name of the table to store the LLM responses in the database.
    - useCache (bool): Flag indicating whether to use cached LLM responses.
    - self_reflection (bool): Flag indicating whether to perform self-reflection on LLM responses.
    - self_reflection_gt (bool): Flag indicating whether to perform self-reflection on LLM responses with ground truth.

    """

    def __init__(self, data_dir_path: str, dataset_name: str, table_name: str = None, useCache: bool = True, self_reflection: bool = False, self_reflection_gt: bool = False):
        self.data_dir_path = data_dir_path
        self.dataset_name = dataset_name
        self.preprocessor = None
        self.prompt_generator = PromptGenerator()
        self.llm_interface = LLMs(model_config)
        self.response_parser = ResponseParser()
        self.db = LLMResponseDB(model_config.DB_PATH)
        self.examples = None
        self.table_name = table_name
        self.useCache = useCache
        self.self_reflection = self_reflection
        self.self_reflection_gt = self_reflection_gt
        self.logger = configure_logging(__name__)

    def load_examples(self, processing_options=None, stratification_options=None):
        """
        Load and preprocess the examples based on the dataset name.

        Parameters:
        - processing_options (dict, optional): Options for preprocessing the examples. Defaults to None.
        - stratification_options (dict, optional): Options for stratifying the examples. Defaults to None.
        """
        if self.dataset_name.lower() == 'owasp':
            owasp_dir = os.path.join(self.data_dir_path, 'OWASP')
            self.preprocessor = OwaspProcessor(owasp_dir)
        elif self.dataset_name.lower().startswith('cvefixes'):
            cvefixes_dir = os.path.join(self.data_dir_path, 'CVEfixes_v1.0.7')
            language = self.dataset_name.split('_')[-1]
            self.preprocessor = CVEFixesProcessor(cvefixes_dir, language)
        else:
            self.logger.error(f"Unsupported dataset: {self.dataset_name}")
            return

        examples = self.preprocessor.load_and_process_examples(processing_options)

        if stratification_options and stratification_options['stratify']:
            self.examples = get_stratified_sample(examples, stratification_options)
            self.logger.info(f"Loaded stratified sample of {len(self.examples)} examples from the {self.dataset_name} dataset.")
        else:
            self.examples = examples
            self.logger.info(f"Loaded {len(self.examples)} examples from the {self.dataset_name} dataset.")

    def get_prompt(self, code_snippet: str, prompt_type: str):
        """
        Generate a prompt for a given code snippet and prompt type.

        Parameters:
        - code_snippet (str): The code snippet to generate a prompt for.
        - prompt_type (str): The type of prompt to generate.

        Returns:
        - str: The generated prompt.

        """
        return self.prompt_generator.generate_prompt(code_snippet, prompt_type)
    
    def send_to_llm(self, model_names: list, prompt_type: str):
        """
        Determines which examples need querying based on cache and existing responses, prepares prompts,
        queries the LLMs, and stores the responses in the database.

        Parameters:
        - model_names (list): The names of the LLM models to query.
        - prompt_type (str): The type of prompt to use for querying the models.

        """
        if self.examples is None:
            self.logger.error("Examples not loaded.")
            return

        self.prepare_and_query_llms(model_names, prompt_type)

        if self.self_reflection:
            self.prepare_and_query_llms(model_names, f"{prompt_type}_self_reflection", reflection=True)
        if self.self_reflection_gt:
            self.prepare_and_query_llms(model_names, f"{prompt_type}_self_reflection_with_ground_truth", reflection=True)

    def prepare_and_query_llms(self, model_names: list, prompt_type: str, reflection: bool = False):
        """
        Prepares prompts, queries the LLMs, and stores the responses in the database.

        Parameters:
        - model_names (list): The names of the LLM models to query.
        - prompt_type (str): The type of prompt to use for querying the models.
        - reflection (bool): Flag indicating whether this is for self-reflection prompts.

        """
        missing_indices = {}

        # Determine the correct table name
        if reflection:
            if "ground_truth" in prompt_type:
                table_name = f"{self.table_name}_self_reflection_with_ground_truth"
            else:
                table_name = f"{self.table_name}_self_reflection"
        else:
            table_name = self.table_name

        for model_name in model_names:
            missing_indices[model_name] = [
                index for index, row in self.examples.iterrows()
                if not self.db.response_exists(table_name, index, model_name) or not self.useCache
            ]

        if not any(missing_indices.values()):
            self.logger.info(f"Responses for the '{prompt_type}' prompt by models {model_names} already exist in the database. Skipping LLM calls.")
            return

        prompts_per_model = {}
        prompt_lookup = {}

        for model_name, indices in missing_indices.items():
            prompts_per_model[model_name] = []
            for index in indices:
                if reflection:
                    response_data = self.db.get_response_by_index_and_model(self.table_name, index, model_name)  # Get original prompt and response
                    if response_data:
                        original_prompt, original_response = response_data["prompt"], response_data["response"]
                        chat_history = [
                            original_prompt[0],                 # Original system message
                            original_prompt[1],                 # Original user message
                            ("ai", original_response)           # Original response
                        ]
                        if "ground_truth" in prompt_type:
                            ground_truth = self.examples.loc[index, "real_vulnerability"]
                            prompt = self.prompt_generator.generate_self_reflection_with_ground_truth_prompt(chat_history, ground_truth)
                        else:
                            prompt = self.prompt_generator.generate_self_reflection_prompt(chat_history)
                else:
                    prompt = self.get_prompt(self.examples.loc[index, "code_snippet"], prompt_type)
                
                prompts_per_model[model_name].append((index, prompt))
                prompt_lookup[(index, model_name)] = prompt

        responses = self.llm_interface.query_llms(prompts_per_model)

        for (index, model_name), response in responses.items():
            prompt = prompt_lookup[(index, model_name)]
            self.db.insert_response(table_name, index, prompt, model_name, response)
            
            if reflection:
                self.logger.info(f"A self-reflection response by {model_name} for {index} is stored in database.")
            else:
                self.logger.info(f"A response by {model_name} for {index} is stored in database.")

    def generate_reports(self, prompt_type: str, experiment: str = None):
        """
        Generate reports based on the LLM responses.

        Parameters:
        - prompt_type (str): The type of prompt used for querying the models.
        - experiment (str, optional): The name of the experiment.

        """
        self.logger.info(f"Generating reports for Experiment-{experiment} based on LLM responses.")

        # Standard evaluation
        self.evaluate_and_save_results(prompt_type, experiment)

        # Self-reflection evaluation
        if self.self_reflection:
            self.evaluate_and_save_results(f"{prompt_type}_self_reflection", experiment)
        if self.self_reflection_gt:
            self.evaluate_and_save_results(f"{prompt_type}_self_reflection_with_ground_truth", experiment)

    def evaluate_and_save_results(self, prompt_type: str, experiment: str):
        """
        Evaluate the LLM responses and save the results.

        Parameters:
        - prompt_type (str): The type of prompt used for querying the models.
        - experiment (str): The name of the experiment.
        """
        # Determine the correct table name
        if "self_reflection_with_ground_truth" in prompt_type:
            table_name = f"{self.table_name}_self_reflection_with_ground_truth"
        elif "self_reflection" in prompt_type:
            table_name = f"{self.table_name}_self_reflection"
        else:
            table_name = self.table_name

        responses = self.db.get_all_responses(table_name)
        df = self.response_parser.responses_to_dataframe(responses, prompt_type)

        evaluator = VulnerabilityEvaluator(df, self.examples)
        results = evaluator.evaluate_by_model(experiment, self.dataset_name, prompt_type)
        # print(f"\nEvaluation Results: \n{results}")

        results_json = json.dumps(results, indent=4)
        with open(f'./Evaluation/{experiment}/{self.dataset_name}-{prompt_type}.json', 'w') as file:
            file.write(results_json)


if __name__ == '__main__':
    dataset_name = "cvefixes"
    prompt_type = "simple"
    table_name = f"{dataset_name}_{prompt_type}_prompt_test"
    model_names = [model_config.HOC_MODEL_LIST[0]]
    stratification_options = {
        "stratify": True,
        "stratify_cols": ["cwe_name", "cwe"],
        "test_size": 0.1,
        "random_state": 12
    }
    self_reflection = False
    self_reflection_gt = False

    controller = Controller(model_config.DATA_DIR_PATH, dataset_name, table_name, self_reflection=self_reflection, self_reflection_gt=self_reflection_gt)
    controller.load_examples(stratification_options=stratification_options)
    controller.examples.to_csv(f'./{table_name}_examples.csv')
    controller.send_to_llm(model_names, prompt_type)
    
    controller.generate_reports(prompt_type, experiment="test")
    controller.db.close()
