#!/usr/bin/env python3
"""Contains a class that can draw consecutive objects of type Line."""

import curses


class LineView:
    """Draws consecutive Line objects in a curses textpad."""

    def __init__(self, lines) -> None:
        self._cursor_pos = 0
        self.lines = lines

    def _refresh(self) -> None:
        self.pad.refresh(
            self.y_offset,
            0,
            self.y,
            0,
            self.view_rows,
            self.view_cols,
        )

    @property
    def cursor_pos(self):
        return self._cursor_pos

    @cursor_pos.setter
    def cursor_pos(self, value) -> None:
        """Set a certain selected line and draw it inverse color."""
        if not 0 <= value < len(self.lines):
            return

        if value < self.y_offset:
            self.y_offset = value
        elif value >= self.y_offset + self.view_rows - self.y:
            self.y_offset = value - self.view_rows + self.y

        self.lines[self._cursor_pos].draw(self.pad, self._cursor_pos, False)
        self.lines[value].draw(self.pad, value, True, " ")
        self._cursor_pos = value
        self._refresh()

    def draw(self, y, view_rows, view_cols) -> None:
        """Draw or redraw the LineView."""
        rows = len(self.lines)
        cols = view_cols
        self.view_rows = view_rows
        self.view_cols = view_cols
        self.y = y
        self.y_offset = max(0, min(self._cursor_pos - view_rows // 2, rows - view_rows + 1))
        self.pad = curses.newpad(rows, cols)

        for i, line in enumerate(self.lines):
            line.draw(self.pad, i, i == self._cursor_pos, " ")
            i += 1

        self._refresh()
