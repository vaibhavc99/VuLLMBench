import os
import json
import pandas as pd
import streamlit as st
import plotly.express as px
import plotly.figure_factory as ff
import psutil

class DashboardManager:
    def __init__(self, data_files):
        self.data = self.load_and_aggregate_data(data_files)
        self.model_list = {model for model, _ in self.data.keys()}
        self.cwe_list = self.get_cwe_list()

    def get_cwe_list(self):
        cwe_set = set()
        for model_data in self.data.values():
            vulnerability_metrics = model_data.get("Vulnerability Type Metrics", {})
            predicted_cwe_metrics = vulnerability_metrics.get("Using Predicted CWE", {})
            per_class_metrics = predicted_cwe_metrics.get("Per Class Metrics", {})
            accuracy_metrics = per_class_metrics.get("Accuracy", {})
            cwe_set.update(accuracy_metrics.keys())
        sorted_cwe_list = sorted(list(cwe_set), key=lambda cwe: int(cwe.split('-')[1]))
        return sorted_cwe_list

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
                if id in aggregated_data:
                    aggregated_data[id].update(model_results)
                else:
                    aggregated_data[id] = model_results
        return aggregated_data

    def render_sidebar(self):
        st.sidebar.title("Model Selection")
        selected_model = st.sidebar.selectbox("Choose a model", sorted(self.model_list))

        st.sidebar.title("Classification Type")
        classification_type = st.sidebar.radio("Choose Classification Type", 
                                               ("Binary Classification", "Multiclass Classification"))
        
        selected_cwe = None
        if classification_type == "Multiclass Classification" and self.cwe_list:
            selected_cwe = st.sidebar.selectbox("Choose a CWE Category", 
                                                ["Aggregated Metrics"] + self.cwe_list)

        return selected_model, classification_type, selected_cwe

    def run(self):
        st.set_page_config(
            page_title="VuLLMBench Dashboard",
            page_icon="📊",
            layout="wide",
            initial_sidebar_state="expanded",
        )
        st.markdown("<h1 style='text-align: center;'>VuLLMBench Dashboard</h1>", unsafe_allow_html=True)

        selected_model, classification_type, selected_cwe = self.render_sidebar()

        model_data = {key: value for key, value in self.data.items() if key[0] == selected_model}
        metrics_visualizer = Visualizer(model_data, self.cwe_list)

        if classification_type == "Binary Classification":
            st.markdown("<h2 style='text-align: center;'>Vulnerability Classification Metrics</h2>", unsafe_allow_html=True)
            st.markdown("<br><br>", unsafe_allow_html=True)

            col1, col2 = st.columns(2)
            self.render_binary_classification(metrics_visualizer, col1, col2)

        elif classification_type == "Multiclass Classification":
            st.markdown("<h2 style='text-align: center;'>Vulnerability Type Classification Metrics</h2>", unsafe_allow_html=True)
            st.markdown("<br><br>", unsafe_allow_html=True)

            self.render_multiclass_classification(metrics_visualizer, selected_cwe)

        # Exit button at the sidebar
        st.sidebar.markdown(14*"<br>", unsafe_allow_html=True)
        exit_app = st.sidebar.button("Shut Down")
        if exit_app:
            pid = os.getpid()
            p = psutil.Process(pid)
            p.terminate()

    def render_binary_classification(self, metrics_visualizer, col1, col2):
        display_option = st.sidebar.selectbox("Choose a Display Option", 
                                                ["Individual Metrics"] + ["Aggregated Metrics"])

        if display_option == "Aggregated Metrics":
            fig = metrics_visualizer.create_aggregated_binary_classification_plot()
            if fig:
                st.plotly_chart(fig, use_container_width=True)
        else:
            figures = metrics_visualizer.create_binary_classification_plots()
            metrics_list = list(figures.keys())

            for i, metric in enumerate(metrics_list):
                if i % 2 == 0:
                    with col1:
                        st.subheader(metric)
                        st.plotly_chart(figures[metric])
                else:
                    with col2:
                        st.subheader(metric)
                        st.plotly_chart(figures[metric])

    def render_multiclass_classification(self, metrics_visualizer, selected_cwe):
        if selected_cwe == "Aggregated Metrics":
            self.render_aggregated_multiclass(metrics_visualizer)
        else:
            fig = metrics_visualizer.create_multiclass_classification_plot(selected_cwe)
            if fig:
                st.plotly_chart(fig, use_container_width=True)

    def render_aggregated_multiclass(self, metrics_visualizer):
        fig, confusion_matrix_figs = metrics_visualizer.create_aggregated_multiclass_classification_plot()
        if fig:
            st.plotly_chart(fig, use_container_width=True)
        
        if confusion_matrix_figs:
            col1, col2 = st.columns(2)
            for i, cm_fig in enumerate(confusion_matrix_figs):
                if i % 2 == 0:
                    with col1:
                        st.plotly_chart(cm_fig)
                else:
                    with col2:
                        st.plotly_chart(cm_fig)

class Visualizer:
    def __init__(self, model_data, cwe_list):
        self.model_data = model_data
        self.cwe_list = cwe_list

    def create_grouped_bar_plot(self, data, x, y, color, title, barmode='group', facet_row=None, height=400):
        return px.bar(
            data,
            x=x,
            y=y,
            color=color,
            barmode=barmode,
            facet_row=facet_row,
            title=title,
            height=height
        )

    def create_binary_classification_plots(self):
        metrics = ["F1-Score", "Precision", "Recall", "Accuracy"]
        figures = {}
        for metric in metrics:
            data = self.prepare_metric_data(metric, "Vulnerability Metrics")
            if not data.empty:
                figures[metric] = self.create_grouped_bar_plot(
                    data, "Dataset", "Value", "Prompt Type", f"{metric} by Dataset and Prompt Type"
                )
        return figures
    
    def create_aggregated_binary_classification_plot(self):
        metrics = ["F1-Score", "Precision", "Recall"]
        data = []

        for metric in metrics:
            metric_data = self.prepare_metric_data(metric, "Vulnerability Metrics")
            if not metric_data.empty:
                metric_data["Metric"] = metric
                data.append(metric_data)

        if data:
            combined_data = pd.concat(data)
            fig = self.create_grouped_bar_plot(
                combined_data, "Metric", "Value", "Prompt Type", 
                "Aggregated Binary Classification Metrics", barmode='group', facet_row="Dataset", height=600
            )
            return fig
        else:
            st.warning("No data available for binary classification metrics.")
            return None


    def prepare_metric_data(self, metric_name, metrics_key):
        data = []
        for (_, prompt_type), metrics in self.model_data.items():
            dataset = metrics.get("Dataset", "Unknown")
            value = metrics.get(metrics_key, {}).get(metric_name, 0)
            data.append({"Prompt Type": prompt_type, "Dataset": dataset, "Value": value})
        return pd.DataFrame(data) if data else pd.DataFrame()

    def create_aggregated_multiclass_classification_plot(self):
        metrics_to_extract = ["Macro Precision", "Macro Recall", "Macro F1-Score", 
                              "Weighted Precision", "Weighted Recall", "Weighted F1-Score"] 
                            #   "MCC", "Cohen Kappa"]
        aggregated_metrics, confusion_matrices = self.extract_aggregated_metrics(metrics_to_extract)

        if aggregated_metrics.empty:
            st.warning("No aggregated metrics found in the data.")
            return None, None

        fig = self.create_grouped_bar_plot(
            aggregated_metrics, "Metric", "Value", "Prompt Type", "Aggregated Multiclass Classification Metrics"
        )

        confusion_matrix_figs = []
        for prompt_type, cm in confusion_matrices.items():
            if cm:
                cm_fig = self.create_confusion_matrix_plot(prompt_type, cm)
                confusion_matrix_figs.append(cm_fig)

        return fig, confusion_matrix_figs

    def extract_aggregated_metrics(self, metrics_to_extract):
        aggregated_metrics = []
        confusion_matrices = {}

        for (model, prompt_type), metrics in self.model_data.items():
            vuln_metrics = metrics.get("Vulnerability Type Metrics", {}).get("Using Predicted CWE", {})
            for metric in metrics_to_extract:
                value = vuln_metrics.get(metric, 0)
                aggregated_metrics.append({"Metric": metric, "Value": value, "Prompt Type": prompt_type})
            confusion_matrices[prompt_type] = vuln_metrics.get("Confusion Matrix", [])

        return pd.DataFrame(aggregated_metrics), confusion_matrices

    def create_confusion_matrix_plot(self, prompt_type, confusion_matrix):
        return ff.create_annotated_heatmap(
            z=confusion_matrix[::-1],
            x=self.cwe_list,
            y=self.cwe_list[::-1],
            colorscale="Viridis",
            showscale=True
        ).update_layout(title=f"Confusion Matrix for '{prompt_type}' prompt")

    def create_multiclass_classification_plot(self, selected_cwe):
        data = self.prepare_cwe_data(selected_cwe)
        if not data.empty:
            return self.create_grouped_bar_plot(
                data, "Dataset", "Value", "Prompt Type", 
                f"Metrics for CWE Category: {selected_cwe} Grouped by Prompt Type and Dataset", 
                facet_row="Metric", height=800
            )
        else:
            st.warning("No data available for the selected CWE category.")
            return None

    def prepare_cwe_data(self, selected_cwe):
        metric_names = ["F1-Score", "Precision", "Recall", "Accuracy"]
        data = []
        for (_, prompt_type), metrics in self.model_data.items():
            vuln_metrics = metrics.get("Vulnerability Type Metrics", {}).get("Using Predicted CWE", {})
            per_class_metrics = vuln_metrics.get("Per Class Metrics", {})
            for metric in metric_names:
                value = per_class_metrics.get(metric, {}).get(selected_cwe, 0)
                dataset = metrics.get("Dataset", "Unknown")
                data.append({"Prompt Type": prompt_type, "Dataset": dataset, "Metric": metric, "Value": value})
        return pd.DataFrame(data) if data else pd.DataFrame()


def main():
    data_directory = "../Evaluation/exp14/"
    data_files = [os.path.join(data_directory, file) for file in os.listdir(data_directory) if file.endswith(".json")]

    dashboard_manager = DashboardManager(data_files)
    dashboard_manager.run()


if __name__ == "__main__":
    main()
