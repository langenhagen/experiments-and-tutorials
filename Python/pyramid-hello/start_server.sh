#!/bin/bash
# Configure the server via environment variables start the virtual environment and start the webapp.

source .venv/bin/activate
export PYRAMID_RELOAD_TEMPLATES=true

python application.py
