import pandas as pd
import re
from sklearn.metrics import (
    accuracy_score, precision_score, recall_score, f1_score, roc_auc_score, 
    confusion_matrix, matthews_corrcoef, cohen_kappa_score
)
from ReportsGenerator.vuln_names_mapper import get_cwe_from_vuln_names

class VulnerabilityEvaluator:
    """
    Evaluate the performance of LLMs in vulnerability prediction.
    
    Attributes:
    - predicted_df (pd.DataFrame): DataFrame containing the predicted vulnerabilities.
    - true_df (pd.DataFrame): DataFrame containing the actual vulnerabilities.
    """
    def __init__(self, predicted_df: pd.DataFrame, true_df: pd.DataFrame):
        self.predicted_df = predicted_df
        self.true_df = true_df

    def extract_cwe(self, description):
        """
        Extracts the CWE number from a Predicted Vulnerability Type.

        Parameters:
        - description (str): Predicted Vulnerability Type.

        Returns:
        - int: The extracted CWE number or None if no CWE is found.
        """
        match = re.search(r'(?i)cwe-?(\d+)|\b(\d+)\b', description)
        if match:
            cwe_number = match.group(1) if match.group(1) else match.group(2)
            return int(cwe_number)
        return None

    def evaluate_by_model(self, experiment_name:str, prompt_type:str):
        """
        Evaluates the performance of each model in the predicted_df DataFrame.

        Parameters:
        - experiment_name (str): The name of the experiment.
        - prompt_type (str): The type of prompt used for the LLM queries.

        Returns:
        - dict: A dictionary containing the evaluation results for each model.
        """
        results = {}
        for model in self.predicted_df['Model'].unique():
            model_df:pd.DataFrame = self.predicted_df[self.predicted_df['Model'] == model].copy()

            model_df['cwe'] = model_df['predicted_cwe'].apply(self.extract_cwe)
            merged_df = model_df.merge(self.true_df, left_index=True, right_index=True, suffixes=('_pred', '_true'))
            merged_df['cwe_from_pred_name'] = get_cwe_from_vuln_names(merged_df['predicted_vulnerability_name'])
            
            vuln_metrics = self.evaluate_vulnerability_binary(merged_df)

            if merged_df['cwe_pred'].notnull().any():
                merged_df['cwe_pred'] = merged_df['cwe_pred'].fillna(-1)
                type_metrics_cwe = self.evaluate_vulnerability_type_multiclass(merged_df['cwe_true'], merged_df['cwe_pred'])
            else:
                type_metrics_cwe = 'Not Evaluated'

            type_metrics_name = self.evaluate_vulnerability_type_multiclass(merged_df['cwe_true'], merged_df['cwe_from_pred_name'])

            results[model] = {
                'Vulnerability Metrics': vuln_metrics,
                'Vulnerability Type Metrics': {
                    'Using Predicted CWE': type_metrics_cwe,
                    'Using Predicted Vulnerability Name': type_metrics_name
                }
            }

            self.save_dataframe(merged_df, model, experiment_name, prompt_type)

        return results

    def evaluate_vulnerability_binary(self, df: pd.DataFrame):
        """
        Evaluates the binary classification performance for vulnerability detection.

        Parameters:
        - df (pd.DataFrame): DataFrame containing the predicted and true vulnerabilities.

        Returns:
        - dict: A dictionary containing various binary classification metrics.
        """
        y_true = df['real_vulnerability'].astype(bool)
        y_pred = df['predicted_vulnerability']

        tn, fp, fn, tp = confusion_matrix(y_true, y_pred).ravel()
        specificity= tn / (tn + fp)          # True Negative Rate
        negative_predictive_value = tn / (tn + fn)
        false_discovery_rate = fp / (tp + fp)
        false_positive_rate = fp / (fp + tn)
        false_negative_rate = fn / (tp + fn)

        metrics = {
            'Accuracy': accuracy_score(y_true, y_pred),
            'Precision': precision_score(y_true, y_pred, zero_division=0),  # Positive Predictive Value
            'Recall': recall_score(y_true, y_pred, zero_division=0),        # True Positive Rate | Sensitivity
            'F1-Score': f1_score(y_true, y_pred, zero_division=0),
            'ROC-AUC Score': roc_auc_score(y_true, y_pred),
            'Confusion Matrix': confusion_matrix(y_true, y_pred).tolist(),
            'FPR': false_positive_rate,
            'FNR': false_negative_rate,
            'Specificity': specificity,
            'NPV': negative_predictive_value,
            'FDR': false_discovery_rate,
            'MCC': matthews_corrcoef(y_true, y_pred),
        }

        return metrics

    def evaluate_vulnerability_type_multiclass(self, cwe_true, cwe_pred):
        """
        Evaluates the multi-class classification performance for vulnerability types.

        Parameters:
        - cwe_true (pd.Series): Series containing the true CWE labels.
        - cwe_pred (pd.Series): Series containing the predicted CWE labels.

        Returns:
        - dict: A dictionary containing various multi-class classification metrics.
        """
        cwe_labels = cwe_true.unique()
        precision_dict = {}
        recall_dict = {}
        accuracy_dict = {}
        f1_score_dict = {}

        for label in cwe_labels:
            true_binary = (cwe_true == label)
            predicted_binary = (cwe_pred == label)

            accuracy_dict[f"CWE-{label}"] = accuracy_score(true_binary, predicted_binary)
            precision_dict[f"CWE-{label}"] = precision_score(true_binary, predicted_binary, zero_division=0)
            recall_dict[f"CWE-{label}"] = recall_score(true_binary, predicted_binary, zero_division=0)
            f1_score_dict[f"CWE-{label}"] = f1_score(true_binary, predicted_binary, zero_division=0)


        # overall_accuracy = accuracy_score(cwe_true, cwe_pred)
        macro_precision = precision_score(cwe_true, cwe_pred, labels=cwe_labels, average='macro', zero_division=0)
        macro_recall = recall_score(cwe_true, cwe_pred, labels=cwe_labels, average='macro', zero_division=0)
        macro_f1 = f1_score(cwe_true, cwe_pred, labels=cwe_labels, average='macro', zero_division=0)
        weighted_precision = precision_score(cwe_true, cwe_pred, labels=cwe_labels, average='weighted', zero_division=0)
        weighted_recall = recall_score(cwe_true, cwe_pred, labels=cwe_labels, average='weighted', zero_division=0)
        weighted_f1 = f1_score(cwe_true, cwe_pred, labels=cwe_labels, average='weighted', zero_division=0)
        mcc = matthews_corrcoef(cwe_true, cwe_pred)
        kappa = cohen_kappa_score(cwe_true, cwe_pred, labels=cwe_labels)
        conf_matrix = confusion_matrix(cwe_true, cwe_pred, labels=cwe_labels).tolist()

        return {
            'Per Class Metrics': {
                'Accuracy': accuracy_dict,
                'Precision': precision_dict,
                'Recall': recall_dict,
                'F1-Score': f1_score_dict
            },
            # 'Overall Accuracy': overall_accuracy,
            'Macro Precision': macro_precision,
            'Macro Recall': macro_recall,
            'Macro F1-Score': macro_f1,
            'Weighted Precision': weighted_precision,
            'Weighted Recall': weighted_recall,
            'Weighted F1-Score': weighted_f1,
            'MCC': mcc,
            'Cohen Kappa': kappa,
            'Confusion Matrix': conf_matrix
        }

    def save_dataframe(self, df:pd.DataFrame, model:str, experiment_name:str, prompt_type:str):
        """
        Save the DataFrame to a CSV file.

        Parameters:
        - df (pd.DataFrame): The DataFrame to be saved.
        - model (str): The name of the model.
        - experiment_name (str): The name of the experiment.
        - prompt_type (str): The type of prompt used for the LLM queries.
        """
        columns = ['real_vulnerability', 'predicted_vulnerability', 'cwe_true', 'cwe_pred', 'cwe_from_pred_name']
        df.index.name = 'test_name'
        model = model.replace('/', '-')
        df.to_csv(f'Evaluation/{experiment_name}/eval_df_{prompt_type}-prompt_{model}.csv', index=True, columns=columns)