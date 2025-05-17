#!/usr/bin/env python3
"""Showcase the usage of args and kwargs."""


def my_purely_args_function(*args) -> None:
    """Showcase the implementation of a pure args function."""
    print(f"{args=}")
    print(f"{type(args)=}")


def my_purely_kwargs_function(**kwargs) -> None:
    """Showcase the implementation of a pure kwargs function."""
    print(f"{kwargs=}")
    print(f"{type(kwargs)=}")


print("--- 1 pure args ---\n")

print("Without args")
my_purely_args_function()

print("With args")
my_purely_args_function(23, "Jorge")

print("\n--- 2 pure kwargs ---\n")

print("Without kwargs")
my_purely_kwargs_function()

print("With kwargs")
my_purely_kwargs_function(i=23, n="Jorge")
