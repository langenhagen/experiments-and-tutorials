#!/usr/bin/env python3
"""Hello world with Pytest:

Run like
$ pytest pytest-hello.py

Based on:
- https://www.oreilly.com/library/view/python-testing-with/9781680502848/f_0046.xhtml
- https://docs.pytest.org/en/6.2.x/parametrize.html
"""

from typing import NoReturn

import pytest


def greet(name) -> None:
    """A sample function."""
    print(f"Hi, {name}")


def raise_exc() -> NoReturn:
    """Raise an ValueError."""
    print("Foo")
    raise ValueError("This is an exception")


def test_greet(capsys) -> None:
    """Test the stdout with capsys."""
    greet("Earthling")
    out, err = capsys.readouterr()
    assert out == "Hi, Earthling\n"
    assert err == ""

    greet("Brian")
    greet("Nerd")


def test_raise_exc(capsys) -> None:
    """Test raising an exception."""
    with pytest.raises(ValueError):
        raise_exc()
    out, err = capsys.readouterr()
    assert out == "Foo\n"
    assert err == ""


@pytest.mark.parametrize("first_parameter", ["Jeff", "Jonas"])
@pytest.mark.parametrize("test_input, expected", [("3+5", 8), ("2*4", 8)])
def test_eval(first_parameter, test_input, expected) -> None:
    """Showcase parameterized tests."""
    assert first_parameter[0] == "J"
    assert eval(test_input) == expected
