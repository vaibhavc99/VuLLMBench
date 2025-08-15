# VuLLMBench

## Overview

VuLLMBench is a benchmarking platform designed to evaluate the vulnerability detection capabilities of Large Language Models (LLMs) on code examples. It uses code examples from datasets like OWASP Benchmark and CVEFixes, it allows for customizable preprocessing of code examples, generates prompts using various prompting strategies, queries multiple LLMs, parses and evaluates responses, and generates evaluation reports. An interactive dashboard is also available for detailed analysis.

## Features

- **Multiple Datasets Support**: Works with OWASP Benchmark and CVEFixes datasets.
- **Preprocessing of Code Examples**: Apply customizable preprocessing (obfuscation, code cleaning) steps before evaluation.
- **Stratified Sampling**: Sample code examples based on criteria like vulnerability types.
- **Prompt Generation**: Create different prompt types for LLMs.
- **LLM Integration**: Interface with multiple LLMs for querying.
- **Response Parsing and Evaluation**: Analyze and evaluate LLM responses.
- **Reporting**: Generate detailed evaluation reports.
- **Interactive Dashboard**: Visualize results and insights.
- **Docker Support**: Easily deploy and run the benchmark platform using Docker or Docker Compose.

## Installation

### Prerequisites

- **Docker** and **Docker Compose** (for Docker deployment)
- **Python 3.10** or higher (for manual installation)
- **Git** (for cloning the repository)

### Clone the Repository

```bash
git clone https://github.com/vaibhavc99/VuLLMBench.git
```

### Environment Variables

VuLLMBench requires certain environment variables to be set for API keys and endpoints depending on the LLMs to be used:

- `OPENAI_API_KEY`
- `GROQ_API_KEY`
- `AZURE_OPENAI_API_KEY`
- `AZURE_OPENAI_API_VERSION`
- `AZURE_OPENAI_ENDPOINT`
- `OLLAMA_HOST_URL`
- `HOC_HOST_URL`

These variables can be stored in a `.env` file in the project root directory or exported as environment variables in the shell.

## Usage

VuLLMBench can be run using Docker or directly via the command line interface (CLI).

### Running an Experiment with Docker

Experiments can be run using either Docker CLI or Docker Compose.

#### Using Docker CLI

First, build the Docker image:

```bash
docker build -t vullmbench .
```

Run an experiment using:

```bash
docker run --name vullmbench \
  -e EXPERIMENT=exp01 \
  --env-file .env \
  -v $(pwd)/Evaluation:/vullmbench/Evaluation \
  -v $(pwd)/CodeExamples:/vullmbench/CodeExamples \
  -v $(pwd)/LLMsInterface/responses:/vullmbench/LLMsInterface/responses \
  vullmbench
```

- Replace `exp01` with the name of the experiment.
- Ensure that the required environment variables are set, either by using the `--env-file .env` option or by exporting them.

#### Using Docker Compose

Run the experiment:

```bash
EXPERIMENT=exp01 docker compose up
```

- Set the `EXPERIMENT` environment variable to specify the experiment name.
- Docker Compose will use the configuration in the `docker-compose.yml` file.

### Running an Experiment via CLI

To run the benchmark without Docker:

#### Manual Installation

1. **Create a Virtual Environment**

   ```bash
   python3 -m venv vullmbench-venv
   source vullmbench-venv/bin/activate
   ```

2. **Install Required Packages**

   ```bash
   pip install -r requirements.txt
   ```

3. **Set Environment Variables**

   Ensure that the required environment variables are set, either by creating a `.env` file or exporting them in the shell.

#### Run the Experiment

Execute the benchmark using:

```bash
python VuLLMBench.py -e exp01
```

- Replace `exp01` with the name of the experiment.
- Additional command-line arguments can be passed as needed (see below).

### Command-line Arguments

- `-d`, `--data`: Path to the directory containing the data (Code Examples). **Default**: `./CodeExamples`
- `--dataset_name`: Name of the dataset to use (`owasp` or `cvefixes_<language>`). **Default**: `owasp`
- `-m`, `--model_names`: Names of the models to use.
- `--prompt_types`: Types of prompts to use for LLM queries.

  **Choices**:

  - `simple`
  - `vulnerability_specific`
  - `few_shot`
  - `chain_of_thought`
  - `holistic_vulnerability_analysis`

  **Default**: `['simple']`

- `--no_cache`: Do not use cache. By default, caching is enabled to reuse previous LLM responses.
- `-e`, `--experiment`: Name of the experiment to execute. Must correspond to a directory in the `Evaluation` directory containing a configuration file.
- `--processing_options`: Processing options to apply to the examples.

  **Choices**:

  - `obfuscate_code`
  - `remove_multiline_comments`
  - `remove_import_statements`
  - `remove_package_declarations`
  - `replace_benchmark_names`
  - `replace_cwe_names`

  **Default**: `['remove_multiline_comments']`

- `--self_reflection`: Enable self-reflection on LLM responses.
- `--self_reflection_gt`: Enable self-reflection on LLM responses with ground truth.
- `--dashboard`: Run the Evaluation dashboard.

## Experiment Configuration

When running experiments using the `--experiment` option, specify configurations in an `experiment.conf` file located in the corresponding directory under `Evaluation`.

### Example `experiment.conf`

```ini
[General]
use_cache = True
prompt_type = simple, vulnerability_specific
logging = debug
dataset_name = owasp
self_reflection = False

[Preprocessing Options]
obfuscate_code = True
remove_multiline_comments = True
replace_cwe_names = True

[Data Stratification]
stratify = True
stratify_by = category, cwe
test_size = 0.2
random_state = 12

[LLM]
model_names = gpt-4o-2024-05-13, llama3.1:70b
```

## Project Structure

A brief overview of the important files and their purposes:

- [`VuLLMBench.py`](VuLLMBench.py): The main script that orchestrates the benchmark, handling argument parsing and execution flow.
- [`Controller.py`](Controller.py): Manages the overall workflow of the benchmark process.
- [`LLMsInterface/llms.py`](LLMsInterface/llms.py): Handles interactions with various LLMs, supports both sequential and parallel processing of prompts.
- [`LLMsInterface/ollama_utils.py`](LLMsInterface/ollama_utils.py): Provides utility functions for the Ollama server.
- [`LLMsInterface/responseDB.py`](LLMsInterface/responseDB.py): Manages LLM responses using a SQLite database.
- [`LLMsInterface/responses/llms_responses.db`](LLMsInterface/responses/llms_responses.db): SQLite database for storing LLM responses.
- [`Preprocessor/owasp_processor.py`](Preprocessor/owasp_processor.py): Processes OWASP code examples with preprocessing and caching.
- [`Preprocessor/owasp_cache.py`](Preprocessor/owasp_cache.py): Manages caching for processed OWASP examples.
- [`Preprocessor/obfuscation.py`](Preprocessor/obfuscation.py): Performs code obfuscation to anonymize code.
- [`Preprocessor/cvefixes_processor.py`](Preprocessor/cvefixes_processor.py): Processes CVEFixes code examples.
- [`Preprocessor/stratification.py`](Preprocessor/stratification.py): Creates balanced test sets through data stratification.
- [`PromptGenerator/generator.py`](PromptGenerator/generator.py): Generates prompts based on predefined templates.
- [`PromptGenerator/prompt_templates.py`](PromptGenerator/prompt_templates.py): Contains prompt templates for different types.
- [`PromptGenerator/cwe_lists.py`](PromptGenerator/cwe_lists.py): Contains lists of CWEs for prompt generation.
- [`ReportsGenerator/vulnEvaluator.py`](ReportsGenerator/vulnEvaluator.py): Evaluates LLM performance and computes metrics.
- [`ReportsGenerator/responseParser.py`](ReportsGenerator/responseParser.py): Parses and structures LLM response data.
- [`ReportsGenerator/dashboard.py`](ReportsGenerator/dashboard.py): Manages the interactive evaluation dashboard.
- [`ReportsGenerator/visualizer.py`](ReportsGenerator/visualizer.py): Generates plots for the dashboard using Plotly.
- [`Utils/model_config.py`](Utils/model_config.py): Contains configuration settings for models.
- [`Utils/configure_logging.py`](Utils/configure_logging.py): Sets up logging configurations.
- [`Utils/cvefixes_extractor.py`](Utils/cvefixes_extractor.py): Extracts code examples from the CVEFixes database.

## Data Preparation

Ensure that the data directory (default `./CodeExamples`) contains the datasets to be used.

### Supported Datasets

- **OWASP Benchmark Project**: [Link](https://github.com/OWASP-Benchmark/BenchmarkJava/tree/master/src/main/java/org/owasp/benchmark/testcode)

  - Download the OWASP Benchmark code examples and place it in `./CodeExamples/OWASP`.

- **CVEfixes Dataset**: [Link](https://zenodo.org/records/7029359)

  - Download the CVEFixes dataset and place it in `./CodeExamples/CVEfixes_v1.0.7`.

#### Extracting Code Examples from CVEFixes

After downloading the CVEFixes dataset, run the `cvefixes_extractor` to extract the code examples.

To run the extractor:

```bash
python Utils/cvefixes_extractor.py
```

This will generate CSV files containing the extracted code examples, which can then be used by VuLLMBench.

## Reports and Evaluation

After running the benchmark, reports are generated and saved in the `Evaluation` directory under the specified experiment.

### Reports Include

- Evaluation metrics for each model and prompt type.
- DataFrames used for evaluation (true and predicted).
- Logs of other metrics (runtime, costs).

## Dashboard

An interactive dashboard is available to visualize and analyze the results.

### Launch the Dashboard via CLI

```bash
python VuLLMBench.py --dashboard
```

### Running the Dashboard with Docker

#### Using Docker CLI

```bash
docker run --name vullmbench_dashboard \
  -p 8501:8501 \
  --env-file .env \
  -v $(pwd)/Evaluation:/vullmbench/Evaluation \
  vullmbench \
  python VuLLMBench.py --dashboard
```

#### Using Docker Compose

Override the default command to run the dashboard:

```bash
docker compose run --service-ports vullmbench python VuLLMBench.py --dashboard
```

Access the dashboard at `http://localhost:8501`.

### Features

- **Model Selection**: Choose between aggregated results or individual models.
- **Classification Type**: Toggle between binary and multiclass classification metrics.
- **Interactive Plots**: Visualize metrics with interactive Plotly charts.
- **Customization**: Select specific CWEs or prompts for detailed metrics.
- **Export Options**: Save figures and data for reporting purposes.

## Logging

Logs are saved in the `Evaluation/<experiment>` directory with the name `<experiment>.log`.

- **Standard Output**: Info and error messages are printed to the console.
- **Log File**: Detailed logs are written to the log file for debugging and record-keeping.
