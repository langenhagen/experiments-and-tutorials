#!/usr/bin/env python3
"""Contains common curses helper functions and wrappers araund curses functions."""
import curses

import exceptions


def getmaxyx(screen):
    """Retrieve the given screen's dimensions. Raise an exception if the dimensions are too small."""
    screen_rows, screen_cols = screen.getmaxyx()
    if screen_rows < 3 or screen_cols < 3:
        raise exceptions.ScreenTooSmallError(
            f"The screen resolution is too small:"
            f" {screen_rows}x{screen_cols} characters."
        )
    return screen_rows, screen_cols


def addstr(screen, y, x, text):
    """Safely add a string and abbreviate and draw ellipses if necessary."""
    screen_rows, screen_cols = getmaxyx(screen)
    if not ((0 <= y < screen_rows) and (0 <= x < screen_cols)):
        raise exceptions.OutOfBoundsError(
            f"The addstr() position is out of bounds: ({y}, {x})"
            f" on {screen_rows}x{screen_cols} character screen."
        )
    elif x + len(text) > screen_cols:
        text = f"{text[:max(0, screen_cols - x - 3)]}..."

    screen.addstr(y, x, text)


def move(screen, y, x):
    """Move the cursor to the given position. Raise an exception if the position is out of bounds."""
    screen_rows, screen_cols = getmaxyx(screen)
    if (not 0 <= y < screen_rows) or (not 0 <= x < screen_cols):
        raise exceptions.OutOfBoundsError(
            f"The move() position is out of bounds: ({y}, {x})"
            f" on {screen_rows}x{screen_cols} character screen."
        )
    screen.move(y, x)


def elide_middle(text: str, max_n_chars: int) -> str:
    """Return an in the middle elided copy of the given text that fits the given length."""
    if len(text) <= max_n_chars:
        return text
    if max_n_chars <= 3:
        return '.' * max_n_chars

    n_first_chars = max_n_chars // 2 - 2
    n_last_chars = max_n_chars - n_first_chars - 3
    return f"{text[:n_first_chars]}...{text[-n_last_chars:]}"


def elide_end(text: str, max_n_chars: int) -> str:
    """Return an at the end elided copy of the given text that fits the given length."""
    if len(text) <= max_n_chars:
        return text
    if max_n_chars <= 3:
        return '.' * max_n_chars

    n_first_chars = max_n_chars - 3
    return f"{text[:n_first_chars]}..."


def prompt_screen_too_small(screen):
    """Prompt that the screen is too small and ask for action."""
    def show_message(screen):
        screen.clear()
        screen.insstr(
            0,
            0,
            "Warning: terminal too small,\n"
            " please either resize your terminal,\n"
            " press i to ignore, or press q to quit."
        )
    show_message(screen)
    char = None
    while not (char == ord('i') or char == ord('q')):
        char = screen.getch()
        if char == curses.KEY_RESIZE:
            show_message(screen)
    screen.clear()
    return True if char == ord('i') else False
