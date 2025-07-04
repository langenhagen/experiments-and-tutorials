"""Convert grayscale images to black/white images via gamma correction.

God for converting grayscale images with gray backgrounds to rather smooth-sh
black-and-white-ish images, good for batch-creating images for coloring.
"""

from pathlib import Path

from PIL import Image

input_dir = Path(".")
output_dir = Path("./gamma-corrected")
threshold = 64  # below = black; above = progressively whitened
gamma = 0.2  # tone curve compression

output_dir.mkdir(parents=True, exist_ok=True)


def soften(p: int) -> int:
    """Apply gamma correction and threshold to a grayscale pixel.
    Return black (0) for values below threshold,
    else soften towards white (255).
    """
    if p < threshold:
        return 0
    norm = (p - threshold) / (255 - threshold)
    adjusted = pow(norm, gamma) * 255
    return int(min(255, adjusted))


for p in input_dir.iterdir():
    if not p.is_file() or p.suffix.lower() not in (".png", ".webp"):
        continue
    img = Image.open(p).convert("L")
    smooth = img.point(soften)
    output_path = output_dir / (p.stem + ".corrected.png")
    smooth.save(output_path)
