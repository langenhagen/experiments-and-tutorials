#!/usr/bin/env python3
"""Tiny Matcha-TTS library example.

Nice thing about Matcha is, that it can pronounce code nicely, unlike kokoro.

Usage

    time uv run main.py --file foo.txt
    time uv run main.py --text "Hello, there!"

    # apparently, to get another voice you have to choose the multi-speaker voice
    time uv run main.py --model matcha_vctk --spk 100 --text "Hello, there!" --output-folder outputs/spk100


The script does four things:
1) parse a subset of matcha's CLI arguments,
2) map them into the argument shape expected by `matcha.cli`,
3) load/checkpoint + vocoder through the Matcha helpers,
4) synthesize one utterance and save output files.

author: andreasl
"""

import argparse
import os
import sys
from pathlib import Path

import matcha.cli
import torch


def parse_args() -> argparse.Namespace:
    """Parse a small subset of Matcha options."""
    parser = argparse.ArgumentParser(description="Generate speech with Matcha-TTS.")
    text_input = parser.add_mutually_exclusive_group()
    text_input.add_argument(
        "--text",
        default=None,
        help="Text to synthesize into speech.",
    )
    text_input.add_argument(
        "--file",
        default=None,
        help="Path to a UTF-8 text file. Entire file is synthesized as one utterance.",
    )
    parser.add_argument(
        "--model",
        default="matcha_ljspeech",
        choices=["matcha_ljspeech", "matcha_vctk"],
        help=(
            "Pretrained Matcha model: "
            "ljspeech is single-speaker, vctk is multi-speaker."
        ),
    )
    parser.add_argument(
        "--vocoder",
        default=None,
        choices=["hifigan_T2_v1", "hifigan_univ_v1"],
        help="Optional vocoder override. Usually leave unset and let Matcha choose.",
    )
    parser.add_argument(
        "--output-folder",
        default=".",
        help="Output directory for generated wav/npy/png files.",
    )
    parser.add_argument(
        "--temperature",
        type=float,
        default=0.667,
        help="Sampling noise amount. Higher = more variation, lower = more stable.",
    )
    parser.add_argument(
        "--speaking-rate",
        type=float,
        default=None,
        help="Speech speed scale used by Matcha. Higher is slower, lower is faster.",
    )
    parser.add_argument(
        "--steps",
        type=int,
        default=10,
        help=(
            "ODE solver steps for generation. "
            "More steps = slower but often higher quality."
        ),
    )
    parser.add_argument(
        "--spk",
        type=int,
        default=None,
        help="Speaker ID for multi-speaker models (e.g. matcha_vctk).",
    )
    parser.add_argument(
        "--denoiser-strength",
        type=float,
        default=0.00025,
        help="Post-vocoder denoiser strength. Increase to remove more background hiss.",
    )
    parser.add_argument(
        "--cpu",
        action="store_true",
        help="Force CPU inference even if CUDA is available.",
    )
    return parser.parse_args()


@torch.inference_mode()
def run_matcha(args: argparse.Namespace) -> int:
    """Run synthesis using the matcha Python API functions."""
    # Matcha currently needs this with newer torch default loading behavior.
    os.environ.setdefault("TORCH_FORCE_NO_WEIGHTS_ONLY_LOAD", "1")

    # Build a namespace that matches the internal `matcha.cli` functions.
    matcha_args = argparse.Namespace(
        model=args.model,
        checkpoint_path=None,
        vocoder=args.vocoder,
        text=args.text,
        file=args.file,
        spk=args.spk,
        temperature=args.temperature,
        speaking_rate=args.speaking_rate,
        steps=args.steps,
        cpu=args.cpu,
        denoiser_strength=args.denoiser_strength,
        output_folder=args.output_folder,
        batched=False,
        batch_size=32,
    )

    # Validate args, pick device, and make sure model files are present.
    matcha_args = matcha.cli.validate_args(matcha_args)
    device = matcha.cli.get_device(matcha_args)
    matcha.cli.print_config(matcha_args)
    paths = matcha.cli.assert_required_models_available(matcha_args)

    # Load acoustic model + vocoder for waveform generation.
    model = matcha.cli.load_matcha(matcha_args.model, paths["matcha"], device)
    vocoder, denoiser = matcha.cli.load_vocoder(
        matcha_args.vocoder,
        paths["vocoder"],
        device,
    )

    # Multi-speaker models use an optional speaker tensor.
    spk = (
        torch.tensor([matcha_args.spk], device=device, dtype=torch.long)
        if matcha_args.spk is not None
        else None
    )

    if matcha_args.file is not None:
        with Path(matcha_args.file).open(encoding="utf-8") as text_file:
            file_text = text_file.read().strip()
        if not file_text:
            sys.stderr.write("Input file is empty. Provide text in the file.\n")
            return 1
        texts = [file_text]
    else:
        texts = [matcha_args.text]

    # Keep this hello-world path simple: synthesize one utterance.
    matcha.cli.unbatched_synthesis(
        matcha_args,
        device,
        model,
        vocoder,
        denoiser,
        texts,
        spk,
    )
    return 0


def main() -> None:
    """Run the program."""
    args = parse_args()
    if args.text is args.file is None:
        args.text = "Henlo, this is matcha text to speech."
    raise SystemExit(run_matcha(args))


if __name__ == "__main__":
    main()
