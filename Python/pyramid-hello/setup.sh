#!/bin/bash
set -e

cd "$(dirname "${BASH_SOURCE[0]}")"

virtualenv --python python2 .venv
source .venv/bin/activate
pip install -r requirements.txt
