#!/usr/bin/env python
"""Showcase the behavior of @classmethods in the light of subclassing.
Sublclassing is possible, cls.__names__ match the actually instantiated classes/instances.
Basically straightforward & what I hoped for.
"""


class C:
    @classmethod
    def foo(cls, a, b) -> None:
        print("C " + str(cls.__name__) + " a=" + str(a) + " b=" + str(b))


class D(C):
    @classmethod
    def foo(cls, a, b) -> None:
        print("D " + str(cls.__name__) + " a=" + str(a) + " b=" + str(b))


class E(C):
    pass


print("--- 1 - calls via classes ---")

print("\nC.foo(1,2):")
C.foo(1, 2)

print("\nD.foo(1,2):")
D.foo(1, 2)

print("\nE.foo(1,2):")
E.foo(1, 2)

print("\n--- 2 - calls via instances ---")

print("\nD().foo(1,2):")
D().foo(1, 2)

print("\nsuper(D,D()).foo(1,2):")
super(D, D()).foo(1, 2)
