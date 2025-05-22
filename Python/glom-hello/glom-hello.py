#!/usr/bin/env python3
"""
Showcase the usage of the external package `glom` for nested data transformation
and extraction. It eases the usage of complex dicts.

Install like:

  pip install glom

See:
- https://glom.readthedocs.io/en/latest/
- https://pypi.org/project/glom/
"""

from glom import Coalesce, assign, glom

print("--- 1 basic extraction ---\n")

data = {"a": {"b": {"c": 123}}, "x": {"y": "hello"}}
val: int = glom(data, "a.b.c")
print(f"{val=}")

print("\n--- 2 restructure/transform with glom ---\n")

data = {
    "user": {
        "profile": {"name": "Alice", "age": 29},
        "stats": {"score": 42},
    }
}
spec = {
    "username": "user.profile.name",
    "age": "user.profile.age",
    "score": "user.stats.score",
}
result = glom(data, spec)
print(f"{result=}")

print("\n--- 3 provide default if missing ---\n")

data = {"foo": {}}
val = glom(data, "foo.bar", default="my default")
print(f"{val=}")

print("\n--- 4 coalesce: first non-missing path ---\n")

data = {"foo": {"bar": None}, "baz": 123}
val = glom(data, Coalesce("foo.bar", "baz"))
print(f"{val=}")

print("\n--- 5 assign: mutate existing dict structure ---\n")

d = {"a": {"b": {"c": 43, "d": 32}}}


def on_missing(*args, **kwargs):
    """*args, **kwargs are empty, you can omit them in production"""
    print(f"hello from on_missing!  {locals()=}")
    return {"my autocreated dict": 43}


assign(d, "a.b.c", 128)
assign(d, "a.x.y.z", 256, missing=on_missing)
print(f"{d=}")
