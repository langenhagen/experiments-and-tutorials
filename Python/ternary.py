#!/usr/bin/env python3
"""Showcase how Python is a bit messy with comparison operators when applied one after another,
apparently treating them as ternary operations
"""

assert (5 > 4) == True
assert 5 > (4 == True)

# assert 5 > 4 == True   # fails
# assert 5 > 4 == False  # fails as well
assert 5 > 4 == 4
