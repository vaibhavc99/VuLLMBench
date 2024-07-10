# MA_Chaudhari_LLM-Benchmark

## Table of Contents
1. [Description](#description)
2. [Project Structure](#project-structure)
3. [Installation](#installation)
4. [Usage](#usage)
5. [Experiments](#experiments)


## Description
The project is designed to evaluate and benchmark the performance of various Large Language Models (LLMs) in detecting and comprehending security vulnerabilities within code. This platform provides a structured approach for data preprocessing, prompt generation, experiment execution, and evaluation of LLMs' responses.

## Project Structure
The repository is organized as follows:
- `VuLLMBench.py`: Main script that runs the benchmark platform.
- `Controller.py`: Script to run and control different components of the benchmark.
- `CodeExamples/`: Contains code examples used for evaluating LLMs. 
- `Preprocessor/`: Scripts for preprocessing data (Obfuscation, stratification, data cleaning, etc).
- `PromptGenerator/`: Scripts for generating prompts for LLMs using predefined templates.
- `LLMsInterface/`: Interface scripts for interacting with different LLMs.
- `ReportsGenerator/`: Scripts for generating reports and evaluating the LLM responses.
- `Evaluation/`: Contains experiment configurations and evaluation results.
- `config.py`: File containing Data/Database paths, API keys, and model lists used in the project.
- `requirements.txt`: List of Python dependencies.
- `llms_responses.db`: Database storing responses from different LLMs.

## Installation
1.  **Clone the repository**
    
    Clone the repository using the following command:
    ```bash
    git clone https://gitlab.cc-asp.fraunhofer.de/iem_paderborn/oe300/abschlussarbeiten/ma_chaudhari_llm-benchmark.git
    ```

2.  **Install Dependencies and Set Up Virtual Environment**

    Run the following commands to set up and activate the virtual environment.

    ```bash
    python3 -m venv python-venv
    ```
    Activate the virtual environment:
    ```bash
    source python-venv/bin/activate
    ```
    Install the necessary dependencies:
    ```bash
    pip install -r requirements.txt
    ```


## Usage
### Running the Benchmark
The main script to run the benchmark platform is `VuLLMBench.py`. Use the following command to see the available options:
```bash
python VuLLMBench.py -h
```
The CLI options are as follows:
```
options:
  -h, --help            show this help message and exit
  -d DATA, --data DATA  Path to the directory containing the data (Code Examples)
  -m MODEL_NAMES [MODEL_NAMES ...], --model_names MODEL_NAMES [MODEL_NAMES ...]
                        Names of the models to use
  --prompt_types {simple,vulnerability_specific,vulnerability_names,explanatory_insights,solution_oriented} [{simple,vulnerability_specific,vulnerability_names,explanatory_insights,solution_oriented} ...]
                        Types of prompts to use for LLM queries
  --no_cache            Do not use cache
  -e EXPERIMENT, --experiment EXPERIMENT
                        Name of the experiment to execute. The name must correspond to one directory in the Evaluation directory which contains a configuration file
  --processing_options [{obfuscate_code,remove_multiline_comments,remove_import_statements,remove_package_declarations,replace_benchmark_names,replace_cwe_names} ...]
                        Processing options to apply for the examples
  --self_reflection     Enable to perform self-reflection on LLM responses.
```

### Examples
To run an experiment with specific data, models, and prompt types, use the following command:
```bash
python VuLLMBench.py -d /path/to/data -m llm1 llm2 --prompt_types simple vulnerability_specific
```
Replace `/path/to/data` with the path to your data directory, `llm1 llm2` with the names of the models you want to use.

#### Using Processing Options
You can specify various processing options to apply to the examples:
```bash
python VuLLMBench.py -d /path/to/data -m model1 --prompt_types simple --processing_options obfuscate_code remove_multiline_comments
```

## Experiments
Experiments are organized into different directories under `Evaluation/`. Each directory contains:
- `experiment.conf`: Configuration file for the specific experiment.
- `eval_results_*.json`: JSON files with evaluation results.
- `eval_df_*.csv`: Dataframe used for evaluation.


### Example: Running an Experiment with Configuration File
To run an experiment using a configuration file, use the following command:

```bash
python VuLLMBench.py -e exp01
```
Replace exp01 with the name of the experiment directory containing the `experiment.conf` file you want to use.

### Results
Results from the experiments are stored in the `Evaluation/` directory. The results include JSON and CSV files that detail the performance of the LLMs on the given tasks.
