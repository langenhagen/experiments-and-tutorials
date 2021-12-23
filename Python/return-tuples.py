#!/usr/env/bin python3
"""Showcase that Python wraps multiple return objects in a tuple."""

print("--- 1 - without parentheses ---")


def return_tuple_without_parens() -> tuple[int, int, int]:
    """Return a tuple without parentheses."""
    return 1, 2, 3


r = return_tuple_without_parens()
print(f"Result: {r}; Type of the result {type(r)}")


print("--- 2 - with parentheses ---")


def return_tuple_with_parens() -> tuple[int, int, int]:
    """Return a tuple with parentheses."""
    return (4, 5, 6)


r = return_tuple_with_parens()
print(f"Result: {r}; Type of the result {type(r)}")
