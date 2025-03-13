#!/usr/bin/env python3
"""Showcase the usage of the 3rd party `wat-inspector` library, aka `wat`.

The `wat-inspector` library provides tools for deep, convenient introspection of Python objects.
It simplifies debugging by allowing the exploration of attributes, methods, and other object properties.

See: https://github.com/igrek51/wat

author: andreasl
"""

from wat import wat

print("--- 1 inspect simple data structures ---\n")

my_list = [1, 2, 3, "hello", {"key": "value"}]
wat / my_list

print("\n--- 2 inspect class instances ---\n")


class C:
    def __init__(self):
        self.number = 42
        self.text = "example"

    def greet(self):
        return f"Hello from {self.text}!"


c = C()
wat / c

print("\n--- 3 inspect source code and dunders ---\n")

wat.all.code.dunder / c

print("\n--- 4 inspect in long style ---\n")

wat.all.long / c

print("\n--- 5 inspect in long style ---\n")

wat.all / c

print("\n--- 6 disable output colors ---\n")

wat.gray / C

print("\n--- 7 return the output as string instead of returning it ---\n")

s = wat.gray.str / c  # .gray, otherwise color escape sequences are in the string
print(f"{s}")

print("\n--- 8 inspect functions ---\n")


def foo(x, y):
    return x + y


wat / foo

print("\n--- 9 inspect classes ---\n")

wat / C

print("\n--- 10 inspect locals ---\n")


def bar():
    my_local_var = "I am local"
    wat.locals


bar()

print("\n--- 11 inspect globals ---\n")

wat.globals
