#!/usr/bin/env python3
"""Showcase that you should overload __bool__ to get `and` and `or` going."""


class WithoutCustomBool:
    pass


a = WithoutCustomBool()
b = WithoutCustomBool()

assert bool(a) == True
assert bool(b) == True
assert (a and b) == b
assert (b and a) == a
assert (a or b) == a
assert (b or a) == b


class D:
    pass


class WithCustomBool:
    def __init__(self, v) -> None:
        self.v = v

    def __bool__(self) -> bool:
        """This guy has to return bool, otherwise the Python interpreter complains."""
        return D()
        return bool(self.v)


c = WithCustomBool(1)
d = WithCustomBool(0)

assert bool(c) == True
assert bool(d) == False

assert (c and d) == d
assert (d and c) == d

assert (c or d) == c
assert (d or c) == c
