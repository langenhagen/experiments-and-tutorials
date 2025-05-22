"""Convert grayscale images to black/white images via gamma correction."""

import os

from PIL import Image

input_dir = "."
output_dir = "."
threshold = 64  # below = black, above = progressively whitened
gamma = 0.2  # tone curve compression

os.makedirs(output_dir, exist_ok=True)


def soften(p):
    if p < threshold:
        return 0
    norm = (p - threshold) / (255 - threshold)
    adjusted = pow(norm, gamma) * 255
    return int(min(255, adjusted))


for fname in os.listdir(input_dir):
    if not fname.endswith(".webp"):
        continue
    img = Image.open(os.path.join(input_dir, fname)).convert("L")
    smooth = img.point(soften)
    smooth.save(os.path.join(output_dir, fname.replace(".webp", "_print.png")))
