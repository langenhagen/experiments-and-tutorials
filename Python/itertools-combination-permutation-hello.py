"""
Showcase getting combinations, permutations and alike from iterables.

See: https://docs.python.org/2/library/itertools.html
"""

import itertools

ALPHABET = "0123456789abcdefghijklmnopqrstuvwxyx"

for i in itertools.product(ALPHABET, repeat=32):  # 32 digits; think: AA AB BA BB, i.e. len(ALPHABET)**32 combinations
    # print(i)  # i is a tuple
    print("".join(i))
