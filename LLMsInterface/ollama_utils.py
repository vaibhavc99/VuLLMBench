import ollama
import logging
        
logging.basicConfig(level=logging.INFO)

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

    def is_running(self):
        """
        Checks if the connection to the Ollama server is successful.

        Returns:
        - bool: True if the connection is successful, False otherwise.
        """
        try:
            self.client._request('GET', '')
            logging.info("Connection to Ollama Host is successful.")
            return True
        
        except Exception as e:
            logging.error(f"Failed to connect to Ollama Host: {e}")
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
            logging.info(f"Model {model} is available.")
            return True
        
        except Exception as e:
            if (str(e) == f"model '{model}' not found"):
                logging.error(f"Model {model} not available.")
                logging.info(f"Attempting to download {model}...")
                self.client.pull(model)
                logging.info(f"Model downloaded: {model}")
            else:
                logging.error(f"Failed to connect to Ollama Host: {e}")
                return False

if __name__ == '__main__':  
    ol = OllamaUtils("http://localhost:11434")
    # ol.is_running()
    ol.model_available('llama3')