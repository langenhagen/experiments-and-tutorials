#!/usr/bin/env bash
# Run the minimal Kokoro TTS kokoro.py with the project venv.
set -euo pipefail

script_path="$(readlink -f -- "${BASH_SOURCE[0]}")"
script_dir="$(dirname -- "$script_path")"

python_bin="${script_dir}/.venv/bin/python"
kokoro_py="${script_dir}/kokoro.py"

if [[ ! -x "$python_bin" ]]; then
    printf 'kokoro: venv python not found at %s\nrun "uv sync" first\n' "$python_bin" >&2
    exit 1
fi

exec "$python_bin" "$kokoro_py" "$@"
