#!/usr/bin/env python3
"""Showcase basic usage of the 3rd party library `attrs`.

author: andreasl
version: 2021-02-08
"""

import attr


@attr.s()
class MyAttrClass:
    """Similar to dataclass but with extra features."""

    my_field = attr.ib()
    my_other_field = attr.ib(default=None)


print("--- 1 simple usage ---\n")
o = MyAttrClass(42, "answer to wh0t")
print(f"{o=}")
print(f"{o.my_field=}")
print(f"{o.my_other_field=}")

p = MyAttrClass(32)
print(f"{p=}")
# q = MyAttrClass()  # doesn't work

print("\n--- 2 convert attr class to dict ---\n")
d = attr.asdict(o)
print(d)

print("\n--- 3 copy an object from another object ---\n")
o2 = attr.evolve(o)
print(f"{o2=}")
print(f"{o2.my_field=}")
print(f"{o2.my_other_field=}")
assert o is not o2  # o and o2 are different objects
