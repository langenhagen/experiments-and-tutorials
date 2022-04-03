"""Showcase that mypy is inconsistent about whether None is in `Any` or not.

run via:
  mypy mypy-any-hello.py
"""
from typing import Any, Optional

def foo(i: Optional[Any], j: Any) -> Any:
    """Showcase that addition on Optional[Any] fails, but on Any works.
    Controversely, a function can return None as Any. However, addition only
    works on Any, not on Optional[Any]."""
    a = i + 1  # fails
    b = j + 1
    return None
