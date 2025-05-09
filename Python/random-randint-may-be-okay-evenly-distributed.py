import json
import random

# random.Seed() or random.seed(None) seeds from current time or whatnot

max_ = 10

results: dict[int, int] = dict.fromkeys(range(1, max_ + 1), 0)

for _ in range(1_000_000):
    r = random.randint(1, max_)
    results[r] += 1


print(json.dumps(results, indent=4))
