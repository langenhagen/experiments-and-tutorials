"""Load the mflux model behind a --model name (see argparser.MODEL_DEFAULTS).

Both FLUX.2 Klein and FLUX.1 dev accept `image_path` and `image_strength` in
`generate_image`, so the same classes serve text2img.py and img2img.py.

`model_path` points at a local diffusers-format directory (transformer/,
text_encoder_2/, text_encoder/, vae/, tokenizer/, tokenizer_2/) and replaces
the built-in weights for `name`; the class and its config still come from
`name`, so a FLUX.1 finetune uses `--model flux1-dev --model-path DIR`.
"""


def load_model(name: str, quantize: int, model_path: str | None = None):
    """Return the mflux model object for `name`, quantized to `quantize` bits."""
    # Import late so that --help stays fast.
    from mflux.models.common.config import ModelConfig
    from mflux.models.flux.variants.txt2img.flux import Flux1
    from mflux.models.flux2.variants import Flux2Klein

    if name == "klein-4b":
        return Flux2Klein(quantize=quantize, model_config=ModelConfig.flux2_klein_4b())
    if name == "klein-9b":
        return Flux2Klein(quantize=quantize, model_config=ModelConfig.flux2_klein_9b())
    if name == "flux1-dev":
        return Flux1(quantize=quantize, model_config=ModelConfig.dev(), model_path=model_path)
    raise ValueError(f"unknown model: {name}")
