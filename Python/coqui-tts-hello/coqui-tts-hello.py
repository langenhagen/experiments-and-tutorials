#!/usr/bin/env python3
"""Showcase the use of Coqui TTS in an interactive Gradio Webapp.

I don't like Coqui as much as I like Kokoro, which is seems overall much higher
quality, but Coqui is more than just text-to-speech. Allegedly, it can also do
speech-to-text and voice cloning.

based off: https://www.youtube.com/watch?v=EyzRixV8s54&ab_channel=HussainMustafa
"""

from functools import partial

import gradio as gr
import torch
from TTS.api import TTS


def generate_audio(text: str, model: str, device: str) -> str:
    tts = TTS(model_name=model).to(device)
    out_file_path = "output.wav"
    tts.tts_to_file(text=text, file_path=out_file_path)
    return out_file_path  # return for gradio.Audio to pick it up from the outputs


def main() -> None:
    """Main program entry point."""

    device = "cuda" if torch.cuda.is_available() else "cpu"
    print(f"{device=}")

    interface = gr.Interface(
        fn=partial(generate_audio, device=device),
        inputs=[
            gr.Text(label="Text", value="Henlo, this is some text", autofocus=True),
            gr.Text(label="Model", value="tts_models/en/ljspeech/fast_pitch"),
        ],
        outputs=[gr.Audio(label="Audio")],
    )

    interface.launch()


if __name__ == "__main__":
    main()
