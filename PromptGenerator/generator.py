from langchain_core.prompts import ChatPromptTemplate
from PromptGenerator import prompt_templates
from PromptGenerator import cwe_lists

class PromptGenerator:
    """
    A class that generates prompts for code snippets based on different prompt types.

    Attributes:
    - prompt_types (dict): A dictionary mapping prompt types to their corresponding system and user prompts.
    """

    def __init__(self):
        self.prompt_types = {
            "simple": (prompt_templates.SIMPLE_SYSTEM_PROMPT, prompt_templates.SIMPLE_USER_PROMPT),
            "vulnerability_specific": (prompt_templates.VUL_SYSTEM_PROMPT, prompt_templates.VUL_USER_PROMPT),
            "vulnerability_names": (prompt_templates.VUL_NAME_SYSTEM_PROMPT, prompt_templates.VUL_NAME_USER_PROMPT),
            "explanatory_insights": (prompt_templates.EXPL_SYSTEM_PROMPT, prompt_templates.EXPL_USER_PROMPT),
            "solution_oriented": (prompt_templates.SOL_SYSTEM_PROMPT, prompt_templates.SIMPLE_USER_PROMPT)
        }

    def generate_prompt(self, code_snippet, prompt_type):
        """
        Generates a prompt for the given code snippet and prompt type.

        Parameters:
        - code_snippet (str): The code snippet for which the prompt is generated.
        - prompt_type (str): The type of prompt to generate.

        Returns:
        - str: The generated prompt.

        Raises:
        - ValueError: If the prompt type is not defined.
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

if __name__ == "__main__":
    gen = PromptGenerator()
    messages = gen.generate_prompt("simple")
    print(messages)


