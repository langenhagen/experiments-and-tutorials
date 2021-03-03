#!/usr/bin/env python3
"""
Showcase traceback functionality without and with Exceptions being involved.

There are different approaches when exceptions are involved.

See: https://docs.python.org/3.6/library/traceback.html
"""
import sys
import traceback

print("=== 1 print a traceback ===")


def foo():
    bar(32)


def bar(i):
    print("Normal traceback BEGIN")
    traceback.print_stack()
    print("Normal traceback END")

foo()


print("\n=== 2 get a traceback object from an exception ===")


def fail():
    raise ValueError("Please crash")


def catch():
    try:
        fail()
    except ValueError as err:
        exc_type, exc_value, exc_traceback = sys.exc_info()
        formatted = traceback.format_tb(exc_traceback)
        print(f"type(exc_traceback): {type(exc_traceback)}")
        print(f"type(formatted): {type(formatted)}")
        print(f"formatted:\n {formatted}")
        return exc_traceback


tb = catch()
