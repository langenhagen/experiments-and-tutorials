"""Showcase the quirks and use of mypy.

run via:
  mypy mypy-any-hello.py
"""
from typing import Any, Iterable, Optional

print("\n--- 1 Mypy is inconsistent about whether None is in `Any` or not ---\n")


def mypy_is_inconsistent_about_any(i: Optional[Any], j: Any) -> Any:
    """Showcase that addition on Optional[Any] fails, but on Any works.
    Controversely, a function can return None as Any. However, addition only
    works on Any, not on Optional[Any]."""
    a = i + 1  #  error: Unsupported operand types for + ("None" and "int")
    b = j + 1
    return None


print("\n--- 2 Lists and Tuples are Iterables ---\n")

l = [1, 2, 3]
t = (1, 2, 3)


def receive_lists(l: list[int]):
    print(f"{l=}")


receive_lists(l)  # ok
receive_lists(
    t
)  # error: has incompatible type "Tuple[int, int, int]"; expected "List[int]"


def receive_iterables(l: Iterable[int]):
    print(f"{l=}")


receive_iterables(l)  # ok
receive_iterables(t)  # ok
