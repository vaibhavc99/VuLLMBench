import os
import json
import pandas as pd
import streamlit as st
import plotly.express as px
import plotly.figure_factory as ff
import psutil

class StreamlitHandler:
    def __init__(self, data_files):
        self.data_files = data_files
        self.data = self.load_and_aggregate_data(data_files)
        self.model_list = set(model for model, _ in self.data.keys())
        self.cwe_list = self.get_cwe_list()

    def get_cwe_list(self):
        cwe_set = set()
        for model_data in self.data.values():
            if "Vulnerability Type Metrics" in model_data:
                cwe_keys = model_data["Vulnerability Type Metrics"]["Using Predicted CWE"]["Per Class Metrics"]["Accuracy"].keys()
                cwe_set.update(cwe_keys)
        return list(cwe_set)

    @staticmethod
    def load_data(file):
        with open(file, 'r') as f:
            return json.load(f)

    def load_and_aggregate_data(self, files):
        aggregated_data = {}
        for file in files:
            data = self.load_data(file)
            for model, model_results in data.items():
                id = (model, model_results.get("Prompt Type"))
                if id not in aggregated_data:
                    aggregated_data[id] = model_results
                else:
                    # Merge or update existing entries if necessary
                    aggregated_data[id].update(model_results)

        return aggregated_data

    def render_sidebar(self):
        st.sidebar.title("Model Selection")
        selected_model = st.sidebar.selectbox("Choose a model", sorted(self.model_list))

        st.sidebar.title("Classification Type")
        classification_type = st.sidebar.radio("Choose Classification Type", ("Binary Classification", "Multiclass Classification"))

        selected_cwe = None
        if classification_type == "Multiclass Classification" and self.cwe_list:
            selected_cwe = st.sidebar.selectbox("Choose a CWE Category (default - 'Aggregated Metrics'", ["Aggregated Metrics"] + sorted(self.cwe_list))

        return selected_model, classification_type, selected_cwe

    def run(self):
        st.set_page_config(
            page_title="VuLLMBench Dashboard",
            page_icon="📊",
            initial_sidebar_state="expanded",
        )

        selected_model, classification_type, selected_cwe = self.render_sidebar()

        st.title("VuLLMBench Dashboard")

        model_data = {key: value for key, value in self.data.items() if key[0] == selected_model}

        # Depending on the classification type, create the appropriate plots
        graph_creator = GraphCreator(model_data, self.cwe_list)

        if classification_type == "Binary Classification":
            st.header("Binary Classification Metrics")
            figures = graph_creator.create_binary_classification_plots()
            
            for metric, fig in figures.items():
                st.subheader(f"{metric}")
                st.plotly_chart(fig)

        elif classification_type == "Multiclass Classification" and selected_cwe:
            st.header("Multiclass Classification Metrics")
            if selected_cwe == "Aggregated Metrics":
                fig, confusion_matrix_figs = graph_creator.create_aggregated_multiclass_classification_plot()
                st.plotly_chart(fig)
                if confusion_matrix_figs:
                    for cm_fig in confusion_matrix_figs:
                        st.plotly_chart(cm_fig)
            else:
                fig = graph_creator.create_multiclass_classification_plot(selected_cwe)
                st.plotly_chart(fig)
        elif classification_type == "Multiclass Classification" and not self.cwe_list:
            st.error("No 'Vulnerability Type Metrics' found in the data.")

        exit_app = st.sidebar.button("Shut Down")
        if exit_app:
            pid = os.getpid()
            p = psutil.Process(pid)
            p.terminate()


class GraphCreator:
    def __init__(self, model_data, cwe_list):
        self.model_data = model_data
        self.cwe_list = cwe_list

    def create_grouped_bar_plot(self, metric_name):
        # Prepare data for plotting
        data = []

        for (model, prompt_type), metrics in self.model_data.items():
            if "Vulnerability Metrics" in metrics:
                data.append({
                    "Prompt Type": prompt_type,
                    "Dataset": metrics.get("Dataset", "Unknown"),
                    "Value": metrics["Vulnerability Metrics"].get(metric_name, 0)
                })

        if not data:
            st.warning(f"No data available for the metric '{metric_name}'.")
            return None

        df = pd.DataFrame(data)

        # Plot grouped bar chart using Plotly
        fig = px.bar(
            df,
            x="Dataset",
            y="Value",
            color="Prompt Type",
            barmode="group",
            title=f"{metric_name} by Dataset and Prompt Type",
            labels={"Value": metric_name, "Dataset": "Dataset", "Prompt Type": "Prompt Type"},
            height=400
        )
        return fig

    def create_binary_classification_plots(self):
        # Create plots for each metric
        metrics = ["Accuracy", "Precision", "Recall", "F1-Score"]
        figures = {}
        for metric in metrics:
            fig = self.create_grouped_bar_plot(metric)
            if fig:
                figures[metric] = fig
        return figures

    def create_aggregated_multiclass_classification_plot(self):
        # Extract aggregated metrics from the data
        aggregated_metrics = []
        confusion_matrix_dict = {}

        for (model, prompt_type), metrics in self.model_data.items():
            if "Vulnerability Type Metrics" in metrics:
                vulnerability_metrics = metrics["Vulnerability Type Metrics"]["Using Predicted CWE"]
                aggregated_metrics.append({
                    "Metric": "Macro Precision",
                    "Value": vulnerability_metrics.get("Macro Precision", 0),
                    "Prompt Type": prompt_type
                })
                aggregated_metrics.append({
                    "Metric": "Macro Recall",
                    "Value": vulnerability_metrics.get("Macro Recall", 0),
                    "Prompt Type": prompt_type
                })
                aggregated_metrics.append({
                    "Metric": "Macro F1-Score",
                    "Value": vulnerability_metrics.get("Macro F1-Score", 0),
                    "Prompt Type": prompt_type
                })
                aggregated_metrics.append({
                    "Metric": "Weighted Precision",
                    "Value": vulnerability_metrics.get("Weighted Precision", 0),
                    "Prompt Type": prompt_type
                })
                aggregated_metrics.append({
                    "Metric": "Weighted Recall",
                    "Value": vulnerability_metrics.get("Weighted Recall", 0),
                    "Prompt Type": prompt_type
                })
                aggregated_metrics.append({
                    "Metric": "Weighted F1-Score",
                    "Value": vulnerability_metrics.get("Weighted F1-Score", 0),
                    "Prompt Type": prompt_type
                })
                aggregated_metrics.append({
                    "Metric": "MCC",
                    "Value": vulnerability_metrics.get("MCC", 0),
                    "Prompt Type": prompt_type
                })
                aggregated_metrics.append({
                    "Metric": "Cohen Kappa",
                    "Value": vulnerability_metrics.get("Cohen Kappa", 0),
                    "Prompt Type": prompt_type
                })
                confusion_matrix_dict[prompt_type] = vulnerability_metrics.get("Confusion Matrix", [])

        # Check if we have the aggregated metrics
        if not aggregated_metrics:
            st.warning("No aggregated metrics found in the data.")
            return None, None

        # Create a dataframe for aggregated metrics
        df = pd.DataFrame(aggregated_metrics)

        # Plot grouped bar chart for aggregated metrics using Plotly
        fig = px.bar(
            df,
            x="Metric",
            y="Value",
            color="Prompt Type",
            barmode="group",
            title="Aggregated Multiclass Classification Metrics",
            height=400
        )

        # Create confusion matrix plots for each prompt type
        confusion_matrix_figs = []
        for prompt_type, confusion_matrix in confusion_matrix_dict.items():
            if confusion_matrix:
                cm_fig = ff.create_annotated_heatmap(
                    z=confusion_matrix,
                    x=[f"Class {i}" for i in range(len(confusion_matrix))],
                    y=[f"Class {i}" for i in range(len(confusion_matrix))],
                    colorscale="Viridis",
                    showscale=True
                )
                cm_fig.update_layout(title=f"Confusion Matrix for {prompt_type}")
                confusion_matrix_figs.append(cm_fig)

        return fig, confusion_matrix_figs

    def create_multiclass_classification_plot(self, selected_cwe):
        # Prepare data for plotting
        data = []
        metric_names = ["Accuracy", "Precision", "Recall", "F1-Score"]

        for (model, prompt_type), metrics in self.model_data.items():
            if "Vulnerability Type Metrics" in metrics:
                cwe_metrics = metrics["Vulnerability Type Metrics"]["Using Predicted CWE"]["Per Class Metrics"]
                for metric in metric_names:
                    if selected_cwe in cwe_metrics[metric]:
                        data.append({
                            "Prompt Type": prompt_type,
                            "Dataset": metrics.get("Dataset", "Unknown"),
                            "Metric": metric,
                            "Value": cwe_metrics[metric].get(selected_cwe, 0)
                        })

        if not data:
            st.warning("No data available for the selected CWE category.")
            return None

        df = pd.DataFrame(data)

        # Plot grouped bar chart using Plotly
        fig = px.bar(
            df,
            x="Dataset",
            y="Value",
            color="Prompt Type",
            barmode="group",
            facet_row="Metric",
            title=f"Metrics for CWE Category: {selected_cwe} Grouped by Prompt Type and Dataset",
            height=800
        )

        return fig

def main():
    # Specify the directory containing your JSON files
    data_directory = "Evaluation/exp09/"
    
    # Get a list of all JSON files in the directory
    data_files = [
        os.path.join(data_directory, file)
        for file in os.listdir(data_directory)
        if file.endswith(".json")
    ]
    
    # Initialize the Streamlit handler with the list of files
    streamlit_handler = StreamlitHandler(data_files)
    streamlit_handler.run()

if __name__ == "__main__":
    main()
