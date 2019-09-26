"""
Showcase traceback functionality without Exceptions being involved.

There are different approaches when exceptions are involved.
"""
import traceback


def foo():
    bar(32)


def bar(i):
    print("Normal traceback BEGIN")
    traceback.print_stack()
    print("Normal traceback END")

foo()