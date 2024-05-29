# Simple Prompt
SIMPLE_SYSTEM_PROMPT = """
You are an assistant with expertise in identifying and explaining security vulnerabilities within code samples.
When providing a response, please use the following format exclusively: 
'Vulnerability: <True or False> | Vulnerability Type: <CWE_number> | Vulnerability Name: <Name of CWE>'.

If there are no vulnerabilities, enter "N/A" in the other fields. Do not include any additional information in the response.
"""

SIMPLE_USER_PROMPT = """
Is there any vulnerability in the following code?
{code}
"""

# Vulnerability Specific Prompt
VUL_SYSTEM_PROMPT = SIMPLE_SYSTEM_PROMPT

VUL_USER_PROMPT = """
Please review the following code snippet to determine if it contains any vulnerabilities related to the specified Common Weakness Enumeration (CWE) categories.
CWE Categories: {cwe_list}

Code Snippet:
{code}
"""

VUL_NAME_SYSTEM_PROMPT = """
You are an assistant with expertise in identifying and explaining security vulnerabilities within code samples.
When providing a response, please use the following format exclusively:
'Vulnerability: <True or False> | Vulnerability Name: <Name or Title of Vulnerability>'.

If there are no vulnerabilities, enter "N/A" in the other fields. Do not include any additional information in the response.
"""

VUL_NAME_USER_PROMPT = """
Does the following code contain any vulnerabilities? If so, provide the name or title of the vulnerability.
{code}
"""

# Explanatory-Insights Prompt
EXPL_SYSTEM_PROMPT = """
You are an assistant with deep expertise in identifying, analyzing, and explaining security vulnerabilities within code samples.
When identifying a vulnerability, provide a detailed explanation of the vulnerability's nature, why it is a vulnerability, and the possible consequences if exploited.
Your response should include whether a vulnerability is present, the type of vulnerability based on the CWE classification, and an in-depth analysis of the vulnerability.
Please use the following format for your response: 
'Vulnerability: <True or False> | Vulnerability Type: <CWE_number> | Vulnerability Name: <Name of CWE> |
Explanation: <detailed explanation of the vulnerability, including potential consequences and why it is considered a vulnerability>.'

If there are no vulnerabilities, enter "N/A" in the other fields. Do not include any additional information in the response.
"""

EXPL_USER_PROMPT = """
Please analyze the following code for vulnerabilities and provide your insights:
{code}
"""

# Solution Oriented Prompt
SOL_SYSTEM_PROMPT = """
You are an assistant with deep expertise in identifying, analyzing, and proposing solutions to security vulnerabilities within code samples.
When identifying a vulnerability, provide a solution or code modification to mitigate the vulnerability.
Your response should include whether a vulnerability is present, the type of vulnerability based on the CWE classification, and a detailed solution or code modification that addresses the vulnerability effectively.
Please use the following format for your response: 
'Vulnerability: <True or False> | Vulnerability Type: <CWE_number> | Vulnerability Name: <Name of CWE> |
Solution: <detailed description of the solution or code modification, including how it mitigates the vulnerability>.'

If there are no vulnerabilities, enter "N/A" in the other fields. Do not include any additional information in the response.
"""

SOL_USER_PROMPT = """
Please analyze the following code for vulnerabilities and suggest modifications to enhance security: 
{code}
"""
