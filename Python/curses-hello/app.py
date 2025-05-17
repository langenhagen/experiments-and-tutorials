#!/usr/bin/env python3
"""Contains a scaffold for my typical Curses-based application."""

import curses

import common


class CursesApp:
    """A scaffold for a general Curses application."""

    def __init__(self) -> None:
        self.keep_running = None
        self.min_screen_rows = 3
        self.min_screen_cols = 3
        self.screen_rows = None
        self.screen_cols = None
        self.screen = curses.initscr()
        curses.curs_set(0)  # disable blinking cursor

    def run(self) -> None:
        """Call the draw_loop() function with the Curses context."""
        curses.wrapper(self.draw_loop)

    def draw_loop(self, screen) -> None:
        """Repeatedly draw and process input."""
        self.read_screen_size()
        self.redraw()
        self.keep_running = True
        while self.keep_running:
            key = screen.getch()
            if key == curses.KEY_RESIZE:
                self.handle_key_resize_event()
            else:
                self.handle_key_event(key)

    def draw_screen_dimensions(self) -> None:
        """Draw the screen Dimensions to the screen."""
        message = f"h:{self.screen_rows}, w:{self.screen_cols}"
        common.addstr(
            self.screen,
            self.screen_rows // 2,
            (self.screen_cols - len(message)) // 2,
            message,
        )

    def read_screen_size(self) -> None:
        """Write the screen's rows and columns to member variables."""
        self.screen_rows, self.screen_cols = common.getmaxyx(self.screen)

    def prompt_for_screen_size(self) -> bool:
        """Read the screen size. If it is too small, prompt a dialog."""
        if self.min_screen_rows >= self.screen_rows:
            return common.prompt_screen_too_small(self.screen)
        if self.min_screen_cols >= self.screen_cols:
            return common.prompt_screen_too_small(self.screen)
        return True

    def handle_key_resize_event(self) -> None:
        """Call redraw or ask for action if the screen is too small."""
        self.read_screen_size()
        if self.prompt_for_screen_size():
            self.redraw()
        else:
            self.keep_running = False

    def handle_key_event(self, key) -> None:
        """Handle key events. Override this method."""
        if key == ord("q"):
            self.keep_running = False

    def redraw(self) -> None:
        """Clear the screen and draw a frame. Override this method."""
        self.screen.clear()
        self.draw_screen_dimensions()
