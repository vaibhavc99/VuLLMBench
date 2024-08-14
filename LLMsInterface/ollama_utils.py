import ollama
from tqdm import tqdm
from Utils.configure_logging import configure_logging

class OllamaUtils:
    """
    A utility class to interact with the Ollama server for managing models.

    Parameters:
    - base_url (str): The base URL of the Ollama server.

    Attributes:
    - client (ollama.Client): The client object to interact with the Ollama server.
    """
    def __init__(self, base_url):
        self.client = ollama.Client(base_url)
        self.logger = configure_logging("OllamaUtils")

    def is_running(self):
        """
        Checks if the connection to the Ollama server is successful.

        Returns:
        - bool: True if the connection is successful, False otherwise.
        """
        try:
            self.client._request('GET', '')
            self.logger.info("Connection to Ollama Host is successful.")
            return True
        
        except Exception as e:
            self.logger.error(f"Failed to connect to Ollama Host: {e}")
            return False

    def model_available(self, model):
        """
        Checks if a model is available on the Ollama server. If not, it attempts to download the model.

        Parameters:
        - model (str): The name of the model to check.

        Returns:
        - bool: True if the model is available or successfully downloaded, False otherwise.
        """
        try:
            self.client.show(model)
            self.logger.info(f"Model {model} is available.")
            return True
        
        except Exception as e:
            if (str(e) == f"model '{model}' not found"):
                self.logger.error(f"Model {model} not available.")
                user_input = input("Do you want to download it? (yes/no): ").strip().lower()
                if user_input in ['yes', 'y']:
                    self.download_model(model)
            else:
                self.logger.error(f"Failed to connect to Ollama Host: {e}")
                return False
            
    def list_models(self):
        models = self.client.list()
        model_names = [model['name'] for model in models['models']]
        return model_names

    def download_model(self, model_name):
        if model_name in self.list_models():
            self.logger.info(f"Model {model_name} already exists")
        else:
            stream = self.client.pull(model=model_name, stream=True)
            total_size = None
            progress_bar = None
            for chunk in stream:
                if 'status' in chunk and 'total' in chunk and 'completed' in chunk:
                    if total_size is None:
                        total_size = chunk['total']
                        progress_bar = tqdm(total=total_size, unit='B', unit_scale=True, desc=f"Downloading {model_name}")
                    completed = chunk['completed']
                    progress_bar.n = completed
                    progress_bar.update(0)
                else:
                    print(chunk)

            if progress_bar is not None:
                progress_bar.close()

            if model_name in self.list_models():
                self.logger.info(f"Model {model_name} downloaded successfully")

    def remove_model(self, model_name):
        if model_name in self.list_models():
            self.client.delete(model=model_name)
            self.logger.info(f"Model {model_name} removed successfully")
        else:
            self.logger.info(f"Model {model_name} does not exist")
    
    def show_running_models(self):
        running_models = self.client.ps()
        model_names = [model['name'] for model in running_models['models']]
        return model_names

    def unload_model(self, model_name):
        if model_name in self.show_running_models():
            self.client.generate(model=model_name, keep_alive=0)
            self.logger.info(f"Model {model_name} unloaded successfully")
        else:
            self.logger.info(f"Model {model_name} is not running")
    
if __name__ == '__main__':  
    base_url = "http://hoc-lx-gpu02.ad.iem-hoc.de:8083"
    ol = OllamaUtils(base_url)
    print(ol.list_models()) 
    ol.download_model("llama3.1:70b")