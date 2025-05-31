#!/usr/bin/env python3
"""Show a line that is like I want it with ncursesTraitExplorer."""

import curses

from common import addstr, elide_middle, getmaxyx, move
from line import ItemLine, Line
from lineview import LineView
from node import ItemNode


def create_lines():
    level_up_line = Line("/..".rjust(28))
    lines = [level_up_line]
    for i in range(100):
        my_node = ItemNode()
        my_node.value = f"{i % 10}.0 GiB"
        my_node.name = f"{my_node.name}{i}"
        lines.append(ItemLine(my_node, i % 10))
    return lines


# def draw_screen_dimensions(screen):
#     rows, cols = getmaxyx(screen)
#     line = Line(f"Screen Dimensions: h:{rows}, w:{cols}")
#     line.draw(screen, 0)


def draw_top_line(screen) -> None:
    line = Line("nte 0.1 - Use the arrow keys to navigate, press ? for help")
    line.draw(screen, 0, invert_color=True, fill_character=" ")


def draw_path_line(screen, path) -> None:
    _, cols = getmaxyx(screen)
    line = Line(elide_middle(f"--- {path} ---", cols))
    line.draw(screen, 1, invert_color=False, fill_character="-")


def draw_bottom_line(screen, aggregated_total, n_items) -> None:
    rows, _ = getmaxyx(screen)
    footer_string = f"Total aggregated value: {aggregated_total}  Items: {n_items}"
    line = Line(footer_string)
    line.draw(screen, rows - 1, True, " ")


def draw_screen(screen, line_view) -> None:
    # draw_screen_dimensions(screen)
    screen.refresh()
    draw_top_line(screen)
    draw_path_line(screen, "some/weird/very/loooooooong/path")
    screen_rows, screen_cols = getmaxyx(screen)
    line_view.draw(2, screen_rows - 2, screen_cols)
    draw_bottom_line(screen, 123.4, 13)


def main(screen) -> None:
    curses.curs_set(0)  # disable blinking cursor
    lines = create_lines()
    line_view = LineView(lines)

    draw_screen(screen, line_view)

    key = ord("x")
    while key != ord("q"):
        key = screen.getch()

        if key == curses.KEY_RESIZE:
            screen.clear()
            draw_screen(screen, line_view)

        elif key == curses.KEY_UP:
            line_view.cursor_pos -= 1

        elif key == curses.KEY_DOWN:
            line_view.cursor_pos += 1

        elif key == curses.KEY_RIGHT:
            pass

        elif key == curses.KEY_LEFT:
            pass


stdscr = curses.initscr()
curses.wrapper(main)

