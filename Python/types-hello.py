#!/usr/bin/env python3
"""Showcase the usage of Python's `types` module.

The types module allows one to inspect and handle specific object types, like
checking if an object is a function or creating functions dynamically. It is
useful for dynamic type checking and meta-programming.
"""
import types

print("--- 1 check types of things ---\n")


def foo():
    return "Hello, World!"


print(f"{isinstance(foo, types.FunctionType)=}")  # True

print("\n--- 2 create a dynamic functions ---\n")

dynamic_func = types.FunctionType(foo.__code__, globals())
print(f"{isinstance(dynamic_func, types.FunctionType)=}")  # True
print(f"{dynamic_func()=}")  # "Hello, World!"
