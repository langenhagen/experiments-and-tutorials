#!/usr/bin/env python3
"""Curses Hello World Playground."""
import curses
import curses.panel

from common import *


class Box():

    def __init__(self, text, h, w, y, x):
        self.coords = (y, x)
        self.win = curses.newwin(h, w, y, x)
        self.win.box()
        self.win.move(2, 4)
        self.win.addstr(text)
        self.panel = curses.panel.new_panel(self.win)
        Box.draw()

    def move(self, y, x):
        self.coords = (y, x)
        self.panel.move(y, x)
        Box.draw()

    @staticmethod
    def draw():
        curses.panel.update_panels()
        curses.doupdate()


class App:

    @staticmethod
    def draw_key_info(screen, key):
        screen.move(3, 0)
        screen.addstr(3, 0, f'Key Code: {key}    ')
        screen.move(4, 0)
        screen.addstr(4, 0, f'chr({key}): {chr(key)}    ')

    @staticmethod
    def draw_screen_dimensions(screen):
        screen_rows, screen_cols = getmaxyx(screen)
        addstr(
            screen,
            0,
            0,
            f"Screen Dimensions: h:{screen_rows}, w:{screen_cols}"
        )

    def main(self, screen):
        curses.curs_set(0)  # disable blinking cursor
        App.draw_screen_dimensions(screen)

        a = Box( "Box A", 10, 20, 10, 30)
        b = Box( "Box B", 10, 20, 10, 50)

        key = None
        while key != ord('q'):
            key = screen.getch()
            App.draw_key_info(screen, key)

            if key == curses.KEY_RESIZE:
                screen.clear()
                App.draw_screen_dimensions(screen)
                curses.panel.update_panels()
                screen.refresh()
            elif key == curses.KEY_UP:
                a.move(a.coords[0] - 1, a.coords[1])
            elif key == curses.KEY_DOWN:
                a.move(a.coords[0] + 1, a.coords[1])
            elif key == curses.KEY_RIGHT:
                a.move(a.coords[0], a.coords[1] + 1)
            elif key == curses.KEY_LEFT:
                a.move(a.coords[0], a.coords[1] - 1)
            elif key == ord('w'):
                b.move(b.coords[0] - 1, b.coords[1])
            elif key == ord('s'):
                b.move(b.coords[0] + 1, b.coords[1])
            elif key == ord('a'):
                b.move(b.coords[0], b.coords[1] - 1)
            elif key == ord('d'):
                b.move(b.coords[0], b.coords[1] + 1)


screen = curses.initscr()
app = App()
curses.wrapper(app.main)
