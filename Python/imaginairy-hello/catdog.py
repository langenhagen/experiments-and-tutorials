import random

from imaginairy import ImaginePrompt, WeightedPrompt, imagine

for i in range(4):
    p = ImaginePrompt(
        [
            WeightedPrompt("cat", weight=2),
            WeightedPrompt("dog", weight=1),
        ],
        seed=random.randint(1, 999999999),
    )
    list(imagine(p))[0].save(f"my_image_{i}.jpg")
