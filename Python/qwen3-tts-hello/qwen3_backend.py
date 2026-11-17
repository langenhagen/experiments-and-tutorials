"""Run Qwen3-TTS with the library that fits the platform.

- Linux: the official `qwen-tts` package on an NVIDIA GPU, full precision only
- macOS: the MLX port in `mlx-audio` on the Apple Silicon GPU, in full
  precision (bf16) or smaller 8, 6, 5, and 4-bit versions

Both libraries load the same three Qwen3-TTS 1.7B models and use the same
lowercase language and speaker names. This module hides what differs: model
IDs, loading, the voice-cloning call, and the type of the returned audio.

The three model kinds:
- Base: voice cloning from a reference recording, ideally with its transcript;
  without the transcript only the voice's overall character is copied
- CustomVoice: 9 preset speakers, optionally steered by a style instruction
- VoiceDesign: a new voice described in words by the instruction

- `generate_chunks()` synthesizes a text and returns the text and audio of
  each chunk as its own piece, plus the sample rate
- `generate()` does the same and joins the pieces into one audio
- `seed()` makes the next generations reproducible
- `saved_voice_names()` and `load_saved_voice()` read voice clones saved in
  voices/NAME/ as reference.<ext> plus transcript.txt, e.g. by the Gradio app
"""

import functools
import sys
from pathlib import Path

import numpy as np

import chunking

USES_MLX = sys.platform == "darwin"

if USES_MLX:
    import mlx.core as mx
    from mlx_audio.tts.utils import load
else:
    import torch
    from qwen_tts import Qwen3TTSModel

VOICES_DIR = Path(__file__).parent / "voices"

MODEL_KINDS = ["Base", "CustomVoice", "VoiceDesign"]
PRECISIONS = ["bf16", "8bit", "6bit", "5bit", "4bit"] if USES_MLX else ["bf16"]

LANGUAGES = [
    "auto",
    "english",
    "german",
    "chinese",
    "french",
    "italian",
    "japanese",
    "korean",
    "portuguese",
    "russian",
    "spanish",
]

# Preset speakers of the CustomVoice model, labeled with their native language.
SPEAKERS = {
    "Ryan (English)": "ryan",
    "Aiden (English)": "aiden",
    "Vivian (Chinese)": "vivian",
    "Serena (Chinese)": "serena",
    "Uncle_Fu (Chinese)": "uncle_fu",
    "Dylan (Beijing dialect)": "dylan",
    "Eric (Sichuan dialect)": "eric",
    "Ono_Anna (Japanese)": "ono_anna",
    "Sohee (Korean)": "sohee",
}


@functools.lru_cache(maxsize=1)  # Keep only one model in memory at a time
def load_model(kind: str, precision: str):
    """Load a model kind in a precision with the library of this platform."""
    if USES_MLX:
        return load(f"mlx-community/Qwen3-TTS-12Hz-1.7B-{kind}-{precision}")
    if precision != "bf16":
        raise ValueError(f"qwen-tts on Linux supports only bf16, not {precision}")
    return Qwen3TTSModel.from_pretrained(
        f"Qwen/Qwen3-TTS-12Hz-1.7B-{kind}",
        device_map="cuda:0",
        dtype=torch.bfloat16,
        attn_implementation="sdpa",
    )


@functools.lru_cache(maxsize=8)
def _clone_prompt(model, ref_audio: str, ref_text: str | None):
    """Analyse a reference recording once and reuse it; qwen-tts only."""
    return model.create_voice_clone_prompt(
        ref_audio=ref_audio, ref_text=ref_text, x_vector_only_mode=ref_text is None
    )


def saved_voice_names() -> list[str]:
    """Return the names of the voice clones saved in voices/."""
    return sorted(path.parent.name for path in VOICES_DIR.glob("*/transcript.txt"))


def load_saved_voice(name: str) -> tuple[str, str | None]:
    """Return the reference audio path and transcript of a saved voice clone."""
    voice_dir = VOICES_DIR / name
    if not (voice_dir / "transcript.txt").exists():
        raise ValueError(
            f"No saved voice {name!r} in {VOICES_DIR}; saved voices: {saved_voice_names()}"
        )
    (reference,) = voice_dir.glob("reference.*")
    return str(reference), (voice_dir / "transcript.txt").read_text().strip() or None


def seed(value: int) -> None:
    """Seed the random generator of the platform library."""
    if USES_MLX:
        mx.random.seed(value)
    else:
        torch.manual_seed(value)


def _generate_piece(
    model, kind: str, text: str, language: str, voice: dict, sampling: dict
) -> tuple[np.ndarray, int]:
    if USES_MLX:
        mlx_sampling = {
            ("max_tokens" if name == "max_new_tokens" else name): value
            for name, value in sampling.items()
        }
        if kind == "Base":
            # split_pattern="" because generate() already splits the text into chunks.
            results = model.generate(
                text=text, lang_code=language, split_pattern="", **voice, **mlx_sampling
            )
        elif kind == "CustomVoice":
            results = model.generate_custom_voice(
                text=text, language=language, **voice, **mlx_sampling
            )
        else:
            results = model.generate_voice_design(
                text=text, language=language, **voice, **mlx_sampling
            )
        return np.concatenate(
            [np.array(result.audio) for result in results]
        ), model.sample_rate

    if kind == "Base":
        prompt = _clone_prompt(model, voice["ref_audio"], voice["ref_text"])
        wavs, sample_rate = model.generate_voice_clone(
            text=text, language=language, voice_clone_prompt=prompt, **sampling
        )
    elif kind == "CustomVoice":
        wavs, sample_rate = model.generate_custom_voice(
            text=text, language=language, **voice, **sampling
        )
    else:
        wavs, sample_rate = model.generate_voice_design(
            text=text, language=language, **voice, **sampling
        )
    return wavs[0], sample_rate


def generate_chunks(
    kind: str,
    text: str,
    language: str = "auto",
    *,
    precision: str = "bf16",
    speaker: str | None = None,
    instruct: str | None = None,
    ref_audio: str | None = None,
    ref_text: str | None = None,
    temperature: float = 0.9,
    top_k: int = 50,
    top_p: float = 1.0,
    repetition_penalty: float = 1.05,
    max_new_tokens: int = 2048,
    max_chars: int = chunking.DEFAULT_MAX_CHARS,
) -> tuple[list[tuple[str, np.ndarray]], int]:
    """Synthesize text chunk by chunk (see chunking.py) and return each chunk's
    text and audio, plus the sample rate. Speaker is for CustomVoice, instruct
    for CustomVoice and VoiceDesign, ref_audio and the optional ref_text for
    Base.
    """
    model = load_model(kind, precision)
    voice = {
        "Base": {"ref_audio": ref_audio, "ref_text": ref_text},
        "CustomVoice": {"speaker": speaker, "instruct": instruct},
        "VoiceDesign": {"instruct": instruct},
    }[kind]

    chunks = []
    for chunk in chunking.split_chunks(text, max_chars):
        sampling = {
            "temperature": temperature,
            "top_k": top_k,
            "top_p": top_p,
            "repetition_penalty": repetition_penalty,
            "max_new_tokens": max_new_tokens,
        }
        audio, sample_rate = _generate_piece(
            model, kind, chunk, language, voice, sampling
        )
        chunks.append((chunk, audio))
    return chunks, sample_rate


def generate(*args, **kwargs) -> tuple[np.ndarray, int]:
    """Like generate_chunks(), but return all chunks joined into one audio."""
    chunks, sample_rate = generate_chunks(*args, **kwargs)
    return np.concatenate([audio for _, audio in chunks]), sample_rate
