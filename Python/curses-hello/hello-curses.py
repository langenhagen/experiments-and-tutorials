#!/usr/bin/env python3
"""Curses Hello World Playground."""
import curses
import time

print('hallo')
print('welt')
print('yooo')


class App:
    text_y = 13
    text_x = 15

    def draw_frame(self, stdscr):
        screen_rows, screen_cols = stdscr.getmaxyx()
        stdscr.clear()
        for y in range(screen_rows):
            stdscr.insstr(y, 0, '3', curses.A_REVERSE)
            stdscr.insstr(y, screen_cols - 1, '4', curses.A_REVERSE)

        stdscr.insstr(0, 0, '1' * screen_cols, curses.A_REVERSE)
        stdscr.insstr(screen_rows - 1, 0, '2' * screen_cols, curses.A_REVERSE)

    def main(self, stdscr):
        curses.curs_set(0)  # disable blinking cursor
        stdscr.addstr(10, 5, "Hello")

        screen_rows, screen_cols = stdscr.getmaxyx()
        stdscr.addstr(11, 5, f"Screen Dimensions: h:{screen_rows}, w:{screen_cols}")

        self.draw_frame(stdscr)

        n_key = 0
        key = None
        while key != ord('q'):
            key = stdscr.getch()
            n_key += 1
            stdscr.addch(12, 5 + (n_key % 5), key)
            stdscr.refresh()

            stdscr.move(self.text_y, self.text_x)
            # stdscr.clrtoeol()

            stdscr.addstr(self.text_y, self.text_x, ' ' * 10)
            try:
                if key == curses.KEY_RIGHT:
                    self.text_x += 1
                    stdscr.addstr(self.text_y, self.text_x, "RIGHT")
                elif key == curses.KEY_LEFT:
                    stdscr.addstr(self.text_y, self.text_x, "LEFT")
                    self.text_x -= 1
                elif key == curses.KEY_UP:
                    self.text_y -= 1
                    stdscr.addstr(self.text_y, self.text_x, "UP")
                elif key == curses.KEY_DOWN:
                    self.text_y += 1
                    stdscr.addstr(self.text_y, self.text_x, "DOWN")
                elif key == curses.KEY_RESIZE:
                    self.draw_frame(stdscr)
            except:
                pass

        # stdscr.refresh()
        # time.sleep(5)


stdscr = curses.initscr()
app = App()
curses.wrapper(app.main)
