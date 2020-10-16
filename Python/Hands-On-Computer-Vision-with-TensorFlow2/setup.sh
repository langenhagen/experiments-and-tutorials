#!/bin/bash
# Set up the python project.

command -v pyenv && pyenv local 3.8.3

python -m venv .venv
source .venv/bin/activate

pip install --upgrade -r requirements.txt
