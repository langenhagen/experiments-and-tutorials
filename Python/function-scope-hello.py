#!/usr/bin/env python3
"""
Showcase the somewhat weird behaviour of function scopes in Python.

Default arguments are evaluated when the function definition is
encountered.
Subsequent calls to the default will use the same object.
The object may be changed in between.
Thet means, you can modify the object to transport information in and out of
recursive function calls.

See:
https://stackoverflow.com/questions/15284552/python-recursive-function-not-initializing-default-argument-values
"""


class O:
    def __init__(self) -> None:
        self.x = 42


def foo(obj=O()) -> None:
    print(obj.x)
    obj.x += 1
    if obj.x < 100:
        foo()  # here, obj is passed forward without being passed in


foo()  # yields 42, 43, 44, ..., 99

# print(f">>> {obj.x}")  # outside the function however, obj is not visible


def bar(obj=O()) -> None:
    print(f"-{obj.x}")  # yields -42, -43, ..., -99
    obj.x += 1
    if obj.x < 100:
        bar()  # here, obj is passed forward without being passed in
    print(f"+{obj.x}")  # yields +100, +100, ..., +100


bar()
