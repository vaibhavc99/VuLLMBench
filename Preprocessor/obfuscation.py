import javalang
import re
import hashlib

def _rename(name, prefix):
    return f'{prefix}_{hashlib.md5(name.encode()).hexdigest()[:8]}'

def obfuscate(source_code, classes=True, methods=True, variables=True, parameters=True):
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
