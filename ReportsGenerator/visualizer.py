import os
import pandas as pd
import plotly.express as px
import plotly.figure_factory as ff

class Visualizer:
    def __init__(self, model_data, cwe_list, output_dir):
        self.model_data = model_data
        self.cwe_list = cwe_list
        self.output_dir = output_dir

    def create_grouped_bar_plot(self, data, x, y, color, title, barmode='group', facet_row=None, height=400, filename=None):
        fig = px.bar(
            data,
            x=x,
            y=y,
            color=color,
            barmode=barmode,
            facet_row=facet_row,
            title=title,
            height=height
        )

        fig.update_layout(title=dict(font=dict(size=22)))

        # Save the figure as SVG if filename is provided
        if filename:
            filepath = os.path.join(self.output_dir, f"{filename}.svg")
            fig.write_image(filepath, width=1500, height=1000)
        return fig

    def create_binary_classification_plots(self, aggregated, display_option):
        metrics = ["F1-Score", "Precision", "Recall", "Accuracy"]
        data = []
        for metric in metrics:
            metric_data = self.prepare_metric_data(metric, "Vulnerability Metrics")
            data.append(metric_data)
        combined_data = pd.concat(data) if data else pd.DataFrame()

        figures = {}
        if not combined_data.empty:
            if aggregated:
                if display_option == "Individual Metrics":
                    # Display results with models as colors, x as prompt type, different figures based on dataset
                    for dataset_name, dataset_group in combined_data.groupby("Dataset"):
                        filename = f"binary_agg_individual_{dataset_name}"
                        fig = self.create_grouped_bar_plot(
                            dataset_group,
                            x="Prompt Type",
                            y="Value",
                            color="Model",
                            title=f"Metrics for Dataset: {dataset_name}",
                            barmode='group',
                            facet_row="Metric",
                            height=800,
                            filename=filename
                        )
                        figures[dataset_name] = fig
                else:  # display_option == "Aggregated Metrics"
                    for prompt_type_name, prompt_group in combined_data.groupby("Prompt Type"):
                        filename = f"binary_agg_aggregated_{prompt_type_name}"
                        fig = self.create_grouped_bar_plot(
                            prompt_group,
                            x="Metric",
                            y="Value",
                            color="Model",
                            title=f"Metrics for Prompt Type: {prompt_type_name}",
                            barmode='group',
                            facet_row="Dataset",
                            height=800,
                            filename=filename
                        )
                        figures[prompt_type_name] = fig
            else:
                if display_option == "Individual Metrics":
                    for metric in metrics:
                        metric_data = combined_data[combined_data["Metric"] == metric]
                        filename = f"binary_model_individual_{metric}"
                        fig = self.create_grouped_bar_plot(
                            metric_data,
                            x="Dataset",
                            y="Value",
                            color="Prompt Type",
                            title=f"{metric} by Dataset and Prompt Type",
                            height=400,
                            filename=filename
                        )
                        figures[metric] = fig
                else:  # display_option == "Aggregated Metrics"
                    for dataset_name, dataset_group in combined_data.groupby("Dataset"):
                        filename = f"binary_model_aggregated_{dataset_name}"
                        fig = self.create_grouped_bar_plot(
                            dataset_group,
                            x="Metric",
                            y="Value",
                            color="Prompt Type",
                            title=f"Metrics for Dataset: {dataset_name}",
                            barmode='group',
                            height=600,
                            filename=filename
                        )
                        figures[dataset_name] = fig
        else:
            pass  # No data available
        return figures

    def prepare_metric_data(self, metric_name, metrics_key):
        data = []
        for (model, prompt_type, exp_type), metrics in self.model_data.items():
            dataset = metrics.get("Dataset", "Unknown")
            value = metrics.get(metrics_key, {}).get(metric_name, 0)
            data.append({
                "Model": model,
                "Prompt Type": f"{prompt_type} ({exp_type})",
                "Dataset": dataset,
                "Metric": metric_name,
                "Value": value
            })
        return pd.DataFrame(data) if data else pd.DataFrame()

    def create_aggregated_multiclass_classification_plots(self):
        metrics_to_extract = ["Macro Precision", "Macro Recall", "Macro F1-Score",
                              "Weighted Precision", "Weighted Recall", "Weighted F1-Score"]

        data = []
        for (model, prompt_type, exp_type), metrics in self.model_data.items():
            vuln_metrics = metrics.get("Vulnerability Type Metrics", {}).get("Using Predicted CWE", {})
            dataset = metrics.get("Dataset", "Unknown")
            for metric in metrics_to_extract:
                value = vuln_metrics.get(metric, 0)
                data.append({
                    "Model": model,
                    "Prompt Type": f"{prompt_type} ({exp_type})",
                    "Dataset": dataset,
                    "Metric": metric,
                    "Value": value
                })

        combined_data = pd.DataFrame(data) if data else pd.DataFrame()

        figures = {}
        if not combined_data.empty:
            for prompt_type_name, prompt_group in combined_data.groupby("Prompt Type"):
                filename = f"multiclass_agg_aggregated_{prompt_type_name}"
                fig = self.create_grouped_bar_plot(
                    prompt_group,
                    x="Metric",
                    y="Value",
                    color="Model",
                    title=f"Aggregated Multiclass Metrics for Prompt Type: {prompt_type_name}",
                    barmode='group',
                    facet_row="Dataset",
                    height=800,
                    filename=filename
                )
                figures[prompt_type_name] = fig
        else:
            pass  # No data available

        return figures

    def create_multiclass_classification_plots_for_cwe(self, selected_cwe):
        data = self.prepare_cwe_data(selected_cwe)
        figures = {}
        if not data.empty:
            for dataset_name, dataset_group in data.groupby("Dataset"):
                filename = f"multiclass_agg_cwe_{selected_cwe}_{dataset_name}"
                fig = self.create_grouped_bar_plot(
                    dataset_group,
                    x="Prompt Type",
                    y="Value",
                    color="Model",
                    title=f"Metrics for CWE Category: {selected_cwe} in Dataset: {dataset_name}",
                    barmode='group',
                    facet_row="Metric",
                    height=800,
                    filename=filename
                )
                figures[dataset_name] = fig
        else:
            pass  # No data available
        return figures

    def create_multiclass_classification_plot(self, selected_cwe):
        data = self.prepare_cwe_data(selected_cwe)
        if not data.empty:
            filename = f"multiclass_model_cwe_{selected_cwe}"
            fig = self.create_grouped_bar_plot(
                data,
                x="Dataset",
                y="Value",
                color="Prompt Type",
                title=f"Metrics for CWE Category: {selected_cwe} Grouped by Prompt Type and Dataset",
                barmode='group',
                facet_row="Metric",
                height=800,
                filename=filename
            )
            return fig
        else:
            pass  # No data available
            return None

    def prepare_cwe_data(self, selected_cwe):
        metric_names = ["F1-Score", "Precision", "Recall", "Accuracy"]
        data = []
        for (model, prompt_type, exp_type), metrics in self.model_data.items():
            vuln_metrics = metrics.get("Vulnerability Type Metrics", {}).get("Using Predicted CWE", {})
            per_class_metrics = vuln_metrics.get("Per Class Metrics", {})
            for metric in metric_names:
                value = per_class_metrics.get(metric, {}).get(selected_cwe, 0)
                dataset = metrics.get("Dataset", "Unknown")
                data.append({
                    "Model": model,
                    "Prompt Type": f"{prompt_type} ({exp_type})",
                    "Dataset": dataset,
                    "Metric": metric,
                    "Value": value
                })
        return pd.DataFrame(data) if data else pd.DataFrame()

    def create_individual_model_aggregated_multiclass_plot(self):
        metrics_to_extract = ["Macro Precision", "Macro Recall", "Macro F1-Score",
                              "Weighted Precision", "Weighted Recall", "Weighted F1-Score"]

        data = []
        confusion_matrices = {}
        for (model, prompt_type, exp_type), metrics in self.model_data.items():
            vuln_metrics = metrics.get("Vulnerability Type Metrics", {}).get("Using Predicted CWE", {})
            dataset = metrics.get("Dataset", "Unknown")
            for metric in metrics_to_extract:
                value = vuln_metrics.get(metric, 0)
                data.append({
                    "Prompt Type": f"{prompt_type} ({exp_type})",
                    "Dataset": dataset,
                    "Metric": metric,
                    "Value": value
                })
            # Extract confusion matrix
            confusion_matrix = vuln_metrics.get("Confusion Matrix", [])
            confusion_matrices[f"{prompt_type} ({exp_type})"] = confusion_matrix

        combined_data = pd.DataFrame(data) if data else pd.DataFrame()

        if not combined_data.empty:
            filename = f"multiclass_model_aggregated"
            fig = self.create_grouped_bar_plot(
                combined_data,
                x="Metric",
                y="Value",
                color="Prompt Type",
                title="Aggregated Multiclass Classification Metrics",
                barmode='group',
                facet_row="Dataset",
                height=800,
                filename=filename
            )
        else:
            fig = None  # No data available

        # Create confusion matrix figures
        confusion_matrix_figs = {}
        for prompt_type, cm in confusion_matrices.items():
            if cm:
                filename = f"confusion_matrix_{prompt_type.replace(' ', '_')}"
                cm_fig = self.create_confusion_matrix_plot(prompt_type, cm, filename)
                confusion_matrix_figs[prompt_type] = cm_fig

        return fig, confusion_matrix_figs

    def create_confusion_matrix_plot(self, prompt_type, confusion_matrix, filename=None):
        z = confusion_matrix[::-1]
        x = self.cwe_list
        y = self.cwe_list[::-1]
        z_text = [[str(int(cell)) for cell in row] for row in z]
        fig = ff.create_annotated_heatmap(
            z=z,
            x=x,
            y=y,
            annotation_text=z_text,
            colorscale="Viridis",
            showscale=True,
            hoverinfo="z"
        )
        fig.update_layout(
            title=dict(text=f"Confusion Matrix for '{prompt_type}' prompt", font=dict(size=15)),
            xaxis_title="Predicted Label",
            yaxis_title="True Label"
        )

        # Save the figure as SVG if filename is provided
        if filename:
            filepath = os.path.join(self.output_dir, f"{filename}.svg")
            fig.write_image(filepath, width=1000, height=800)
        return fig
