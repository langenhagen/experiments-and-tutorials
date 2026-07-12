# Kokoro Hello

## Setup

```bash
sudo apt update && sudo apt install --yes ffmpeg
uv sync
```

Download the ONNX model files (not tracked in git):

```bash
wget --no-clobber https://github.com/thewh1teagle/kokoro-onnx/releases/download/model-files/kokoro-v0_19.onnx
wget --no-clobber https://github.com/thewh1teagle/kokoro-onnx/releases/download/model-files/voices.json
```
