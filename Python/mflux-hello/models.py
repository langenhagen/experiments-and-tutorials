"""Load the mflux model behind a --model name (see argparser.MODEL_DEFAULTS).

Both FLUX.2 Klein and FLUX.1 dev accept `image_path` and `image_strength` in
`generate_image`, so the same classes serve text2img.py and img2img.py.
"""


def load_model(name: str, quantize: int):
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
        return Flux1(quantize=quantize, model_config=ModelConfig.dev())
    raise ValueError(f"unknown model: {name}")
