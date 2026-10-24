#!/usr/bin/env python3
"""Text-to-image with FLUX via mflux (MLX, Apple Silicon).

Usage

    time uv run python text2img.py --help
    time uv run python text2img.py
    time uv run python text2img.py --prompt "a cat on a skateboard" --seed 42
    time uv run python text2img.py --model klein-9b --width 1344 --height 768
    time uv run python text2img.py --model flux1-dev --out-file dev.png

The first run of each model downloads its weights (several GB).

author: andreasl
"""

from argparser import build_parser, resolve_defaults
from models import load_model


def main() -> None:
    """Run the program."""
    args = resolve_defaults(
        build_parser("Generate an image from a text prompt.", "text2img.png").parse_args()
    )

    model = load_model(args.model, args.quantize)

    print(f"model: {args.model}, seed: {args.seed}, steps: {args.steps}, guidance: {args.guidance}")
    image = model.generate_image(
        seed=args.seed,
        prompt=args.prompt,
        num_inference_steps=args.steps,
        width=args.width,
        height=args.height,
        guidance=args.guidance,
    )
    image.save(args.out_file, overwrite=True)
    print(f"wrote {args.out_file}")


if __name__ == "__main__":
    main()
