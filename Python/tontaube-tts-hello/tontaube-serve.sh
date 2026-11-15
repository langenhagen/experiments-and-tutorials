#!/usr/bin/env bash
# Start the local Tontaube inference server on http://127.0.0.1:8080.
#
# Starts the upstream server cloned into tontaube-server/ (see README.md) with
# its own locked Python 3.12 environment. The first start downloads about 2 GB
# of model weights into the Hugging Face cache.
#
# Requires Linux with an NVIDIA GPU (Ampere or newer, about 6.5 GiB VRAM peak)
# or an x86-64 CPU with AVX2, plus ffmpeg and libopus0. Native macOS is not
# supported.
#
# Extra arguments are passed on to `tontaube serve`; settings are environment
# variables, for example `LITE_DEVICE=cpu ./tontaube-serve.sh`.
#
# See: https://github.com/craitech/tontaube
set -euo pipefail

script_path="$(readlink -f -- "${BASH_SOURCE[0]}")"
script_dir="$(dirname -- "$script_path")"

cd "${script_dir}/tontaube-server"
exec uv run --python 3.12 --frozen tontaube serve "$@"
