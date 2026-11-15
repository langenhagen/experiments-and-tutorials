#!/usr/bin/env python3
"""Interactive Gradio browser app for the local Tontaube inference server.

Start the server first with `./tontaube-serve.sh`. The server posts its full
request schema at http://127.0.0.1:8080/docs.

- Language: selects the language and filters the bundled voices
- Voice: a bundled voice reference; an uploaded or recorded reference audio
  overrides it and clones that voice instead
- Style: audiobook, conversational, or agentic delivery
- Temperature: lower values favor correctness, higher values sound more expressive
- Style sliders: -1 keeps the model's own choice; 0 is the low end
"""

import base64
import json
import tempfile
from pathlib import Path

import gradio as gr
import httpx

SERVER_URL = "http://127.0.0.1:8080"

# Generating long texts takes minutes, especially on the CPU.
TIMEOUT_SECONDS = 600.0

# Voice catalogue of the upstream clone, which matches the server's voices.
VOICES_DIR = Path(__file__).parent / "tontaube-server" / "voices"

# Manifest language codes mapped to the server's language names.
LANGUAGES = {
    "en": "english",
    "de": "german",
    "es": "spanish",
    "fr": "french",
    "it": "italian",
    "nl": "dutch",
    "pt": "portuguese",
}
TAGS = ["audiobook", "conversational", "agentic"]

# Style sliders of TontaubeV1.1 with their highest step; every slider starts at 0.
CONTROL_MAX_STEPS = {"rate": 4, "pause": 4, "pitch_range": 5, "pitch_level": 4, "legato": 2}

SAMPLE_TEXTS = {
    "english": "Wild jays flick past six quiet brown dogs on the foggy lawn. "
    "Jumping frogs vex bold quick wren by the pond's edge.",
    "german": "Falsches Üben von Xylophonmusik quält jeden größeren Zwerg. "
    "Zwölf Boxkämpfer jagen Viktor quer über den großen Sylter Deich.",
}


def voice_choices(language: str) -> gr.Dropdown:
    """List the bundled voices for a language; voices without a language fit every language."""
    manifest = json.loads((VOICES_DIR / "manifest.json").read_text())["voices"]
    choices = [
        (path.stem, f"samples/{path.name}")
        for path in sorted((VOICES_DIR / "samples").iterdir())
        if LANGUAGES.get(manifest.get(path.name, {}).get("language"), language) == language
    ]
    return gr.Dropdown(choices=choices, value=choices[0][1])


def change_language(language: str) -> tuple[gr.Dropdown, str]:
    return voice_choices(language), SAMPLE_TEXTS.get(language, "")


def generate(
    text: str,
    language: str,
    voice_path: str,
    reference_audio: str | None,
    tag: str,
    temperature: float,
    seed: float,
    *control_steps: float,
) -> str:
    request = {"text": text, "language": language, "tag": tag, "temperature": temperature, "format": "wav"}
    if reference_audio is None:
        request["voice_path"] = voice_path
    else:
        request["voice_audio_b64"] = base64.b64encode(Path(reference_audio).read_bytes()).decode()
    if seed != 0:
        request["seed"] = int(seed)
    controls = {name: int(step) for name, step in zip(CONTROL_MAX_STEPS, control_steps) if step >= 0}
    if controls:
        request["controls"] = controls

    response = httpx.post(f"{SERVER_URL}/predict", json=request, timeout=TIMEOUT_SECONDS)
    if response.is_error:
        raise gr.Error(f"Tontaube server answered {response.status_code}: {response.text}")

    with tempfile.NamedTemporaryFile(suffix=".wav", delete=False) as audio_file:
        audio_file.write(base64.b64decode(response.json()["audio_b64"]))
    return audio_file.name


def main() -> None:
    with gr.Blocks(title="Tontaube Hello") as demo:
        with gr.Row():
            with gr.Column():
                text = gr.Textbox(value=SAMPLE_TEXTS["english"], label="Text to synthesize", max_lines=20)
                language = gr.Dropdown(choices=list(LANGUAGES.values()), value="english", label="Language")
                voice = gr.Dropdown(label="Bundled voice")
                reference_audio = gr.Audio(
                    sources=["upload", "microphone"],
                    type="filepath",
                    label="Reference audio for voice cloning (5 to 60 s of clean speech; overrides the bundled voice)",
                )
                tag = gr.Radio(choices=TAGS, value="audiobook", label="Style")
                temperature = gr.Slider(
                    0.0,
                    2.0,
                    step=0.05,
                    value=0.7,
                    label="Temperature (low favors correctness, high sounds more expressive)",
                )
                seed = gr.Number(value=0, label="Random seed (0 for random)")
                with gr.Accordion("Style sliders (-1 keeps the model's choice)", open=False):
                    control_sliders = [
                        gr.Slider(-1, max_step, step=1, value=-1, label=name)
                        for name, max_step in CONTROL_MAX_STEPS.items()
                    ]
                run_button = gr.Button("Generate", variant="primary")

            with gr.Column():
                audio_output = gr.Audio(label="Output Audio", type="filepath")

        demo.load(fn=voice_choices, inputs=language, outputs=voice)
        language.input(fn=change_language, inputs=language, outputs=[voice, text])
        run_button.click(
            fn=generate,
            inputs=[text, language, voice, reference_audio, tag, temperature, seed, *control_sliders],
            outputs=audio_output,
        )

    demo.launch()


if __name__ == "__main__":
    main()
