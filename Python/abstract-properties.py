"""Showcase properties in Python, especially defined in an abstract base class."""

from abc import ABC, abstractmethod


class MyBase(ABC):
    @property
    @abstractmethod
    def my_property(self):
        pass


class C(MyBase):
    def __init__(self, value) -> None:
        self._value = value

    @property
    def my_property(self):
        return self._value


c = C(42)
print(c.my_property)  # 42
