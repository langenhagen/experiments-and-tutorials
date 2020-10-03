#!/bin/bash
# Set up the python project

pyenv local 3.8.3

python -m venv .venv
source .venv/bin/activate

pip install bpython numpy
