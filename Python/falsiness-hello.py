#!/usr/bin/env python3
"""Showcase the usage of boolean checks."""

print("--- 1 falsiness of False ---")

cond = False

if not cond:  # triggers
    print("not cond")

if cond is False:  # triggers
    print("cond is False")

print("--- 1 falsiness of None ---")

cond2 = None

if not cond2:  # triggers

    print("not cond")

if cond2 is False:  # doesn't trigger
    print("cond is False")

# not recommended by flake8 and pylint
# comparison to False should be 'if cond is False:' or 'if not cond:'
# if cond == False:
#     print("cond == False")
