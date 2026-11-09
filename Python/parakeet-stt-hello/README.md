# Parakeet STT Hello

Showcase NVIDIA [Parakeet TDT 0.6B v3](https://huggingface.co/nvidia/parakeet-tdt-0.6b-v3)
for microphone speech-to-text in 25 European languages, including English and
German.

Parakeet is faster than Whisper and, according to my quick research, at least as accurate if not
better than Whisper, esp for English. Also works in German.

Paired with Silero VAD that can detect voice activity, you can stream textual contents
nicely.

`--device cpu` (default) runs the int8 ONNX export via
[onnx-asr](https://github.com/istupakov/onnx-asr) (0.7 GB download).
`--device gpu` runs the MLX port via [mlx-audio](https://github.com/Blaizzy/mlx-audio)
(2.5 GB download). Once downloaded, both run without an internet connection.

[Silero VAD](https://github.com/snakers4/silero-vad) (`vad.py`) cuts the
microphone stream at speech pauses, so each line is a whole phrase. While you
speak, a provisional line shows the text so far and is replaced at the pause.

## Usage

```bash
uv sync
uv run python stream_stdout.py --help
uv run python stream_stdout.py                       # ONNX Runtime on the CPU
uv run python stream_stdout.py --device gpu          # MLX on the GPU
uv run python stream_stdout.py --min-silence-ms 300  # end phrases at shorter pauses
uv run python stream_stdout.py --partial-seconds 0   # final lines only
```

Press `Ctrl+C` to stop.
