#!/usr/bin/env python3
"""Showcase the AI powered speech-to-text package Vosk.

Vosk is apparently rather reliable and friendly on the CPU.
Not the fastest on the CPU of course.

author: andreasl
"""

import json
import sys
from contextlib import suppress
from pathlib import Path

import pyaudio
from vosk import KaldiRecognizer, Model


def main() -> None:
    """Program main entry point."""

    # Ensure the model is downloaded
    if not Path("model"):
        print(
            "Please download the model from https://alphacephei.com/vosk/models and unpack as 'model' in the current folder."
        )
        sys.exit(1)

    # Load the Vosk model
    model = Model("model")
    recognizer = KaldiRecognizer(model, 16000)

    # Initialize PyAudio
    p = pyaudio.PyAudio()
    stream = p.open(
        format=pyaudio.paInt16,
        channels=1,
        rate=16000,
        input=True,
        frames_per_buffer=8000,
    )
    stream.start_stream()

    print("Say something...")

    while True:
        audio_data = stream.read(1000)
        if recognizer.AcceptWaveform(audio_data):
            result = recognizer.Result()
            result_json = json.loads(result)
            text = result_json["text"]
            if not text:
                continue

            print(text)


if __name__ == "__main__":
    with suppress(KeyboardInterrupt):
        main()
