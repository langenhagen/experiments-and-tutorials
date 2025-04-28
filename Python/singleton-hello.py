#!/usr/bin/env python3
"""Showcase robuts and flexible Singleton impls in Python.


See also: https://stackoverflow.com/questions/6760685/what-is-the-best-way-of-implementing-a-singleton-in-python
"""

print("--- 1 singleton metaclass implementation and usage ---\n")


class SingletonMeta(type):
    """Singleton metaclass

    Use as a metaclass when creating your own singleton classes:

        class MySingleton(metaclass=SingletonMeta):
            ...

        a = MySingleton()
        a = MySingleton()
        assert a is b
    """

    __instances: dict["SingletonMeta", object] = {}

    def __call__(cls: "SingletonMeta", *args, **kwargs):
        print(f"{type(cls)=}  {cls=}")
        if cls not in cls.__instances:
            cls.__instances[cls] = super().__call__(*args, **kwargs)
        return cls.__instances[cls]


class MySingleton(metaclass=SingletonMeta):
    def __init__(self):
        self.value = 42


# usage
a = MySingleton()
b = MySingleton()
print(f"{a is b = }")


class OtherSingleton(metaclass=SingletonMeta):
    def __init__(self):
        self.other = 23


c = OtherSingleton()
d = OtherSingleton()

print(f"{a is c = }")
print(f"{b is c = }")
print(f"{b is d = }")
print(f"{c is d = }")

print("\n--- 2 type checks ---\n")


class C:
    pass


print(f"{issubclass(SingletonMeta, object)=}")  # True
print(f"{issubclass(SingletonMeta, type)=}")  # True
print()
print(f"{issubclass(MySingleton, SingletonMeta)=}")  # False
print(f"{issubclass(MySingleton, object)=}")  # True
print(f"{issubclass(MySingleton, type)=}")  # False
print()
print(f"{isinstance(MySingleton, SingletonMeta)=}")  # True
print(f"{isinstance(OtherSingleton, SingletonMeta)=}")  # True
print(f"{isinstance(MySingleton, object)=}")  # True
print(f"{isinstance(MySingleton, type)=}")  # True
print(f"{isinstance(MySingleton, int)=}")  # False
print(f"{isinstance(C, object)=}")  # True
print(f"{isinstance(C, type)=}")  # True
print(f"{isinstance(C, SingletonMeta)=}")  # False
print()
print(f"{isinstance(a, SingletonMeta)=}")  # False
print(f"{isinstance(a, MySingleton)=}")  # True
print(f"{isinstance(a, object)=}")  # True
print(f"{isinstance(a, type)=}")  # False
