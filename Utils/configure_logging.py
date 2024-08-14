import logging
from pathlib import Path

LOGGING_FILE = None
# LOGGING_DIR = Path(__file__).parent.parent / "Logs"
# LOGGING_DIR.mkdir(exist_ok=True)

def configure_logging(logger_name, log_file=None, config=None):
    global LOGGING_FILE

    if log_file and not LOGGING_FILE:
        LOGGING_FILE = log_file

    # if not LOGGING_FILE:
    #     LOGGING_FILE = "vullmbench.log"

    logging_options = {
        'debug': logging.DEBUG,
        'info': logging.INFO,
        'warning': logging.WARNING,
        'error': logging.ERROR,
        'critical': logging.CRITICAL
    }
    
    logger = logging.getLogger(logger_name)
    logger.setLevel(logging.DEBUG)
    
    console_handler = logging.StreamHandler()
    file_handler = logging.FileHandler(LOGGING_FILE, mode="a")
    
    if config:
        level = logging_options[config['General']['logging']]
        console_handler.setLevel(level)
        file_handler.setLevel(level)
    else:
        console_handler.setLevel(logging.INFO)
        file_handler.setLevel(logging.DEBUG)
    
    formatter = logging.Formatter(
        "%(asctime)s - %(name)s - %(levelname)s - %(message)s"
    )
    
    console_handler.setFormatter(formatter)
    file_handler.setFormatter(formatter)

    logger.addHandler(console_handler)
    logger.addHandler(file_handler)
    
    logger.propagate = False

    return logger
