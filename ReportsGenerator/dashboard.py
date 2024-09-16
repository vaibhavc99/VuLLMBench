import os
import json
import pandas as pd
import streamlit as st
import plotly.io as pio
import psutil

from visualizer import Visualizer

# Set the default Plotly template
pio.templates.default = "plotly_white"

class DashboardManager:
    def __init__(self, eval_dirs, output_dir):
        self.data = self.load_and_aggregate_data(eval_dirs)
        self.model_list = {model for model, _, _ in self.data.keys()}
        self.cwe_list = self.get_cwe_list()
        self.output_dir = output_dir
        os.makedirs(self.output_dir, exist_ok=True)

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

    def load_and_aggregate_data(self, dirs):
        aggregated_data = {}
        for exp_dir in dirs:
            for file in os.listdir(exp_dir):
                if file.endswith(".json"):
                    data = self.load_data(os.path.join(exp_dir, file))
                    experiment_type = "obfuscated" if "obfs" in exp_dir else "regular"
                    for model, model_results in data.items():
                        id = (model, model_results.get("Prompt Type"), experiment_type)
                        if id in aggregated_data:
                            aggregated_data[id].update(model_results)
                        else:
                            aggregated_data[id] = model_results
        return aggregated_data

    def render_sidebar(self):
        st.sidebar.title("VuLLMBench Dashboard")

        st.sidebar.header("Model Selection")
        selected_model = st.sidebar.selectbox(
            "Choose a model", ["Aggregated Results"] + sorted(self.model_list)
        )

        st.sidebar.header("Classification Type")
        classification_type = st.sidebar.radio(
            "Choose Classification Type",
            ("Binary Classification", "Multiclass Classification")
        )

        selected_cwe = None
        if classification_type == "Multiclass Classification" and self.cwe_list:
            selected_cwe = st.sidebar.selectbox(
                "Choose a CWE Category", ["Aggregated Metrics"] + self.cwe_list
            )

        return selected_model, classification_type, selected_cwe

    def run(self):
        st.set_page_config(
            page_title="VuLLMBench Dashboard",
            page_icon="📊",
            layout="wide",
            initial_sidebar_state="expanded",
        )
        st.markdown(
            "<h1 style='text-align: center;'>VuLLMBench Dashboard</h1>",
            unsafe_allow_html=True
        )

        self.selected_model, classification_type, selected_cwe = self.render_sidebar()

        if self.selected_model == "Aggregated Results":
            model_data = self.data
        else:
            model_data = {key: value for key, value in self.data.items() if key[0] == self.selected_model}

        metrics_visualizer = Visualizer(model_data, self.cwe_list, self.output_dir)

        if classification_type == "Binary Classification":
            st.markdown(
                "<h2 style='text-align: center;'>Vulnerability Classification Metrics</h2>",
                unsafe_allow_html=True
            )
            st.markdown("<br><br>", unsafe_allow_html=True)

            col1, col2 = st.columns(2)
            self.render_binary_classification(metrics_visualizer, col1, col2)

        elif classification_type == "Multiclass Classification":
            st.markdown(
                "<h2 style='text-align: center;'>Vulnerability Type Classification Metrics</h2>",
                unsafe_allow_html=True
            )
            st.markdown("<br><br>", unsafe_allow_html=True)

            self.render_multiclass_classification(metrics_visualizer, selected_cwe)

        # Exit button at the sidebar
        st.sidebar.markdown(12 * "<br>", unsafe_allow_html=True)
        exit_app = st.sidebar.button("Shut Down")
        if exit_app:
            pid = os.getpid()
            p = psutil.Process(pid)
            p.terminate()

    def render_binary_classification(self, metrics_visualizer, col1, col2):
        display_option = st.sidebar.selectbox("Choose a Display Option", ["Individual Metrics", "Aggregated Metrics"])
        aggregated = self.selected_model == "Aggregated Results"
        figures = metrics_visualizer.create_binary_classification_plots(aggregated, display_option)

        if figures:
            if aggregated and display_option == "Individual Metrics":
                # Display figures for each dataset
                for dataset_name, fig in figures.items():
                    st.subheader(f"Dataset: {dataset_name}")
                    st.plotly_chart(fig, use_container_width=True)
            elif aggregated and display_option == "Aggregated Metrics":
                # Display figures for each prompt type
                for prompt_type_name, fig in figures.items():
                    st.subheader(f"Prompt Type: {prompt_type_name}")
                    st.plotly_chart(fig, use_container_width=True)
            elif not aggregated and display_option == "Individual Metrics":
                # Individual model selected, display as before
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
            else:  # not aggregated and display_option == "Aggregated Metrics"
                # Individual model selected, prompt type as colors, x as metrics, facet row as dataset
                for dataset_name, fig in figures.items():
                    st.subheader(f"Dataset: {dataset_name}")
                    st.plotly_chart(fig, use_container_width=True)
        else:
            st.warning("No figures to display.")

    def render_multiclass_classification(self, metrics_visualizer, selected_cwe):
        aggregated = self.selected_model == "Aggregated Results"
        if aggregated:
            if selected_cwe == "Aggregated Metrics":
                figures = metrics_visualizer.create_aggregated_multiclass_classification_plots()
                if figures:
                    for prompt_type_name, fig in figures.items():
                        st.subheader(f"Prompt Type: {prompt_type_name}")
                        st.plotly_chart(fig, use_container_width=True)
            else:
                figures = metrics_visualizer.create_multiclass_classification_plots_for_cwe(selected_cwe)
                if figures:
                    for dataset_name, fig in figures.items():
                        st.subheader(f"Dataset: {dataset_name}")
                        st.plotly_chart(fig, use_container_width=True)
        else:
            if selected_cwe == "Aggregated Metrics":
                # Individual model and aggregated metrics selected
                fig, confusion_matrix_figs = metrics_visualizer.create_individual_model_aggregated_multiclass_plot()
                if fig:
                    st.plotly_chart(fig, use_container_width=True)
                if confusion_matrix_figs:
                    col1, col2 = st.columns(2)
                    for i, (prompt_type, cm_fig) in enumerate(confusion_matrix_figs.items()):
                        with col1 if i % 2 == 0 else col2:
                            st.subheader(f"Confusion Matrix: {prompt_type}")
                            st.plotly_chart(cm_fig, use_container_width=True)
            else:
                fig = metrics_visualizer.create_multiclass_classification_plot(selected_cwe)
                if fig:
                    st.plotly_chart(fig, use_container_width=True)


def start_dashboard(eval_dir, output_dir):
    eval_dirs = [
        os.path.join(eval_dir, dir_name) for dir_name in os.listdir(eval_dir)
        if os.path.isdir(os.path.join(eval_dir, dir_name)) and ('exp' in dir_name)
    ]

    dashboard = DashboardManager(eval_dirs, output_dir)
    dashboard.run()

if __name__ == "__main__":
    eval_dir = "Evaluation/"
    output_dir = "ReportsGenerator/figures"
    start_dashboard(eval_dir, output_dir)
