#!/bin/bash
# Configure the server via environment variables start the virtual environment and start the webapp.

export PYRAMID_RELOAD_TEMPLATES true
source .venv/bin/activate

python application.py
