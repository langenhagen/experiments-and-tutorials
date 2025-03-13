#!/usr/bin/env python3
"""Hello Dataclasses."""

from dataclasses import dataclass, field
from typing import ClassVar


@dataclass
class MyClass:
    i: int
    s: str = "default string"
    class_var: ClassVar[int] = 0
    a_list: list[str] = field(default_factory=list)

    def __post_init__(self):
        """An optional place for additional validation."""
        print("Hello from _post_init!")


print("--- 1 dataclasses ---")

c = MyClass(42)
print(c)
