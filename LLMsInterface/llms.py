from langchain_openai import ChatOpenAI
from langchain_community.chat_models import ChatOllama
from langchain_groq import ChatGroq
from concurrent.futures import ThreadPoolExecutor, as_completed
from LLMsInterface.ollama_utils import OllamaUtils
import time

class LLMs:
    def __init__(self, config):
        self.config = config
        self.ollama_checked = set()
        
    def get_model(self, model_name):
        if model_name in self.config.OPENAI_MODEL_LIST:
            model = ChatOpenAI(api_key=self.config.OPENAI_API_KEY, model=model_name, temperature=0)

        elif model_name in self.config.OLLAMA_MODEL_LIST:
            if model_name not in self.ollama_checked:
                self.ollama_checked.add(model_name)
                host = OllamaUtils(self.config.OLLAMA_HOST)
                if not host.model_available(model_name):
                    return None
            model = ChatOllama(base_url=self.config.OLLAMA_HOST, model=model_name, temperature=0)

        elif model_name in self.config.GROQ_MODEL_LIST:
            model = ChatGroq(groq_api_key=self.config.GROQ_API_KEY, model=model_name, temperature=0)

        else:
            return None

        return model 
    
    def get_response(self, prompt, model):
        while True:
            try:
                response = model.invoke(prompt)
                return response.content
                # break
            except Exception as e:
                print(f"Error invoking model: {str(e)}")
                print(f"Retrying after 5 seconds...")
                time.sleep(5)

    def _ask_llm(self, model, prompt):
        return model.invoke(prompt)
    
    def ask_llms(self, prompt, model_names):
        responses = {}

        for model_name in model_names:
            model = self.get_model(model_name)
            response = self.get_response(prompt, model)           
            responses[model_name] = response

        return responses

    def ask_llms_parallel(self, prompt, model_names):
        responses = {}
        models = [(model_name, self.get_model(model_name)) for model_name in model_names]

        with ThreadPoolExecutor() as executor:
            future_to_model = {executor.submit(self.get_response, prompt, model): model_name for model_name, model in models if model}

            for future in as_completed(future_to_model):
                model_name = future_to_model[future]
                try:
                    response = future.result()
                    responses[model_name] = response
                except Exception as e:
                    print(f"Error getting response from model {model_name}: {str(e)}")
                    continue

        return responses

if __name__ == '__main__':
    import config
    model_list = ["llama2", "mistral:instruct"]
    llms = LLMs(config)
    responses = llms.ask_llms_parallel("Translate the sentence to German: 'I love programming'.", model_list)
    for model_name, response in responses.items():
        print(f"Response from {model_name}:")
        print(response)
        print("-------------------------")
