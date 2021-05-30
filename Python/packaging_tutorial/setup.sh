#!/bin/bash

python -m venv .venv
source .venv/bin/activate
python -m pip install --upgrade pip


cd "$(dirname "${BASH_SOURCE[0]}")" || exit 1

mkdir -p src/example_pkg
mkdir -p tests

printf "My License :)\n" > LICENSE
printf "# My Readme!\nbased on https://packaging.python.org/tutorials/packaging-projects/\n" \
    > README.md

touch setup.cfg
touch pyproject.toml
