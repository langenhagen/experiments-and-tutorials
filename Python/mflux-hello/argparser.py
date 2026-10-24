"""Command line arguments shared by text2img.py and img2img.py.

Model names map to the defaults that suit them: the distilled FLUX.2 Klein
models need only 4 steps and guidance 1.0, FLUX.1 dev needs about 20 steps and
guidance around 3.5. mflux has no FLUX.2 dev (32B) config, so it is not listed.
"""

import argparse
import random

# model name -> (default steps, default guidance)
MODEL_DEFAULTS = {
    "klein-4b": (4, 1.0),
    "klein-9b": (4, 1.0),
    "flux1-dev": (20, 3.5),
}

DEFAULT_PROMPT = "a photo of an astronaut riding a horse on mars"


def build_parser(description: str, default_out_file: str) -> argparse.ArgumentParser:
    """Create a parser with the options both scripts share."""
    parser = argparse.ArgumentParser(description=description)
    parser.add_argument(
        "--prompt",
        default=DEFAULT_PROMPT,
        help="Text describing the image.",
    )
    parser.add_argument(
        "--model",
        default="klein-4b",
        choices=list(MODEL_DEFAULTS),
        help="klein-4b is the smallest and fastest. flux1-dev is the 12B classic.",
    )
    parser.add_argument(
        "--width",
        type=int,
        default=1024,
        help="Output width in pixels (multiple of 16).",
    )
    parser.add_argument(
        "--height",
        type=int,
        default=1024,
        help="Output height in pixels (multiple of 16).",
    )
    parser.add_argument(
        "--steps",
        type=int,
        default=None,
        help="Denoising steps. Default depends on --model (Klein 4, flux1-dev 20).",
    )
    parser.add_argument(
        "--guidance",
        type=float,
        default=None,
        help=(
            "How strongly to follow the prompt. "
            "Default depends on --model (Klein 1.0, flux1-dev 3.5). "
            "Distilled Klein models ignore values other than 1.0."
        ),
    )
    parser.add_argument(
        "--seed",
        type=int,
        default=None,
        help="Random seed. Default is random and gets printed.",
    )
    parser.add_argument(
        "--quantize",
        type=int,
        default=8,
        choices=[3, 4, 5, 6, 8],
        help="Weight quantization in bits. Lower uses less memory.",
    )
    parser.add_argument(
        "--out-file",
        default=default_out_file,
        help="Output image path. An existing file is overwritten.",
    )
    return parser


def resolve_defaults(args: argparse.Namespace) -> argparse.Namespace:
    """Fill in the model-dependent defaults and the random seed."""
    default_steps, default_guidance = MODEL_DEFAULTS[args.model]
    if args.steps is None:
        args.steps = default_steps
    if args.guidance is None:
        args.guidance = default_guidance
    if args.seed is None:
        args.seed = random.randint(0, 2**32 - 1)
    return args
