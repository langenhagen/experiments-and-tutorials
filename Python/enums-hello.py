#!/usr/bin/env python3
"""Showcase the usage of Python enumerations."""
import enum

print("--- 1 simple enums ---")


class MyEnum(enum.Enum):
    """Simple enum with enum.auto()"""

    VAL_1 = enum.auto()
    VAL_2 = enum.auto()


a = MyEnum.VAL_1
b = MyEnum.VAL_2

print(f"{a=}")
print(f"{a.name=}")
print(f"{a.value=}")

print(f"{b=}")
print(f"{b.name=}")
print(f"{b.value=}")


print("--- 2 enums with string values ---")


class MyStringEnum(enum.Enum):
    """Enum with string values."""

    VAL_1 = "my val"  # <NAME> = <VALUE>
    VAL_2 = "my other val"


c = MyStringEnum.VAL_1
d = MyStringEnum.VAL_2

print(f"{c=}")
print(f"{c.name=}    {c.value=}")
print(f"{d=}")
print(f"{d.name=}    {d.value=}")

print("--- 3 enums from string values ---")

e = MyStringEnum("my val")
print(f"{e=}")

f = MyStringEnum("my other val")
print(f"{f=}")

# g = MyStringEnum("my not existing val")  # ValueError: 'my not existing val' is not a valid MyStringEnum
