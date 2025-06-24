#!/usr/bin/env python3
"""Interactive Chatterbox Voice Cloning Gradio browser app."""

import gradio as gr
import torch
from chatterbox.vc import ChatterboxVC


def load_model(device: str) -> ChatterboxVC:
    return ChatterboxVC.from_pretrained(device)


def clone_voice(device, model, audio_input_path, target_voice_path) -> tuple:
    if model is None:
        model = ChatterboxVC.from_pretrained(device)
    wav = model.generate(
        audio=audio_input_path,
        target_voice_path=target_voice_path,
    )
    return (model.sr, wav.squeeze(0).numpy())


def main() -> None:
    if torch.cuda.is_available():
        device = "cuda"
    elif torch.backends.mps.is_available():
        device = "mps"
    else:
        device = "cpu"

    with gr.Blocks(title="Chatterbox Voice Cloning Hello") as demo:
        model_state = gr.State(None)

        with gr.Row():
            with gr.Column():
                audio_input = gr.Audio(
                    sources=["upload", "microphone"],
                    type="filepath",
                    label="Audio to Clone",
                    value=None,
                )
                target_voice = gr.Audio(
                    sources=["upload", "microphone"],
                    type="filepath",
                    label="Target Voice (reference sample)",
                    value=None,
                )
                run_button = gr.Button("Clone Voice", variant="primary")
            with gr.Column():
                output_audio = gr.Audio(label="Cloned Output Audio")

        demo.load(fn=load_model, inputs=[gr.State(device)], outputs=model_state)
        run_button.click(
            fn=clone_voice,
            inputs=[
                gr.State(device),
                model_state,
                audio_input,
                target_voice,
            ],
            outputs=output_audio,
        )

    demo.launch()


if __name__ == "__main__":
    main()
