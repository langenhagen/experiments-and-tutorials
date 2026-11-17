#!/usr/bin/env python3
"""Interactive Matcha-TTS Gradio browser app.

Runs the pretrained Matcha-TTS models from the `matcha-tts` package. The first
run of each model downloads its checkpoint and vocoder.

- Model: ljspeech has one female voice; vctk has 108 speakers
- Speaker: the VCTK speaker ID, 0 to 107; only for the vctk model
- Speaking rate: higher is slower, lower is faster
- Temperature: noise added during generation; higher varies more
- Steps: refinement steps of the ODE solver; more is slower and often cleaner
- Denoiser strength: removes the hiss left by the vocoder
- Each line of the text is spoken as its own piece and the pieces are joined

Matcha can pronounce code and acronyms, unlike Kokoro, but sounds a bit more
robotic.

See:
- https://github.com/shivammehta25/Matcha-TTS
"""

import argparse
import functools
import os
import time

import gradio as gr
import matcha.cli
import numpy as np
import torch

# Matcha currently needs this with newer torch default loading behavior.
os.environ.setdefault("TORCH_FORCE_NO_WEIGHTS_ONLY_LOAD", "1")

SAMPLE_RATE = 22050

# Name, vocoder, default speaking rate, and speaker IDs of each pretrained model.
MODELS = {**matcha.cli.SINGLESPEAKER_MODEL, **matcha.cli.MULTISPEAKER_MODEL}

SAMPLE_TEXT = (
    "Hello! This is Matcha text to speech. It can read code, like print "
    "open parenthesis quote hello quote close parenthesis, and acronyms "
    "like GPU, CPU and API."
)


@functools.lru_cache(maxsize=1)  # Keep only one model in memory at a time
def load_model(model_name: str, device: str):
    info = MODELS[model_name]
    paths = matcha.cli.assert_required_models_available(
        argparse.Namespace(
            model=model_name, vocoder=info["vocoder"], checkpoint_path=None
        )
    )
    torch_device = torch.device(device)
    model = matcha.cli.load_matcha(model_name, paths["matcha"], torch_device)
    vocoder, denoiser = matcha.cli.load_vocoder(
        info["vocoder"], paths["vocoder"], torch_device
    )
    return model, vocoder, denoiser


@torch.inference_mode()
def generate(
    model_name: str,
    device: str,
    text: str,
    speaker: float,
    speaking_rate: float,
    temperature: float,
    steps: float,
    denoiser_strength: float,
    seed: float,
) -> tuple[tuple[int, np.ndarray], str]:
    model, vocoder, denoiser = load_model(model_name, device)
    torch_device = torch.device(device)
    if seed != 0:
        torch.manual_seed(int(seed))
    spks = (
        torch.tensor([int(speaker)], device=torch_device, dtype=torch.long)
        if MODELS[model_name]["spk"] is not None
        else None
    )

    start = time.monotonic()
    pieces = []
    for line in [line.strip() for line in text.splitlines() if line.strip()]:
        processed = matcha.cli.process_text(1, line, torch_device)
        output = model.synthesise(
            processed["x"],
            processed["x_lengths"],
            n_timesteps=int(steps),
            temperature=temperature,
            spks=spks,
            length_scale=speaking_rate,
        )
        waveform = matcha.cli.to_waveform(
            output["mel"], vocoder, denoiser, denoiser_strength
        )
        pieces.append(waveform.numpy())
    processing_seconds = time.monotonic() - start

    audio = np.concatenate(pieces)
    audio_seconds = len(audio) / SAMPLE_RATE
    info = (
        f"Audio: {audio_seconds:.1f} s, processing: {processing_seconds:.1f} s, "
        f"real-time factor: {audio_seconds / processing_seconds:.2f}x"
    )
    return (SAMPLE_RATE, audio), info


def change_model(model_name: str) -> tuple[dict, dict]:
    info = MODELS[model_name]
    return (
        gr.update(visible=info["spk"] is not None),
        gr.update(value=info["speaking_rate"]),
    )


def main() -> None:
    devices = ["cpu", "cuda"] if torch.cuda.is_available() else ["cpu"]
    with gr.Blocks(title="Matcha Hello") as demo:
        with gr.Row():
            with gr.Column():
                model_name = gr.Radio(
                    choices=list(MODELS),
                    value="matcha_ljspeech",
                    label="Model",
                    info="ljspeech: one female voice. vctk: 108 speakers.",
                )
                device = gr.Radio(choices=devices, value=devices[-1], label="Device")
                text = gr.Textbox(
                    value=SAMPLE_TEXT,
                    label="Text to synthesize (each line is spoken as its own piece)",
                    lines=6,
                    max_lines=20,
                )
                speaker = gr.Slider(
                    0,
                    107,
                    step=1,
                    value=0,
                    label="Speaker ID (vctk only)",
                    info="One of the 108 VCTK speakers.",
                    visible=False,
                )
                speaking_rate = gr.Slider(
                    0.5,
                    1.5,
                    step=0.01,
                    value=MODELS["matcha_ljspeech"]["speaking_rate"],
                    label="Speaking rate",
                    info="Higher is slower, lower is faster. Default 0.95 for "
                    "ljspeech, 0.85 for vctk.",
                )
                temperature = gr.Slider(
                    0.0,
                    2.0,
                    step=0.01,
                    value=0.667,
                    label="Temperature (default 0.667)",
                    info="Noise added during generation. Higher sounds more varied, "
                    "lower sounds more stable.",
                )
                steps = gr.Slider(
                    1,
                    50,
                    step=1,
                    value=10,
                    label="Steps (default 10)",
                    info="Refinement steps of the ODE solver. More is slower and "
                    "often a bit cleaner.",
                )
                denoiser_strength = gr.Slider(
                    0.0,
                    0.01,
                    step=0.00005,
                    value=0.00025,
                    label="Denoiser strength (default 0.00025)",
                    info="Removes the background hiss of the vocoder. Too high "
                    "makes the voice dull.",
                )
                seed = gr.Number(
                    value=0,
                    label="Random seed (0 for random)",
                    info="The same seed with the same settings and text gives the "
                    "same audio.",
                )
                run_button = gr.Button("Generate", variant="primary")

            with gr.Column():
                audio_output = gr.Audio(label="Output Audio", type="numpy")
                info_output = gr.Textbox(label="Generation Info", interactive=False)

        model_name.change(
            fn=change_model, inputs=model_name, outputs=[speaker, speaking_rate]
        )
        run_button.click(
            fn=generate,
            inputs=[
                model_name,
                device,
                text,
                speaker,
                speaking_rate,
                temperature,
                steps,
                denoiser_strength,
                seed,
            ],
            outputs=[audio_output, info_output],
        )

    demo.launch()


if __name__ == "__main__":
    main()
