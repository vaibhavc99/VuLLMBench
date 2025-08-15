from langchain_openai import ChatOpenAI, AzureChatOpenAI
from langchain_ollama import ChatOllama
from langchain_groq import ChatGroq
from langchain_community.callbacks import get_openai_callback
from concurrent.futures import ThreadPoolExecutor, as_completed
from LLMsInterface.ollama_utils import OllamaUtils
import time
from Utils.configure_logging import configure_logging

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
        self.logger = configure_logging("LLMsInterface")
        self.total_cost = 0.0
        self.total_tokens = 0
        self.prompt_tokens = 0
        self.completion_tokens = 0
        
    def get_model(self, model_name):
        """
        Retrieves the model object based on the model name.

        Parameters:
        - model_name (str): The name of the model to be retrieved.

        Returns:
        - model: The model object if found, otherwise None.
        """
        if model_name in self.config.OPENAI_MODEL_LIST:
            model = ChatOpenAI(
                api_key=self.config.OPENAI_API_KEY,
                model=model_name,
                temperature=self.config.MODEL_PARAMETERS["temperature"],
                max_tokens=self.config.MODEL_PARAMETERS["max_tokens"]
            )
                    
        elif model_name in self.config.AZURE_OPENAI_MODEL_LIST:
            if model_name.startswith("o"):
                temperature=None
            else:
                temperature=self.config.MODEL_PARAMETERS["temperature"]
            model = AzureChatOpenAI(
                azure_endpoint=self.config.AZURE_OPENAI_ENDPOINT,
                azure_deployment=model_name,
                api_key=self.config.AZURE_OPENAI_API_KEY,
                api_version=self.config.AZURE_OPENAI_API_VERSION,
                temperature=temperature
            )

        elif model_name in self.config.OLLAMA_MODEL_LIST:
            if model_name not in self.ollama_checked:
                self.ollama_checked.add(model_name)
                host = OllamaUtils(self.config.OLLAMA_HOST_URL)
                if not host.model_available(model_name):
                    return None
            
            model = ChatOllama(
                base_url=self.config.OLLAMA_HOST_URL,
                model=model_name,
                temperature=self.config.MODEL_PARAMETERS["temperature"],
                num_predict=self.config.MODEL_PARAMETERS["max_tokens"],
            )

        elif model_name in self.config.GROQ_MODEL_LIST:
            model = ChatGroq(
                groq_api_key=self.config.GROQ_API_KEY,
                model=model_name,
                temperature=self.config.MODEL_PARAMETERS["temperature"],
                max_tokens=self.config.MODEL_PARAMETERS["max_tokens"]
            )

        elif model_name in self.config.HOC_MODEL_LIST:
            model = ChatOpenAI(
                api_key="EMPTY", 
                base_url=self.config.HOC_HOST_URL,
                model=model_name,
                temperature=self.config.MODEL_PARAMETERS["temperature"],
                max_tokens=self.config.MODEL_PARAMETERS["max_tokens"]
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
                if model.__class__.__name__ == "AzureChatOpenAI":
                    with get_openai_callback() as cb:
                        response = model.invoke(prompt)
                        self.total_cost += cb.total_cost
                        self.total_tokens += cb.total_tokens
                        self.prompt_tokens += cb.prompt_tokens
                        self.completion_tokens += cb.completion_tokens
                        return response.content
                else:
                    response = model.invoke(prompt)
                    return response.content
            except Exception as e:
                error_message = str(e)
                self.logger.error(f"Error invoking model: {error_message}")
                
                if any(x in error_message for x in ["max_tokens", "maximum context length", "400"]):
                    self.logger.error("max_tokens error encountered, returning None.")
                    return None
                self.logger.info(f"Retrying after 2 seconds...")
                time.sleep(2)

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

        # Separate the models based on whether prompts should be processed in parallel or sequentially
        parallel_prompt_models = {model_name: prompts for model_name, prompts in prompts_per_model.items()
                                  if model_name not in self.config.OLLAMA_MODEL_LIST}
        sequential_prompt_models = {model_name: prompts for model_name, prompts in prompts_per_model.items()
                                    if model_name in self.config.OLLAMA_MODEL_LIST}

        # Process models with prompts that can be handled in parallel
        if parallel_prompt_models:
            responses.update(self.query_prompts_parallel(parallel_prompt_models))

        # Process models with prompts that need to be handled sequentially (Ollama models)
        if sequential_prompt_models:
            responses.update(self.query_prompts_sequential(sequential_prompt_models))

        return responses

    def query_prompts_parallel(self, parallel_prompt_models):
        """
        Processes prompts that can be handled in parallel.

        Parameters:
        - parallel_prompt_models (dict): A dictionary with model names as keys and lists of prompts as values.

        Returns:
        - dict: A dictionary with the responses from the models.
        """
        responses = {}
        try:
            with ThreadPoolExecutor(max_workers=10) as executor:
                for model_name, prompts in parallel_prompt_models.items():
                    model = self.get_model(model_name)
                    if model is None:
                        self.logger.error(f"Model {model_name} could not be retrieved.")
                        continue
                    
                    start_time = time.time()
                    futures = {}
                    for index, prompt in prompts:
                        futures[(index, model_name)] = executor.submit(self.get_response, prompt, model)

                    for key, future in futures.items():
                        try:
                            responses[key] = future.result()
                            self.logger.info(f"Got response from {key[1]} at index {key[0]}")
                        except Exception as e:
                            self.logger.error(f"Failed to get response for {key}: {e}")

                    elapsed_time = time.strftime("%H:%M:%S", time.gmtime(time.time() - start_time))
                    if model_name in self.config.AZURE_OPENAI_MODEL_LIST:
                        self.logger.info(f"Model {model_name} executed {len(prompts)} prompts in {elapsed_time}")
                        self.logger.info(f"Total cost: {self.total_cost} USD, Total tokens: {self.total_tokens}, Prompt tokens: {self.prompt_tokens}, Completion tokens: {self.completion_tokens}")
                        self.total_cost, self.total_tokens, self.prompt_tokens, self.completion_tokens = 0.0, 0, 0, 0
                    else:
                        self.logger.info(f"Model {model_name} executed {len(prompts)} prompts in {elapsed_time}")

        except (KeyboardInterrupt, SystemExit):
            self.logger.warning("Program interrupted. Returning collected responses so far.")
        
        return responses

    def query_prompts_sequential(self, sequential_prompt_models):
        """
        Processes prompts that need to be handled sequentially (e.g., Ollama models).

        Parameters:
        - sequential_prompt_models (dict): A dictionary with model names as keys and lists of prompts as values.

        Returns:
        - dict: A dictionary with the responses from the models.
        """
        responses = {}
        try:
            for model_name, prompts in sequential_prompt_models.items():
                start_time = time.time()
                model = self.get_model(model_name)
                if model is None:
                    self.logger.error(f"Model {model_name} could not be retrieved.")
                    continue

                for index, prompt in prompts:
                    response = self.get_response(prompt, model)
                    responses[(index, model_name)] = response
                    self.logger.info(f"Got response from {model_name} at index {index}")

                elapsed_time = time.strftime("%H:%M:%S", time.gmtime(time.time() - start_time))
                self.logger.info(f"Model {model_name} executed {len(prompts)} prompts in {elapsed_time}")

        except (KeyboardInterrupt, SystemExit):
            self.logger.warning("Program interrupted during sequential processing. Returning collected responses so far.")

        return responses

if __name__ == '__main__':
    import Utils.model_config as model_config
    llms = LLMs(model_config)
    prompts = [(0, "Translate the sentence to German: 'I love programming'."),
              (1, "Translate the sentence to Spanish: 'I am a software engineer'."),
              (2, "Translate the sentence to French: 'I am learning new languages'.")]
    prompts_per_model = {
        "hugging-quants/Meta-Llama-3.1-70B-Instruct-AWQ-INT4": prompts,
        # "llama3.1:8b": prompts,
        "gpt-35-turbo-16k-0613": prompts,
        # "gpt-4o-2024-05-13": prompts
    }

    responses = llms.query_llms(prompts_per_model)

    for (index, model_name), response in responses.items():
        print(f"Response from {model_name}:")
        print(response)
        print("-------------------------")
