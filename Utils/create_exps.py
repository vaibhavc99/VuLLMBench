import os

datasets = [
    'cvefixes_java',
    'cvefixes_c',
    'cvefixes_cpp',
    'cvefixes_csharp',
    'cvefixes_python',
    'cvefixes_javascript'
]

model_names = ['gemma2:27b', 'qwen2:72b']

# Starting experiment number
start_exp_num = 10

# Configuration file template
conf_template = """
[General]
use_cache = True
prompt_type = simple, vulnerability_specific, few_shot, chain_of_thought
logging = debug
dataset_name = {dataset_name}

[Data Stratification]
stratify = False
stratify_by = cwe_name, cwe
test_size = 0.65
random_state = 12

[Preprocessing Options]
top_25_cwe = True
file_examples = {file_examples}
method_examples = {method_examples}

[LLM]
model_names = {model_names}
"""

exp_num = start_exp_num

for dataset in datasets:
    for method_flag in [False, True]:
        exp_name = f"exp{exp_num}"
        if method_flag:
            exp_name += "method"
            method_examples = "True"
            file_examples = "False"
        else:
            method_examples = "False"
            file_examples = "True"

        os.makedirs(exp_name, exist_ok=True)

        # Format the configuration content
        conf_content = conf_template.format(
            dataset_name=dataset,
            file_examples=file_examples,
            method_examples=method_examples,
            model_names=', '.join(model_names)
        )

        conf_file_path = os.path.join(exp_name, 'experiment.conf')
        with open(conf_file_path, 'w') as conf_file:
            conf_file.write(conf_content.strip())

    exp_num += 1