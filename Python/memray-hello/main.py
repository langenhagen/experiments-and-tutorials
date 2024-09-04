#!/usr/bin/env python3
"""
Showcase the usage of the 3rd party tool `memray` that can help track memory usage.

Example usage:

    rm *.bin; rm *.html;
    memray run main.py;         # run a program under memray
    memray flamegraph *.bin;    # generate a flamegraph html
    xdg-open *.html

author: andreasl
"""
from dataclasses import dataclass


@dataclass
class C:
    i: int


N = 10_000_000

l = [C(i=i) for i in range(N)]

for i in range(N):
    del l[10_000_000 - 1 - i]

print("END")
