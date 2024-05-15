from langchain_core.prompts import ChatPromptTemplate
from PromptGenerator import prompt_templates
from PromptGenerator import cwe_lists

class PromptGenerator:
    def __init__(self):
        self.prompt_types = {
            "simple": (prompt_templates.SIMPLE_SYSTEM_PROMPT, prompt_templates.SIMPLE_USER_PROMPT),
            "vulnerability_specific": (prompt_templates.VUL_SYSTEM_PROMPT, prompt_templates.VUL_USER_PROMPT),
            "explanatory_insights": (prompt_templates.EXPL_SYSTEM_PROMPT, prompt_templates.EXPL_USER_PROMPT),
            "solution_oriented": (prompt_templates.SOL_SYSTEM_PROMPT, prompt_templates.SIMPLE_USER_PROMPT)
        }

    def generate_prompt(self, code_snippet, prompt_type):
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

        # prompt_text = f"""{system_prompt}/n{user_prompt}"""

        return final_prompt

if __name__ == "__main__":
    gen = PromptGenerator()
    messages = gen.generate_prompt("simple")
    print(messages)


