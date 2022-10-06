#!/bin/bash
# Experiment main entry point

script_dir="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
cd "$script_dir" || exit 1

source .venv/bin/activate

python main.py
