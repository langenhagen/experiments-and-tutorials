#!/usr/bin/env python3
"""Showcase the usage of the 3rd party library deepdiff that allows for diffing
nested objects.

See: https://github.com/seperman/deepdiff

author: andreasl
"""

from deepdiff import DeepDiff
from pydantic import BaseModel

print("--- 1 simple DeepDiff with dicts ---\n")

d = {
    "my": {
        "nested": "dict",
        "value": 42,
        "timestamp": 123,
    },
}
e = {
    "my": {
        "other": "dict",
        "value": 42,
        "timestamp": 345,
    },
}
print(f"{DeepDiff(d, e) = }")

print("\n--- 2 with Pydantic models ---\n")


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

print("\n--- 2 exclude_paths with Pydantic models ---\n")

exclude_paths = ["root.status", "root.nested.v"]
print(f"{DeepDiff(m, n, exclude_paths=["status"])      = }")
print(f"{DeepDiff(m, n, exclude_paths=["root.status"]) = }")  # just the same

print(f"{DeepDiff(m, n, exclude_paths=["nested.v"])      = }")
print(f"{DeepDiff(m, n, exclude_paths=["root.nested.v"]) = }")  # just the same

print(f"{DeepDiff(m, n, exclude_paths=["root.status", "root.nested.v"]) = }")

print("\n--- 3 exclude_regex_paths with lists of Pydantic models ---\n")

l = [Nested(v=1, w=2), Nested(v=2, w=3), Nested(v=3, w=4)]
k = [Nested(v=4, w=2), Nested(v=5, w=3), Nested(v=6, w=5)]

print(f"{DeepDiff(l, k) = }")

# ignore all .v's
my_regex = r"root\[\d+\].v"
print(f"{DeepDiff(l, k, exclude_regex_paths=[my_regex]) = }")


print("\n--- 4 exclude_paths with dicts ---\n")

print(f"{DeepDiff(d, e, exclude_paths=["root['my']['timestamp']"]) = }")

print(f"{DeepDiff(d, e, exclude_paths=["root['my']['timestamp']","root['my']['nested']","root['my']['other']"]) = }")


print("\n--- 5 dict against None works ---\n")

print(f"{DeepDiff(None, d) =}")

print("\n--- 5 Pydantic model against None with exclude works ---\n")

print(f"{DeepDiff(None, Nested(v=11, w=12), exclude_paths=['v'])}")

print("\n--- 6 Falsiness of DeepDiff when identical objects ---\n")

if DeepDiff(None, None):
    print("Deepdiff is truthy")
else:
    print("Deepdiff is falsy")  # this holds
