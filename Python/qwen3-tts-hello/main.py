"""Showcase Qwen3 TTS Model, a strong text-to-speach model.

Apparently available native english speakers:
- Aiden   Sunny American male voice, clear midrange
- Ryan    Dynamic male voice, strong rhythmic drive

"""

import soundfile as sf
import torch
from qwen_tts import Qwen3TTSModel

model = Qwen3TTSModel.from_pretrained(
    "Qwen/Qwen3-TTS-12Hz-1.7B-CustomVoice",
    device_map="cuda:0",
    dtype=torch.bfloat16,
    attn_implementation="sdpa",
)

wavs, sr = model.generate_custom_voice(
    text="Hello! This is Qwen three TTS running locally!",
    language="English",
    # speaker="Ryan",
    speaker="Aiden",
)

sf.write("output.wav", wavs[0], sr)
