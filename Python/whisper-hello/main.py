#!/usr/bin.env python3
"""Showcase `faster-whisper` for microphone speech-to-text, here with clipboard
output.

This script records from the microphone, runs local Whisper inference, and
copies the recognized text to the system clipboard. It can transcribe in the
source language or translate speech to English.

author: andreasl
"""

# pylint: disable=import-error

import argparse
import shutil
import sys
import tempfile
from pathlib import Path

import numpy as np
import pyperclip
import sounddevice as sd
from faster_whisper import WhisperModel
from scipy.io.wavfile import write as wav_write


def parse_args() -> argparse.Namespace:
    """Parse command-line flags for recording and Whisper inference."""
    parser = argparse.ArgumentParser(
        description="Record microphone audio, run Whisper, copy result to clipboard.",
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
        default="translate",
        choices=["transcribe", "translate"],
        help="Whisper task: translate to English or transcribe original language",
    )
    parser.add_argument(
        "--language",
        default=None,
        help="Language code, e.g. en, de, fr",
    )
    parser.add_argument("--sample-rate", type=int, default=16000)
    parser.add_argument("--channels", type=int, default=1)
    parser.add_argument(
        "--keep-audio",
        action="store_true",
        help="Keep recorded wav file and print its path",
    )
    return parser.parse_args()


def record_wav(path: Path, sample_rate: int, channels: int) -> None:
    """Record microphone audio until Ctrl+C and save as a WAV file."""
    chunks: list[np.ndarray] = []

    def callback(
        indata: np.ndarray,
        _frames: int,
        _time: object,
        status: sd.CallbackFlags,
    ) -> None:
        """Collect input stream chunks and report stream status warnings."""
        if status:
            stderr(f"{status}\n")
        chunks.append(indata.copy())

    stdout("Recording... press Ctrl+C to stop.\n")
    try:
        with sd.InputStream(
            samplerate=sample_rate,
            channels=channels,
            dtype="float32",
            callback=callback,
        ):
            while True:
                sd.sleep(200)
    except KeyboardInterrupt:
        stdout("Stopped recording.\n")

    if not chunks:
        msg = "No audio captured from microphone"
        raise RuntimeError(msg)

    audio = np.concatenate(chunks, axis=0)
    audio_int16 = np.clip(audio, -1, 1)
    audio_int16 = (audio_int16 * 32767).astype(np.int16)
    wav_write(path, sample_rate, audio_int16)


def whisper_to_text(
    wav_path: Path,
    args: argparse.Namespace,
) -> tuple[str, str | None]:
    """Run Whisper on a WAV file and return text plus detected language."""
    model = WhisperModel(
        args.model,
        device=args.device,
        compute_type=args.compute_type,
    )
    segments, info = model.transcribe(
        str(wav_path),
        task=args.task,
        language=args.language,
        vad_filter=True,
    )
    text = " ".join(
        segment.text.strip() for segment in segments if segment.text.strip()
    )
    return text.strip(), getattr(info, "language", None)


def copy_to_clipboard(text: str) -> str:
    """Copy text to the first available system clipboard utility."""
    pyperclip.copy(text)

    if shutil.which("wl-copy"):
        return "wl-copy"
    if shutil.which("xclip"):
        return "xclip"
    if shutil.which("xsel"):
        return "xsel"
    if shutil.which("pbcopy"):
        return "pbcopy"
    return "pyperclip backend"


def stdout(message: str) -> None:
    """Write a message to standard output."""
    sys.stdout.write(message)


def stderr(message: str) -> None:
    """Write a message to standard error."""
    sys.stderr.write(message)


def main() -> None:
    """Record audio, transcribe/translate it, and copy the result."""
    args = parse_args()

    with tempfile.NamedTemporaryFile(suffix=".wav", delete=not args.keep_audio) as tmp:
        wav_path = Path(tmp.name)
        record_wav(wav_path, sample_rate=args.sample_rate, channels=args.channels)

        stdout("Transcribing...\n")
        text, detected_language = whisper_to_text(
            wav_path=wav_path,
            args=args,
        )

        if not text:
            stderr("No speech detected.\n")
            sys.exit(1)

        command_used = copy_to_clipboard(text)
        stdout(f"{text}\n")
        if detected_language:
            stdout(f"Detected language: {detected_language}\n")
        stdout(f"Copied to clipboard with: {command_used}\n")

        if args.keep_audio:
            stdout(f"Saved recording: {wav_path}\n")


if __name__ == "__main__":
    main()
