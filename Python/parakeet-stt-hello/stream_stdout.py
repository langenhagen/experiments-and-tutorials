#!/usr/bin/env python3
"""Stream microphone speech-to-text to stdout with Parakeet TDT 0.6B v3.

Silero VAD (vad.py) cuts the microphone stream at speech pauses, and each
segment is transcribed whole, so words and sentences stay intact. While a
segment is still running, a provisional line shows its transcription so far
and is replaced by the final text at the pause.

--device cpu (default) runs the int8 ONNX export via onnx-asr, --device gpu
runs the MLX port via mlx-audio. The language is detected automatically.

Usage

    uv run python stream_stdout.py --help
    uv run python stream_stdout.py
    uv run python stream_stdout.py --device gpu
    uv run python stream_stdout.py --min-silence-ms 300
    uv run python stream_stdout.py --partial-seconds 0

The first run of each device downloads its weights (gpu about 2.5 GB, cpu
about 0.7 GB). Later runs load the cached weights without network access.

author: andreasl
"""

# pylint: disable=import-error

import argparse
import shutil
import sys
import threading
from collections.abc import Callable
from pathlib import Path

import numpy as np
import sounddevice as sd

from vad import SAMPLE_RATE, SpeechSegmenter

MLX_REPO = "mlx-community/parakeet-tdt-0.6b-v3"
ONNX_MODEL = "nemo-parakeet-tdt-0.6b-v3"

# Carriage return plus ANSI "erase line" to overwrite a provisional line.
CLEAR_LINE = "\r\x1b[K"


def parse_args() -> argparse.Namespace:
    """Parse command-line options for streaming transcription."""
    parser = argparse.ArgumentParser(
        description="Stream microphone transcription to stdout.",
    )
    parser.add_argument(
        "--device",
        default="cpu",
        choices=["gpu", "cpu"],
        help="gpu: MLX bf16 via mlx-audio. cpu: int8 ONNX via onnx-asr.",
    )
    parser.add_argument(
        "--min-silence-ms",
        type=int,
        default=500,
        help="Pause length that ends a speech segment",
    )
    parser.add_argument(
        "--max-segment-seconds",
        type=float,
        default=15.0,
        help="Cut a segment at this length even without a pause",
    )
    parser.add_argument(
        "--partial-seconds",
        type=float,
        default=1.0,
        help=(
            "Re-transcribe the unfinished segment after this much new audio and show "
            "it as a provisional line. 0 disables provisional lines."
        ),
    )
    parser.add_argument(
        "--poll-ms",
        type=int,
        default=150,
        help="Microphone polling interval in milliseconds",
    )
    return parser.parse_args()


def stdout(message: str) -> None:
    """Write a message to standard output and flush immediately."""
    sys.stdout.write(message)
    sys.stdout.flush()


def stderr(message: str) -> None:
    """Write a message to standard error and flush immediately."""
    sys.stderr.write(message)
    sys.stderr.flush()


def resolve_mlx_model_path(repo: str) -> Path:
    """Return the local model directory, downloading only if it is not cached.

    mlx-audio contacts Hugging Face whenever it gets a repo name, even for a
    cached model. Passing the cached directory instead keeps runs offline.
    """
    from huggingface_hub import snapshot_download
    from huggingface_hub.errors import LocalEntryNotFoundError
    from mlx_audio.utils import DEFAULT_ALLOW_PATTERNS, get_model_path

    try:
        # mlx-audio downloads only these patterns. Without them the cache
        # counts as incomplete because README.md and .gitattributes are missing.
        return Path(
            snapshot_download(
                repo,
                local_files_only=True,
                allow_patterns=DEFAULT_ALLOW_PATTERNS,
            )
        )
    except LocalEntryNotFoundError:
        stderr(f"{repo} is not cached yet, downloading...\n")
        return get_model_path(repo)


def load_transcriber(device: str) -> Callable[[np.ndarray], str]:
    """Load Parakeet for `device` and return a mono float32 audio -> text function."""
    # Import late so that --help stays fast.
    if device == "gpu":
        import mlx.core as mx
        from mlx_audio.stt import load

        stderr(f"device: gpu (MLX {mx.default_device()})\n")
        model_path = resolve_mlx_model_path(MLX_REPO)
        stderr(f"Loading model {model_path}...\n")
        model = load(model_path)
        return lambda audio: model.generate(mx.array(audio)).text.strip()

    import onnx_asr

    # Without an explicit provider, onnxruntime on macOS also uses CoreML.
    stderr("device: cpu (onnxruntime CPUExecutionProvider)\n")
    stderr(f"Loading model {ONNX_MODEL} int8...\n")
    # onnx-asr looks in the Hugging Face cache before downloading.
    model = onnx_asr.load_model(
        ONNX_MODEL,
        quantization="int8",
        providers=["CPUExecutionProvider"],
    )
    return lambda audio: model.recognize(audio, sample_rate=SAMPLE_RATE).strip()


def build_callback(
    pending_chunks: list[np.ndarray],
    lock: threading.Lock,
) -> object:
    """Create an input-stream callback that stores captured mono chunks."""

    def callback(
        indata: np.ndarray,
        _frames: int,
        _time: object,
        status: sd.CallbackFlags,
    ) -> None:
        """Collect incoming microphone chunks from the stream callback."""
        if status:
            stderr(f"{status}\n")
        with lock:
            pending_chunks.append(indata[:, 0].copy())

    return callback


def drain_pending(
    pending_chunks: list[np.ndarray],
    lock: threading.Lock,
) -> list[np.ndarray]:
    """Return and clear chunks captured since the last polling step."""
    with lock:
        fresh = pending_chunks.copy()
        pending_chunks.clear()
    return fresh


def print_partial(text: str) -> None:
    """Overwrite the current terminal line with a provisional transcription."""
    # Show only the tail that fits, since \r cannot return across wrapped lines.
    width = shutil.get_terminal_size().columns - 1
    stdout(f"{CLEAR_LINE}{text[-width:]}")


def print_final(text: str, show_partials: bool) -> None:
    """Print a finished segment, replacing its provisional line if one is shown."""
    prefix = CLEAR_LINE if show_partials else ""
    stdout(f"{prefix}{text}\n" if text else prefix)


def run_stream(args: argparse.Namespace) -> None:
    """Capture microphone audio and print one line per speech segment."""
    transcribe = load_transcriber(args.device)
    segmenter = SpeechSegmenter(args.min_silence_ms, args.max_segment_seconds)

    pending_chunks: list[np.ndarray] = []
    lock = threading.Lock()
    callback = build_callback(pending_chunks, lock)

    # Provisional lines need a terminal; in a pipe they would leave \r garbage.
    show_partials = args.partial_seconds > 0 and sys.stdout.isatty()
    partial_samples = int(args.partial_seconds * SAMPLE_RATE)
    last_partial_len = 0
    stderr("Streaming... press Ctrl+C to stop.\n")

    try:
        with sd.InputStream(
            samplerate=SAMPLE_RATE,
            channels=1,
            dtype="float32",
            callback=callback,
        ):
            while True:
                sd.sleep(args.poll_ms)
                fresh = drain_pending(pending_chunks, lock)
                if not fresh:
                    continue

                for segment in segmenter.feed(np.concatenate(fresh)):
                    print_final(transcribe(segment), show_partials)
                    last_partial_len = 0

                current = segmenter.current_segment
                if (
                    show_partials
                    and current is not None
                    and len(current) - last_partial_len >= partial_samples
                ):
                    print_partial(transcribe(current))
                    last_partial_len = len(current)
    except KeyboardInterrupt:
        fresh = drain_pending(pending_chunks, lock)
        if fresh:
            for segment in segmenter.feed(np.concatenate(fresh)):
                print_final(transcribe(segment), show_partials)
        if (segment := segmenter.flush()) is not None:
            print_final(transcribe(segment), show_partials)
        stderr("Stopped.\n")


def main() -> None:
    """Run streaming transcription from CLI arguments."""
    args = parse_args()
    run_stream(args)


if __name__ == "__main__":
    main()
