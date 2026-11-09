#!/usr/bin/env python3
"""Stream microphone speech-to-text to stdout with Voxtral Mini 4B Realtime.

Voxtral Realtime consumes audio as it arrives and emits text with a fixed lag
(--delay-ms) instead of transcribing fixed chunks. The language is detected
automatically.

Usage

    uv run python stream_stdout.py --help
    uv run python stream_stdout.py
    uv run python stream_stdout.py --delay-ms 480
    uv run python stream_stdout.py --model fp16
    uv run python stream_stdout.py --device cpu

The first run of each model downloads its weights (4bit about 3 GB, fp16
about 9 GB). Later runs load the cached weights without network access.

author: andreasl
"""

# pylint: disable=import-error

import argparse
import queue
import sys
from pathlib import Path
from typing import TYPE_CHECKING

import numpy as np
import sounddevice as sd

if TYPE_CHECKING:
    from mlx_audio.stt.models.voxtral_realtime.streaming import VoxtralStreamingSession

# --model name -> Hugging Face repo
MODELS = {
    "4bit": "mlx-community/Voxtral-Mini-4B-Realtime-2602-4bit",
    "fp16": "mlx-community/Voxtral-Mini-4B-Realtime-2602-fp16",
}

# Voxtral Realtime expects 16 kHz mono audio.
SAMPLE_RATE = 16000


def parse_args() -> argparse.Namespace:
    """Parse command-line options for streaming transcription."""
    parser = argparse.ArgumentParser(
        description="Stream microphone transcription to stdout.",
    )
    parser.add_argument(
        "--model",
        default="4bit",
        choices=list(MODELS),
        help="4bit is faster and smaller. fp16 is the unquantized model.",
    )
    parser.add_argument(
        "--device",
        default="gpu",
        choices=["gpu", "cpu"],
        help="MLX device. cpu is much slower but avoids competing for a busy GPU.",
    )
    parser.add_argument(
        "--delay-ms",
        type=int,
        default=960,
        help=(
            "How far the text lags behind the audio, in multiples of 80 ms. "
            "Lower is faster but less accurate. Mistral recommends 480."
        ),
    )
    parser.add_argument(
        "--block-ms",
        type=int,
        default=100,
        help="Microphone block size in milliseconds",
    )
    parser.add_argument(
        "--max-tokens",
        type=int,
        default=4096,
        help=(
            "Tokens per session before starting a new one. "
            "One token covers 80 ms of audio, so 4096 is about 5.5 minutes."
        ),
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


def build_callback(audio_queue: "queue.Queue[np.ndarray]") -> object:
    """Create an input-stream callback that queues captured mono samples."""

    def callback(
        indata: np.ndarray,
        _frames: int,
        _time: object,
        status: sd.CallbackFlags,
    ) -> None:
        """Queue incoming microphone samples from the stream callback."""
        if status:
            stderr(f"{status}\n")
        audio_queue.put(indata[:, 0].copy())

    return callback


def feed_queued_audio(
    audio_queue: "queue.Queue[np.ndarray]",
    session: "VoxtralStreamingSession",
) -> None:
    """Wait briefly for audio, then feed everything queued to the session."""
    try:
        session.feed(audio_queue.get(timeout=0.1))
    except queue.Empty:
        return
    while True:
        try:
            session.feed(audio_queue.get_nowait())
        except queue.Empty:
            return


def resolve_model_path(repo: str) -> Path:
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


def run_stream(args: argparse.Namespace) -> None:
    """Capture microphone audio and print transcription deltas."""
    # Import late so that --help stays fast.
    import mlx.core as mx

    # Set the device before importing mlx_audio, which creates an MLX stream on
    # the default device at import time.
    mx.set_default_device(mx.cpu if args.device == "cpu" else mx.gpu)
    stderr(f"device: {mx.default_device()}\n")

    from mlx_audio.stt import load

    model_path = resolve_model_path(MODELS[args.model])
    stderr(f"Loading model {model_path}...\n")
    model = load(model_path)

    def new_session() -> "VoxtralStreamingSession":
        return model.create_streaming_session(
            max_tokens=args.max_tokens,
            transcription_delay_ms=args.delay_ms,
        )

    audio_queue: queue.Queue[np.ndarray] = queue.Queue()
    session = new_session()
    stderr("Streaming... press Ctrl+C to stop.\n")

    try:
        with sd.InputStream(
            samplerate=SAMPLE_RATE,
            channels=1,
            dtype="float32",
            blocksize=int(SAMPLE_RATE * args.block_ms / 1000),
            callback=build_callback(audio_queue),
        ):
            while True:
                feed_queued_audio(audio_queue, session)
                for delta in session.step(max_decode_tokens=16):
                    stdout(delta)
                if session.done:
                    stdout("\n")
                    session = new_session()
    except KeyboardInterrupt:
        # Flush the audio still queued or inside the delay window.
        feed_queued_audio(audio_queue, session)
        session.close()
        while not session.done:
            for delta in session.step(max_decode_tokens=64):
                stdout(delta)
        stdout("\n")
        stderr("Stopped.\n")


def main() -> None:
    """Run streaming transcription from CLI arguments."""
    args = parse_args()
    run_stream(args)


if __name__ == "__main__":
    main()
