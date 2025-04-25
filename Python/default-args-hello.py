#!/usr/bin/env python3
"""Showcase the usage of default function arguments."""

import time

print("--- 1 default ---\n")


def foo(x, y=23):
    print(f"{locals()=}")


foo(1)

print("\n--- 2 pitfall: function invocations as default args get evaluated only once ---\n")


def get_time() -> float:
    print("Hello from get_time()!")
    return time.time()


# gets only evaluated once, intially!
def bar(x, y=get_time()):
    print(f"{locals()=}")


bar(2)
time.sleep(1)
bar(3)
