#!/usr/bin/env python3
"""Showcase the context manager contextlib.suppress() that allows for convenient
ignoring of exceptions"""

import time
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


print("\n--- 2 suppress on KeyboardInterrupts e.g. <CTRL + C> ---")


def foo() -> None:
    i = 0
    while True:
        print(f"{i} Press <CTRL + C>")
        i += 1
        time.sleep(1)


with suppress(KeyboardInterrupt):
    foo()


print("\n--- 3 suppress with several Exception types ---")


def foo() -> None:
    i = 10
    while True:
        print(f"{i} Press <CTRL + C> or wait")
        print(f"1/{i} = {1 / i}")
        i -= 1
        time.sleep(1)


with suppress(KeyboardInterrupt, ZeroDivisionError):
    foo()

print("Done")
