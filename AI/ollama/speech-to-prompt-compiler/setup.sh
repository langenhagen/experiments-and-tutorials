#!/usr/bin/env bash

set -euo pipefail

script_dir="$(cd -- "$(dirname -- "${BASH_SOURCE[0]}")" && pwd)"
modelfile="$script_dir/Modelfile"

command -v ollama >/dev/null 2>&1 || {
    printf 'error: ollama not found on PATH\n' >&2
    exit 1
}

base_model="$(awk '$1 == "FROM" { print $2; exit }' "$modelfile")"

if ! ollama show "$base_model" >/dev/null 2>&1; then
    ollama pull "$base_model"
fi

ollama create speech-to-prompt-compiler -f "$modelfile"

ollama show speech-to-prompt-compiler >/dev/null
printf 'Ready: speech-to-prompt-compiler\n'
