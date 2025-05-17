#!/usr/bin/env python3
"""TODO."""

import curses

import infobox as infobox

# TODO
# draw... line(selected) dir_up(selected) frame help_window progress_window


class App:
    text_y = 13
    text_x = 15

    @staticmethod
    def draw_frame(stdscr) -> None:
        screen_rows, screen_cols = stdscr.getmaxyx()
        stdscr.insstr(0, 0, " " * screen_cols, curses.A_REVERSE)
        HEADER_MESSAGE = "nfte 0.1 - Use the arrow keys to navigate, press ? for help"
        stdscr.insstr(0, 0, HEADER_MESSAGE, curses.A_REVERSE)
        stdscr.insstr(screen_rows - 1, 0, " " * screen_cols, curses.A_REVERSE)
        FOOTER_MESSAGE = "Total aggregated value: {}  Items: {}"
        stdscr.insstr(screen_rows - 1, 0, FOOTER_MESSAGE, curses.A_REVERSE)

    @staticmethod
    def draw_current_directory(stdscr, path) -> None:
        _, screen_cols = stdscr.getmaxyx()
        stdscr.insstr(1, 0, "-" * screen_cols)
        stdscr.addstr(1, 3, f" {path} ")

    @staticmethod
    def draw_content(stdscr) -> None:
        screen_rows, screen_cols = stdscr.getmaxyx()
        MESSAGE = f"Screen Dimensions: h:{screen_rows}, w:{screen_cols}"
        stdscr.addstr(screen_rows // 2, (screen_cols - len(MESSAGE)) // 2, MESSAGE)

    @staticmethod
    def draw_window(stdscr) -> None:
        App.draw_frame(stdscr)
        App.draw_current_directory(stdscr, "my/fancy/path")
        App.draw_content(stdscr)

    @staticmethod
    def draw_key_info(stdscr, key) -> None:
        stdscr.move(3, 0)
        stdscr.clrtoeol()
        stdscr.addstr(3, 0, f"Key Code: {key}")
        stdscr.move(4, 0)
        stdscr.clrtoeol()
        stdscr.addstr(4, 0, f"chr({key}): {chr(key)}")

    @staticmethod
    def draw_info(stdscr) -> None:
        MESSAGE = (
            "This is my default veeeerrryyyy long text that I want to \n"
            "display with other stuff like the following. E.g., info for testing some stuff\n"
            "Of course :)\n"
            "Of course :)))))\n"
            "Of course :))))))\n"
            "Of course :)))))))\n"
            "Of course :))))))))\n"
            "Of course >:)"
        )
        infobox.draw_infobox(stdscr, 10, 40, "example info", MESSAGE)

    def main(self, stdscr) -> None:
        curses.curs_set(0)  # disable blinking cursor

        App.draw_window(stdscr)

        key = None
        while key != ord("q"):
            # there are some problems with using ncurses and ESC key; generally avoid using ESC key
            key = stdscr.getch()
            App.draw_key_info(stdscr, key)

            if key == curses.KEY_RESIZE:
                stdscr.clear()
                App.draw_window(stdscr)
            elif key == ord("?"):
                App.draw_info(stdscr)
                stdscr.clear()
                App.draw_window(stdscr)
            elif key == curses.KEY_RIGHT:
                self.text_x += 1
                stdscr.addstr(self.text_y, self.text_x, "RIGHT")
            elif key == curses.KEY_LEFT:
                self.text_x -= 1
                stdscr.addstr(self.text_y, self.text_x, "LEFT")
            elif key == curses.KEY_UP:
                self.text_y -= 1
                stdscr.addstr(self.text_y, self.text_x, "UP")
            elif key == curses.KEY_DOWN:
                self.text_y += 1
                stdscr.addstr(self.text_y, self.text_x, "DOWN")


# try:
stdscr = curses.initscr()

app = App()
curses.wrapper(app.main)
# except:
#     print("Error")
