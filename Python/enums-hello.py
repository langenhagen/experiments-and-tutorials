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
print(f"{b=}")


print("--- 2 enums with string values ---")


class MyStringEnum(enum.Enum):
    """Enum with string values."""

    VAL_1 = "my val"
    VAL_2 = "my other val"


c = MyStringEnum.VAL_1
d = MyStringEnum.VAL_2

print(f"{c=}")
print(f"{d=}")
