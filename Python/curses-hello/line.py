#!/usr/bin/env python3
"""Contains classes that contain full lines in a curses screen."""

import curses

from common import elide_middle, getmaxyx, move
from node import ItemNode


class Line:
    """Represents a line on the screen."""

    def __init__(self, string):
        self.string = string

    def generate_string(self):
        return self.string

    def draw(self, screen, y, invert_color=False, fill_character=None):
        """Draw the internally stored string representation of the line."""
        self.screen = screen
        string = self.generate_string()
        move(screen, y, 0)
        screen.clrtoeol()
        if fill_character:
            _, screen_cols = getmaxyx(screen)
            string = string.ljust(screen_cols, fill_character)
        if invert_color:
            screen.insstr(y, 0, string, curses.A_REVERSE)
        else:
            screen.insstr(y, 0, string)


class ItemLine(Line):
    """Represents an line depicting an ItemNode on the screen."""

    def __init__(self, node: ItemNode, progress: int):
        self.node = node
        self.progress = progress

    @staticmethod
    def _get_node_value_string(value) -> str:
        # TODO add the unit
        return str(value).rjust(11, " ")

    @staticmethod
    def _get_node_name_string(node, max_n_chars: int) -> str:
        name = f"/{node.name}" if node.children else node.name
        return elide_middle(name, max_n_chars)

    def generate_string(self) -> str:
        _, screen_cols = getmaxyx(self.screen)
        value_str = ItemLine._get_node_value_string(self.node.value)
        pad1_str = " ["
        progress_str = f"{'#' * max(0, min(self.progress, 10))}".ljust(10, " ")
        pad2_str = "] "
        node_str = ItemLine._get_node_name_string(
            self.node,
            screen_cols
            - len(value_str)
            - len(pad1_str)
            - len(progress_str)
            - len(pad2_str),
        )
        return f"{value_str}{pad1_str}{progress_str}{pad2_str}{node_str}"
