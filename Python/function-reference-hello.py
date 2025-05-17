#!/usr/bin/env python3
"""Showcase the behavior of function references."""

import inspect

print("---1---")


def foo(s: str, i=0) -> None:
    print(f"{s} : {i}")


print(f"Signature of foo() as a function: {inspect.signature(foo)}")


class C:
    def __init__(self, function_reference) -> None:
        self.function_reference = function_reference

    def method(self, b: bool) -> None:
        print(f" C.method() b={b}")


c = C(foo)

print(f"Signature of foo() as a function reference in object c: {inspect.signature(c.function_reference)}")
print(f"Signature of method C.method() in object c: {inspect.signature(c.method)}")

# print(f"Signature of foo() as a function reference in object c: {inspect.signature(C.function_reference)}")  # not available before calling __init__
print(f"Signature of method C.method() in object c: {inspect.signature(C.method)}")  # will also have a field self
