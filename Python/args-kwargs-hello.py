#!/usr/bin/env python3
"""Showcase the usage of args and kwargs."""


def my_purely_kwargs_function(**kwargs):
    """Showcase the implementation of a pure kwargs function."""
    print(f"{kwargs=}")
    print(f"{type(kwargs)=}")


print("--- 1 pure kwargs ---\n")

print("Without kwargs")
my_purely_kwargs_function()

print("With kwargs")
my_purely_kwargs_function(i=23, n="Jorge")
