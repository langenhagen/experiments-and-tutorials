"""
Show that sys.argv can also be retrieved from inside a function.
"""
import sys


def foo(some, params):
    print(sys.argv)  # finds the program's argv also from within a function


foo(42, 'bar')
