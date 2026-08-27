#!/usr/bin/env python3
"""Showcase emotion2vec+ large speech emotion recognition with a Gradio
interface.

This app lets the user upload an audio file (ogg, mp3, wav, m4a, ...) and runs
utterance-level emotion recognition with the `emotion2vec_plus_large` model.
Results are shown as the dominant emotion plus a bar chart of all nine class
probabilities.

The model is loaded once at startup through FunASR (PyTorch under the hood).
The weights (~300 MB) are downloaded automatically on first run and cached.
CPU RAM-heavy: peak memory scales with clip length (~25 GB at 3 minutes).

author: andreasl
"""

import os
from functools import lru_cache

import gradio as gr
import pandas as pd
from funasr import AutoModel

EMOTIONS: list[str] = [
    "angry",
    "disgusted",
    "fearful",
    "happy",
    "neutral",
    "other",
    "sad",
    "surprised",
    "unknown",
]

MODEL_ID: str = "emotion2vec_plus_large"
HUB: str = os.getenv("EMOTION2VEC_HUB", "hf")
DEVICE: str = os.getenv("EMOTION2VEC_DEVICE", "cpu")


def normalize_label(label: str) -> str:
    """Normalize a model label to a plain English emotion name.

    The model returns bilingual labels such as `'生气/angry'`; take the English
    token after the `/`. The unknown class is returned as `'<unk>'`.
    """
    name = label.rsplit("/", maxsplit=1)[-1].strip()
    if name == "<unk>":
        return "unknown"
    return name


@lru_cache(maxsize=1)
def get_model() -> AutoModel:
    """Build and cache the emotion recognition model on first call."""
    return AutoModel(model=MODEL_ID, hub=HUB, device=DEVICE)


def predict(
    audio_path: str,
) -> tuple[dict[str, float], pd.DataFrame]:
    """Run emotion recognition on an audio file and return result dicts."""
    if not audio_path:
        msg = "Please provide an audio file."
        raise gr.Error(msg)

    res = get_model().generate(
        input=audio_path,
        granularity="utterance",
        extract_embedding=False,
    )

    if not res:
        msg = f"No result from the model for: {audio_path}"
        raise gr.Error(msg)

    first = res[0]
    labels = first.get("labels", [])
    scores = first.get("scores", [])
    if not scores:
        msg = f"No scores in model output for: {audio_path}"
        raise gr.Error(msg)

    label_map = {
        normalize_label(label): float(score)
        for label, score in zip(labels, scores, strict=True)
    }
    probs = {emotion: label_map.get(emotion, 0.0) for emotion in EMOTIONS}
    rows = pd.DataFrame(
        {"emotion": emotion, "probability": prob} for emotion, prob in probs.items()
    )
    return probs, rows


def main() -> None:
    """Build the interface and launch the Gradio app."""
    with gr.Blocks(title="emotion2vec+ large") as demo:
        gr.Markdown(
            "# emotion2vec+ large\n"
            "Upload a speech audio file to recognize its emotion across "
            "nine classes.",
        )

        with gr.Row():
            audio = gr.Audio(type="filepath", label="Audio file")
            output = gr.Label(num_top_classes=3, label="Emotion")

        chart = gr.BarPlot(
            x="emotion",
            y="probability",
            title="Emotion probabilities",
            y_label="Probability",
        )

        predict_btn = gr.Button("Recognize emotion", variant="primary")
        predict_btn.click(
            fn=predict,
            inputs=[audio],
            outputs=[output, chart],
        )

    demo.launch()


if __name__ == "__main__":
    main()
