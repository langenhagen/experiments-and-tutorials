"""Convert grayscale images to black/white images"""

import os

from PIL import Image

input_dir = "."
output_dir = "."
threshold = 32  # tweak as needed

os.makedirs(output_dir, exist_ok=True)

for fname in os.listdir(input_dir):
    if not fname.endswith(".webp"):
        continue
    img = Image.open(os.path.join(input_dir, fname)).convert("L")
    # Binarize: keep black, turn gray to white
    bin_img = img.point(lambda p: 0 if p < threshold else 255, mode="1")
    bin_img.save(os.path.join(output_dir, fname.replace(".webp", "_binary.png")))
