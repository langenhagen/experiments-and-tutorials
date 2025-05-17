#!/usr/bin/env python3
"""Showcase the usage of less common try-clauses.

Comment out the functions you don't want to see crashing, so you can see the
program crashing in places that you are interested in.
"""

print("--- 1 try-finally ---\n")


def try_finally() -> None:
    # Does the following:
    #
    # This will happen before the exception crashes the program
    # Traceback (most recent call last):
    #   File "<input>", line 2, in <module>
    #     raise ValueError("hahaha")
    # ValueError: hahaha
    try:
        raise ValueError("hahaha")
    finally:
        print("Hello from the finally clause: happens before program crashes")


# try_finally()

print("\n--- 2 try-except-finally ---\n")


def try_except_finally() -> None:
    try:
        raise ValueError("hahaha")
    except ValueError as e:
        print(f"Caught a Value Error: {e}")
    finally:
        print("Hello from the finally clause!")


try_except_finally()

print("\n--- 3 try-except-raise-finally - finally kicks in always ---\n")


def try_except_finally() -> None:
    try:
        raise ValueError("hahaha")
    except ValueError as e:
        print(f"Caught a Value Error: {e}, raising again!")
        raise
    finally:
        print("Hello from the finally clause!")


try_except_finally()
