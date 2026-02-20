#!/usr/bin/env python3
"""Stream microphone speech-to-text to stdout in fixed chunks."""

# pylint: disable=import-error

import argparse
import sys
import tempfile
import threading
from pathlib import Path

import numpy as np
import sounddevice as sd
from faster_whisper import WhisperModel
from scipy.io.wavfile import write as wav_write


def parse_args() -> argparse.Namespace:
    """Parse command-line options for chunked streaming transcription."""
    parser = argparse.ArgumentParser(
        description="Stream microphone transcription/translation to stdout.",
    )
    parser.add_argument("--model", default="base", help="Whisper model name/path")
    parser.add_argument(
        "--device",
        default="auto",
        help="Whisper device: auto, cpu, cuda",
    )
    parser.add_argument(
        "--compute-type",
        default="int8",
        help="faster-whisper compute type (int8, float16, float32, ...)",
    )
    parser.add_argument(
        "--task",
        default="transcribe",
        choices=["transcribe", "translate"],
        help="Whisper task: transcribe original language or translate to English",
    )
    parser.add_argument(
        "--language",
        default=None,
        help="Language code, e.g. en, de, fr",
    )
    parser.add_argument("--sample-rate", type=int, default=16000)
    parser.add_argument("--channels", type=int, default=1)
    parser.add_argument(
        "--chunk-seconds",
        type=float,
        default=2.5,
        help="Audio duration to decode per update",
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


def build_callback(
    pending_chunks: list[np.ndarray],
    lock: threading.Lock,
) -> object:
    """Create an input-stream callback that stores captured chunks."""

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
            pending_chunks.append(indata.copy())

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


def append_chunks(buffer: np.ndarray, fresh_chunks: list[np.ndarray]) -> np.ndarray:
    """Append fresh chunks to the undecoded audio buffer."""
    if not fresh_chunks:
        return buffer
    return np.concatenate([buffer, *fresh_chunks], axis=0)


def to_wav_data(audio: np.ndarray, channels: int) -> np.ndarray:
    """Convert float32 stream audio to int16 wav payload."""
    audio_int16 = np.clip(audio, -1, 1)
    audio_int16 = (audio_int16 * 32767).astype(np.int16)
    if channels == 1:
        return audio_int16[:, 0]
    return audio_int16


def transcribe_chunk(
    model: WhisperModel,
    wav_path: Path,
    args: argparse.Namespace,
    audio: np.ndarray,
) -> str:
    """Run Whisper on one chunk and return normalized text."""
    wav_write(wav_path, args.sample_rate, to_wav_data(audio, args.channels))
    segments, _ = model.transcribe(
        str(wav_path),
        task=args.task,
        language=args.language,
        vad_filter=False,
        condition_on_previous_text=False,
        beam_size=1,
    )
    return " ".join(
        segment.text.strip() for segment in segments if segment.text.strip()
    ).strip()


def run_stream(args: argparse.Namespace) -> None:
    """Capture microphone audio and print chunked transcriptions."""
    stderr("Streaming... press Ctrl+C to stop.\n")
    stderr("Loading model...\n")
    model = WhisperModel(
        args.model,
        device=args.device,
        compute_type=args.compute_type,
    )
    stderr("Model ready.\n")

    pending_chunks: list[np.ndarray] = []
    lock = threading.Lock()
    callback = build_callback(pending_chunks, lock)

    chunk_samples = max(1, int(args.chunk_seconds * args.sample_rate))
    min_final_samples = max(1, int(0.4 * args.sample_rate))
    buffer = np.empty((0, args.channels), dtype=np.float32)

    with tempfile.NamedTemporaryFile(suffix=".wav") as tmp:
        wav_path = Path(tmp.name)

        try:
            with sd.InputStream(
                samplerate=args.sample_rate,
                channels=args.channels,
                dtype="float32",
                callback=callback,
            ):
                while True:
                    sd.sleep(args.poll_ms)
                    fresh = drain_pending(pending_chunks, lock)
                    buffer = append_chunks(buffer, fresh)

                    while len(buffer) >= chunk_samples:
                        chunk = buffer[:chunk_samples]
                        buffer = buffer[chunk_samples:]
                        text = transcribe_chunk(model, wav_path, args, chunk)
                        if text:
                            stdout(f"{text}\n")
        except KeyboardInterrupt:
            if len(buffer) >= min_final_samples:
                text = transcribe_chunk(model, wav_path, args, buffer)
                if text:
                    stdout(f"{text}\n")
            stderr("Stopped.\n")


def main() -> None:
    """Run streaming transcription from CLI arguments."""
    args = parse_args()
    run_stream(args)


if __name__ == "__main__":
    main()
