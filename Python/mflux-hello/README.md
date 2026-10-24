# Mflux Hello

Showcase [mflux](https://github.com/filipstrand/mflux) (FLUX on Apple Silicon
via MLX) for text-to-image and image-to-image.

## Setup

```bash
uv sync
```

The first run of each model downloads its weights from Hugging Face (several
GB). Only `klein-4b` is ungated. For `klein-9b` and `flux1-dev`:

1. Create a free Hugging Face account.
2. Accept the license on the model page, logged in, by submitting its form
   ([Klein 9B](https://huggingface.co/black-forest-labs/FLUX.2-klein-9B),
   [FLUX.1 dev](https://huggingface.co/black-forest-labs/FLUX.1-dev)).
   Without it the download fails with 401 (no token) or 403 (token, no access).
3. Create a read token at <https://huggingface.co/settings/tokens>.
4. Run `uv run hf auth login` and paste the token. Alternatively put
   `export HF_TOKEN=hf_...` into the git-ignored `.envrc`.

## Usage

```bash
uv run python text2img.py --help

time uv run python text2img.py                                  # default prompt, Klein 4B
time uv run python text2img.py --prompt "a cat on a skateboard" --seed 42
time uv run python text2img.py --prompt "a dog on a bicycle" --model klein-9b --width 1344 --height 768
time uv run python text2img.py --prompt "a mouse on a plane" --model flux1-dev --out-file dev.png

time uv run python img2img.py --prompt "the same scene at night"  # starts from text2img.png
time uv run python img2img.py --init-image photo.jpg --init-image-strength 0.3 --prompt "an oil painting"
```

Both scripts share their options (`argparser.py`) and model loading
(`models.py`). `img2img.py` adds `--init-image` and `--init-image-strength`.
Unlike in Stable Diffusion tools, a higher strength stays closer to the init
image.

## Models

| `--model`   | Model            | Default steps | Default guidance |
| ----------- | ---------------- | ------------- | ---------------- |
| `klein-4b`  | FLUX.2 Klein 4B  | 4             | 1.0              |
| `klein-9b`  | FLUX.2 Klein 9B  | 4             | 1.0              |
| `flux1-dev` | FLUX.1 dev (12B) | 20            | 3.5              |

mflux has no FLUX.2 dev (32B).

Klein 4B on an M-series Mac with 48 GB, `--quantize 8`: about 12 s per 1024x1024
image after the download.
