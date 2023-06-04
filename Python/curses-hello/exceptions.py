#!/usr/bin/env python3
"""Contains common Curses-related exceptions."""


class ScreenTooSmallError(Exception):
    """An exception that may be thrown when the screen is too small."""


class OutOfBoundsError(Exception):
    """An exception that may be thrown when the cursor moves out of bounds."""
