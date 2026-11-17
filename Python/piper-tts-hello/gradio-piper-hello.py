#!/usr/bin/env python3
"""Interactive Piper TTS Gradio browser app.

Runs Piper voices (small VITS models exported to ONNX) through `piper-tts` and
ONNX Runtime: on the CPU anywhere, and on an NVIDIA GPU under Linux. There is no
MLX version of Piper, so a Mac uses the CPU, which is already faster than real
time.

Piper has no voice cloning and no voice design: every voice is a separate
trained model. Voices come in 4 quality levels: x_low and low (16 kHz), medium
and high (22.05 kHz). Some voices hold many speakers, picked by name below.

- Language and voice: the list is read from the `rhasspy/piper-voices` catalog
  on Hugging Face; the chosen voice is downloaded to `.voices/` on first use
- Speaker: for voices with several speakers (for example en_US-libritts with 904)
- Device: CPU, or CUDA on Linux with an NVIDIA GPU
- Speed (length scale): below 1.0 is faster, above 1.0 slower; 1.0 is the default
- Sound variation (noise scale): randomness of the sound; 0.667 is the default
- Rhythm variation (noise W scale): randomness of the phoneme lengths; 0.8 is
  the default
- Volume: multiplier for the samples; 1.0 is the default
- Normalize: scale each sentence to the full range before the volume is applied

The sound and rhythm variation make every run differ slightly; there is no seed.
Raw espeak-ng phonemes can be put into the text as `[[ bˈætmæn ]]`.

See:
- https://github.com/OHF-Voice/piper1-gpl
- https://huggingface.co/rhasspy/piper-voices
"""

import functools
import json
import time
from pathlib import Path
from urllib.request import urlopen

import gradio as gr
import numpy as np
import onnxruntime
from piper import PiperVoice, SynthesisConfig
from piper.download_voices import VOICES_JSON, download_voice

VOICES_DIR = Path(__file__).parent / ".voices"
QUALITY_ORDER = ["x_low", "low", "medium", "high"]

SAMPLE_TEXTS = {
    "en": "Wild jays flick past six quiet brown dogs on the foggy lawn. "
    "Jumping frogs vex bold quick wren by the pond's edge.",
    "de": "Falsches Üben von Xylophonmusik quält jeden größeren Zwerg. "
    "Zwölf Boxkämpfer jagen Viktor quer über den großen Sylter Deich.",
}

# Thai needs the piper-tts[th] extra, whose gensim dependency does not build on Python 3.14.
UNSUPPORTED_LANGUAGES = {"th_TH"}

with urlopen(VOICES_JSON) as response:
    CATALOG = {
        key: voice
        for key, voice in json.load(response).items()
        if voice["language"]["code"] not in UNSUPPORTED_LANGUAGES
    }


def language_choices() -> list[tuple[str, str]]:
    languages = {
        voice["language"]["code"]: f"{voice['language']['name_english']} ({voice['language']['country_english']})"
        for voice in CATALOG.values()
    }
    return [(f"{name} - {code}", code) for code, name in sorted(languages.items(), key=lambda item: item[1])]


def voice_choices(language_code: str) -> list[tuple[str, str]]:
    voices = [voice for voice in CATALOG.values() if voice["language"]["code"] == language_code]
    voices.sort(key=lambda voice: (voice["name"], QUALITY_ORDER.index(voice["quality"])))
    return [
        (
            f"{voice['name']} - {voice['quality']} - "
            f"{voice['num_speakers']} speaker{'s' if voice['num_speakers'] > 1 else ''} - "
            f"{_model_size_mb(voice):.0f} MB",
            voice["key"],
        )
        for voice in voices
    ]


def _model_size_mb(voice: dict) -> float:
    (size_bytes,) = (file["size_bytes"] for path, file in voice["files"].items() if path.endswith(".onnx"))
    return size_bytes / 1e6


@functools.lru_cache(maxsize=1)  # Keep only one voice in memory at a time
def load_voice(voice_key: str, device: str) -> PiperVoice:
    VOICES_DIR.mkdir(exist_ok=True)
    download_voice(voice_key, VOICES_DIR)
    if device == "cuda":
        onnxruntime.preload_dlls()  # Find the CUDA libraries that pip installed
    return PiperVoice.load(VOICES_DIR / f"{voice_key}.onnx", use_cuda=device == "cuda")


def change_language(language_code: str) -> tuple[gr.Dropdown, str]:
    choices = voice_choices(language_code)
    text = SAMPLE_TEXTS.get(language_code.split("_")[0], gr.skip())
    return gr.Dropdown(choices=choices, value=choices[0][1]), text


def change_voice(voice_key: str) -> gr.Dropdown:
    speakers = CATALOG[voice_key]["speaker_id_map"]
    if not speakers:
        return gr.Dropdown(choices=[], value=None, visible=False)
    return gr.Dropdown(choices=list(speakers), value=next(iter(speakers)), visible=True)


def generate(
    device: str,
    voice_key: str,
    speaker: str | None,
    text: str,
    length_scale: float,
    noise_scale: float,
    noise_w_scale: float,
    volume: float,
    normalize_audio: bool,
) -> tuple[tuple[int, np.ndarray], str]:
    voice = load_voice(voice_key, device)
    syn_config = SynthesisConfig(
        speaker_id=CATALOG[voice_key]["speaker_id_map"][speaker] if speaker else None,
        length_scale=length_scale,
        noise_scale=noise_scale,
        noise_w_scale=noise_w_scale,
        volume=volume,
        normalize_audio=normalize_audio,
    )

    start = time.perf_counter()
    audio = np.concatenate([chunk.audio_float_array for chunk in voice.synthesize(text, syn_config)])
    processing_seconds = time.perf_counter() - start

    sample_rate = voice.config.sample_rate
    audio_seconds = len(audio) / sample_rate
    info = (
        f"Audio: {audio_seconds:.1f} s at {sample_rate} Hz, "
        f"real-time factor: {processing_seconds / audio_seconds:.2f}x, "
        f"processing: {processing_seconds:.1f} s"
    )
    return (sample_rate, audio), info


def main() -> None:
    cuda_available = "CUDAExecutionProvider" in onnxruntime.get_available_providers()
    default_voice = "en_US-lessac-medium"
    with gr.Blocks(title="Piper TTS Hello") as demo:
        with gr.Row():
            with gr.Column():
                device = gr.Radio(
                    choices=["cpu", "cuda"],
                    value="cuda" if cuda_available else "cpu",
                    label="Device",
                    info="cuda needs Linux with an NVIDIA GPU; there is no MLX or Metal version",
                )
                language = gr.Dropdown(choices=language_choices(), value="en_US", label="Language")
                voice = gr.Dropdown(
                    choices=voice_choices("en_US"),
                    value=default_voice,
                    label="Voice",
                    info="Quality x_low and low is 16 kHz, medium and high 22.05 kHz; "
                    "higher is a bigger model, slower, and finer",
                )
                speaker = gr.Dropdown(
                    choices=[],
                    value=None,
                    visible=False,
                    label="Speaker",
                    info="Voices with several speakers; for thorsten_emotional the speakers are emotions",
                )
                text = gr.Textbox(
                    value=SAMPLE_TEXTS["en"],
                    label="Text to synthesize",
                    info="Raw espeak-ng phonemes work in double brackets: I am the [[ bˈætmæn ]]",
                    max_lines=20,
                )
                length_scale = gr.Slider(
                    0.5,
                    2.0,
                    step=0.05,
                    value=1.0,
                    label="Speed (length scale)",
                    info="Below 1.0 is faster, above 1.0 slower; default 1.0",
                )
                noise_scale = gr.Slider(
                    0.0,
                    1.5,
                    step=0.01,
                    value=0.667,
                    label="Sound variation (noise scale)",
                    info="Randomness of the sound; default 0.667. With both variations at 0 the output is "
                    "identical on every run",
                )
                noise_w_scale = gr.Slider(
                    0.0,
                    1.5,
                    step=0.01,
                    value=0.8,
                    label="Rhythm variation (noise W scale)",
                    info="Randomness of the phoneme lengths, so of the rhythm; default 0.8",
                )
                volume = gr.Slider(
                    0.1,
                    2.0,
                    step=0.05,
                    value=1.0,
                    label="Volume",
                    info="Multiplier for the samples; default 1.0",
                )
                normalize_audio = gr.Checkbox(
                    value=True,
                    label="Normalize",
                    info="Scale each sentence to the full range before the volume is applied",
                )
                run_button = gr.Button("Generate", variant="primary")

            with gr.Column():
                audio_output = gr.Audio(label="Output Audio")
                info_output = gr.Textbox(label="Generation Info", interactive=False)

        language.input(fn=change_language, inputs=language, outputs=[voice, text]).then(
            fn=change_voice, inputs=voice, outputs=speaker
        )
        voice.input(fn=change_voice, inputs=voice, outputs=speaker)
        run_button.click(
            fn=generate,
            inputs=[device, voice, speaker, text, length_scale, noise_scale, noise_w_scale, volume, normalize_audio],
            outputs=[audio_output, info_output],
        )

    demo.launch()


if __name__ == "__main__":
    main()
