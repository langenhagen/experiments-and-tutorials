#!/usr/bin/env python3
"""Image-to-image with FLUX via mflux (MLX, Apple Silicon).

Starts denoising from a noised version of the init image instead of from pure
noise. Note that mflux's strength works the other way round than in Stable
Diffusion tools: a HIGHER --init-image-strength stays CLOSER to the init image
(it skips that fraction of the denoising steps).

Usage

    time uv run python img2img.py --help
    time uv run python text2img.py                  # makes text2img.png first
    time uv run python img2img.py --prompt "the same scene at night"
    time uv run python img2img.py --init-image photo.jpg --init-image-strength 0.3 \
        --prompt "an oil painting"
    time uv run python img2img.py --model flux1-dev --steps 30

Output size defaults to 1024x1024, so pass --width/--height to match a
non-square init image.

author: andreasl
"""

from argparser import build_parser, resolve_defaults
from models import load_model


def main() -> None:
    """Run the program."""
    parser = build_parser("Generate an image from a text prompt and an init image.", "img2img.png")
    parser.add_argument(
        "--init-image",
        default="text2img.png",
        help="Image to start from. Default is the output of text2img.py.",
    )
    parser.add_argument(
        "--init-image-strength",
        type=float,
        default=0.5,
        help="0..1. Higher stays closer to the init image, lower follows the prompt more.",
    )
    args = resolve_defaults(parser.parse_args())

    model = load_model(args.model, args.quantize)

    print(f"model: {args.model}, seed: {args.seed}, steps: {args.steps}, guidance: {args.guidance}")
    image = model.generate_image(
        seed=args.seed,
        prompt=args.prompt,
        num_inference_steps=args.steps,
        width=args.width,
        height=args.height,
        guidance=args.guidance,
        image_path=args.init_image,
        image_strength=args.init_image_strength,
    )
    image.save(args.out_file, overwrite=True)
    print(f"wrote {args.out_file}")


if __name__ == "__main__":
    main()
