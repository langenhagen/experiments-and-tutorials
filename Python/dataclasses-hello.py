#!/usr/bin/env python3
"""Hello Dataclasses."""
from dataclasses import dataclass
from typing import ClassVar


@dataclass
class MyClass:
    i: int
    s: str = 'default string'
    class_var: ClassVar[int] = 0
