#!/usr/bin/env python3
"""Showcase usage of the standard library `trace` that can help track program
execution, generate code coverage, and analyze function calls.

author: andreasl
"""

from hello import say_hello


def main() -> None:
    print("Starting the main program.")
    say_hello()
    say_hello()  # aaand a second time


if __name__ == "__main__":
    main()
