import pandas as pd
from sklearn.model_selection import train_test_split

def get_stratified_sample(data: pd.DataFrame, stratification_options: dict):
    """
    Performs stratified sampling on a DataFrame based on composite keys.

    Parameters:
    - data (pd.DataFrame): The input DataFrame.
    - stratification_options (dict): A dictionary containing the following options:
        - 'stratify_cols' (list): List of two column names to use for creating a composite stratification key.
        - 'test_size' (float): Proportion of the dataset to include in the sample (0-1).
        - 'random_state' (int): Seed for random shuffling.

    Returns:
    - sample_df (pd.DataFrame): DataFrame containing the stratified sample.
    """
    # Unpack stratification options
    stratify_cols = stratification_options['stratify_cols']
    test_size = stratification_options['test_size']
    random_state = stratification_options['random_state']

    # Create a composite key for stratification
    data['composite_key'] = data[stratify_cols[0]].astype(str) + "_" + data[stratify_cols[1]].astype(str)

    # Prepare data for stratification
    y = data['composite_key']  # Stratification labels
    X = data  # Data points

    # Perform stratified sampling
    _, X_sample = train_test_split(
        X, test_size=test_size, stratify=y, random_state=random_state
    )

    # Drop the composite key before returning
    X_sample = X_sample.drop(columns=['composite_key'])

    return X_sample