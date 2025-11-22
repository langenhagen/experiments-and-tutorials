#!/bin/bash
#
# Vosk comes with its own Transcriber CLI tool. Use that.
source .venv/bin/activate

input_file="$1"
language="$2"
[ -z "$language" ] && { vosk-transcriber --list-languages; exit 1; }

vosk-transcriber --lang "$language" --input "$input_file" --output "${input_file}.txt"
