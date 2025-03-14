#!/bin/bash

script_dir="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"

set -x

"$script_dir"/.venv/bin/tts --list_models
