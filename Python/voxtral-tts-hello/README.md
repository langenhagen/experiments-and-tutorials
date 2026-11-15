# Voxtral TTS Hello

Gradio app for Mistral's Voxtral 4B TTS via `mlx-audio`. 20 preset voices in 9
languages, including English and German. No voice cloning with the released
weights. Non-commercial license (CC BY-NC 4.0).

https://huggingface.co/mistralai/Voxtral-4B-TTS-2603

Longer audio snippets than Chatterbox, English and German, but only a few preset
voices.

## Usage

```bash
uv run gradio-voxtral-hello.py   # http://127.0.0.1:7860
```

macOS uses the Metal GPU, Linux an NVIDIA GPU via CUDA. The CPU is very slow.

## Model Versions

All three are the same model at different precisions:

| Version | Size   | Speed on Apple Silicon (RTF, lower is faster) |
| ------- | ------ | --------------------------------------------- |
| bf16    | 8 GB   | 6.3 to 6.5                                    |
| 6-bit   | 3.5 GB | 1.1                                           |
| 4-bit   | 2.5 GB | 0.7 to 1.0                                    |

Speeds are from the mlx-community model cards. No quality comparison is
published. An 8 GB GPU needs the 6-bit or 4-bit version.
