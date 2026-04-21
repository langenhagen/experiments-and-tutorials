# Whisper Hello / Faster-Whisper Hello

Showcase `faster-whisper` for microphone speech-to-text.

This script records from the microphone, runs local Whisper inference, and
copies the recognized text to the system clipboard. It can transcribe in the
source language or translate speech to English.

## Prerequisites

```bash
sudo apt install sox libsox-fmt-mp3 xclip
```

The script auto-detects one of:
- `wl-copy` (Wayland)
- `xclip` (X11)
- `xsel` (X11)
- `pbcopy` (macOS)

Install at least one of these if clipboard copy fails.

## Usage

```bash
# Speech to clipboard
uv run python main.py --help
time uv run python main.py  # autodetect spoken language, transscribe to english automatically
time uv run python main.py --task transcribe  # transcribe language and keep spoken language
time uv run python main.py --model small # use a different model
time uv run python main.py --keep-audio  # keep the temporary wav for debugging

# Speech-Stream to text
uv run python stream_stdout.py --help
uv run python stream_stdout.py  # stream mic to stdout with default chunking
uv run python stream_stdout.py --task transcribe  # stream and keep original spoken language
uv run python stream_stdout.py --task translate  # stream and translate speech to English
uv run python stream_stdout.py --chunk-seconds 2.0  # smaller chunks for lower output latency
```

Press `Ctrl+C` to stop recording.
