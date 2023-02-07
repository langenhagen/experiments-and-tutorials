#!/usr/bin/env python3
"""Showcase the context manager contextlib.suppress() that allows for convenient
ignoring of exceptions"""
from contextlib import suppress

print("--- 1 suppress vs try/except/pass")

# classic way
try:
    hohoho = 11 / 0
except ZeroDivisionError:
    pass


# suppress way
with suppress(ZeroDivisionError):
    hahaha = 12 / 0


print("--- 2 suppress on KeyboardInterrupts e.g. <CTRL + C> ---")


def foo():
    i = 0
    while True:
        print(f"{i} Press <CTRL + C>")
        i += 1


with suppress(KeyboardInterrupt):
    foo()

print("Done")