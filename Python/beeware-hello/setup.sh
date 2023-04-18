#!/bin/bash
# Set up the Python project for local development.
# Set up a Python virtual environment and install the dependencies.
#
# Usage:
#
#   scripts/setup.sh [--clean]
#
# Examples:
#
#   scripts/setup.sh               # set up the project
#   scripts/setup.sh --clean       # clean already existing artifacts and set up the project
set -e

[[ "$*" =~ '--clean' ]] && rm -fr .venv/

python -m venv .venv
# shellcheck disable=SC1091
source .venv/bin/activate

pip install --upgrade pip
pip install --upgrade -r requirements.txt

