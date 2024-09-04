#!/usr/bin/env python3
"""Showcase the usage of the 3rd party `guppy3` library.

Provides tools for heap memory analysis, useful for profiling and debugging
memory usage.

See: https://pypi.org/project/guppy3/

author: andreasl
"""
from guppy import hpy

print("\n--- 1 inspect current memory heap ---\n")

h = hpy()  # create a heap instance
print("Heap info:")
print(f"{h.heap()=}")

print("\n--- 2 monitor memory growth ---\n")

l = list(range(100_000))

print("Heap after allocation:")
print(f"{h.heap()=}")

print("\n--- 3 analyze memory usage by object ---\n")

print("Memory usage by object:")
print(h.iso(l))  # iso[late] object l


print("\n--- 4 make a memory dump ---\n")
h.heap().dump("guppy_heap_status.out")
