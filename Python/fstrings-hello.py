#!/usr/env/bin python3
"""Showcas the f-strings that exist since Python 3.6."""

print("--- 1 f strings bind early, just like normal ---")

v = 23

a = "for a %% formatted string, v is %d" % v  # pylint complains: C0209 Formatting a regular string which could be a f-string
b = f"for an f-string, v is {v}"

print(a)  # for a % formatted string, v is 23
print(b)  # for an f-string, v is 23

v = 42

print(a)  # for a % formatted string, v is 23
print(b)  # for an f-string, v is 23

print("--- 2 f strings have sugar ---")

f = 1.234

print(f"Use of equals sign: {f=}")
print(f"Use of equals sign with arbitrary code: {1+2+3=}")
print(f"Use of equals sign: {f:.2f}")
