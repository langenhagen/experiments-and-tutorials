"""Showcase Python's decorators"""

from collections.abc import Callable


def my_decorator(function: Callable) -> Callable:
    def wrap(*args, **kwargs):
        print("before")
        res = function(*args, **kwargs)
        print("after")
        return res

    return wrap


class C:
    @my_decorator
    def foo(self) -> None:
        print("Hello there!")


c = C()
c.foo()
