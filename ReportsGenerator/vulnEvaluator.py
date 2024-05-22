import pandas as pd
import re
from sklearn.metrics import (
    accuracy_score, precision_score, recall_score, f1_score, roc_auc_score, 
    confusion_matrix, matthews_corrcoef, cohen_kappa_score
)

class VulnerabilityEvaluator:
    def __init__(self, predicted_df: pd.DataFrame, true_df: pd.DataFrame):
        self.predicted_df = predicted_df
        self.true_df = true_df

    def extract_cwe(self, description):
        match = re.search(r'(?i)cwe-?(\d+)|\b(\d+)\b', description)
        if match:
            cwe_number = match.group(1) if match.group(1) else match.group(2)
            return int(cwe_number)
        return None

    def evaluate_by_model(self):
        results = {}
        for model in self.predicted_df['Model'].unique():
            model_df = self.predicted_df[self.predicted_df['Model'] == model].copy()
            model_df['cwe'] = model_df['Predicted Vulnerability Type'].apply(self.extract_cwe)
            merged_df = model_df.merge(self.true_df, left_index=True, right_index=True, suffixes=('_pred', '_true'))
            merged_df['cwe_pred'] = merged_df['cwe_pred'].fillna(-1)
            
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
            # 'Confusion Matrix': confusion_matrix(y_true, y_pred).tolist(),
            'FPR': false_positive_rate,
            'FNR': false_negative_rate,
            'Specificity': specificity,
            'NPV': negative_predictive_value,
            'FDR': false_discovery_rate,
            'MCC': matthews_corrcoef(y_true, y_pred),
        }
        return metrics

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


        overall_accuracy = accuracy_score(cwe_true, cwe_pred)
        macro_precision = precision_score(cwe_true, cwe_pred, average='macro', zero_division=0)
        macro_recall = recall_score(cwe_true, cwe_pred, average='macro', zero_division=0)
        macro_f1 = f1_score(cwe_true, cwe_pred, average='macro', zero_division=0)
        weighted_precision = precision_score(cwe_true, cwe_pred, average='weighted', zero_division=0)
        weighted_recall = recall_score(cwe_true, cwe_pred, average='weighted', zero_division=0)
        weighted_f1 = f1_score(cwe_true, cwe_pred, average='weighted', zero_division=0)
        mcc = matthews_corrcoef(cwe_true, cwe_pred)
        kappa = cohen_kappa_score(cwe_true, cwe_pred)
        # conf_matrix = confusion_matrix(cwe_true, cwe_pred).tolist()

        return {
            'Per Class Metrics': {
                'Accuracy': accuracy_dict,
                'Precision': precision_dict,
                'Recall': recall_dict,
                'F1-Score': f1_score_dict
            },
            'Overall Accuracy': overall_accuracy,
            'Macro Precision': macro_precision,
            'Macro Recall': macro_recall,
            'Macro F1-Score': macro_f1,
            'Weighted Precision': weighted_precision,
            'Weighted Recall': weighted_recall,
            'Weighted F1-Score': weighted_f1,
            'MCC': mcc,
            'Cohen Kappa': kappa,
            # 'Confusion Matrix': conf_matrix
        }
