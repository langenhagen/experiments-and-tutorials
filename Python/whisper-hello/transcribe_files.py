#!/usr/bin/env python3
"""Transcribe audio files with faster-whisper and save each result to a .txt file."""

# pylint: disable=import-error

import argparse
import sys
from pathlib import Path

from faster_whisper import WhisperModel


def parse_args() -> argparse.Namespace:
    """Parse command-line flags for transcription."""
    parser = argparse.ArgumentParser(
        description=(
            "Transcribe audio files with faster-whisper and save text to .txt files."
        ),
    )
    parser.add_argument(
        "files",
        nargs="+",
        help="Audio file paths (wav, ogg, mp3, flac, m4a, ...)",
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
        help="Whisper task: transcribe in original language or translate to English",
    )
    parser.add_argument(
        "--language",
        default=None,
        help="Language code (default: auto-detect per file)",
    )
    return parser.parse_args()


def stdout(message: str) -> None:
    """Write a message to standard output."""
    sys.stdout.write(message)


def stderr(message: str) -> None:
    """Write a message to standard error."""
    sys.stderr.write(message)


def main() -> None:
    """Transcribe audio files and save each result to a .txt file."""
    args = parse_args()

    paths = [Path(f) for f in args.files]
    missing = [p for p in paths if not p.is_file()]
    if missing:
        for p in missing:
            stderr(f"Error: file not found: {p}\n")
        sys.exit(1)

    stderr(f"Loading model '{args.model}' on device '{args.device}'...\n")
    model = WhisperModel(args.model, device=args.device, compute_type=args.compute_type)
    stderr(f"Model ready. Using device: {model.model.device}\n\n")

    for path in paths:
        stderr(f"[{path}]\n")
        segments, info = model.transcribe(
            str(path),
            task=args.task,
            language=args.language,
            vad_filter=True,
        )
        text = " ".join(
            segment.text.strip() for segment in segments if segment.text.strip()
        )

        output_path = path.with_suffix(path.suffix + ".txt")
        output_path.write_text(text, encoding="utf-8")

        stdout(f"{text}\n\n")
        lang = getattr(info, "language", None)
        stderr(f"  detected language: {lang}  ->  {output_path}\n")


if __name__ == "__main__":
    main()
