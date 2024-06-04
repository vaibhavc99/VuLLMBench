import config
from Preprocessor.owasp_processor import OwaspProcessor
from PromptGenerator.generator import PromptGenerator
from LLMsInterface.llms import LLMs
from LLMsInterface.responseDB import LLMResponseDB
from ReportsGenerator.responseParser import ResponseParser
from ReportsGenerator.vulnEvaluator import VulnerabilityEvaluator
import pandas as pd
import logging
import json

class Controller:
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

    def load_examples(self, processing_options=None):
        self.examples = self.preprocessor.load_and_process_examples(processing_options).head(2)
        logging.info(f"Loaded {len(self.examples)} OWASP examples.")

    def get_prompt(self, code_snippet:str, prompt_type:str):
        return self.prompt_generator.generate_prompt(code_snippet, prompt_type)
    
    def send_to_llm(self, model_names:list, prompt_type:str):
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
                # print(responses)

                for model_name, response in responses.items():
                    self.db.insert_response(self.table_name, index, prompt, model_name, response)
                    logging.info(f"A response by {model_name} for {index} is stored in database.")

            else:
                logging.info(f"All responses for {index} already exist. Skipping LLM calls.")

    def generate_reports(self, prompt_type:str):
        logging.info("Generating reports based on LLM responses.")

        responses = self.db.get_all_responses(self.table_name)
        # print({k: responses[k] for k in list(responses)[:5]})
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
    # controller.generate_reports(prompt_type)
    controller.db.close()
   
    logging.info("Done.")
