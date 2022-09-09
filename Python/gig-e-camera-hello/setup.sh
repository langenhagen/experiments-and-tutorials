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


if [[ "$*" =~ '--clean' ]]; then
    rm -fr .venv/
    rm -fr MVS_STD_GML_V2.1.1_220511/

    cat MVS_STD_GML_V2.1.1_220511.zip.d/MVS_STD_GML_V2.1.1_220511.zip.split-* > MVS_STD_GML_V2.1.1_220511.zip
    unzip MVS_STD_GML_V2.1.1_220511.zip -d MVS_STD_GML_V2.1.1_220511/
    cd MVS_STD_GML_V2.1.1_220511/
    sudo apt install ./MVS-2.1.1_x86_64_20220511.deb
    cd ../
fi

python -m venv .venv
# shellcheck disable=SC1091
source .venv/bin/activate

pip install --upgrade pip
pip install --upgrade -r requirements.txt
