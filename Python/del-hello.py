#!/usr/bin/env python3
"""Showcase how to delete/unreference a thing in Python.

author: andreasl
"""


class C:
    """A simple class with a member var."""

    def __init__(self) -> None:
        """Init `self.l`."""
        self.l = [1, 2, 3]


c = C()
print(f"{c.l=}")

x = c.l
del x
# print(f"{x=}")  # won't work because x doesn't exist anymore
print(f"{c.l=}")  # still works

x = c.l
c.l.append(4)
del c
print(f"{x=}")  # still refers to the member of the unreferenced object `c`
