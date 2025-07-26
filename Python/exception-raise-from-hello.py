#!/usr/bin/env python3
"""Showcase the usage of raise ... from to raise sequences of exceptions.

However, `raise from` is only necessary when you have several exceptions or
causes you can choose from, e.g. in nested try/except blocks.
"""

print("--- 1 the last thrown exception gets propagated ---\n")
try:
    try:
        raise ValueError("The first message")
    except ValueError:
        # following raise logs both the first exception and the second exception
        raise KeyError("Yep, the second message")
except KeyError as err:
    print(f"The error says: {err}")


# print("\n--- 2 simple `raise from` is unnecessary ---\n")

# try:
#     raise ValueError("The first message")
# except ValueError:  # except ValueError as err is not necessary
#     # following raise logs both the first exception and the second exception
#     print("Spongebob, no!")
#     raise KeyError("Yep, the second message")
#     # raise KeyError("Yep, the second message") from err  # does the same

print("\n--- 3 valid `raise from` usecase ---\n")

try:
    raise ValueError("¡Dios mío!")
except ValueError as verr:
    try:
        raise KeyError("¡Ay caramba!")
    except KeyError:
        raise AssertionError("verr Qué padre") from verr
        # raise AssertionError("kerr Qué padre") from kerr
        # raise AssertionError("automatico Qué padre")  # same as ... from kerr
