# Qwen3 TTS Hello

Local text-to-speech with Qwen3-TTS-12Hz-1.7B-CustomVoice on an RTX 5050
laptop GPU.

## Setup

    uv sync

First run downloads the ~1.7 GB model into the Hugging Face cache.

## Scripts

### main.py - Demo

    uv run main.py

Synthesizes one line with speaker Ryan to output.wav.

### create-audiobook-from-textfiles.py - Audiobook Generator

    uv run create-audiobook-from-textfiles.py --help
