from Preprocessor.owasp import OWASP
from PromptGenerator.generator import PromptGenerator
from LLMsInterface.llms import LLMs
from LLMsInterface.responseDB import LLMResponseDB
from LLMsInterface import config

class Controller:
    def __init__(self):
        self.examples = self.preprocess_code_examples()

    def preprocess_code_examples(self):
        owasp = OWASP()
        examples =  owasp.df.head(5)
        print(f"Loaded {len(examples)} OWASP examples.")

        return examples

    def get_prompt(self, code_snippet, prompt_strategy):
        # strategies = ["simple", "vulnerability-specific", "explanatory-insights", "solution-oriented"]

        gen = PromptGenerator()
        prompt = gen.generate_prompt(code_snippet, prompt_strategy)

        return prompt
    
    def send_to_llm(self):
        model_names = [config.GROQ_MODEL_LIST[0], config.GROQ_MODEL_LIST[1]]
        prompt_strategy = "simple"

        db = LLMResponseDB(prompt_strategy, config.DB_PATH)
        
        for index, row in self.examples.iterrows():
            prompt = self.get_prompt(row["code_snippet"], prompt_strategy)
            models_to_query = []

            for model_name in model_names:
                if not db.response_exists(index, model_name):
                    models_to_query.append(model_name)

            if models_to_query:
                llms = LLMs(models_to_query)
                responses = llms.ask_llms(prompt)

                for model_name, response in responses.items():
                    db.insert_response(index, model_name, response)
                    print(f"A response by {model_name} for {index} is stored in database.")
                
            else:
                print(f"All responses for {index} already exist. Skipping LLM calls.")
        
        db.close()

    def generate_reports(self, responses):
        print("Generating reports based on LLM responses.")

if __name__ == '__main__':
    controller = Controller()
    controller.send_to_llm()
