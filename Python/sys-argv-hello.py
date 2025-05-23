#!/usr/bin/env python3
"""Show that sys.argv can also be retrieved from inside a function.

Usage:

    python sys-argv-hello.py yvan eht nioj
"""

import sys


def foo(some, params) -> None:
    print(sys.argv)  # finds the program's argv also from within a function


foo(42, "bar")
