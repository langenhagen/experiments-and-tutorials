#!/usr/bin/env python3
"""Interactive Qwen3-TTS Gradio browser app.

Runs Qwen3-TTS 1.7B on Linux with an NVIDIA GPU or on an Apple Silicon Mac;
see qwen3_backend.py. Each mode uses its own model, which downloads on first use:

- Voice cloning (Base model): speaks in the voice of a reference recording.
  With the transcript of the recording, the clone is closer to the original;
  without it, only the voice's overall character is copied
- Preset voice (CustomVoice model): one of 9 built-in speakers; the
  instruction steers emotion and style, e.g. "Calm, even audiobook narration"
- Voice design (VoiceDesign model): the instruction describes a new voice,
  e.g. "A deep, calm male voice"

Settings:
- Precision: bf16 is full precision; 8, 6, 5, and 4-bit are smaller and
  faster, and only available on the Mac
- Language: auto detects the language from the text
- Temperature: randomness of the delivery; lower is steadier, higher livelier
- Top-k and top-p: how many candidate sounds the model may pick from
- Repetition penalty: discourages repeating the same sounds; on the Mac, voice
  cloning with a transcript uses at least 1.5
- Max tokens: limit per line of text; 12.5 tokens are one second of audio
- Each line of the text is spoken as its own piece, so put paragraphs on
  separate lines for long texts

See:
- https://github.com/QwenLM/Qwen3-TTS
- https://github.com/Blaizzy/mlx-audio
"""

import shutil
import time
from pathlib import Path

import gradio as gr
import numpy as np

import qwen3_backend

MODES = {
    "Voice cloning": "Base",
    "Preset voice": "CustomVoice",
    "Voice design": "VoiceDesign",
}

MODE_DESCRIPTIONS = {
    "Voice cloning": "Speaks in **your voice**, or anyone's: upload or record a "
    "reference recording below, ideally with its transcript.",
    "Preset voice": "Speaks with one of **9 built-in speakers**; an optional "
    "instruction steers the style, e.g. *Calm, even audiobook narration*.",
    "Voice design": "Speaks with a **new voice you describe in words**, e.g. "
    "*A deep, calm male voice with a slight rasp*.",
}

SAMPLE_TEXT = (
    "Wild jays flick past six quiet brown dogs on the foggy lawn. "
    "Jumping frogs vex bold quick wren by the pond's edge."
)


def generate(
    mode: str,
    precision: str,
    text: str,
    language: str,
    reference_audio: str | None,
    reference_text: str,
    speaker: str,
    instruct: str,
    temperature: float,
    top_k: float,
    top_p: float,
    repetition_penalty: float,
    max_tokens: float,
    seed: float,
) -> tuple[tuple[int, np.ndarray], str]:
    if seed != 0:
        qwen3_backend.seed(int(seed))

    start = time.monotonic()
    audio, sample_rate = qwen3_backend.generate(
        MODES[mode],
        text,
        language,
        precision=precision,
        speaker=speaker,
        instruct=instruct.strip() or None,
        ref_audio=reference_audio,
        ref_text=reference_text.strip() or None,
        temperature=temperature,
        top_k=int(top_k),
        top_p=top_p,
        repetition_penalty=repetition_penalty,
        max_new_tokens=int(max_tokens),
    )
    processing_seconds = time.monotonic() - start

    audio_seconds = len(audio) / sample_rate
    info = (
        f"Audio: {audio_seconds:.1f} s, processing: {processing_seconds:.1f} s "
        f"(including model loading), real-time factor: {audio_seconds / processing_seconds:.2f}x"
    )
    return (sample_rate, audio), info


def save_voice(
    name: str, reference_audio: str | None, reference_text: str
) -> gr.Dropdown:
    name = name.strip()
    if not name or reference_audio is None:
        raise gr.Error("Saving a voice needs a name and a reference audio.")
    voice_dir = qwen3_backend.VOICES_DIR / name
    voice_dir.mkdir(parents=True, exist_ok=True)
    for old_reference in voice_dir.glob("reference.*"):
        old_reference.unlink()
    shutil.copy(reference_audio, voice_dir / f"reference{Path(reference_audio).suffix}")
    (voice_dir / "transcript.txt").write_text(reference_text.strip())
    gr.Info(f"Saved voice {name} to {voice_dir}")
    return gr.Dropdown(choices=qwen3_backend.saved_voice_names(), value=name)


def load_voice(name: str | None) -> tuple[str, str]:
    if not name:
        raise gr.Error("Pick a saved voice first.")
    reference, transcript = qwen3_backend.load_saved_voice(name)
    return reference, transcript or ""


def change_mode(mode: str) -> tuple[str, gr.Group, gr.Dropdown, gr.Textbox]:
    instruct_labels = {
        "Voice cloning": "Instruction (not used for voice cloning)",
        "Preset voice": "Style instruction (optional), e.g. Calm, even audiobook narration",
        "Voice design": "Voice description (required), e.g. A deep, calm male voice with a slight rasp",
    }
    return (
        MODE_DESCRIPTIONS[mode],
        gr.Group(visible=mode == "Voice cloning"),
        gr.Dropdown(visible=mode == "Preset voice"),
        gr.Textbox(label=instruct_labels[mode], interactive=mode != "Voice cloning"),
    )


def main() -> None:
    with gr.Blocks(title="Qwen3-TTS Hello") as demo:
        with gr.Row():
            with gr.Column():
                mode = gr.Radio(
                    choices=list(MODES),
                    value="Voice cloning",
                    label="Mode",
                    info="Where the voice comes from",
                )
                mode_description = gr.Markdown(MODE_DESCRIPTIONS["Voice cloning"])
                precision = gr.Dropdown(
                    choices=qwen3_backend.PRECISIONS,
                    value=qwen3_backend.PRECISIONS[0],
                    label="Precision (bf16 is full precision; lower bits are smaller and faster, Mac only)",
                )
                text = gr.Textbox(
                    value=SAMPLE_TEXT,
                    label="Text to synthesize (each line is spoken as its own piece)",
                    max_lines=20,
                )
                language = gr.Dropdown(
                    choices=qwen3_backend.LANGUAGES, value="auto", label="Language"
                )
                with gr.Group(visible=True) as cloning_group:
                    reference_audio = gr.Audio(
                        sources=["upload", "microphone"],
                        type="filepath",
                        label="Reference audio to clone (a few seconds to about 30 s of clean speech)",
                    )
                    reference_text = gr.Textbox(
                        label="Transcript of the reference audio (optional; makes the clone closer)",
                    )
                    with gr.Row():
                        voice_name = gr.Textbox(
                            label="Voice name", placeholder="e.g. andreas-de", scale=3
                        )
                        save_button = gr.Button("Save voice", scale=1)
                    with gr.Row():
                        saved_voice = gr.Dropdown(
                            choices=qwen3_backend.saved_voice_names(),
                            value=None,
                            label="Saved voices (in the voices/ folder)",
                            scale=3,
                        )
                        load_button = gr.Button("Load voice", scale=1)
                speaker = gr.Dropdown(
                    choices=list(qwen3_backend.SPEAKERS.items()),
                    value="ryan",
                    label="Speaker",
                    visible=False,
                )
                instruct = gr.Textbox(
                    label="Instruction (not used for voice cloning)", interactive=False
                )
                with gr.Group():
                    temperature = gr.Slider(
                        0.1,
                        1.5,
                        step=0.05,
                        value=0.9,
                        label="Temperature (default 0.9)",
                        info="How adventurous the model is when picking the next bit of sound. "
                        "Lower sounds steadier and more even, but can get monotone; higher "
                        "sounds livelier, but slips and odd pronunciations become likelier.",
                    )
                    top_k = gr.Slider(
                        1,
                        200,
                        step=1,
                        value=50,
                        label="Top-k (default 50)",
                        info="The model only picks among this many most likely next sounds. "
                        "Lower is safer and more uniform; higher allows more variety.",
                    )
                    top_p = gr.Slider(
                        0.1,
                        1.0,
                        step=0.01,
                        value=1.0,
                        label="Top-p (default 1.0)",
                        info="The model only picks among the most likely next sounds that "
                        "together make up this share of the probability. 1.0 means no limit; "
                        "0.8 drops the unlikely 20 percent, which is safer.",
                    )
                    repetition_penalty = gr.Slider(
                        1.0,
                        2.0,
                        step=0.01,
                        value=1.05,
                        label="Repetition penalty (default 1.05)",
                        info="Makes the model less likely to repeat the same sounds, which "
                        "helps against stuttering and loops. Too high can sound unnatural. "
                        "On the Mac, voice cloning with a transcript uses at least 1.5.",
                    )
                    max_tokens = gr.Slider(
                        100,
                        8192,
                        step=1,
                        value=2048,
                        label="Max tokens per line (default 2048)",
                        info="The model produces audio in small pieces called tokens, 12.5 per "
                        "second. This caps how long one line of text may become and stops "
                        "runaway generation; 2048 tokens are about 2.7 minutes.",
                    )
                seed = gr.Number(
                    value=0,
                    label="Random seed (0 for random)",
                    info="The same seed with the same settings and text gives the same "
                    "audio, which helps to compare settings.",
                )
                run_button = gr.Button("Generate", variant="primary")

            with gr.Column():
                audio_output = gr.Audio(label="Output Audio")
                info_output = gr.Textbox(label="Generation Info", interactive=False)

        mode.change(
            fn=change_mode,
            inputs=mode,
            outputs=[mode_description, cloning_group, speaker, instruct],
        )
        save_button.click(
            fn=save_voice,
            inputs=[voice_name, reference_audio, reference_text],
            outputs=saved_voice,
        )
        load_button.click(
            fn=load_voice,
            inputs=saved_voice,
            outputs=[reference_audio, reference_text],
        )
        run_button.click(
            fn=generate,
            inputs=[
                mode,
                precision,
                text,
                language,
                reference_audio,
                reference_text,
                speaker,
                instruct,
                temperature,
                top_k,
                top_p,
                repetition_penalty,
                max_tokens,
                seed,
            ],
            outputs=[audio_output, info_output],
        )

    demo.launch()


if __name__ == "__main__":
    main()
