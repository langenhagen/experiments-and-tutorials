#!/bin/bash
# Set up the Python project for local development.
# Set up a Python virtual environment and install the dependencies.
#
# Usage:
#
#   bash setup.sh [--clean]
#
# Examples:
#
#   bash setup.sh           # set up the project
#   bash setup.sh --clean   # clean already existing artifacts and set up the project
set -e

sudo apt-get install -y libhidapi-dev;

cd "$(dirname "${BASH_SOURCE[0]}")"

[[ "$*" =~ '--clean' ]] && rm -fr '.venv/'

python -m venv .venv
# shellcheck disable=SC1091
source .venv/bin/activate

pip install --upgrade pip
pip install --upgrade -r requirements.txt
