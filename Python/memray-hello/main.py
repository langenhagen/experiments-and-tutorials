#!/usr/bin/env python3
"""
Showcase the usage of the 3rd party tool `memray` that can help track memory usage.

Note:
When it comes to details, Memray seems to be somewhat unreliable, especially
with transient memory hogs. Sometimes also seems to report wrong values. In this
little example, it seems to report back the biggest memory hog in every frame
proper, but the second and subsequent highest hogs get either not reported or
reported with wrong values. I'd take what Memray gives me back with a grain of
salt.

Example usage:

    rm *.bin; rm *.html;
    memray run main.py;         # run a program under memray
    memray flamegraph *.bin;    # generate a flamegraph html
    xdg-open *.html

author: andreasl
"""

from dataclasses import dataclass
from time import sleep


def hog_memory(n: int):
    return [i for i in range(n)]


@dataclass
class C:
    i: int


# N = 10_000_000
N = 1_000_000

# Not-so transient memory hogging
l = [C(i=i) for i in range(N)]
# sleep(1)
for i in range(N):
    del l[N - 1 - i]

# Basically tranient memory hogging
x = hog_memory(N)
# sleep(1)
del x

# Very much transient memory hoggin
[i for i in range(N)]

print("END")
