#!/usr/bin/env python3
"""Showcase the usage of the 3rd party library deepdiff that allows for diffing
nested objects.

See: https://github.com/seperman/deepdiff

author: andreasl
"""

from deepdiff import DeepDiff
from pydantic import BaseModel

print("--- 1 simple DeepDiff ---\n")

d = {
    "my": {
        "nested": "dict",
        "value": 42,
    },
}
e = {
    "my": {
        "other": "dict",
        "value": 42,
    },
}
print(f"{DeepDiff(d, e) = }")

print("\n--- 2 DeepDiff with Pydantic models ---\n")


class Nested(BaseModel):
    v: int
    w: int


class MyModel(BaseModel):
    status: str = "ok"
    value: int = 42
    nested: Nested


m = MyModel(status="ok", value=42, nested=Nested(v=1, w=2))
n = MyModel(status="nok", value=42, nested=Nested(v=2, w=2))

print(f"{DeepDiff(m, n) = }")

print("\n--- 2 DeepDiff exclude_paths with Pydantic models ---\n")

exclude_paths = ["root.status", "root.nested.v"]
print(f"{DeepDiff(m, n, exclude_paths=["root.status"]) = }")
print(f"{DeepDiff(m, n, exclude_paths=["root.nested.v"]) = }")
print(f"{DeepDiff(m, n, exclude_paths=["root.status", "root.nested.v"]) = }")


print("\n--- 3 DeepDiff exclude_paths with lists of Pydantic models ---\n")

l = [Nested(v=1, w=2), Nested(v=2, w=3), Nested(v=3, w=4)]
k = [Nested(v=4, w=2), Nested(v=5, w=3), Nested(v=6, w=5)]

print(f"{DeepDiff(l, k) = }")

# ignore all .v's
print(f"{DeepDiff(l, k, exclude_regex_paths=[r"root\[\d+\].v"]) = }")
