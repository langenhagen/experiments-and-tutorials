# Tontaube TTS Hello

Gradio app for TontaubeV1.1, a local text-to-speech model with voice cloning.
English and German are its primary languages. Built for long texts like
audiobooks: up to 40,000 characters per request, without voice drift.

https://github.com/craitech/tontaube

Linux only. Does not run on macOS. The server needs an NVIDIA GPU or an
x86-64 CPU with AVX2.

## Setup

```bash
sudo apt-get install --yes ffmpeg libopus0
git clone --branch v1.1.0 --depth 1 https://github.com/craitech/tontaube.git tontaube-server
uv sync
```

## Usage

```bash
./tontaube-serve.sh               # First start downloads about 2 GB of models
uv run gradio-tontaube-hello.py   # http://127.0.0.1:7860
```
