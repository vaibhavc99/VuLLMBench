DATA_DIR_PATH = "CodeExamples"

DB_PATH = "LLMsInterface/responses/llms_responses.db"

OPENAI_API_KEY = ""

OLLAMA_HOST = "https://hoc-lx-gpu02.ad.iem-hoc.de:8083"

GROQ_API_KEY = ""

OPENAI_MODEL_LIST = [
    "gpt-3.5-turbo",
    "gpt-4",
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
                        "max_tokens": 50
                    }
