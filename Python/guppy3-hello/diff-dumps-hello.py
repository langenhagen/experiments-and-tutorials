#!/usr/bin/env python3
"""Compare 2 guppy3/heapy heap dumps."""
from pathlib import Path

from guppy import hpy


dir = Path.home() / "Desktop/memdumps/2024-09-04-21-18-3rd"

h = hpy()
h1 = h.load(f"{dir}/2024-09-04_18-24-03_guppy_heap_status.out")
h2 = h.load(f"{dir}/2024-09-04_19-13-24_guppy_heap_status.out")

diff = h2 - h1

print(diff)
