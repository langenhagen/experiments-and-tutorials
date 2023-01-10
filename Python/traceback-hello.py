#!/usr/bin/env python3
"""
Showcase traceback functionality without and with Exceptions being involved.

There are different approaches when exceptions are involved.

See: https://docs.python.org/3.6/library/traceback.html
"""
import sys
import traceback
from typing import List

print("--- 1 print a traceback ---")


def foo():
    bar(32)


def bar(i):
    print("BEGIN print_stack()")
    traceback.print_stack()
    print("END print_stack()\n")
    print('BEGIN manually print stack trace via "".join(format_list(extract_stack()))')
    # short recipe:
    # import traceback
    # "".join(traceback.format_list(traceback.extract_stack()))
    stack: List[traceback.FrameSummary] = traceback.extract_stack()
    formatted: List[str] = traceback.format_list(stack)
    print("formatted:")
    print("".join(formatted))
    print('END manually print stack trace via "".join(format_list(extract_stack()))\n')


foo()


print("\n--- 2 get a traceback object from an exception ---")


def fail():
    raise ValueError("Please crash")


def catch():
    try:
        fail()
    except ValueError as err:
        exc_type, exc_value, exc_traceback = sys.exc_info()
        formatted = traceback.format_tb(exc_traceback)
        print(f"{type(exc_type)};  {type(exc_value)};  {type(exc_traceback)}\n")
        print(f"{type(formatted)}")
        print(f"{formatted}\n")
        print(f"{type(traceback.format_exc())}")
        print(f"{traceback.format_exc()}")

        print(f"{traceback.format_exception(*sys.exc_info())}\n")

        return exc_traceback


tb = catch()
