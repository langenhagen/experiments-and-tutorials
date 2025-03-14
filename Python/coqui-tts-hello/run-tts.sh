#!/bin/bash
# Run the AI Text To Speech system Coqui TTS.
# It supports several models, with different languages. Spme better, some worse
#
# To be frank, I find `kokoro`` way better and easier.
# This one monel I tried here (https://www.thorsten-voice.de/) has more resemblance with
# Suno's `Bark`, but Bark os more fun, multilingual and the voices with Bark are context-aware. From
# the one model that self-advertised as "high quality" don't find that Coqui TTS from is good.
#
# But I guess coqui is more than just plain kokoro - apparently, coqui also can do speech-to-text
# and voice cloning.
#
# See: https://pypi.org/project/coqui-tts/
#
set -x

script_dir="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"

text="${1:-"Hallo Welt, der Text ist Deutsch by the way"}"
model="${2:-"tts_models/de/thorsten/tacotron2-DCA"}"

"$script_dir"/.venv/bin/tts --text "$text" --model_name "$model"
