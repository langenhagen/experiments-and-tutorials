#!/bin/bash
# Setup the Python project.

python -m venv .venv
# shellcheck disable=SC1091
source .venv/bin/activate

pip install --upgrade pip
pip install --upgrade -r requirements.txt
