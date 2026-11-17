# Piper TTS Hello

Showcase for [Piper](https://github.com/OHF-Voice/piper1-gpl) TTS, a small,
fast, local text-to-speech engine. 183 voices in 57 languages and dialect
variants, including English and German, no Denglish.

Smaller and faster than Kokoro but flatter. Piper has far more voices w/ varying
quality, but Kokoro is better or at least equal, quality wise.

No voice cloning, no voice design, no emotion or style control.  
GPL-3.0; eachvoice has its own license in its `MODEL_CARD` file.

https://huggingface.co/rhasspy/piper-voices

## Usage

```bash
uv run gradio-piper-hello.py   # http://127.0.0.1:7860
```

The voice list comes from Hugging Face, so the first start needs internet. A
voice downloads to the git-ignored `.voices/` on first use.

## Platforms

Piper runs on ONNX Runtime.

- CPU: works everywhere.
- Linux with NVIDIA: CUDA via `onnxruntime-gpu`; not tested.
- Apple MLX or Metal: not available.

CPU speed on an Apple Silicon Mac for ~21 seconds of English, mean of 3
runs after warm-up. The real-time factor (RTF) is processing time divided by
audio length, so 0.02 means 50 times faster than real time.

| Voice               | Size   | Processing | RTF  |
| ------------------- | ------ | ---------- | ---- |
| en_US-lessac-low    | 63 MB  | 0.3 s      | 0.02 |
| en_US-lessac-medium | 63 MB  | 0.9 s      | 0.04 |
| en_US-lessac-high   | 114 MB | 7.0 s      | 0.32 |

## Notes

- No seed. Both variation sliders at 0 give identical output on every run.
- Thai is hidden: its extra needs `gensim`, which does not build on Python 3.14.
