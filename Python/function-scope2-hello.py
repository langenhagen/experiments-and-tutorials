"""Showcase that inner functions can see outer params."""

import requests


def foo(param1) -> None:
    def bar() -> None:
        print(f"In bar(): param1= {param1}")  # param1 is visible from within bar()
        print(f"In bar(): param2= {param2}")  # even param2 is visible from within bar()
        print(f"In bar(): session= {session}")  # even session from the context manager is visible from within bar()

    param2 = "parma"
    with requests.Session() as session:
        bar()


foo("Hello")
