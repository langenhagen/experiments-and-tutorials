#!/bin/bash
# Convenience script to start the app.

script_dir="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
cd ${script_dir}

source .venv/bin/activate

python main.py
