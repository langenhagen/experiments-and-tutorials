"""Showcase the quirks and use of mypy.

run via:

  mypy mypy-hello.py
"""

from typing import Any, Iterable, Literal

print("\n--- 1 Mypy is inconsistent about whether None is in `Any` or not ---\n")


def mypy_is_inconsistent_about_any(i: Any | None, j: Any) -> Any:
    """Showcase that addition on `Optional[Any]` aka `Any | None` fails, but on
    Any works. Controversely, a function can return None as Any. However,
    addition only works on Any, not on `Optional[Any]`."""
    a = i + 1  #  error: Unsupported operand types for + ("None" and "int")
    b = j + 1
    return a + b


print("\n--- 2 Lists and Tuples are Iterables ---\n")

l = [1, 2, 3]
t = (1, 2, 3)


def receive_lists(l: list[int]):
    print(f"{l=}")


receive_lists(l)  # ok

# error: has incompatible type "Tuple[int, int, int]"; expected "List[int]"
receive_lists(t)


def receive_iterables(l: Iterable[int]):
    print(f"{l=}")


receive_iterables(l)  # ok
receive_iterables(t)  # ok


print("\n--- 3 no dynamic literals; values in Literals must be ... literals ---\n")

A: Literal["Hey"] = "Hey"
B = "Ho!"
C = "Let's"
D = "Go!"

ALL = [A, B, C, D]


def get_literal() -> Literal["Hey", "Ho!", "Let's", "Go!"]:
    return A


# Literal[*ALL] doesn't work

# won't work, either
# All_TYPE = Literal[A,B,C,D]
# neither does
# All_TYPE = Literal[*ALL]
# def get_literal2() -> All_TYPE:  # Literal[*ALL] doesn't work
#     return A


print("\n--- 4 shortcut assignments for type hints ---\n")

Mytype = Literal["One"] | Literal["Other"]


def foo(x: Mytype) -> None:
    print(f"Got {x}")


foo("was erlauben strunz")  # fails
foo("One")
foo("Other")
foo("other")  # fails
