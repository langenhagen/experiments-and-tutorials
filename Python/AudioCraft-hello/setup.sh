#!/bin/bash
# Set up the Python project for local development.
# Set up a Python virtual environment and install the dependencies.
#
# Usage:
#
#   scripts/setup.sh [--dev] [--clean]
#
# Examples:
#
#   scripts/setup.sh               # set up the project
#   scripts/setup.sh --dev         # set up the project including development packages
#   scripts/setup.sh --clean       # clean already existing artifacts and set up the project
#   scripts/setup.sh --dev --clean # clean artifacts and set the project up for development
set -ex

cd "$(dirname "${BASH_SOURCE[0]}")"

[[ "$*" =~ '--clean' ]] && rm -fr .venv/

python -m venv .venv
# shellcheck disable=SC1091
source .venv/bin/activate

pip install --upgrade pip
pip install --upgrade -r requirements.txt
