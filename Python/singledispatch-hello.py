#!/usr/bin/env python3
"""Tests and experiments with Python's standard lib functools.singledispatch
functionality.

Singledispatch allows for a kind of C++ function overloading in Python
"""

from functools import singledispatch
from typing import Any

print("--- 1 - with unregistered type ---\n")


@singledispatch
def foo(o) -> None:
    print(f"Base foo function: {type(o)=}  {o=}")


foo([1, 2, 3])  # list uses base function

print("\n--- 2 - register another type types ---\n")


@foo.register(int)
def _(o) -> None:
    print(f"foo function for int: {type(o)=}  {o=}")


foo("Hello there")
foo(123)


print("\n--- 3 - register several types ---\n")


@foo.register(str)
@foo.register(bool)
def _(o) -> None:
    print(f"foo impl for several types: {type(o)=}  {o=}")


foo(10)
foo("hello")
foo(True)


print("\n--- 3: Register a custom class ---\n")


class C:
    def __init__(self, v: Any) -> None:
        self.v = v


@foo.register(C)
def _(o) -> None:
    print(f"foo impl for class C: {type(o)=}  {o=}")


obj = C("henlo!")
foo(obj)
