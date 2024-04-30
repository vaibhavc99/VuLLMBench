from langchain_core.prompts import ChatPromptTemplate
from PromptGenerator import prompt_templates

cwe_list = [
        "CWE-22 (Path Traversal)", "CWE-78 (Command Injection)", "CWE-79 (XSS (Cross-Site Scripting))", 
        "CWE-89 (SQL Injection)", "CWE-90 (LDAP Injection)", "CWE-327 (Weak Cryptography)",
        "CWE-328 (Weak Hashing)", "CWE-330 (Weak Randomness)", "CWE-501 (Trust Boundary Violation)",
        "CWE-614 (Secure Cookie Flag)", "CWE-643 (XPATH Injection)"
    ]

class PromptGenerator:
    def __init__(self):
        self.strategies = {
            "simple": (prompt_templates.SIMPLE_SYSTEM_PROMPT, prompt_templates.SIMPLE_USER_PROMPT),
            "vulnerability-specific": (prompt_templates.VUL_SYSTEM_PROMPT, prompt_templates.VUL_USER_PROMPT),
            "explanatory-insights": (prompt_templates.EXPL_SYSTEM_PROMPT, prompt_templates.EXPL_USER_PROMPT),
            "solution-oriented": (prompt_templates.SOL_SYSTEM_PROMPT, prompt_templates.SIMPLE_USER_PROMPT)
        }

    def generate_prompt(self, code_snippet, strategy_name):
        system_prompt, user_prompt = self.strategies.get(strategy_name, (None, None))

        if not system_prompt or not user_prompt:
            raise ValueError(f"Strategy '{strategy_name}' is not defined")

        chat_template = ChatPromptTemplate.from_messages(
            [
                ("system", system_prompt),
                ("human", user_prompt)
            ]
        )

        final_prompt = chat_template.format_messages(cwe_list=cwe_list, code=code_snippet)

        # prompt_text = f"""{system_prompt}/n{user_prompt}"""

        return final_prompt

if __name__ == "__main__":
    gen = PromptGenerator()
    messages = gen.generate_prompt("simple")
    print(messages)


