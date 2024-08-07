from langchain_openai import ChatOpenAI
from langchain_community.chat_models import ChatOllama
from langchain_groq import ChatGroq
from concurrent.futures import ThreadPoolExecutor, as_completed
from LLMsInterface.ollama_utils import OllamaUtils
import time
import logging

class LLMs:
    """
    A class to manage and interact with various Large Language Models (LLMs) through hosts like OpenAI, Ollama, and Groq.

    Attributes:
    - config (object): Configuration object containing API keys, model lists, and other settings.
    - ollama_checked (set): A set to keep track of checked Ollama models.
    """
    def __init__(self, config):
        self.config = config
        self.ollama_checked = set()
        
    def get_model(self, model_name):
        """
        Retrieves the model object based on the model name.

        Parameters:
        - model_name (str): The name of the model to be retrieved.

        Returns:
        - model: The model object if found, otherwise None.
        """
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

        elif model_name in self.config.HOC_MODEL_LIST:
            model = ChatOpenAI(
                openai_api_key="EMPTY", 
                openai_api_base="http://hoc-lx-gpu02.ad.iem-hoc.de:8080/v1",
                model=model_name,
                temperature=0
            )

        else:
            return None

        return model 
    
    def get_response(self, prompt, model):
        """
        Retrieves the response from the model for a given prompt.

        Parameters:
        - prompt (str): The input prompt to be sent to the model.
        - model: The model object to be invoked.

        Returns:
        - str: The response content from the model.
        """
        while True:
            try:
                response = model.invoke(prompt)
                return response.content
            except Exception as e:
                logging.error(f"Error invoking model: {str(e)}")
                logging.info(f"Retrying after 5 seconds...")
                time.sleep(5)

    def query_llms(self, prompts_per_model: dict):
        """
        Queries multiple models with concurrent prompts and retrieves their responses.

        Parameters:
        - prompts_per_model (dict): A dictionary where keys are model names and values are lists of tuples,
        each containing an index and a prompt to be sent to the respective model. 

        Returns:
        - dict: A dictionary with keys as tuples of (index, model name) and their responses as values.
        """
        responses = {}

        try:
            with ThreadPoolExecutor(max_workers=10) as executor:
                futures = {}
                for model_name, prompts in prompts_per_model.items():
                    if model_name in self.config.OLLAMA_MODEL_LIST:
                        responses.update(self.query_ollama_models_sequentially(prompts, model_name))
                    else:
                        model = self.get_model(model_name)
                        for index, prompt in prompts:
                            futures[(index, model_name)] = executor.submit(self.get_response, prompt, model)

                for key, future in futures.items():
                    try:
                        responses[key] = future.result()
                        logging.info(f"Got response from {key[1]} at index {key[0]}")
                    except Exception as e:
                        logging.error(f"Failed to get response for {key}: {e}")

        except (KeyboardInterrupt, SystemExit):
            logging.warning("Program interrupted. Returning collected responses so far.")
        finally:
            return responses


    def query_ollama_models_sequentially(self, prompts, model_name):
        """
        Queries Ollama models sequentially with given prompts and retrieves their responses.

        Parameters:
        - prompts (list): A list of tuples, each containing an index and a prompt to be sent to the model.
        - model_name (str): The name of the Ollama model to be invoked.

        Returns:
        - dict: A dictionary with keys as tuples of (index, model name) and their responses as values.
        """
        responses = {}
        model = self.get_model(model_name)
        for index, prompt in prompts:
            response = self.get_response(prompt, model)
            responses[(index, model_name)] = response
            logging.info(f"Got response from {model_name} at index {index}")

        return responses

if __name__ == '__main__':
    import config
    llms = LLMs(config)
    prompts_per_model = {
        "psyche/Meta-Llama-3-70B-Instruct-awq": [(0, "Translate the sentence to German: 'I love programming'.")],
        "gemma": [(0, "Translate the sentence to German: 'I love programming'.")],
    }

    responses = llms.query_llms(prompts_per_model)

    for (index, model_name), response in responses.items():
        print(f"Response from {model_name}:")
        print(response)
        print("-------------------------")
