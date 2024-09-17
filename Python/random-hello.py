"""
Showcase the usage of of the package `random`.
"""

import random
import string

# random.Seed() or random.seed(None) seeds from current time or from an
# operating system specific randomness source if available
# random.seed(None) happens implicitly.
# random.seed()

print("--- 1 random floats ---\n")

for _ in range(10):
    print(random.random())  #  ]0,1[

print("\n--- 2 random integers ---\n")

for _ in range(10):
    print(random.randint(1, 10))  # (min, max) aka including min and INCLUDING max


print("\n--- 3 random strings ---\n")

s1 = "".join(random.choices(string.ascii_letters, k=5))
print(s1)

s2 = "".join(random.choices(string.ascii_letters + string.digits, k=6))
print(s2)

s3 = "".join(random.choices("aeiou", k=7))
print(s3)

s4 = "".join(random.choices(["a", "b", "c", "1", "2", "3"], k=8))
print(s4)


print("\n--- 4 shuffle lists ---\n")

l = [1, 2, 3, 4]
print(f"{l=}")

sampled = random.sample(l, len(l))
print(f"{sampled=}")  # returns a new shuffled list
print(f"{l=}")

random.shuffle(l)  # shuffles the list in-place
print(f"{l=}")
