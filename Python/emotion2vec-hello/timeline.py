#!/usr/bin/env python3
"""Temporal emotion analysis for an entire audio file with emotion2vec+.

The bundled funasr classifier only reports the dominant emotion for a whole
recording: internally it mean-pools the frame features before classification,
so `granularity="frame"` alone does not yield a per-frame timeline.

This module instead slides a fixed-duration window across the audio and
classifies each window as its own mini-utterance, producing a
(start_sec, end_sec, dominant_emotion, per-emotion scores) timeline. When the
hop is shorter than the window, consecutive windows overlap.

Because each window is classified on its own, splitting the file this way is
less heavy on RAM than classifying the whole recording in a single pass.

Run from the command line:

    python timeline.py

This launches a Gradio web interface (like `main.py`).

CPU RAM-heavy: peak memory scales with clip length (~25 GB at 3 minutes).
Windowing keeps each individual inference small.

author: andreasl
"""

import os
from functools import lru_cache

import gradio as gr
import numpy as np
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
WINDOW_SECONDS: float = float(os.getenv("EMOTION2VEC_WINDOW_SEC", "3.0"))
HOP_SECONDS: float = float(os.getenv("EMOTION2VEC_HOP_SEC", "1.0"))
SAMPLE_RATE: int = 16000


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


def classify_samples(
    samples: np.ndarray,
    sample_rate: int = SAMPLE_RATE,
) -> dict[str, float]:
    """Classify a mono audio buffer and return per-emotion probabilities."""
    res = get_model().generate(
        input=samples,
        input_len=None,
        fs=sample_rate,
        granularity="utterance",
        extract_embedding=False,
    )

    if not res:
        return dict.fromkeys(EMOTIONS, 0.0)

    first = res[0]
    labels = first.get("labels", [])
    scores = first.get("scores", [])
    if not scores:
        return dict.fromkeys(EMOTIONS, 0.0)

    label_map = {
        normalize_label(label): float(score)
        for label, score in zip(labels, scores, strict=True)
    }
    return {emotion: label_map.get(emotion, 0.0) for emotion in EMOTIONS}


def _classify_window(
    samples: np.ndarray,
    start: int,
    end: int,
    window_n: int,
) -> dict[str, object]:
    """Classify a single window slice and return its timeline row."""
    clip = samples[start:end]
    if len(clip) < window_n:
        pad_n = window_n - len(clip)
        clip = np.concatenate([clip, np.zeros(pad_n, dtype=np.float32)])
    probs = classify_samples(clip)
    dominant = max(probs, key=lambda emotion: probs[emotion])
    row: dict[str, object] = {
        "start": round(start / float(SAMPLE_RATE), 3),
        "end": round(end / float(SAMPLE_RATE), 3),
        "dominant": dominant,
    }
    row.update(probs)
    return row


def _to_float_mono16k(
    samples: np.ndarray,
    sample_rate: int,
) -> np.ndarray:
    """Normalize input to float32 mono at 16 kHz.

    Accepts int16 (Gradio `type="numpy"` default) or float samples and any
    channel layout; returns float32 in the range -1..1 at `SAMPLE_RATE`.
    """
    if samples.dtype.kind == "i":
        samples = samples.astype(np.float32) / 32768.0
    else:
        samples = samples.astype(np.float32)
    if samples.ndim > 1:
        samples = samples.mean(axis=1)
    if sample_rate != SAMPLE_RATE:
        src_n = len(samples)
        dst_n = round(src_n * SAMPLE_RATE / sample_rate)
        positions = np.linspace(0.0, src_n - 1, num=dst_n)
        samples = np.interp(positions, np.arange(src_n), samples).astype(np.float32)
    return samples


def analyze(
    samples: np.ndarray,
    sample_rate: int,
    window_sec: float = WINDOW_SECONDS,
    hop_sec: float = HOP_SECONDS,
) -> pd.DataFrame:
    """Slide a window across decoded audio and classify each slice.

    `samples` is already-decoded audio (from Gradio `type="numpy"`, so the
    browser handled format decoding). It is normalized to float32 mono 16 kHz
    before analysis. Returns a DataFrame with one row per window: `start`,
    `end` (seconds), `dominant`, and one column per emotion holding that
    window's probability.
    """
    samples = _to_float_mono16k(samples, sample_rate)
    total_sec = len(samples) / float(SAMPLE_RATE)
    window_n = round(window_sec * SAMPLE_RATE)
    hop_n = round(hop_sec * SAMPLE_RATE)

    rows: list[dict[str, object]] = []
    for start in range(0, len(samples), hop_n):
        end = min(start + window_n, len(samples))
        if end <= start:
            continue
        row = _classify_window(samples, start, end, window_n)
        rows.append(row)

    if not rows:
        msg = "No audio content to analyze."
        raise ValueError(msg)

    result = pd.DataFrame(rows)
    if total_sec > 0:
        result.loc[result.index[-1], "end"] = round(total_sec, 3)
    return result


def to_long(df: pd.DataFrame) -> pd.DataFrame:
    """Reshape the timeline into long form for plotting.

    Returns a DataFrame with columns `start`, `emotion`, `probability`, one row
    per window and emotion, suitable for a Gradio `LinePlot` with color series.
    """
    return df.melt(
        id_vars=["start"],
        value_vars=EMOTIONS,
        var_name="emotion",
        value_name="probability",
    )


def overall_scores(df: pd.DataFrame) -> pd.DataFrame:
    """Aggregate per-window probabilities into overall per-emotion scores.

    Returns a DataFrame with columns `emotion`, `probability` (mean over all
    windows), suitable for a `BarPlot`.
    """
    scores = {emotion: float(df[emotion].mean()) for emotion in EMOTIONS}
    return pd.DataFrame(
        {"emotion": emotion, "probability": scores[emotion]} for emotion in EMOTIONS
    )


def predict_timeline(
    audio: tuple[int, np.ndarray],
    window_sec: float = WINDOW_SECONDS,
    hop_sec: float = HOP_SECONDS,
) -> tuple[pd.DataFrame, pd.DataFrame, pd.DataFrame]:
    """Run the full timeline analysis and return Gradio-ready results.

    `audio` is the `(sample_rate, samples)` tuple delivered by a Gradio
    `Audio(type="numpy")` component, already decoded by the browser.

    Returns `(long_plot, overall_bar, overview)`, where:
    - `long_plot`: long-form DataFrame for the probability-over-time line plot.
    - `overall_bar`: per-emotion aggregate for the bar chart.
    - `overview`: per-window `start`/`end`/`dominant` table for display.
    """
    sample_rate, samples = audio
    df = analyze(samples, sample_rate, window_sec=window_sec, hop_sec=hop_sec)
    long_plot = to_long(df)
    overall = overall_scores(df)
    overview = df[["start", "end", "dominant"]]
    return long_plot, overall, overview


def launch_app() -> None:
    """Launch the Gradio interface for temporal emotion analysis."""
    with gr.Blocks(title="emotion2vec+ temporal emotion timeline") as app:
        gr.Markdown(
            "# emotion2vec+ timeline\n"
            "Upload a speech audio file; the app slides a window across the "
            "whole recording and classifies each window, producing an emotion "
            "timeline over time.",
        )
        with gr.Row():
            audio = gr.Audio(type="numpy", label="Audio file")
        with gr.Row():
            window_slider = gr.Slider(
                0.5,
                10.0,
                value=WINDOW_SECONDS,
                step=0.5,
                label="Window (seconds)",
                info="Audio covered per reading (bigger = stable, coarser).",
            )
            hop_slider = gr.Slider(
                0.5,
                10.0,
                value=HOP_SECONDS,
                step=0.5,
                label="Hop (seconds)",
                info=(
                    "Spacing between readings (smaller = smoother, costlier; "
                    "below window, windows overlap)."
                ),
            )
        predict_btn = gr.Button("Analyze emotion timeline", variant="primary")

        line_plot = gr.LinePlot(
            x="start",
            y="probability",
            color="emotion",
            title="Emotion probabilities over time",
            y_lim=[0, 1],
        )
        with gr.Row():
            bar_plot = gr.BarPlot(
                x="emotion",
                y="probability",
                title="Overall emotion scores",
                y_lim=[0, 1],
            )
            table = gr.Dataframe(
                headers=["start", "end", "dominant"],
                label="Timeline (window dominant emotion)",
                interactive=False,
            )

        predict_btn.click(
            fn=predict_timeline,
            inputs=[audio, window_slider, hop_slider],
            outputs=[line_plot, bar_plot, table],
        )

    app.launch()


def main() -> None:
    """Launch the Gradio interface for temporal emotion analysis."""
    launch_app()


if __name__ == "__main__":
    main()
