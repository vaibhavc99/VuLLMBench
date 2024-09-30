import os
import pandas as pd
from sklearn.metrics import (accuracy_score, precision_score, recall_score, f1_score, confusion_matrix)
import json
import re
import numpy as np

base_directory = '../Eval-DFs' 
result_dict = {}

def calculate_metrics(df):
    cwe_metrics = {}
    for cwe, group in df.groupby('cwe_true'):
        y_true = group['real_vulnerability'].astype(bool)
        y_pred = group['predicted_vulnerability'].astype(bool)

        accuracy = accuracy_score(y_true, y_pred)
        precision = precision_score(y_true, y_pred, zero_division=0)
        recall = recall_score(y_true, y_pred, zero_division=0)
        f1 = f1_score(y_true, y_pred, zero_division=0)
        tn, fp, fn, tp = confusion_matrix(y_true, y_pred).ravel()
        false_positive_rate = fp / (fp + tn)
        youden_index = recall - false_positive_rate
        
        cwe_metrics[f"CWE-{cwe}"] = {
            'Accuracy': accuracy,
            'Precision': precision,
            'Recall': recall,
            'F1-Score': f1,
            'Youden Index': youden_index,
            'FPR': false_positive_rate,
            'TP': tp,
            'TN': tn,
            'FP': fp,
            'FN': fn,
        }
    return cwe_metrics

def process_files():
    pattern = re.compile(r'^eval_df_(.+?)-prompt_(.+)\.csv$')
    
    for root, dirs, files in os.walk(base_directory):
        for file in files:
            if file.endswith('.csv'):
                match = pattern.match(file)
                if match:
                    prompt_type = match.group(1)
                    model_name = match.group(2)
                    file_path = os.path.join(root, file)
                    df = pd.read_csv(file_path)

                    cwe_metrics = calculate_metrics(df)

                    # Convert metrics to a serializable format
                    serializable_metrics = {
                        cwe: {k: (v.item() if isinstance(v, (np.int64, np.float64)) else v) for k, v in metrics.items()}
                        for cwe, metrics in cwe_metrics.items()
                    }
                    
                    if model_name not in result_dict:
                        result_dict[model_name] = {}
                    if prompt_type not in result_dict[model_name]:
                        result_dict[model_name][prompt_type] = {}
                    
                    result_dict[model_name][prompt_type].update(serializable_metrics)

def save_results():
    with open('per_cwe_metrics.json', 'w') as f:
        json.dump(result_dict, f, indent=4)

def main():
    process_files()
    save_results()

if __name__ == '__main__':
    main()
