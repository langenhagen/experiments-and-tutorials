#!/usr/bin/env python3
"""Showcase the use of the standard library module `gc` (garbage collector).
`gc` can help seeing object references.
"""

import gc


class B:
    def __init__(self, name) -> None:
        self.name = name

    def __del__(self) -> None:
        print(f"{self} destroyed")

    def __repr__(self) -> str:
        return f"B(name={self.name})"


class C:
    def __init__(self, name, b: B) -> None:
        self.name = name
        self.b = b

    def __del__(self) -> None:
        print(f"{self} destroyed")

    def __repr__(self) -> str:
        return f"C(name={self.name} b={self.b})"


print("--- 1 enabling/disable the garbage collector ---\n")

print(f"{gc.isenabled()=}")  # True
gc.disable()
print(f"{gc.isenabled()=}")  # False
gc.enable()
print(f"{gc.isenabled()=}")  # True

print("--- 2 get_referents() - get objects that given objects directly refer to ---\n")

b1 = B("first")
b2 = B("second")
b3 = B("third")

c1 = C("First", b1)
c2 = C("Second", b2)
c3 = C("Third", b3)

print(f"{c1=}\n{c2=}\n{c3=}\n")

l = [c1, c2, c3]

print(f"{gc.get_referents(b1)=}")
print(f"{gc.get_referents(c1)=}")
print(f"{gc.get_referents(l)=}")

print("\n--- 3 get_referrers(): get objects that refer to given objects ---\n")


def print_referrers(o) -> None:
    referrers = gc.get_referrers(o)
    print(f"Referrers to {o} ({len(referrers)=}):")

    for r in referrers:
        print(f"  {r}")
        print("  ---")

    print("===")


print_referrers(l)
print_referrers(b1)
print_referrers(c1)

print("\nnow deleting list l and checking the referrers of c1 again...\n")
del l
print_referrers(c1)

print("\n--- 4 manual garbage collection ---\n")

# add some circular dependency; gc can deal with that
c1.o = c2
c2.o = c1

# just pro-forma; the gc does NOT immediately kick in when an object is deleted
gc.disable()

del c1
del c2

result = gc.collect()
print(
    f"gc.collect() returned number of unreachable objects {result}"
)  # e.g. circular refs
print(f"Uncollectable objects: {gc.garbage=}")
print(f"\n{gc.get_stats()=}")

print("\n*** END ***\n")
