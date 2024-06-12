import re
import pandas as pd

cwe_map = {
    22: "Path Traversal",
    78: "Command Injection",
    79: "XSS (Cross-Site Scripting",
    89: "SQL Injection",
    90: "LDAP Injection",
    327: "Weak Cryptography",
    328: "Weak Hashing",
    330: "Weak Randomness",
    501: "Trust Boundary Violation",
    614: "Secure Cookie Flag",
    643: "XPATH Injection"
}

abbreviation_map = {
    "xss": "cross-site scripting",
    "sqli": "sql injection",
    "cmd injection": "command injection",
    "cmdi": "command injection",
    "pathtraver": "path traversal",
    "directory traversal": "path traversal",
    "ldapi": "ldap injection",
    "weak crypto": "weak cryptography",
    "weak hash": "weak hashing",
    "weak random": "weak randomness",
    "trust boundary": "trust boundary violation",
    "secure cookie": "secure cookie flag",
    "xpathi": "xpath injection"
}

def normalize_string(s):
    """
    Normalize a string by converting it to lowercase, removing special characters, and reducing multiple spaces to a single space.

    Parameters:
    - s (str): The string to be normalized.

    Returns:
    - str: The normalized string.
    """
    s = s.lower()
    s = re.sub(r'[\(\)\[\]\{\}\-_:,]', '', s)
    s = re.sub(r'\s+', ' ', s).strip()
    return s

def map_abbreviations(s, mapping):
    """
    Map abbreviations to their full forms based on a given mapping.

    Parameters:
    - s (str): The string to be mapped.
    - mapping (dict): A dictionary containing the mapping of abbreviations to their full forms.

    Returns:
    - str: The mapped string if a mapping exists, otherwise the original string.
    """
    return mapping.get(s, s)

def get_cwe_from_vuln_names(vuln_names):
    """
    Get the CWE codes corresponding to a list of predicted vulnerability names.

    Parameters:
    - vuln_names (list): A list of vulnerability names.

    Returns:
    - list: A list of CWE codes corresponding to the vulnerability names.
    """
    normalized_cwe_map = {k: normalize_string(v) for k, v in cwe_map.items()}
    reverse_lookup = {v: k for k, v in normalized_cwe_map.items()}

    normalized_vuln_names = [normalize_string(name) for name in vuln_names]
    mapped_vuln_names = [map_abbreviations(name, abbreviation_map) for name in normalized_vuln_names]

    matched_cwe_codes = []
    for name in mapped_vuln_names:
        matched_cwe = -1
        for vuln_name, cwe_code in reverse_lookup.items():
            if vuln_name in name:
                matched_cwe = cwe_code
                break
        matched_cwe_codes.append(matched_cwe)
    
    return matched_cwe_codes

if __name__ == "__main__":
    vuln_names = ["SQL Injection", "XSS (Cross-Site Scripting)", "Weak Crypto", "LDAP Injection", "pathtraver"]
    matched_cwe_codes = get_cwe_from_vuln_names(vuln_names)

    match_results = pd.DataFrame({
        "Vulnerability Name": vuln_names,
        "Matched CWE": matched_cwe_codes
    })

    print(match_results)