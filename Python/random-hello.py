"""
Showcase the usage of of the package `random`.
"""
import random
import string

# random.Seed() or random.seed(None) seeds from current time or from an
# operating system specific randomness source if available
# random.seed(None) happens implicitly.
# random.seed()

print("--- 1 random integers ---")

for _ in range(10):
    print(random.randint(1, 10))  # including min and INCLUDING max


print("--- 2 random strings ---")

s1 = "".join(random.choices(string.ascii_letters, k=5))
print(s1)

s2 = "".join(random.choices(string.ascii_letters + string.digits, k=6))
print(s2)

s3 = "".join(random.choices("aeiou", k=7))
print(s3)

s4 = "".join(random.choices(["a", "b", "c", "1", "2", "3"], k=8))
print(s4)
