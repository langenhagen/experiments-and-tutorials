#!/usr/bin/env python3
"""Test of the memory usage of creating complex Python objects and sorting them.
Use an external tool to measure the memory usage of sorted().

- values commented inline are for Python 3.10.16
- reported memory usage values differ among runs within the ballpark of ~0.3 MB
- reported memory usage values differ among Python versions, with e.g. Python
  reporting more memory usage than Python 3.10 within the ballpark of ~2-3 MB

author: andreasl
"""

from os import getpid
from sys import getsizeof

from psutil import Process


def print_mem_usage() -> None:
    """Report process memory usage."""
    process = Process(getpid())
    memory_usage = process.memory_info().rss
    print(f"Memory usage: {memory_usage / (1024 * 1024):.2f} MB")


class A:
    """A lil class."""

    def __init__(self, i: int) -> None:
        self.i = i


class O:
    """An exemplaric class."""

    def __init__(
        self,
        s: str,
        a: A,
        d: dict[str, A],
        b: bool,
    ) -> None:
        self.s: str = s
        self.a = a
        self.d = d
        self.b = b


print("--- 0 Initial memory usage ---\n")

print_mem_usage()  # 11.96 MB

print("\n--- 1 Create a bunch of complex nested objects ---\n")

n_objects = 6_000
print(f"Using {n_objects} objects")

objects = [O(s="hi!", a=A(i=i), d={"henlo": A(i=-i)}, b=True) for i in range(n_objects)]

print(f"{getsizeof(objects) = } bytes")  # 5274392 bytes
print_mem_usage()  # 16.59 MB


print("\n--- 2 create a bunch of references to above objects ---\n")

copies = {o.s: o for o in objects}

print(f"{getsizeof(copies) = } bytes")  # 232 bytes
print_mem_usage()  # 16.59 MB


print("\n--- 3 sort the reference bunch, report ---\n")

# ¡ use an external tool to check peak mem usage during sorting !
sorted_objects: list[tuple[str, O]] = sorted(
    copies.items(), key=lambda item: item[1].a.i
)

print(f"{getsizeof(sorted_objects) = } bytes")  # 72 bytes
print_mem_usage()  # 16.59 MB
