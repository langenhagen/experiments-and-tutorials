#!/bin/bash
# Set up the project for local development.
# Set up a Python virtual environment and install the dependencies.
# Also, when --dev is specified, set up an npm project to install npm dev dependencies.
#
# Usage:
#   scripts/setup.sh         # setup the project
set -e

rm -fr .venv/
python -m venv .venv

# shellcheck disable=SC1091
source .venv/bin/activate

pip install --upgrade pip
pip install --upgrade -r requirements.txt
