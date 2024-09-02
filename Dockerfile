# Use an official Python runtime as a parent image
FROM python:3.11-slim-bullseye

# Set environment variables
ENV PYTHONUNBUFFERED=1

# Set the working directory in the container
WORKDIR /vullmbench

# Copy the current directory contents into the container at /vullmbench
COPY . /vullmbench

# Install required packages specified in requirements.txt
RUN pip install --no-cache-dir -r requirements.txt

# Default command (can be overridden in docker-compose or CLI)
CMD ["python", "VuLLMBench.py", "-e", "exp01"]
