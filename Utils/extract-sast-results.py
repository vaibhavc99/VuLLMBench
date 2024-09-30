import os
from bs4 import BeautifulSoup
import pandas as pd
import json

def extract_data_from_html(html_content):
    soup = BeautifulSoup(html_content, 'html.parser')

    title_text = soup.title.string if soup.title else "Unknown Tool"
    tool_name = title_text.split('Scorecard for ')[-1].split(' (SAST)')[0]

    detailed_results_header = soup.find('h2', string="Detailed Results")
    if not detailed_results_header:
        raise ValueError("The 'Detailed Results' section was not found in the HTML.")

    table = detailed_results_header.find_next('table', class_='table')

    rows = table.find_all('tr')
    data = []

    for row in rows:
        cols = row.find_all(['th', 'td'])
        cols = [ele.text.strip() for ele in cols]
        if cols:
            data.append(cols)

    columns = data[0]
    table_data = pd.DataFrame(data[1:], columns=columns)
    
    table_data.columns = table_data.columns.str.strip().str.lower()
    
    required_columns = ['tp', 'tn', 'fp', 'fn', 'total', 'score', 'cwe #']
    if not all(col in table_data.columns for col in required_columns):
        raise KeyError(f"Missing one or more required columns: {required_columns}")

    table_data = table_data.apply(pd.to_numeric, errors='coerce')

    table_data['accuracy'] = (table_data['tp'] + table_data['tn']) / table_data['total']
    table_data['precision'] = table_data['tp'] / (table_data['tp'] + table_data['fp'])
    table_data['recall'] = table_data['tp'] / (table_data['tp'] + table_data['fn'])
    table_data['f1-score'] = 2 * (table_data['precision'] * table_data['recall']) / (table_data['precision'] + table_data['recall'])

    def safe_division(numerator, denominator):
        return numerator / denominator if denominator != 0 else 0

    table_data['fpr'] = table_data.apply(lambda row: safe_division(row['fp'], row['fp'] + row['tn']), axis=1)
    table_data['youden_index'] = table_data['recall'] - table_data['fpr']

    overall_accuracy = table_data["accuracy"].mean()
    overall_f1_score = table_data["f1-score"].mean()
    overall_youden_index = table_data["youden_index"].mean()
    overall_fpr = table_data["fpr"].mean()

    results = {}
    for index, row in table_data.iterrows():
        if pd.isna(row['cwe #']):
            print(f"Skipping row {index} due to missing CWE #")
            continue
        
        try:
            cwe = f"CWE-{int(row['cwe #'])}"
        except ValueError:
            print(f"Invalid CWE # value in row {index}: {row['cwe #']}")
            continue
        
        results[cwe] = {
            "Accuracy": row["accuracy"],
            "Precision": row["precision"],
            "Recall": row["recall"],
            "F1-Score": row["f1-score"],
            "Youden Index": row["youden_index"],
            "FPR": row["fpr"],
            "TP": row["tp"],
            "TN": row["tn"],
            "FP": row["fp"],
            "FN": row["fn"]
        }

    return {tool_name: {"Results": results, "Overall Accuracy": overall_accuracy, "Overall F1-Score": overall_f1_score, "Overall Youden Index": overall_youden_index, "Overall FPR": overall_fpr}}


def process_html_files(directory):
    results = {}

    html_files = html_files = [f for f in os.listdir(directory) if f.endswith('.html')]
    
    for html_file in html_files:
        with open(os.path.join(directory, html_file), 'r', encoding='utf-8') as file:
            html_content = file.read()
            tool_results = extract_data_from_html(html_content)
            results.update(tool_results)
    
    return results


directory = './'
final_results = process_html_files(directory)
output_file = 'sast_results.json'
with open(output_file, 'w', encoding='utf-8') as f:
        json.dump(final_results, f, indent=4)
