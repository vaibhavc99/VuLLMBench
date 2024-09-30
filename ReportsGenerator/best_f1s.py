import pandas as pd
import json
from visualizer import Visualizer

class BestF1s(Visualizer):
    
    def get_best_f1_score_per_model_for_binary(self):
        best_f1_per_model = {}

        # Loop through the model data to extract the best F1 scores for each model, programs type, and dataset
        for (model, prompt_type, dataset, programs_type), metrics in self.model_data.items():
            vuln_metrics = metrics.get("Vulnerability Metrics", {})
            f1_score = vuln_metrics.get("F1-Score", 0)

            key = (model, programs_type, dataset)

            # Check if the current F1 score is the highest encountered so far for this model, programs type, and dataset
            if key not in best_f1_per_model or f1_score > best_f1_per_model[key]["F1-Score"]:
                best_f1_per_model[key] = {
                    "Model": model,
                    "Dataset": dataset,
                    "Programs Type": programs_type,
                    "Benchmark Programs (Type)": f"{dataset} ({programs_type})",
                    "Prompt Type": prompt_type,
                    "F1-Score": f1_score
                }

        # Convert the dictionary to a DataFrame
        best_f1_df = pd.DataFrame(list(best_f1_per_model.values()))
        best_f1_df = best_f1_df.sort_values(by="F1-Score", ascending=False)

        fig = self.create_grouped_bar_plot(
            best_f1_df,
            x="Model",
            y="F1-Score",
            color="Benchmark Programs (Type)",
            barmode='group',
            height=400,
            filename="best_f1_scores_per_model_binary_classification"
        )

        best_f1_df.to_csv(f"{self.output_dir}/best_f1_scores_per_model_binary_classification.csv", index=False)
        return fig

    def get_best_f1_score_per_model_for_multiclass(self):
        best_f1_per_model = {}
        best_per_class_f1_per_model = {}

        # Collect the best Macro and Weighted F1 scores for each model, programs type, and dataset
        for (model, prompt_type, dataset, programs_type), metrics in self.model_data.items():
            vuln_metrics = metrics.get("Vulnerability Type Metrics", {}).get("Using Predicted CWE", {})
            macro_f1_score = vuln_metrics.get("Macro F1-Score", 0)
            weighted_f1_score = vuln_metrics.get("Weighted F1-Score", 0)

            key = (model, programs_type, dataset)

            # Initialize the best scores and handle ties
            if key not in best_f1_per_model:
                best_f1_per_model[key] = []
            
            current_best_macro_f1 = max([entry["Macro F1-Score"] for entry in best_f1_per_model[key]], default=0)
            
            if macro_f1_score > current_best_macro_f1:
                # New best Macro F1 score found, reset the list
                best_f1_per_model[key] = [{
                    "Model": model,
                    "Dataset": dataset,
                    "Programs Type": programs_type,
                    "Benchmark Programs (Type)": f"{dataset} ({programs_type})",
                    "Prompt Type": prompt_type,
                    "Macro F1-Score": macro_f1_score,
                    "Weighted F1-Score": weighted_f1_score
                }]
            elif macro_f1_score == current_best_macro_f1:
                # Tie found, add to the list
                best_f1_per_model[key].append({
                    "Model": model,
                    "Dataset": dataset,
                    "Programs Type": programs_type,
                    "Benchmark Programs (Type)": f"{dataset} ({programs_type})",
                    "Prompt Type": prompt_type,
                    "Macro F1-Score": macro_f1_score,
                    "Weighted F1-Score": weighted_f1_score
                })
            # Else, do nothing (current score is lower)

        # Flatten the best_f1_per_model dictionary into a list
        flattened_best_f1_per_model = []
        for entries in best_f1_per_model.values():
            flattened_best_f1_per_model.extend(entries)

        # Collect the best per-class F1 scores for each CWE, including ties
        for (model, prompt_type, dataset, programs_type), metrics in self.model_data.items():
            key = (model, programs_type, dataset)
            if key not in best_f1_per_model:
                continue

            # Check if this prompt type is among the best ones
            best_entries = best_f1_per_model[key]
            if not any(entry["Prompt Type"] == prompt_type and entry["Model"] == model for entry in best_entries):
                continue

            vuln_metrics = metrics.get("Vulnerability Type Metrics", {}).get("Using Predicted CWE", {})
            per_class_metrics = vuln_metrics.get("Per Class Metrics", {}).get("F1-Score", {})

            for cwe, f1_score in per_class_metrics.items():
                cwe_key = (cwe, programs_type, dataset)
                if cwe_key not in best_per_class_f1_per_model:
                    best_per_class_f1_per_model[cwe_key] = {
                        "CWE": cwe,
                        "Programs Type": programs_type,
                        "Dataset": dataset,
                        "Benchmark Programs (Type)": f"{dataset} ({programs_type})",
                        "Best F1-Score": f1_score,
                        "Models": [model],
                        "Prompt Types": [prompt_type]
                    }
                else:
                    existing_best_f1 = best_per_class_f1_per_model[cwe_key]["Best F1-Score"]
                    if f1_score > existing_best_f1:
                        # New best F1 score, replace the entry
                        best_per_class_f1_per_model[cwe_key]["Best F1-Score"] = f1_score
                        best_per_class_f1_per_model[cwe_key]["Models"] = [model]
                        best_per_class_f1_per_model[cwe_key]["Prompt Types"] = [prompt_type]
                    elif f1_score == existing_best_f1:
                        # Tie, append the model
                        best_per_class_f1_per_model[cwe_key]["Models"].append(model)
                        best_per_class_f1_per_model[cwe_key]["Prompt Types"].append(prompt_type)
                    # Else, do nothing (current F1 score is lower)

        # Prepare DataFrames for plotting
        best_f1_df = pd.DataFrame(flattened_best_f1_per_model)
        best_f1_df = best_f1_df.sort_values(by=["Benchmark Programs (Type)", "Macro F1-Score"], ascending=[False, False])

        # Melt the DataFrame to include both Macro and Weighted F1 scores
        best_f1_df_melted = best_f1_df.melt(
            id_vars=["Model", "Benchmark Programs (Type)", "Programs Type", "Prompt Type"],
            value_vars=["Macro F1-Score", "Weighted F1-Score"],
            var_name="Metric Type",
            value_name="F1-Score"
        )

        # Plot both Macro and Weighted F1 scores
        fig_macro_weighted_f1 = self.create_grouped_bar_plot(
            best_f1_df_melted,
            x="Model",
            y="F1-Score",
            color="Benchmark Programs (Type)",
            barmode='group',
            facet_row="Metric Type",
            height=600,
            filename="best_f1_scores_per_model_multiclass_classification"
        )

        # Prepare per-class F1 score DataFrame
        flattened_per_class_data = []
        for cwe_key, cwe_data in best_per_class_f1_per_model.items():
            for i, model in enumerate(cwe_data["Models"]):
                flattened_per_class_data.append({
                    "CWE": cwe_data["CWE"],
                    "Model": model,
                    "Benchmark Programs (Type)": cwe_data["Benchmark Programs (Type)"],
                    "Programs Type": cwe_data["Programs Type"],
                    "Best F1-Score": cwe_data["Best F1-Score"],
                    "Prompt Type": cwe_data["Prompt Types"][i]
                })

        best_per_class_f1_df = pd.DataFrame(flattened_per_class_data)
        best_per_class_f1_df = best_per_class_f1_df.sort_values(by=["Benchmark Programs (Type)", "CWE", "Best F1-Score"], ascending=[False, True, False])

        # Plot per-class best F1 scores, including all models with the best score
        fig_per_class_f1 = self.create_grouped_bar_plot(
            best_per_class_f1_df,
            x="CWE",
            y="Best F1-Score",
            color="Model",
            barmode='group',
            facet_row="Benchmark Programs (Type)",
            height=800,
            filename="best_per_class_f1_scores_per_model_multiclass_classification"
        )

        # Save DataFrames to CSV
        best_f1_df.to_csv(f"{self.output_dir}/best_f1_scores_per_model_multiclass_classification.csv", index=False)
        best_per_class_f1_df.to_csv(f"{self.output_dir}/best_per_class_f1_scores_per_model_multiclass_classification.csv", index=False)

        return fig_macro_weighted_f1, fig_per_class_f1


    def get_best_metrics_per_model_for_binary(self):
        best_metrics_per_model = []

        metrics_list = ["Precision", "Recall", "Accuracy"]  # Metrics to track along with F1-Score

        best_f1_per_model = {}

        # Loop through all combinations of model, prompt type, dataset, and programs type
        for (model, prompt_type, dataset, programs_type), metrics in self.model_data.items():
            vuln_metrics = metrics.get("Vulnerability Metrics", {})
            f1_score = vuln_metrics.get("F1-Score", 0)  # Get the F1 score for this model

            key = (model, programs_type, dataset)

            # Check if this F1-Score is the best for the current model, programs type, and dataset
            if key not in best_f1_per_model or f1_score > best_f1_per_model[key]["F1-Score"]:
                # If the F1 score is the best, update the best scores and associated metrics
                best_f1_per_model[key] = {
                    "Model": model,
                    "Dataset": dataset,
                    "Programs Type": programs_type,
                    "Benchmark Programs (Type)": f"{dataset} ({programs_type})",
                    "F1-Score": f1_score,
                    "Precision": vuln_metrics.get("Precision", 0),
                    "Recall": vuln_metrics.get("Recall", 0),
                    "Accuracy": vuln_metrics.get("Accuracy", 0),
                    "Prompt Type": prompt_type
                }

        # Convert the best F1-Score and associated metrics for each model, programs type, and dataset into a DataFrame
        for key, metrics in best_f1_per_model.items():
            # Append the best F1-Score and its associated metrics (Precision, Recall, Accuracy)
            best_metrics_per_model.append({
                "Model": metrics["Model"],
                "Benchmark Programs (Type)": metrics["Benchmark Programs (Type)"],
                "Metric": "F1-Score",
                "Value": metrics["F1-Score"]
            })
            for metric in metrics_list:
                best_metrics_per_model.append({
                    "Model": metrics["Model"],
                    "Benchmark Programs (Type)": metrics["Benchmark Programs (Type)"],
                    "Metric": metric,
                    "Value": metrics[metric]  # The best Precision, Recall, or Accuracy associated with the best F1
                })


        best_metrics_df = pd.DataFrame(best_metrics_per_model)
        best_metrics_df = best_metrics_df.sort_values(by=["Metric", "Value"], ascending=False)
        best_metrics_df.to_csv(f"{self.output_dir}/best_metrics_per_model_binary_classification.csv", index=False)

        # Create a grouped bar plot with 'Metric' on the x-axis and 'Value' on the y-axis
        fig = self.create_grouped_bar_plot(
            best_metrics_df,
            x="Metric", 
            y="Value",
            color="Model",
            barmode='group',
            facet_row="Benchmark Programs (Type)",
            height=400,
            filename="best_metrics_per_model_binary_classification"
        )

        return fig

    def get_f1_per_prompt_and_model_binary(self):
        f1_scores = []
        for (model, prompt_type, dataset, programs_type), metrics in self.model_data.items():
            vuln_metrics = metrics.get("Vulnerability Metrics", {})

            f1_score = vuln_metrics.get("F1-Score", 0)

            f1_scores.append({
                "Model": model,
                "Dataset": dataset,
                "Programs Type": programs_type,
                "Benchmark Programs (Type)": f"{dataset} ({programs_type})",
                "Prompt Type": prompt_type,
                "F1-Score": f1_score
            })

        f1_df = pd.DataFrame(f1_scores)
        f1_df = f1_df.sort_values(by="F1-Score", ascending=False)
        f1_df.to_csv(f"{self.output_dir}/promptwise_f1_scores_binary_classification.csv", index=False)

        fig = self.create_grouped_bar_plot(
            f1_df,
            x="Prompt Type",
            y="F1-Score",
            color="Model",
            pattern_shape="Model",
            facet_row="Benchmark Programs (Type)",
            barmode='group',
            height=400,
            filename="promptwise_f1_scores_binary_classification"
        )

        # Create separate plots for each prompt type
        figures = {}
        prompt_types = f1_df["Prompt Type"].unique()

        for prompt_type in prompt_types:
            filtered_df = f1_df[f1_df["Prompt Type"] == prompt_type]

            fig = self.create_grouped_bar_plot(
                filtered_df,
                x="Model",
                y="F1-Score",
                color="Benchmark Programs (Type)",
                barmode='group',
                height=400,
                filename=f"f1_scores_binary_classification_{prompt_type.replace(' ', '_')}"
            )

            figures[prompt_type] = fig

        return fig, figures

    def get_f1_per_prompt_and_model_multiclass(self):
        f1_scores = []

        for (model, prompt_type, dataset, programs_type), metrics in self.model_data.items():
            vuln_metrics = metrics.get("Vulnerability Type Metrics", {}).get("Using Predicted CWE", {})

            macro_f1_score = vuln_metrics.get("Macro F1-Score", 0)
            weighted_f1_score = vuln_metrics.get("Weighted F1-Score", 0)

            f1_scores.append({
                "Model": model,
                "Dataset": dataset,
                "Programs Type": programs_type,
                "Benchmark Programs (Type)": f"{dataset} ({programs_type})",
                "Prompt Type": prompt_type,
                "Macro F1-Score": macro_f1_score,
                "Weighted F1-Score": weighted_f1_score
            })

        f1_df = pd.DataFrame(f1_scores)
        f1_df = f1_df.sort_values(by=["Macro F1-Score", "Weighted F1-Score"], ascending=[False, False])
        f1_df.to_csv(f"{self.output_dir}/promptwise_f1_scores_multiclass_classification.csv", index=False)

        fig = self.create_grouped_bar_plot(
            f1_df,
            x="Prompt Type",
            y="Macro F1-Score",
            color="Model",
            pattern_shape="Model",
            facet_row="Benchmark Programs (Type)",
            barmode='group',
            height=400,
            filename="promptwise_macro_f1_scores_multiclass_classification"
        )

        f1_df_melted = f1_df.melt(
            id_vars=["Model", "Benchmark Programs (Type)", "Programs Type", "Prompt Type"],
            value_vars=["Macro F1-Score", "Weighted F1-Score"],
            var_name="Metric Type",
            value_name="F1-Score"
        )

        figures = {}
        prompt_types = f1_df_melted["Prompt Type"].unique()

        for prompt_type in prompt_types:
            filtered_df = f1_df_melted[f1_df_melted["Prompt Type"] == prompt_type]

            fig = self.create_grouped_bar_plot(
                filtered_df,
                x="Model",
                y="F1-Score",
                color="Benchmark Programs (Type)",
                barmode='group',
                facet_row="Metric Type",  # Split by Macro and Weighted F1 Score
                height=600,
                filename=f"f1_scores_multiclass_classification_{prompt_type.replace(' ', '_')}"
            )

            figures[prompt_type] = fig

        return figures
    

    def get_f1_per_prompt_and_self_reflection(self, task_type='binary'):
        f1_scores = []

        if task_type == 'binary':
            f1_key = "Vulnerability Metrics"
            f1_subkey = "F1-Score"
        else:  # Multiclass case
            f1_key = "Vulnerability Type Metrics"
            f1_subkey = "Macro F1-Score"

        for (model, prompt_type, dataset, programs_type), metrics in self.model_data.items():
            if task_type == 'multiclass':
                vuln_metrics = metrics.get(f1_key, {}).get("Using Predicted CWE", {})
            else:
                vuln_metrics = metrics.get(f1_key, {})

            f1_score = vuln_metrics.get(f1_subkey, 0)

            if "_self_reflection" in prompt_type:
                main_prompt_type = prompt_type.replace("_self_reflection", "")
                reflection_flag = True
            else:
                main_prompt_type = prompt_type
                reflection_flag = False

            f1_scores.append({
                "Model": model,
                "Dataset": dataset,
                "Programs Type": programs_type,
                "Benchmark Programs (Type)": f"{dataset} ({programs_type})",
                "Main Prompt Type": main_prompt_type,
                "Prompt Type": prompt_type,
                "Reflection": reflection_flag,
                f1_subkey: f1_score
            })

        f1_df = pd.DataFrame(f1_scores)
        f1_df = f1_df.sort_values(by="Main Prompt Type", ascending=False)

        x_order = ['llama3.1:70b', 'gpt-4o', 'gemma2:27b', 'qwen2:72b', 'mistral-nemo:12b']
        
        f1_df['Model'] = pd.Categorical(f1_df['Model'], categories=x_order, ordered=True)
        f1_df = f1_df.sort_values(by='Model')

        task_csv_filename = f"promptwise_f1_scores_{task_type}_classification_self_reflection.csv"
        f1_df.to_csv(f"{self.output_dir}/{task_csv_filename}", index=False)

        fig = self.create_grouped_bar_plot(
            f1_df,
            x="Model",
            y=f1_subkey,
            color="Main Prompt Type",
            pattern_shape="Reflection",
            facet_row="Benchmark Programs (Type)",
            barmode='group',
            height=400,
            filename=f"f1_scores_{task_type}_classification_with_self_reflection"
        )

        return fig

    def plot_overall_sast_vs_top5_llms(self):
        with open("ReportsGenerator/owasp-sast-results/sast_results.json", "r") as file:
            sast_data = json.load(file)

        overall_scores = []
        # Gather overall scores from SAST tools
        for tool, tool_data in sast_data.items():
            overall_f1_score = tool_data.get('Overall F1-Score', 0)
            overall_bench_score = tool_data.get('Overall Youden Index', 0)
            overall_fpr = tool_data.get('Overall FPR', 0)
            overall_scores.append({
                "Type": "SAST",
                "Tool/Model": tool,
                "F1-Score": overall_f1_score,
                "Bench-Score": overall_bench_score,
                "FPR": overall_fpr,
                "Prompt Type": "N/A"
            })

        # Gather overall scores from LLMs
        best_f1_per_model = {}
        for (model, prompt_type, dataset, programs_type), metrics in self.model_data.items():
            vuln_metrics = metrics.get("Vulnerability Metrics", {})
            f1_score = vuln_metrics.get("F1-Score", 0)
            bench_score = vuln_metrics.get("Youden Index", 0)
            fpr = vuln_metrics.get("FPR", 0)

            key = (model, programs_type, dataset)
            if key not in best_f1_per_model or f1_score > best_f1_per_model[key]["F1-Score"]:
                best_f1_per_model[key] = {
                    "Type": "LLM",
                    "Tool/Model": model,
                    "Prompt Type": prompt_type,
                    "F1-Score": f1_score,
                    "Bench-Score": bench_score,
                    "FPR": fpr
                }

        llm_scores = pd.DataFrame(list(best_f1_per_model.values()))
        overall_scores_df = pd.DataFrame(overall_scores)
        scores_df = pd.concat([overall_scores_df, llm_scores])

        top_llms_df = scores_df[scores_df['Type'] == 'LLM'].sort_values(by="F1-Score", ascending=False).head(5)
        top_sast_df = scores_df[scores_df['Type'] == 'SAST'].sort_values(by="F1-Score", ascending=False).head(5)
        combined_df = pd.concat([top_sast_df, top_llms_df])
        combined_df.to_csv(f"{self.output_dir}/sast_vs_llms_metrics.csv", index=False)

        melted_df = combined_df.melt(
            id_vars=["Tool/Model", "Type", "Prompt Type"],
            value_vars=["F1-Score", "Bench-Score"],
            var_name="Metric",
            value_name="Score"
        )

        fig = self.create_grouped_bar_plot(
            melted_df,
            x="Tool/Model",
            y="Score",
            color="Metric",
            barmode="group",
            height=400,
            filename="sast_vs_top5_llms"
        )

        return fig

    def plot_sast_vs_top5_llms_per_cwe(self):
        with open("ReportsGenerator/owasp-sast-results/sast_results.json", "r") as file:
            sast_data = json.load(file)
        with open("ReportsGenerator/per_cwe_metrics.json", "r") as file:
            llm_data = json.load(file)
        
        per_cwe_scores = []

        # Gather per-CWE scores for SAST tools
        for tool, tool_data in sast_data.items():
            for cwe, cwe_metrics in tool_data["Results"].items():
                f1_score = cwe_metrics.get('F1-Score', 0)
                bench_score = cwe_metrics.get('Youden Index', 0)
                fpr = cwe_metrics.get('FPR', 0)
                per_cwe_scores.append({
                    "Type": "SAST",
                    "Tool/Model": tool,
                    "CWE": cwe,
                    "F1-Score": f1_score,
                    "Bench-Score": bench_score,
                    "FPR": fpr
                })

        # Gather per-CWE scores for LLMs
        for model, prompt_data in llm_data.items():
            for prompt_type, cwe_data in prompt_data.items():
                for cwe, cwe_metrics in cwe_data.items():
                    f1_score = cwe_metrics.get('F1-Score', 0)
                    bench_score = cwe_metrics.get('Youden Index', 0)
                    fpr = cwe_metrics.get('FPR', 0)
                    per_cwe_scores.append({
                        "Type": "LLM",
                        "Tool/Model": model,
                        "CWE": cwe,
                        "F1-Score": f1_score,
                        "Bench-Score": bench_score,
                        "FPR": fpr
                    })

        cwe_df = pd.DataFrame(per_cwe_scores)
        cwe_df.to_csv(f"{self.output_dir}/sast_vs_llms_per_cwe_metrics.csv", index=False)

        melted_cwe_df = cwe_df.melt(
            id_vars=["Tool/Model", "Type", "CWE"],
            value_vars=["F1-Score", "Bench-Score"],
            var_name="Metric",
            value_name="Score"
        )

        fig = self.create_grouped_bar_plot(
            melted_cwe_df,
            x="CWE",
            y="Score",
            color="Tool/Model",
            facet_row="Metric",
            barmode="group",
            height=400,
            filename="sast_vs_top5llms_per_cwe"
        )

        return fig
