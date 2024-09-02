from langchain_core.prompts import ChatPromptTemplate, MessagesPlaceholder
from PromptGenerator import prompt_templates
from PromptGenerator import cwe_lists

class PromptGenerator:
    """
    Generate prompts based on predefined templates for various prompt types.

    Attributes:
    - prompt_types (dict): A dictionary mapping prompt types to their corresponding system and user prompt templates.
    """
    def __init__(self):
        self.prompt_types = {
            "simple": (prompt_templates.SIMPLE_SYSTEM_PROMPT, prompt_templates.SIMPLE_USER_PROMPT),
            "vulnerability_specific": (prompt_templates.VUL_SYSTEM_PROMPT, prompt_templates.VUL_USER_PROMPT),
            "holistic_vulnerability_analysis": (prompt_templates.HOLISTIC_VULNERABILITY_ANALYSIS_SYSTEM_PROMPT, prompt_templates.HOLISTIC_VULNERABILITY_ANALYSIS_USER_PROMPT),
            "few_shot": (prompt_templates.FEW_SHOT_SYSTEM_PROMPT, prompt_templates.FEW_SHOT_USER_PROMPT),
            "chain_of_thought": (prompt_templates.CHAIN_OF_THOUGHT_SYSTEM_PROMPT, prompt_templates.CHAIN_OF_THOUGHT_USER_PROMPT),
            "self_reflection": prompt_templates.SELF_REFLECTION_PROMPT,
            "self_reflection_with_ground_truth": prompt_templates.SELF_REFLECTION_WITH_GROUND_TRUTH_PROMPT
        }

    def generate_prompt(self, code_snippet, prompt_type):
        """
        Generates a prompt based on the given code snippet and prompt type.

        Parameters:
        - code_snippet (str): The code snippet to be included in the prompt.
        - prompt_type (str): The type of prompt to be generated.

        Returns:
        - str: The formatted prompt based on the specified type.

        Raises:
        - ValueError: If the specified prompt type is not defined.
        """
        system_prompt, user_prompt = self.prompt_types.get(prompt_type, (None, None))

        if not system_prompt or not user_prompt:
            raise ValueError(f"Prompt Type '{prompt_type}' is not defined")

        chat_template = ChatPromptTemplate.from_messages(
            [
                ("system", system_prompt),
                ("human", user_prompt)
            ]
        )

        final_prompt = chat_template.format_messages(cwe_list=cwe_lists.cwe_owasp, code=code_snippet)
        return final_prompt
    
    def generate_self_reflection_prompt(self, chat_history):
        """
        Generates a self-reflection prompt based on the chat history.

        Parameters:
        - chat_history (list): A list of chat messages to be included in the self-reflection prompt.

        Returns:
        - str: The formatted self-reflection prompt.
        """
        self_reflection_template = self.prompt_types.get("self_reflection", None)

        chat_template = ChatPromptTemplate.from_messages(
            [
                MessagesPlaceholder("chat_history"),
                ("human", self_reflection_template)
            ]
        )

        final_prompt = chat_template.format_messages(chat_history=chat_history)

        return final_prompt   
    
    def generate_self_reflection_with_ground_truth_prompt(self, chat_history, ground_truth):
        """
        Generates a self-reflection prompt based on the chat history and ground truth.

        Parameters:
        - chat_history (list): A list of chat messages to be included in the self-reflection prompt.
        - ground_truth (str): The ground truth information to be included in the prompt.

        Returns:
        - str: The formatted self-reflection with ground truth prompt.
        """
        self_reflection_with_ground_truth_template = self.prompt_types.get("self_reflection_with_ground_truth", None)

        chat_template = ChatPromptTemplate.from_messages(
            [
                MessagesPlaceholder("chat_history"),
                ("human", self_reflection_with_ground_truth_template)
            ]
        )

        final_prompt = chat_template.format_messages(chat_history=chat_history, ground_truth=ground_truth)

        return final_prompt

if __name__ == "__main__":
    gen = PromptGenerator()
    messages = gen.generate_prompt("def foo(): pass", "simple")
    print(messages)
