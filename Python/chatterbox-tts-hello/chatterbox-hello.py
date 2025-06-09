#!/usr/bin/env python3
"""Showcase the use of the 3rd party TTS library `chatterbox`.

It's really viable for easy voice synthetization and voice cloning.

For my CPU-only laptop, I had to hack
`.venv/lib/python3.11/site-packages/chatterbox/tts.py` with most calls to
`torch.load()` with an extra argument `map_location=torch.device('cpu')`.

See:
- https://github.com/resemble-ai/chatterbox
"""

import torch
import torchaudio as ta
from chatterbox.tts import ChatterboxTTS

print("--- 1 tts with pretrained voice ---\n")

# Automatically detect the best available device
if torch.cuda.is_available():
    device = "cuda"
elif torch.backends.mps.is_available():
    device = "mps"
else:
    device = "cpu"

print(f"Using device: {device}")


model = ChatterboxTTS.from_pretrained(device=device)

text = "Ezreal and Jinx teamed up with Ahri, Yasuo, and Teemo to take down the enemy's Nexus in an epic late-game pentakill."
# text = "Ich frage mich ob das ding auch Deutsch kann [laughs]"  # sounds like an English native speaker that tries german
# text = """
# The sun dipped behind the old city’s skyline, painting the buildings in soft gold and pink. A gentle
# breeze carried the scent of rain over cobblestone streets, where people wandered home beneath the
# flicker of lanterns. Somewhere, a stray cat curled up on a warm window ledge, purring quietly as the
# world slowed down. Night crept in, steady and calm, folding the day away and promising new stories
# by morning.
# """

wav = model.generate(text)
ta.save("output-pretrained.wav", wav, model.sr)
