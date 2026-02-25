#!/usr/bin/env python3
"""A small playground for Python's standard library `typing` module
and related typing features.

Use like:

    python3 typing-hello.py
    mypy typing-hello.py
"""

from typing import overload

print("--- overload ---\n")
# `overload` allows for different function signatures without confusing type
# checkers.


@overload
def greet(name: str) -> str: ...
@overload
def greet(times: int) -> list[str]: ...


def greet(arg):
    """Greet the user with varying interfaces."""
    if isinstance(arg, str):
        return f"Hello, {arg}!"
    if isinstance(arg, int):
        return ["Hello, World!"] * arg
    raise TypeError("Unsupported type")


print(f"{greet('Alice')=}")
print(f"{greet(3)=}")
print(f"{type(greet(3))=}")
