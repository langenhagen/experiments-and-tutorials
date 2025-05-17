"""
Implement a function that safely retrieves the calling function name.
"""

import sys


# def get_function_name() -> str:  # I recommend this header for python 3
def get_function_name():
    """Retrieve the calling function name."""
    frame = sys._getframe(1)
    function_name = frame.f_code.co_name
    return function_name


def some_free_function() -> str:
    i = 42
    j = 12.3
    print(f"Hello from some_free_function()! {i} {j}")
    print(get_function_name())
    return "miau"


class C:
    def foo(self) -> None:
        print("Hello C.foo()")
        print(get_function_name())

    @staticmethod
    def bar() -> None:
        print("Hello from staticmethod C.bar()")
        print(get_function_name())


class D(C):
    pass


class E(C):
    def foo(self) -> None:
        print("Hello from a derived method E.foo()")
        print(get_function_name())


print("---")
some_free_function()
print("---")
c = C()
c.foo()
C.bar()
print("---")
d = D()
d.foo()
D.bar()
print("---")
e = E()
e.foo()
E.bar()
