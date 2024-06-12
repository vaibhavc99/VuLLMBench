import javalang
import re
import hashlib

def _rename(name, prefix):
    """
    Rename the given name by appending a prefix and a hash of the name.

    Parameters:
    - name (str): The original name to be renamed.
    - prefix (str): The prefix to be appended to the renamed name.

    Returns:
    - str: The renamed name.
    """
    return f'{prefix}_{hashlib.md5(name.encode()).hexdigest()[:8]}'

def obfuscate(source_code, classes=True, methods=True, variables=True, parameters=True):
    """
    Obfuscate the given Java source code by renaming classes, methods, variables, and parameters.

    Parameters:
    - source_code (str): The Java source code to be obfuscated.
    - classes (bool, optional): Whether to obfuscate class names. Defaults to True.
    - methods (bool, optional): Whether to obfuscate method names. Defaults to True.
    - variables (bool, optional): Whether to obfuscate variable names. Defaults to True.
    - parameters (bool, optional): Whether to obfuscate parameter names. Defaults to True.

    Returns:
    - tuple: A tuple containing the modified source code and the new class name.
    """
    tree = javalang.parse.parse(source_code)
    
    modified_code = source_code
    tokens = list(javalang.tokenizer.tokenize(source_code))
    modifications = {}
    new_class_name = None

    for path, node in tree:
        if isinstance(node, javalang.tree.ClassDeclaration) and classes:
            testcase_name = re.search(r'BenchmarkTest(\d{5})', node.name)
            if testcase_name:
                testcase_number = testcase_name.group(1)
                new_class_name = f'Class{testcase_number}'
                modifications[node.name] = new_class_name
            else:
                modifications[node.name] = f'Class_{hashlib.md5(node.name.encode()).hexdigest()[:8]}'

        elif isinstance(node, javalang.tree.MethodDeclaration) and methods:
            modifications[node.name] = _rename(node.name, 'method')
            
        elif isinstance(node, javalang.tree.VariableDeclarator) and variables:
            modifications[node.name] = _rename(node.name, 'var')

        elif isinstance(node, javalang.tree.FormalParameter) and parameters:
            modifications[node.name] = _rename(node.name, 'param')
    
    for token in tokens:
        if token.value in modifications:
            modified_code = re.sub(r'\b' + re.escape(token.value) + r'\b', modifications[token.value], modified_code)
    
    return modified_code, new_class_name

if __name__ == '__main__':
    with open('./BenchmarkTest01533.java', 'r') as file:
        java_code = file.read()

    modified_code, new_class_name = obfuscate(java_code, classes=True, methods=True, variables=True, parameters=True)

    with open(f'./{new_class_name}.java', 'w') as file:
        file.write(modified_code)
