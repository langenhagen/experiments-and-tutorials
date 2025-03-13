#!/usr/bin/env python3
"""
Showcase that Python seamlessly writes tracebacks of exception that it raises from.
Showcase that this looks like impossible call stacks.
"""


def outer():
    print("outer()")
    inner()


def inner():
    print("inner()")
    raise ValueError("ohno!")


my_exception = None


def outmost():
    print("outmost()")
    try:
        outer()
    except Exception as err:
        global my_exception
        my_exception = err


def other_outmost():
    print("other_outmost()")
    print(f"{type(my_exception)=}")

    raise KeyError("dios mio!") from my_exception


if __name__ == "__main__":
    outmost()
    other_outmost()
