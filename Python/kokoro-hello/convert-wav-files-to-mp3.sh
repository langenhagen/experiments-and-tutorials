#!/bin/bash
# Convert all .wav files in a folder to .mp3 using ffmpeg.
#
# Usage:
#     ./convert-wav-files-to-mp3.sh FOLDER
#
# Example:
#     ./convert-wav-files-to-mp3.sh audiobook
#
# Writes NAME.mp3 next to each NAME.wav inside FOLDER. Existing .mp3
# files are silently overwritten. Encoding uses LAME's best VBR quality
# (-q:a 0, roughly 245 kbps average).

set -euo pipefail

folder="${1:?Usage: convert-wav-files-to-mp3.sh FOLDER}"

shopt -s nullglob
wavs=("$folder"/*.wav)

for f in "${wavs[@]}"; do
    echo "> $f"
    ffmpeg -y -i "$f" -c:a libmp3lame -q:a 0 "${f%.wav}.mp3"
done
