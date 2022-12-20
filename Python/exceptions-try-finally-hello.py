#!/usr/bin/env python3
"""Showcase the usage of less common try-clauses."""

print("--- 1 try-finally ---")

try:
    raise ValueError("hahaha")
finally:
    print("This will happen before the exception crashes the program")


# Does the following:
#
# This will happen before the exception crashes the program
# Traceback (most recent call last):
#   File "<input>", line 2, in <module>
#     raise ValueError("hahaha")
# ValueError: hahaha
