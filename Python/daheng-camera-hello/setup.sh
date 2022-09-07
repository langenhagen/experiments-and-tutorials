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

cd "$(dirname "${BASH_SOURCE[0]}")"

sudo apt-get install libffi-dev  # prerequisite for the Galaxy/Daheng stuff

if [[ "$*" =~ '--clean' ]]; then
    rm -fr .venv/
    rm -fr Galaxy_Linux_Python_2.0.2106.9041/
    tar xxf Galaxy_Linux_Python_2.0.2106.9041.tar_1.gz
    cd Galaxy_Linux_Python_2.0.2106.9041/api/
    python setup.py build
    cd ../../
fi

python -m venv .venv
# shellcheck disable=SC1091
source .venv/bin/activate

pip install --upgrade pip
pip install --upgrade -r requirements.txt
