#!/usr/bin/env python3
"""Showcase the usage of the statement `nonlocal`."""


def my_fun():
    """Defines `myvar` in its outer scope."""
    myvar = 42

    def my_inner_fun():
        """Re-binds `myvar` from the outer, but not global scope."""
        nonlocal myvar
        myvar = 10

    my_inner_fun()
    print(f"Printing from f(): {myvar=}")  # prints 10


my_fun()
