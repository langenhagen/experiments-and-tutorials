#!/bin/bash
# Set up the Python project for local development.
# Set up a Python virtual environment and install the dependencies.
#
# Usage:
#
#   bash setup.sh [--clean]
#
# Examples:
#
#   bash setup.sh           # set up the project
#   bash setup.sh --clean   # clean already existing artifacts and set up the project
set -e

sudo apt update && sudo apt install portaudio19-dev  # dependency for pyaudio

cd "$(dirname "${BASH_SOURCE[0]}")"

[[ "$*" =~ '--clean' ]] && rm -fr '.venv/'

python -m venv .venv
# shellcheck disable=SC1091
source .venv/bin/activate

pip install --upgrade pip
pip install --upgrade -r requirements.txt

# Download the special model files for vosk
# See models at https://alphacephei.com/vosk/models

# model='vosk-model-en-us-0.22-lgraph'  # small, allegedly, fast, rather accurate
model='vosk-model-en-us-0.42-gigaspeech'  # big, allegedly accurate

wget --no-clobber "https://alphacephei.com/vosk/models/${model}.zip"
[ ! -d "$model" ] && unzip "${model}.zip" && rm -fr 'model/' && mv "${model}" model
