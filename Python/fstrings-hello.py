#!/usr/env/bin python3
"""Showcas the f-strings that exist since Python 3.6."""

print("--- 1 f-strings bind early, just like normal ---")

i = 23

# pylint complains: C0209 Formatting a regular string which could be a f-string
a = "for a %% formatted string, i is %d" % i
b = f"for an f-string, i is {i}"

print(a)  # for a % formatted string, i is 23
print(b)  # for an f-string, i is 23

i = 42

print(a)  # for a % formatted string, i is 23
print(b)  # for an f-string, i is 23

print("\n--- 2 f-strings have sugar ---")

i = 42
f = 1.234

print(f"Use of equals sign {{f=}}: {f=}")
print(f"Use of equals sign with arbitrary code {{1+2+3=}}: {1+2+3=}")
print(f"Round number {{f:.2f}}: {f:.2f}")
print(f"Whitespace-pad numbers {{f:10f}}: {f:10f}")
print(f"Zero-pad integers {{i:x0d}}: {i:03d}")
