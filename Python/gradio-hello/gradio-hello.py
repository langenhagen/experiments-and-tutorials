#!/usr/bin/env python3
"""Showcase the programmatic quick and easy GUI library Gradio.

taken from: https://www.gradio.app/guides/quickstart
"""

import gradio as gr


def greet(name, intensity):
    return "Hello, " + name + "!" * int(intensity)


demo = gr.Interface(
    fn=greet,
    inputs=["text", "slider"],
    outputs=["text"],
)

demo.launch()
