import logging
import argparse
from pathlib import Path
import configparser
from Controller import Controller
from Utils.configure_logging import configure_logging
import subprocess

def main():
    """
    The main function to execute the vulnerability detection benchmark.
    Parses arguments, loads paths and configurations, and runs the controller.
    """
    args = parse_arguments()
    
    if args.dashboard:
        run_dashboard()
        return

    paths = load_paths(args)
    log_file = paths['ExperimentPath'] / f"{args.experiment}.log" if args.experiment else "vullmbench.log"

    try:
        if args.experiment:
            config = load_config(paths['ConfigPath'])
            logger = configure_logging("VuLLMBench", log_file ,config)
            logger.info(f"Experiment {args.experiment} started...")
            run_controller(args, paths, config)
            logger.info(f"Experiment {args.experiment} finished.")
        else:
            logger = configure_logging("VuLLMBench")
            run_controller(args, paths)

    except Exception as e:
        logging.error(f'An error occurred: {e}')

def run_controller(args, paths, config=None):
    """
    Runs the Controller with the provided arguments and configurations.
    
    Parameters:
    - args: Parsed arguments
    - paths: Dictionary containing paths
    - config: Configuration object if provided, else None
    """
    if config:
        use_cache = config.getboolean('General', 'use_cache')
        prompt_types = [ptype.strip() for ptype in config['General']['prompt_type'].split(',')]
        self_reflection = config.getboolean('General', 'self_reflection', fallback=False)
        self_reflection_gt = config.getboolean('General', 'self_reflection_gt', fallback=False)
        dataset_name = config['General'].get('dataset_name', 'owasp')
        model_names = [name.strip() for name in config['LLM']['model_names'].split(',')]
        processing_options = {key: config['Preprocessing Options'].getboolean(key)
                              for key in config['Preprocessing Options']}
        
        if 'Data Stratification' in config:
            stratification_options = {
                'stratify': config.getboolean('Data Stratification', 'stratify'),
                'stratify_cols': [col.strip() for col in config['Data Stratification']['stratify_by'].split(',')],
                'test_size': config.getfloat('Data Stratification', 'test_size'),
                'random_state': config.getint('Data Stratification', 'random_state')
            }
        else:
            stratification_options = None

    else:
        use_cache = not args.no_cache
        prompt_types = args.prompt_types
        self_reflection = args.self_reflection
        self_reflection_gt = args.self_reflection_gt
        dataset_name = args.dataset_name
        model_names = args.model_names
        processing_options = {option: False for option in PROCESSING_OPTIONS}
        for option in args.processing_options:
            processing_options[option] = True
        stratification_options = None

    controller = Controller(data_dir_path=paths['DataPath'], dataset_name=dataset_name, useCache=use_cache, self_reflection=self_reflection, self_reflection_gt=self_reflection_gt)
    controller.load_examples(processing_options, stratification_options)

    for prompt_type in prompt_types:
        table_name = f"{args.experiment if args.experiment else 'default'}_{prompt_type}_prompt"
        controller.table_name = table_name
        controller.send_to_llm(model_names, prompt_type)
        controller.generate_reports(prompt_type, args.experiment if args.experiment else 'default')
    
    controller.db.close()

def run_dashboard():
    """
    Runs the Evaluation dashboard.
    """
    # subprocess.run(["streamlit", "run", "ReportsGenerator/dashboard.py"])
    try:
        process = subprocess.Popen(["streamlit", "run", "ReportsGenerator/dashboard.py"])
        process.communicate()
    except KeyboardInterrupt:
        print("Closing the Dashboard...")
        process.terminate()

def load_paths(args):
    """
    Loads and validates the paths based on the provided arguments.
    
    Parameters:
    - args: Parsed arguments
    
    Returns:
    - Dictionary containing resolved paths
    """
    paths = {}
    
    data_path = Path(args.data).resolve()
    paths['DataPath'] = data_path

    if args.experiment:
        main_dir = Path(__file__).resolve().parent
        evaluation_path = main_dir / 'Evaluation'
        
        experiment_path = evaluation_path / args.experiment
        config_path = experiment_path / 'experiment.conf'
        
        paths['ExperimentPath'] = experiment_path
        paths['ConfigPath'] = config_path

        if not config_path.exists():
            raise FileNotFoundError(f'Config file {config_path} does not exist')

    return paths

def load_config(config_path):
    """
    Loads the configuration file.
    
    Parameters:
    - config_path: Path to the configuration file
    
    Returns:
    - Configuration object
    """
    config = configparser.ConfigParser()
    config.read(config_path)
    return config

def parse_arguments():
    """
    Parses command-line arguments.
    
    Returns:
    - Parsed arguments
    """
    parser = argparse.ArgumentParser(description='VuLLMBench - Vulnerability Detection Benchmark for Large Language Models')
    parser.add_argument('-d', '--data', help='Path to the directory containing the data (Code Examples)', default='./CodeExamples')
    parser.add_argument('--dataset_name', help='Name of the dataset to use', default='owasp')
    parser.add_argument('-m', '--model_names', nargs='+', help='Names of the models to use', default=['llama3.1:8b'])
    parser.add_argument('--prompt_types', nargs='+', choices=['simple', 'vulnerability_specific', 'few_shot', 'chain_of_thought', 'holistic_vulnerability_analysis'], help='Types of prompts to use for LLM queries', default=['simple'])
    parser.add_argument('--no_cache', action='store_true', help='Do not use cache')
    parser.add_argument('-e', '--experiment', help='Name of the experiment to execute. The name must correspond to one directory in the Evaluation directory which contains a configuration file')
    parser.add_argument('--processing_options', nargs='*', choices=PROCESSING_OPTIONS, help='Processing options to apply for the examples', default=['remove_multiline_comments'])
    parser.add_argument('--self_reflection', action='store_true', help='Enable to perform self-reflection on LLM responses.')
    parser.add_argument('--self_reflection_gt', action='store_true', help='Enable to perform self-reflection on LLM responses with ground truth.')
    parser.add_argument('--dashboard', action='store_true', help='Run the Evaluation dashboard')
    
    return parser.parse_args()

if __name__ == '__main__':
    PROCESSING_OPTIONS = [
        'obfuscate_code',
        'remove_multiline_comments',
        'remove_import_statements',
        'remove_package_declarations',
        'replace_benchmark_names',
        'replace_cwe_names'
    ]
    main()