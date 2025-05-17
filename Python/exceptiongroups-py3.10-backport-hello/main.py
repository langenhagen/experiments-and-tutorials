#!/usr/bin/env python3
"""Showcase the use of the 3rd party `exceptiongroup` package which is a backport of
the ExceptionGroup classes and feature coming with Python 3.11 and upwards.

For the syntactically not-novell approach, ExceptionGroups work just like their Python 3.11 counterpart.


See: https://github.com/agronholm/exceptiongroup

author: andreasl
"""

from exceptiongroup import ExceptionGroup

print("--- 1 catching ExceptionGroups ---\n")


def raise_exceptions() -> None:
    """Raise and handle 2 exceptions and return/raise them late as an
    ExceptionGroup."""
    exceptions: list[Exception] = []
    try:
        1 / 0  # ZeroDivisionError
    except ZeroDivisionError as e:
        exceptions.append(e)

    try:
        [][2]  # IndexError
    except IndexError as e:
        exceptions.append(e)

    if exceptions:
        raise ExceptionGroup("Multiple exceptions occurred", exceptions)


try:
    raise_exceptions()
except ExceptionGroup as eg:
    print(f"Caught ExceptionGroup with {len(eg.exceptions)} exceptions:")
    for exc in eg.exceptions:
        print(f" - {exc.__class__.__name__}: {exc}")
