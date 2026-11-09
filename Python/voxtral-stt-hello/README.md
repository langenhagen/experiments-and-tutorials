# Voxtral STT Hello

Showcase Mistral's [Voxtral](https://mistral.ai/news/voxtral) speech-to-text
models on Apple Silicon via [mlx-audio](https://github.com/Blaizzy/mlx-audio).

Acc to claude it should be more reliable than Whisper, and faster as well. Can't say it is faster -
it is, at least when GPU has other things to do as well, actually too slow for streaming.

`stream_stdout.py` streams microphone speech to stdout with
[Voxtral Mini 4B Realtime](https://huggingface.co/mistralai/Voxtral-Mini-4B-Realtime-2602).
Unlike Whisper in `../whisper-hello`, the model consumes audio as it arrives
instead of transcribing fixed chunks. It detects the language automatically
and only transcribes; it does not translate.

## Setup

```bash
uv sync
```

The first run of each model downloads its weights from Hugging Face. Later
runs load the cached weights and never contact Hugging Face, so they work
without an internet connection. On first use, macOS asks for microphone access
for the terminal.

## Usage

```bash
uv run python stream_stdout.py --help
uv run python stream_stdout.py                 # 4bit model, 960 ms delay
uv run python stream_stdout.py --delay-ms 480  # faster text, slightly less accurate
uv run python stream_stdout.py --model fp16    # unquantized model
uv run python stream_stdout.py --device cpu    # CPU instead of GPU, much slower
```

The first line on stderr names the MLX device, for example `device: Device(gpu, 0)`.

Press `Ctrl+C` to stop. The script then prints the text still inside the delay
window.

## Models

| `--model` | Hugging Face repo                                  | Download |
| --------- | -------------------------------------------------- | -------- |
| `4bit`    | `mlx-community/Voxtral-Mini-4B-Realtime-2602-4bit` | 3.2 GB   |
| `fp16`    | `mlx-community/Voxtral-Mini-4B-Realtime-2602-fp16` | 8.9 GB   |

## Delay And Accuracy

`--delay-ms` sets how far the text lags behind the speech. Word error rates on
FLEURS from the
[model card](https://huggingface.co/mistralai/Voxtral-Mini-4B-Realtime-2602)
(unquantized model):

| `--delay-ms` | German | English |
| ------------ | ------ | ------- |
| 240          | 8.15%  | 5.91%   |
| 480          | 6.19%  | 4.90%   |
| 960          | 4.87%  | 4.34%   |
| 2400         | 4.15%  | 4.05%   |
