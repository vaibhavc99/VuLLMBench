import config
from Preprocessor.owasp import OWASP
from PromptGenerator.generator import PromptGenerator
from LLMsInterface.llms import LLMs
from LLMsInterface.responseDB import LLMResponseDB
from ReportsGenerator.responseParser import ResponseParser
from ReportsGenerator.vulnEvaluator import VulnerabilityEvaluator
import pandas as pd
import logging

class Controller:
    def __init__(self, preprocessor:OWASP, prompt_generator:PromptGenerator, llm_interface:LLMs, response_parser:ResponseParser, db:LLMResponseDB, useCache:bool=True):
        self.preprocessor = preprocessor
        self.prompt_generator = prompt_generator
        self.llm_interface = llm_interface
        self.response_parser = response_parser
        self.examples = None
        self.db = db
        self.useCache = useCache
        logging.basicConfig(level=logging.INFO)


    def load_examples(self):
        self.examples = self.preprocessor.load_and_process_examples().head(5)
        logging.info(f"Loaded {len(self.examples)} OWASP examples.")

    def get_prompt(self, code_snippet, prompt_strategy):
        return self.prompt_generator.generate_prompt(code_snippet, prompt_strategy)
    
    def send_to_llm(self, prompt_strategy):
        if self.examples is None:
            logging.error("Examples not loaded.")
            return
        
        model_names = [config.GROQ_MODEL_LIST[0], config.GROQ_MODEL_LIST[1]]
        
        for index, row in self.examples.iterrows():
            prompt = self.get_prompt(row["code_snippet"], prompt_strategy)
            models_to_query = []

            for model_name in model_names:
                if not self.db.response_exists(prompt_strategy, index, model_name) or not self.useCache:
                    models_to_query.append(model_name)

            if models_to_query:
                self.llm_interface.initialize_models(models_to_query, config)
                responses = self.llm_interface.ask_llms(prompt)

                for model_name, response in responses.items():
                    self.db.insert_response(prompt_strategy, index, model_name, response)
                    logging.info(f"A response by {model_name} for {index} is stored in database.")
                
            else:
                logging.info(f"All responses for {index} already exist. Skipping LLM calls.")

    def generate_reports(self, prompt_strategy):
        logging.info("Generating reports based on LLM responses.")

        responses = self.db.get_all_responses(prompt_strategy)    
        df = self.response_parser.responses_to_dataframe(responses)

        evaluator = VulnerabilityEvaluator(df, self.examples)
        results = evaluator.evaluate_by_model()

        print(f"\nEvaluation Results: \n{results}")
        
        # for model, results in results.items():
        #     classification_report = results['Vulnerability Type Metrics']['Classification Report']
        #     confusion_matrix =  results['Vulnerability Type Metrics']['Confusion Matrix']
        #     print(f"\nmodel: {classification_report}")
        #     print(f"\nmodel: \n{confusion_matrix}")
        

if __name__ == '__main__':
    # prompt_strategies = ["simple", "vulnerability-specific", "explanatory-insights", "solution-oriented"]
    prompt_strategies = ["simple"]
    preprocessor = OWASP(config.DATA_DIR_PATH)
    prompt_generator = PromptGenerator()
    llm_interface = LLMs()
    response_parser = ResponseParser()
    responseDB = LLMResponseDB(config.DB_PATH)

    controller = Controller(preprocessor, prompt_generator, llm_interface, response_parser, responseDB)
    controller.load_examples()

    for prompt_strategy in prompt_strategies:
        controller.send_to_llm(prompt_strategy)
        controller.generate_reports(prompt_strategy)

    responseDB.close()
