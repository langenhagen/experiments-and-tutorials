#!/usr/bin/env python3
"""Showcase the 3rd party library `flatten_json` that can conveniently
flatten nested dicts.
"""

from flatten_json import flatten, unflatten

print("--- 1 flatten ---\n")

d1 = {
    "peeps": [
        {"name": "Martin", "age": 12, "id": 1},
        {"name": "Carl", "age": 23, "id": 2},
    ]
}

d2 = {"a": 1, "b": 2, "c": [{"d": [2, 3, 4], "e": [{"f": 1, "g": 2}]}]}

flat_d1 = flatten(d1, separator=".")
other_flat_d1 = flatten(d1, separator="|")
flat_d2 = flatten(d2, separator=".")

print(f"      {flat_d1=}")
print(f"{other_flat_d1=}")
print(f"{flat_d2=}")

print("\n--- 2 unflatten - almost, but can't reconstruct lists ---\n")

unflattened_d1 = unflatten(flat_d1, separator=".")

# {"peeps": {"0": {"age": 12, "id": 1, "name": "Martin"}, "1": {"age": 23, "id": 2, "name": "Carl"}}}
print(f"{unflattened_d1=}")
