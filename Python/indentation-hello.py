#!/usr/bin/env python3
"""Showcase that working with different indentations in functions works."""


def normal_indented() -> None:
    print("This has a 4 characters indentation")


def three_spaces_indented() -> None:
   print("This has a 3 characters indentation")


normal_indented()
three_spaces_indented()
