#!/usr/bin/env python3
"""Compare 2 guppy3/heapy heap dumps."""

from pathlib import Path

from guppy import hpy

memdumps_root = Path.home() / "Desktop/memdumps"
# dir = memdumps_root / "2024-09-05--11-36-without-uvloop-small"
dir = max([d for d in memdumps_root.iterdir() if d.is_dir()])

print(dir)

files = list(dir.glob("*.out"))
oldest_dump = min(files)
youngest_dump = max(files)

print(f"{oldest_dump.name=}")
print(f"{youngest_dump.name=}")
print()

h = hpy()
h1 = h.load(str(oldest_dump))
h2 = h.load(str(youngest_dump))

diff = h2 - h1

print(diff)
