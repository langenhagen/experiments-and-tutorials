#!/usr/bin/env python
"""Showcase the usage od th python bytecode disassembly package `dis` in the
standard library.
"""

import dis

print("--- 1 - simple disassembly ---\n")


def foo(i: int):
    j = i + 42
    return j


v = foo(23)
print(f"Result of foo(23) = {v}")

print("\nThe result of dis.dis(foo):\n")
dis.dis(foo)
