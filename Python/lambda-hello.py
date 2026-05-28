#!/usr/bin/env python3
"""Showcase the usage of Python lambda (anonymous inline) functions.

Lambdas are single-expression anonymous functions.
Use them for short, throw-away operations where a named def would be overkill.
"""

from collections.abc import Callable

print("--- 1 basic syntax ---\n")

# ruff linter rule E731: do not assign a lambda expression, use a def
double = lambda x: x * 2  # noqa: E731
print(f"{double(5)=}")
print(f"{(lambda x: x * 3)(5)=}")


def make_multiplier(n: int) -> Callable[[int], int]:
    """Create a multiplier function."""
    return lambda x: x * n


triple = make_multiplier(3)
print(f"{triple(5)=}")

print("\n--- 2 lambda with map, filter, sorted ---\n")

numbers = [1, 2, 3, 4, 5]

# ruff linter rule C417: Unnecessary map() usage (rewrite using a list comprehension).
doubled = list(map(lambda x: x * 2, numbers))  # noqa: C417
evens = list(filter(lambda x: x % 2 == 0, numbers))  # noqa: C417
print(f"{doubled=}")
print(f"{evens=}")

pairs = [(1, "z"), (3, "x"), (2, "y")]
sorted_pairs = sorted(pairs, key=lambda p: p[1])
print(f"{sorted_pairs=}")

print("\n--- 3 lambda vs def ---\n")


def add_def(a: int, b: int) -> int:
    """Add two numbers."""
    return a + b


add_lambda = lambda a, b: a + b  # noqa: E731

print(f"{add_def(3, 4)=}")
print(f"{add_lambda(3, 4)=}")

print("\n--- 4 lambda limitations ---\n")

# not possible: statements, annotations, multiple expressions
# lambda x: x; x  # would be a SyntaxError

# but you can use a tuple to sequence expressions
f = lambda x: (x, x * 2, x * 3)  # noqa: E731
print(f"{f(3)=}")
