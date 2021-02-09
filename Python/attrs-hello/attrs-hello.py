#!/usr/bin/env python
"""Showcase basic usage of the 3rd party library `attrs`.

@author: andreasl
@version: 2021-02-08
"""
import attr


@attr.s()
class MyAttrClass:
    """Similar to dataclass but with extra features."""
    my_field = attr.ib()
    my_other_field = attr.ib()

print("=== 1 simple usage ===")
o = MyAttrClass(42, "answer to wh0t")
print(f"{o=}")
print(f"{o.my_field=}")
print(f"{o.my_other_field=}")

print("=== 1 convert attr class to dict ===")
d = attr.asdict(o)
print(d)
