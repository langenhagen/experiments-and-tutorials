#!/usr/bin/env python3
"""Showcase the use of the 3rd party TTS library `chatterbox` voice
synthetization - creating audio from a text with a given voice sample.

For my CPU-only laptop, I had to hack
`.venv/lib/python3.11/site-packages/chatterbox/tts.py` with most calls to
`torch.load()` with an extra argument `map_location=torch.device('cpu')`.

See:
- https://github.com/resemble-ai/chatterbox
"""

from pathlib import Path

import torch
import torchaudio as ta
from chatterbox.tts import ChatterboxTTS

print("--- tts with voice synthetization ---\n")

if torch.cuda.is_available():
    device = "cuda"
elif torch.backends.mps.is_available():
    device = "mps"
else:
    device = "cpu"

print(f"Using device: {device}")

text = """
The sun dipped behind the old city's skyline, painting the buildings in soft gold and pink. A gentle
breeze carried the scent of rain over cobblestone streets, where people wandered home beneath the
flicker of lanterns. Somewhere, a stray cat curled up on a warm window ledge, purring quietly as the
world slowed down. Night crept in, steady and calm, folding the day away and promising new stories
by morning.
"""
text = "Ich frage mich ob das ding auch Deutsch kann [laughs]"  # sounds like an English native speaker that tries german
text = "Ezreal and Jinx teamed up with Ahri, Yasuo, and Teemo to take down the enemy's Nexus in an epic late-game pentakill."

model = ChatterboxTTS.from_pretrained(device=device)

# change this if you want to synthesize with a different voice,
# otherwise set to `None` for default voice
source_audio = None
# source_audio = Path("input.mp3")

audio_prompt_path = str(source_audio) if source_audio else None
wav = model.generate(text, audio_prompt_path=audio_prompt_path)

source_filename = source_audio.name if source_audio else "default"
ta.save(f"output-synthesized-from-{source_filename}.wav", wav, model.sr)
