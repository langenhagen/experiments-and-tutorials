#!/bin/env python3
"""Showcase the usage of Mixins in Python.

Mixins exploit the fact that in the case of standard inheritance, in the list of
ancestors, the first class that implements a specific attribute will be the
selected provider for that resolution.

Code based off: https://www.thedigitalcatonline.com/blog/2020/03/27/mixin-classes-in-python/
"""


class Mother:
    def __init__(self, a, b, c):
        self.a = a
        self.b = b
        self.c = c


class Mixin:
    def __init__(self, x, y):
        """Calls to the Mother class of the class to which we add the Mixin to."""
        super().__init__(x, y, x + y)


class Child(
    Mixin, Mother
):  # Child inherits the __init__ from Mixin since Mixin is listed first.
    pass


class WrongMixinUsage(Mother, Mixin):
    """Showcase wrong usage of Mixin.
    Inheritance list in the wrong order.
    Mixin's implementation would not kick in."""


c = Child(1, 2)
assert c.a == 1
assert c.b == 2
assert c.c == 3

# d = Child(8, 9, 10)  # TypeError: Mixin.__init__() takes 3 positional arguments but 4 were given

e = WrongMixinUsage("a", "b", "c")  # Mixin not kicking in
# f = WrongMixinUsage('a', 'b')  # TypeError: Mother.__init__() missing 1 required positional argument: 'c'
