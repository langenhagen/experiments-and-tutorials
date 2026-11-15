#!/usr/bin/env python3
"""Interactive Voxtral TTS Gradio browser app.

Runs Mistral's Voxtral 4B TTS through `mlx-audio`: on Apple Silicon Macs with
Metal, and on Linux with the CPU or an NVIDIA GPU through MLX's CUDA backend.

- Device: GPU (Metal or CUDA) or CPU
- Model: full precision (bf16, 8 GB) or 6-bit (3.5 GB) and 4-bit (2.5 GB)
  versions; an 8 GB GPU needs the 6-bit or 4-bit version
- Voice: one of the 20 preset voices; the released weights cannot clone voices
- Max length: upper limit of generated audio; one frame is 80 ms, and the
  default of 4096 frames is about 5.5 minutes
- Guidance (CFG alpha): how strongly the sound follows the text; 1.2 is the default
- Denoising steps: the fine sound detail of each 80 ms frame is refined out of
  random noise in this many passes; fewer is faster and rougher, more is slower
  and finer; 8 is the default
- Noise scale (sigma max): how much randomness that refinement starts from;
  lower sounds more uniform and flat, higher more varied, too high adds
  artifacts; 1.0 is the default
- Random seed: same seed and settings give the same audio

`mlx-audio` picks the semantic tokens greedily, so its temperature, top-k, and
top-p arguments have no effect and are not offered here.

See:
- https://huggingface.co/mistralai/Voxtral-4B-TTS-2603
- https://github.com/Blaizzy/mlx-audio
"""

import functools

import gradio as gr
import mlx.core as mx
import numpy as np
from mlx_audio.tts.utils import load

MODELS = [
    "mlx-community/Voxtral-4B-TTS-2603-mlx-bf16",
    "mlx-community/Voxtral-4B-TTS-2603-mlx-6bit",
    "mlx-community/Voxtral-4B-TTS-2603-mlx-4bit",
]

VOICES = {
    "english": ["casual_male", "casual_female", "cheerful_female", "neutral_male", "neutral_female"],
    "german": ["de_male", "de_female"],
    "french": ["fr_male", "fr_female"],
    "spanish": ["es_male", "es_female"],
    "italian": ["it_male", "it_female"],
    "portuguese": ["pt_male", "pt_female"],
    "dutch": ["nl_male", "nl_female"],
    "arabic": ["ar_male"],
    "hindi": ["hi_male", "hi_female"],
}

SAMPLE_TEXTS = {
    "english": "Wild jays flick past six quiet brown dogs on the foggy lawn. "
    "Jumping frogs vex bold quick wren by the pond's edge.",
    "german": "Falsches Üben von Xylophonmusik quält jeden größeren Zwerg. "
    "Zwölf Boxkämpfer jagen Viktor quer über den großen Sylter Deich.",
}


@functools.lru_cache(maxsize=1)  # Keep only one model in memory at a time
def load_model(model_id: str):
    return load(model_id)


def change_language(language: str) -> tuple[gr.Dropdown, str]:
    voices = VOICES[language]
    return gr.Dropdown(choices=voices, value=voices[0]), SAMPLE_TEXTS.get(language, "")


def generate(
    device: str,
    model_id: str,
    text: str,
    voice: str,
    max_frames: float,
    cfg_alpha: float,
    denoising_steps: float,
    sigma_max: float,
    seed: float,
) -> tuple[tuple[int, np.ndarray], str]:
    mx.set_default_device(mx.gpu if device == "gpu" else mx.cpu)
    model = load_model(model_id)

    # The flow-matching settings live on the shared model object, not on generate().
    acoustic_args = model.acoustic_transformer.args
    acoustic_args.cfg_alpha = cfg_alpha
    acoustic_args.n_denoising_steps = int(denoising_steps)
    acoustic_args.sigma_max = sigma_max

    if seed != 0:
        mx.random.seed(int(seed))

    (result,) = model.generate(text=text, voice=voice, max_tokens=int(max_frames))

    info = (
        f"Audio: {result.audio_duration}, real-time factor: {result.real_time_factor:.2f}x, "
        f"processing: {result.processing_time_seconds:.1f} s, peak memory: {result.peak_memory_usage:.1f} GB"
    )
    return (result.sample_rate, np.array(result.audio)), info


def main() -> None:
    with gr.Blocks(title="Voxtral TTS Hello") as demo:
        with gr.Row():
            with gr.Column():
                device = gr.Radio(
                    choices=["gpu", "cpu"],
                    value="gpu" if mx.is_available(mx.gpu) else "cpu",
                    label="Device (gpu is Metal on a Mac, CUDA on Linux)",
                )
                model_id = gr.Dropdown(choices=MODELS, value=MODELS[0], label="Model")
                text = gr.Textbox(value=SAMPLE_TEXTS["english"], label="Text to synthesize", max_lines=20)
                language = gr.Dropdown(choices=list(VOICES), value="english", label="Language")
                voice = gr.Dropdown(choices=VOICES["english"], value=VOICES["english"][0], label="Voice")
                max_frames = gr.Slider(
                    100,
                    8192,
                    step=1,
                    value=4096,
                    label="Max length in frames (80 ms each; 4096 is about 5.5 minutes)",
                )
                cfg_alpha = gr.Slider(
                    1.0,
                    3.0,
                    step=0.05,
                    value=1.2,
                    label="Guidance / CFG alpha (how strongly the sound follows the text; default 1.2)",
                )
                denoising_steps = gr.Slider(
                    2,
                    32,
                    step=1,
                    value=8,
                    label="Denoising steps (refinement passes per 80 ms of sound; fewer is rougher, more is finer "
                    "and slower; default 8)",
                )
                sigma_max = gr.Slider(
                    0.0,
                    2.0,
                    step=0.05,
                    value=1.0,
                    label="Noise scale / sigma max (randomness of the sound detail; lower is flatter, higher is "
                    "more varied, too high adds artifacts; default 1.0)",
                )
                seed = gr.Number(value=0, label="Random seed (0 for random)")
                run_button = gr.Button("Generate", variant="primary")

            with gr.Column():
                audio_output = gr.Audio(label="Output Audio")
                info_output = gr.Textbox(label="Generation Info", interactive=False)

        language.input(fn=change_language, inputs=language, outputs=[voice, text])
        run_button.click(
            fn=generate,
            inputs=[device, model_id, text, voice, max_frames, cfg_alpha, denoising_steps, sigma_max, seed],
            outputs=[audio_output, info_output],
        )

    demo.launch()


if __name__ == "__main__":
    main()
