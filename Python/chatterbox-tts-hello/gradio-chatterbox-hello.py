#!/usr/bin/env python3
"""Interactive Chatterbox Gradio browser app.

- Exaggeration: how expressive or dramatic the speech sounds; higher values: more emotion
- CFG/Pace: how fast or slow the speech is delivered; lower values: slower speech
- Temperature: Adjusts randomness/creativity in voice generation; higher values: more variation.
"""

import random

import gradio as gr
import numpy as np
import torch
from chatterbox.tts import ChatterboxTTS


def set_seed(seed: int) -> None:
    torch.manual_seed(seed)
    torch.cuda.manual_seed(seed)
    torch.cuda.manual_seed_all(seed)
    random.seed(seed)
    np.random.seed(seed)


def load_model(device: str) -> ChatterboxTTS:
    return ChatterboxTTS.from_pretrained(device=device)


def generate(
    device: str,
    model,
    text,
    audio_prompt_path,
    exaggeration,
    temperature,
    seed_num,
    cfgw,
) -> tuple:
    if model is None:
        model = ChatterboxTTS.from_pretrained(device=device)

    if seed_num != 0:
        set_seed(int(seed_num))

    wav = model.generate(
        text=text,
        audio_prompt_path=audio_prompt_path,
        exaggeration=exaggeration,
        temperature=temperature,
        cfg_weight=cfgw,
    )
    return (model.sr, wav.squeeze(0).numpy())


def main() -> None:
    if torch.cuda.is_available():
        device = "cuda"
    elif torch.backends.mps.is_available():
        device = "mps"
    else:
        device = "cpu"

    with gr.Blocks(title="Chatterbox Hello") as demo:
        model_state = gr.State(None)  # Loaded once per session/user

        with gr.Row():
            with gr.Column():
                text = gr.Textbox(
                    value="""
Wild jays flick past six quiet brown dogs on the foggy lawn.
Jumping frogs vex bold quick wren by the pond's edge.
Glib nymphs dwarf jack's cozy hut, vexing poor, quiet boy.
""",
                    label="Text to synthesize",
                    max_lines=20,
                )
                reference_audio = gr.Audio(
                    sources=["upload", "microphone"],
                    type="filepath",
                    label="Reference Audio File",
                    value=None,
                )
                exaggeration = gr.Slider(
                    0.25,
                    2,
                    step=0.05,
                    label="Exaggeration (Neutral = 0.5; low is calm, high is more excited; high values can be unstable)",
                    value=0.5,
                )
                cfg_weight = gr.Slider(
                    0.0,
                    1,
                    step=0.05,
                    label="CFG/Pace (speed)",
                    value=0.5,
                )
                seed = gr.Number(
                    value=0,
                    label="Random seed (0 for random)",
                )
                temperature = gr.Slider(
                    0.05,
                    5,
                    step=0.05,
                    label="temperature (creativity; low values are good; high values become crazy and take long)",
                    value=0.8,
                )
                run_button = gr.Button(
                    "Generate",
                    variant="primary",
                )

            with gr.Column():
                audio_output = gr.Audio(label="Output Audio")

        demo.load(fn=load_model, inputs=[gr.State(device)], outputs=model_state)

        run_button.click(
            fn=generate,
            inputs=[
                gr.State(device),
                model_state,
                text,
                reference_audio,
                exaggeration,
                temperature,
                seed,
                cfg_weight,
            ],
            outputs=audio_output,
        )

    demo.launch()


if __name__ == "__main__":
    main()
