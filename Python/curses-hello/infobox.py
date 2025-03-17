#!/usr/bin/env python3
"""Curses info boxes on the middle of the screen."""

import curses
import curses.panel

from common import elide_end


class Infobox:
    """A simple curses window that can contain text."""

    def __init__(self, headline, text):
        self.headline = headline
        self.text = text

    def _calculate_dimensions(self, screen_rows, screen_cols, rows, cols):
        return (
            max(5, min(rows, screen_rows)),
            max(8, min(cols, screen_cols)),
        )

    def _calculate_position(self, screen_rows, screen_cols, rows, cols):
        return (
            (screen_rows - rows) // 2,
            (screen_cols - cols) // 2,
        )

    def _create_frame(self, pos_y, pos_x, rows, cols):
        frame = curses.newwin(rows, cols, pos_y, pos_x)
        frame.box()
        frame.move(0, 4)
        frame.addstr(elide_end(self.headline, cols - 8))
        self.panel = curses.panel.new_panel(frame)  # reference is important
        curses.panel.update_panels()

    def _calculate_text_rows_and_columns(self):
        array = self.text.split("\n")
        return (
            len(array) + 1,
            len(max(array, key=len)) + 1,
        )

    def _create_textpad(self, pos_y, pos_x, rows, cols):
        self.n_lines, self.n_columns = self._calculate_text_rows_and_columns()
        self.textpad = curses.newpad(self.n_lines, self.n_columns)
        self.textpad_pad_y, self.textpad_pad_x = 0, 0
        self.textpad_pos_y, self.textpad_pos_x = pos_y, pos_x
        self.textpad_rows, self.textpad_cols = rows, cols
        self.textpad.keypad(True)  # makes it understand s.KEY_UP & KEY_DOWN codes

    def _refresh(self):
        self.textpad.refresh(
            self.textpad_pad_y,
            self.textpad_pad_x,
            self.textpad_pos_y,
            self.textpad_pos_x,
            self.textpad_pos_y + self.textpad_rows,
            self.textpad_pos_x + self.textpad_cols,
        )

    def set_text(self, text):
        """Set the text."""
        self.text = text
        # TODO clear textpad
        self.textpad.insstr(text)
        self._refresh()

    def on_key_event(self, key) -> bool:
        """Handle key events like UP, DOWN, LEFT, RIGHT."""
        if key == ord("q"):
            # TODO kill textpad
            return True
        elif key == ord("k") or key == curses.KEY_UP:
            self.textpad_pad_y = max(0, self.textpad_pad_y - 1)
            self._refresh()
            return True
        elif key == ord("j") or key == curses.KEY_DOWN:
            self.textpad_pad_y = min(
                self.textpad_pad_y + 1, self.n_lines - self.textpad_rows - 2
            )
            self._refresh()
            return True
        elif key == ord("h") or key == curses.KEY_LEFT:
            self.textpad_pad_x = max(0, self.textpad_pad_x - 1)
            self._refresh()
            return True
        elif key == ord("l") or key == curses.KEY_RIGHT:
            self.textpad_pad_x = min(
                self.textpad_pad_x + 1, self.n_columns - self.textpad_cols - 2
            )
            self._refresh()
            return True
        return False

    def draw(self, screen_rows, screen_cols, rows, cols):
        """Draw a simple infobox at the middle of the screen."""
        rows, cols = self._calculate_dimensions(
            screen_rows,
            screen_cols,
            rows,
            cols,
        )
        pos_y, pos_x = self._calculate_position(
            screen_rows,
            screen_cols,
            rows,
            cols,
        )
        self._create_frame(pos_y, pos_x, rows, cols)
        self._create_textpad(
            pos_y + 2,
            pos_x + 2,
            rows - 4,
            cols - 5,
        )
        self.set_text(self.text)
        curses.panel.update_panels()
        curses.doupdate()


class WideInfobox(Infobox):
    """An info box that is pinned to the left and right sides of the screen."""

    def draw(self, screen_rows, screen_cols, rows, pad_cols):
        """Draw a left and right anchored infobox at the middle of the screen."""
        super().draw(
            screen_rows,
            screen_cols,
            rows,
            screen_cols - pad_cols,
        )
