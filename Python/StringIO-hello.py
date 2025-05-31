#!/usr/bin/env python3
"""Showcase the allegedly fast and little memory consuming string builder StringIO
from the standard lib.
"""

from io import StringIO

print("--- 1 StringIO ---\n")
sio = StringIO()
sio.write("hello")
sio.write(" dear\n")
sio.write("world!")
print(sio.getvalue())

print("\n--- 2 getvalue() resets the cursor ---\n")
sio = StringIO("this will be overwritten")
print(sio.getvalue())
sio.write("hey ho")
print(sio.getvalue())

print("\n--- 3 seeking ---\n")

sio = StringIO("Let's prepend this thing.")
