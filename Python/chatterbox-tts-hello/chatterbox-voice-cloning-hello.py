#!/usr/bin/env python3
"""Showcase the use of the 3rd party TTS library `chatterbox` voice cloning -
creating audio from a source audio and a target voice sample.

For my CPU-only laptop, I had to hack most calls to `torch.load()` with an extra
argument `map_location=torch.device('cpu')` in the files
- .venv/lib/python3.11/site-packages/chatterbox/tts.py and
- .venv/lib/python3.11/site-packages/chatterbox/vc.py

See:
- https://github.com/resemble-ai/chatterbox
"""

import torch
import torchaudio as ta
from chatterbox.vc import ChatterboxVC

print("--- tts with voice cloning ---\n")

if torch.cuda.is_available():
    device = "cuda"
elif torch.backends.mps.is_available():
    device = "mps"
else:
    device = "cpu"

print(f"Using device: {device}")

AUDIO_PATH = "output-pretrained.wav"
TARGET_VOICE_PATH = "input-voice.m4a"

model = ChatterboxVC.from_pretrained(device)
wav = model.generate(
    audio=AUDIO_PATH,
    target_voice_path=TARGET_VOICE_PATH,
)
ta.save("output-cloning.wav", wav, model.sr)
