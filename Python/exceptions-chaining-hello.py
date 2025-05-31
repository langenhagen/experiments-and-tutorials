#!/usr/bin/env python3
"""Showcase that Python seamlessly writes tracebacks of exception that it raises from.
Showcase that this looks like impossible call stacks.
"""

from typing import NoReturn


def outer() -> None:
    print("outer()")
    inner()


def inner() -> NoReturn:
    print("inner()")
    raise ValueError("ohno!")


my_exception = None


def outmost() -> None:
    print("outmost()")
    try:
        outer()
    except Exception as err:
        global my_exception
        my_exception = err


def other_outmost() -> NoReturn:
    print("other_outmost()")
    print(f"{type(my_exception)=}")

    raise KeyError("dios mio!") from my_exception


if __name__ == "__main__":
    outmost()
    other_outmost()
