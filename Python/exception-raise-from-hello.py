#!/usr/bin/env python
"""
Showcase the usage of raise ... from to raise sequences of exceptions.

However, `raise from` seems only necessary when you have several exceptions or
causes you can choose from.
"""

print("--- 1 raise from catched ---\n")
try:
    try:
        raise ValueError("The first message")
    except ValueError as err:
        # following raise logs both the first exception and the second exception
        raise KeyError("Yep, the second message") from err
except KeyError as err:
    print(f"The error says: {err}")


print("\n--- 2 simple raise from ---\n")

try:
    raise ValueError("The first message")
except ValueError:
    # following raise logs both the first exception and the second exception
    print("Spongebob, no!")
    raise KeyError("Yep, the second message")
    # raise KeyError("Yep, the second message") from err  # does the same
