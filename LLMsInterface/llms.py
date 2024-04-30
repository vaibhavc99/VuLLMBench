from langchain_openai import ChatOpenAI
from langchain_community.chat_models import ChatOllama
from langchain_groq import ChatGroq
from concurrent.futures import ThreadPoolExecutor, as_completed
from LLMsInterface import config

class LLMs:
    def __init__(self, model_names):
        self.models = []
        for model_name in model_names:
            if "gpt" in model_name:
                model = ChatOpenAI(model=model_name, temperature=0)
            else:
                # model = ChatOllama(model=model_name, temperature=0)
                model = ChatGroq(groq_api_key=config.GROQ_API_KEY, model=model_name, temperature=0)
                
            self.models.append((model_name, model))

    def _ask_llm(self, model, prompt):
        return model.invoke(prompt)
    
    def ask_llms(self, prompt):
        responses = {}

        for model_name, model in self.models:
            response = model.invoke(prompt)
            responses[model_name] = response.content

        return responses

    def ask_llms_parallel(self, prompt):
        responses = {}
        with ThreadPoolExecutor() as executor:
            future_to_model = {executor.submit(self._ask_llm, model, prompt): model_name for model_name, model in self.models}

            for future in as_completed(future_to_model):
                model_name = future_to_model[future]
                try:
                    response = future.result()
                    responses[model_name] = response.content
                except Exception as e:
                    responses[model_name] = str(e)

        return responses

if __name__ == '__main__':
    model_list = ["llama2", "mistral:instruct"]
    llms = LLMs(model_list)
    responses = llms.ask_llms_parallel("Translate the sentence to German: 'I love programming'.")
    for model_name, response in responses.items():
        print(f"Response from {model_name}:")
        print(response)
        print("-------------------------")
