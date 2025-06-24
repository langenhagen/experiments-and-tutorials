#!/usr/bin/env python3
"""Showcase the use of the 3rd party TTS library `chatterbox` voice cloning -
creating audio from a source audio and a target voice sample.

For my CPU-only laptop, I had to hack most calls to `torch.load()` with an extra
argument `map_location=torch.device('cpu')` in the files
- .venv/lib/python3.11/site-packages/chatterbox/tts.py and
- .venv/lib/python3.11/site-packages/chatterbox/vc.py

Doesn't seem to show a progress bar.

Audio file should be less than ~1:20 minutes otherwise tensor is too big or so.

See:
- https://github.com/resemble-ai/chatterbox
"""

from pathlib import Path

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

audio_path = Path("output-synthesized-from-default.wav")
target_voice_path = Path("input-voice.m4a")

model = ChatterboxVC.from_pretrained(device)
wav = model.generate(
    audio=str(audio_path),
    target_voice_path=str(target_voice_path),
)
ta.save(
    f"output-cloning-of-{audio_path.name}-with-target-voice-{target_voice_path.name}.wav",
    wav,
    model.sr,
)
