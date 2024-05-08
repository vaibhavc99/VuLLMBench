import pandas as pd
import re
from sklearn.metrics import accuracy_score, precision_score, recall_score, f1_score, confusion_matrix, classification_report

class VulnerabilityEvaluator:
    def __init__(self, predicted_df: pd.DataFrame, true_df: pd.DataFrame):
        self.predicted_df = predicted_df
        self.true_df = true_df

    def extract_cwe(self, description):
        match = re.search(r'CWE-(\d+)', description)
        return int(match.group(1)) if match else None

    def evaluate_by_model(self):
        results = {}
        for model in self.predicted_df['Model'].unique():
            model_df = self.predicted_df[self.predicted_df['Model'] == model].copy()
            model_df['cwe'] = model_df['Predicted Vulnerability Type'].apply(self.extract_cwe)
            
            merged_df = model_df.merge(self.true_df, left_index=True, right_index=True, suffixes=('_pred', '_true'))
            
            vuln_metrics = self.evaluate_vulnerability(merged_df)
            type_metrics = self.evaluate_vulnerability_type_multiclass(merged_df)

            results[model] = {
                'Vulnerability Metrics': vuln_metrics,
                'Vulnerability Type Metrics': type_metrics
            }
        return results

    def evaluate_vulnerability(self, df: pd.DataFrame):
        y_true = df['real_vulnerability'].astype(bool)
        y_pred = df['Predicted Vulnerability']
        return {
            'Accuracy': accuracy_score(y_true, y_pred),
            'Precision': precision_score(y_true, y_pred, zero_division=0),
            'Recall': recall_score(y_true, y_pred, zero_division=0),
            'F1-Score': f1_score(y_true, y_pred, zero_division=0)
        }

    def evaluate_vulnerability_type_multiclass(self, df: pd.DataFrame):
        cwe_true = df['cwe_true']
        cwe_pred = df['cwe_pred']
        
        unique_labels = cwe_true.unique()
        precision_dict = {}
        recall_dict = {}
        accuracy_dict = {}
        f1_score_dict = {}

        for label in unique_labels:
            true_binary = (cwe_true == label)
            predicted_binary = (cwe_pred == label)

            accuracy_dict[f"CWE-{label}"] = accuracy_score(true_binary, predicted_binary)
            precision_dict[f"CWE-{label}"] = precision_score(true_binary, predicted_binary, zero_division=0)
            recall_dict[f"CWE-{label}"] = recall_score(true_binary, predicted_binary, zero_division=0)
            f1_score_dict[f"CWE-{label}"] = f1_score(true_binary, predicted_binary, zero_division=0)


        return {
            'Accuracy': accuracy_dict,
            'Precision': precision_dict,
            'Recall': recall_dict,
            'F1-Score': f1_score_dict
        }

    
    # def evaluate_vulnerability_type(self, df:pd.DataFrame):
    #     cwe_true = df['cwe_true']
    #     cwe_pred = df['cwe_pred']

    #     return {
    #         'Accuracy': accuracy_score(cwe_true, cwe_pred),
    #         'Precision': precision_score(cwe_true, cwe_pred, average='macro', zero_division=0),
    #         'Recall': recall_score(cwe_true, cwe_pred, average='macro',zero_division=0),
    #         'F1-Score': f1_score(cwe_true, cwe_pred, average='macro',zero_division=0),
    #         'Classification Report': classification_report(cwe_true, cwe_pred, zero_division=0),
    #         'Confusion Matrix': confusion_matrix(cwe_true, cwe_pred)
    #     }