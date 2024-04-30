import re
import os
import hashlib

def obfuscate_java_code(java_file_path):
    with open(java_file_path, 'r') as file:
        code = file.read()

    patterns = {
        'class': r'\bclass\s+(\w+)',
        'method': r'\b(?:public|protected|private)\s+\w+\s+(\w+)\(',
        'variable': r'\b(?:int|float|String|boolean)\s+(\w+)[;=]'
    }

    obfuscated_code = code

    for type_, pattern in patterns.items():
        matches = re.findall(pattern, code)
        unique_matches = set(matches)

        for match in unique_matches:
            obfuscated_name = hashlib.md5(match.encode()).hexdigest()[:8]  # Simple obfuscation
            obfuscated_code = re.sub(r'\b' + match + r'\b', obfuscated_name, obfuscated_code)


    obfuscated_file_path = java_file_path.replace('.java', '_obfuscated.java')
    with open(obfuscated_file_path, 'w') as file:
        file.write(obfuscated_code)

    print(f"Obfuscated code saved to: {obfuscated_file_path}")


obfuscate_java_code('./BenchmarkTest00001.java')
