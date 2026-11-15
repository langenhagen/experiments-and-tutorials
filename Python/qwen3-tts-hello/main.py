"""Showcase Qwen3 TTS Model, a strong text-to-speach model.

Runs on Linux with an NVIDIA GPU or on an Apple Silicon Mac; see qwen3_backend.py.

Apparently available native english speakers:
- Aiden   Sunny American male voice, clear midrange
- Ryan    Dynamic male voice, strong rhythmic drive

"""

import soundfile as sf

import qwen3_backend

audio, sample_rate = qwen3_backend.generate(
    "CustomVoice",
    "Hello! This is Qwen three TTS running locally!",
    "english",
    # speaker="ryan",
    speaker="aiden",
)

sf.write("output.wav", audio, sample_rate)
