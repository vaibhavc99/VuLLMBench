import os

DATA_DIR_PATH = "CodeExamples"
DB_PATH = "LLMsInterface/responses/llms_responses.db"

OPENAI_API_KEY = os.getenv("OPENAI_API_KEY")
AZURE_OPENAI_API_KEY = os.getenv("AZURE_OPENAI_API_KEY")
GROQ_API_KEY = os.getenv("GROQ_API_KEY")

AZURE_OPENAI_API_VERSION = os.getenv("AZURE_OPENAI_API_VERSION")

AZURE_OPENAI_ENDPOINT = os.getenv("AZURE_OPENAI_ENDPOINT")
OLLAMA_HOST_URL = os.getenv("OLLAMA_HOST_URL")
HOC_HOST_URL = os.getenv("HOC_HOST_URL")

OPENAI_MODEL_LIST = [
    "gpt-3.5-turbo",
    "gpt-4",
]

AZURE_OPENAI_MODEL_LIST = [
    "gpt-4o-2024-05-13",
    "gpt-4o-mini-2024-07-18",
    "gpt-35-turbo-16k-0613"
]

OLLAMA_MODEL_LIST = [
    "llama3.1:70b",
    "llama3.1:8b-instruct-q4_0",
    "llama3",
    "llama2",
    "gemma2:27b",
    "gemma2:9b",
    "gemma2:2b",
    "mistral-nemo:12b",
    "mistral-large:123b",
    "mixtral:8x7b",
    "mixtral:8x22b",
    "qwen2:72b",
    "qwen2:24b",
    "phi3:14b",
    "codellama:7b-instruct-q4_0",
    "codellama:70b",
    "deepseek-coder-v2:16b-lite-instruct-q4_0",
    "codegemma:7b-instruct-q4_0"
]

GROQ_MODEL_LIST = [
    "llama3-8b-8192",
    "mixtral-8x7b-32768",
    "gemma-7b-it",
    "llama3-70b-8192"
]

HOC_MODEL_LIST = [
    "psyche/Meta-Llama-3-70B-Instruct-awq",
    "hugging-quants/Meta-Llama-3.1-70B-Instruct-AWQ-INT4"
]

MODEL_PARAMETERS = {
                        "temperature": 0,
                        "max_tokens": 75
                    }
