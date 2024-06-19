import pandas as pd
import json
import plotly.express as px
import plotly.graph_objects as go
import dash
from dash import dcc, html
from dash.dependencies import Input, Output

class EvaluationVisualizer:
    def __init__(self, evaluation_results):
        self.evaluation_results = evaluation_results

    def get_vulnerability_metrics_comparison(self):
        all_metrics = []
        for model in self.evaluation_results:
            metrics = self.evaluation_results[model]['Vulnerability Metrics']
            metrics['Model'] = model
            all_metrics.append(metrics)
        
        all_metrics_df = pd.DataFrame(all_metrics)
        metrics_df = all_metrics_df.melt(id_vars='Model', var_name='Metric', value_name='Score')
        
        fig = px.bar(metrics_df, x='Metric', y='Score', color='Model', barmode='group',
                     title='Vulnerability Metrics Comparison Across Models')
        return fig

    def get_confusion_matrix(self, model, method='Using Predicted CWE'):
        type_metrics = self.evaluation_results[model]['Vulnerability Type Metrics'][method]
        if type_metrics == 'Not Evaluated':
            return go.Figure()
        
        # eval_df = pd.read_csv(f'Results/eval_df_{model}.csv')
        # y_true = eval_df['cwe_true']
        # labels = y_true.unique()

        if 'Confusion Matrix' in type_metrics:
            conf_matrix = type_metrics['Confusion Matrix']
            fig = px.imshow(conf_matrix, text_auto=True, color_continuous_scale='YlGnBu',
                            labels=dict(x='Predicted', y='Actual', color='Count'),
                            title=f'Confusion Matrix for {model} using {method}')
            return fig
        return go.Figure()

    def get_vulnerability_metrics_trends(self):
        all_metrics = []
        for model in self.evaluation_results:
            metrics = self.evaluation_results[model]['Vulnerability Metrics']
            metrics['Model'] = model
            all_metrics.append(metrics)
        
        all_metrics_df = pd.DataFrame(all_metrics).set_index('Model').T.reset_index().rename(columns={'index': 'Metric'})
        
        fig = px.line(all_metrics_df, x='Metric', y=all_metrics_df.columns[1:], 
                      title='Vulnerability Metrics Trends Across Models')
        return fig

    def get_vulnerability_type_distribution(self, model, method='Using Predicted CWE'):
        type_metrics = self.evaluation_results[model]['Vulnerability Type Metrics'][method]
        if type_metrics == 'Not Evaluated':
            return go.Figure()
        
        per_class_metrics = type_metrics['Per Class Metrics']
        overall_metrics = {k: v for k, v in type_metrics.items() if k != 'Per Class Metrics'}

        all_distributions = []

        # Plot per class metrics
        for metric, values in per_class_metrics.items():
            metrics_df = pd.DataFrame(values, index=[0]).melt(var_name='Class', value_name=metric)
            fig = px.bar(metrics_df, x='Class', y=metric,
                        title=f'{metric} Distribution per Class for {model} using {method}')
            all_distributions.append(fig)
        
        # Plot overall metrics
        overall_metrics_df = pd.DataFrame(list(overall_metrics.items()), columns=['Metric', 'Value'])
        overall_fig = px.bar(overall_metrics_df, x='Metric', y='Value',
                            title=f'Overall Metrics for {model} using {method}')
        all_distributions.append(overall_fig)

        return all_distributions

    def get_pair_plot_vulnerability_metrics(self):
        all_metrics = []
        for model in self.evaluation_results:
            metrics = self.evaluation_results[model]['Vulnerability Metrics']
            metrics['Model'] = model
            all_metrics.append(metrics)
        
        all_metrics_df = pd.DataFrame(all_metrics)
        fig = px.scatter_matrix(all_metrics_df, dimensions=all_metrics_df.columns[:-1], color='Model',
                                title='Pair Plot of Vulnerability Metrics Across Models')
        return fig

# Load the evaluation results
with open('../Results/evaluation_results-simple_prompt.json', 'r') as f:
    evaluation_results = json.load(f)

visualizer = EvaluationVisualizer(evaluation_results)
# fig = visualizer.get_vulnerability_metrics_comparison()
# fig.show()

# Create a Dash app
app = dash.Dash("Vulnerability Prediction Evaluation Dashboard")

app.layout = html.Div([
    html.H1('Vulnerability Prediction Evaluation Dashboard', style={'fontFamily': 'Arial'}),
    
    dcc.Tabs([
        dcc.Tab(label='Metrics Comparison', style={'fontFamily': 'Arial'}, children=[
            dcc.Graph(figure=visualizer.get_vulnerability_metrics_comparison())
        ]),
        dcc.Tab(label='Confusion Matrix', style={'fontFamily': 'Arial'}, children=[
            dcc.Dropdown(
                id='model-dropdown', style={'fontFamily': 'Arial'},
                options=[{'label': model, 'value': model} for model in evaluation_results.keys()],
                value=list(evaluation_results.keys())[0]
            ),
            dcc.Dropdown(
                id='method-dropdown', style={'fontFamily': 'Arial'},
                options=[
                    {'label': 'Using Predicted CWE', 'value': 'Using Predicted CWE'},
                    {'label': 'Using Predicted Vulnerability Name', 'value': 'Using Predicted Vulnerability Name'}
                ],
                value='Using Predicted CWE'
            ),
            dcc.Graph(id='confusion-matrix-graph')
        ]),
        dcc.Tab(label='Metrics Trends', style={'fontFamily': 'Arial'}, children=[
            dcc.Graph(figure=visualizer.get_vulnerability_metrics_trends())
        ]),
        dcc.Tab(label='Type Distribution', style={'fontFamily': 'Arial'}, children=[
            dcc.Dropdown(
                id='model-dropdown-distribution', style={'fontFamily': 'Arial'},
                options=[{'label': model, 'value': model} for model in evaluation_results.keys()],
                value=list(evaluation_results.keys())[0]
            ),
            dcc.Dropdown(
                id='method-dropdown-distribution', style={'fontFamily': 'Arial'},
                options=[
                    {'label': 'Using Predicted CWE', 'value': 'Using Predicted CWE'},
                    {'label': 'Using Predicted Vulnerability Name', 'value': 'Using Predicted Vulnerability Name'}
                ],
                value='Using Predicted CWE'
            ),
            html.Div(id='type-distribution-graphs')
        ]),
        dcc.Tab(label='Metrics Pair Plot', style={'fontFamily': 'Arial'}, children=[
            dcc.Graph(figure=visualizer.get_pair_plot_vulnerability_metrics())
        ]),
    ])
])

@app.callback(
    Output('confusion-matrix-graph', 'figure'),
    [Input('model-dropdown', 'value'),
     Input('method-dropdown', 'value')]
)
def update_confusion_matrix(selected_model, selected_method):
    return visualizer.get_confusion_matrix(selected_model, selected_method)

@app.callback(
    Output('type-distribution-graphs', 'children'),
    [Input('model-dropdown-distribution', 'value'),
     Input('method-dropdown-distribution', 'value')]
)
def update_type_distribution(selected_model, selected_method):
    figures = visualizer.get_vulnerability_type_distribution(selected_model, selected_method)
    return [dcc.Graph(figure=fig) for fig in figures]

# Run the Dash app
if __name__ == '__main__':
    app.run_server(debug=True)
