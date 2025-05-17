#!/usr/bin/env python3
"""
Showcase Python's decorators can NOT easily be switched at runtime.

You can replace the decorator with another function any point before the
interpreter gets to that function declaration. However, once the decorator is
applied to the function, there seems to be no easy way to change the decorator
later.

See also:
https://stackoverflow.com/questions/642762/is-it-possible-to-replace-a-python-function-method-decorator-at-runtime
"""

import functools
from collections.abc import Callable


def my_decorate_function(f: Callable) -> Callable:
    print("BEGIN my_decorate_function()")

    @functools.wraps(f)
    def wrapper(*args, **kwargs):
        print("  BEGIN my_decorate_function.wraps() before")
        result = f(*args, **kwargs)
        print("  END my_decorate_function.wraps() after")
        return result

    print("END my_decorate_function()")
    return wrapper


def other_decorate_function(f: Callable) -> Callable:
    print("BEGIN other_decorate_function()")

    @functools.wraps(f)
    def wrapper(*args, **kwargs):
        print("  BEGIN other_decorate_function.wraps() before")
        result = f(*args, **kwargs)
        print("  END other_decorate_function.wraps() after")
        return result

    print("END other_decorate_function()")
    return wrapper


my_decorator = my_decorate_function


@my_decorator
def foo() -> None:
    print("    Hello from foo()!")


foo()

# change the decorator; does not have an effect
my_decorator = other_decorate_function
foo()
