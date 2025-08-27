# VuLLMBench – Quick Replication Guide

## 1. Clone Repository

```bash
git clone https://github.com/vaibhavc99/VuLLMBench.git
cd VuLLMBench
```

## 2. Set Environment Variables (as required for particular LLM deployment)

Create a `.env` file (for Docker) in the project root or export variables in your shell:

```
AZURE_OPENAI_API_KEY=...
AZURE_OPENAI_API_VERSION=...
AZURE_OPENAI_ENDPOINT=...
OPENAI_API_KEY=...
OLLAMA_HOST_URL=...
GROQ_API_KEY=...
```

## 3. Experiment Configuration

An `experiment.conf` file must be placed inside `Evaluation/<experiment_name>` (e.g., `Evaluation/exp01`).

Refer to the example configuration file provided in the repo for format and options.

Evaluation reports (JSON, CSV, logs) will be saved in the same experiment directory after the run.

## 4. Run an Experiment

### Option A – Docker Compose

```bash
EXPERIMENT=exp01 docker compose up
```

### Option B – Local CLI

```bash
python3 -m venv vullmbench-venv
source vullmbench-venv/bin/activate
pip install -r requirements.txt
python VuLLMBench.py -e exp01
```

## 5. View Dashboard

### Docker Compose

```bash
docker compose run --service-ports vullmbench python VuLLMBench.py --dashboard
```

### Local CLI

```bash
python VuLLMBench.py --dashboard
```

Access the dashboard at [`http://localhost:8501`](http://localhost:8501/)
