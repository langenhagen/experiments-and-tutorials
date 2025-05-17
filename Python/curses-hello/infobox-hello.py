import curses

from common import addstr, getmaxyx
from infobox import Infobox, WideInfobox


def draw_key_info(screen, key) -> None:
    screen.move(3, 0)
    screen.addstr(3, 0, f"Key Code: {key}    ")
    screen.move(4, 0)
    screen.addstr(4, 0, f"chr({key}): {chr(key)}    ")


def draw_screen_dimensions(screen) -> None:
    screen_rows, screen_cols = getmaxyx(screen)
    addstr(screen, 0, 0, f"Screen Dimensions: h:{screen_rows}, w:{screen_cols}")


def draw_screen(screen, infobox, key, is_wide) -> None:
    draw_screen_dimensions(screen)

    draw_key_info(screen, key)
    screen_rows, screen_cols = getmaxyx(screen)
    if is_wide:
        infobox.draw(screen_rows, screen_cols, 5, 4)
    else:
        infobox.draw(screen_rows, screen_cols, 5, 40)


def create_normal_infobox():
    return Infobox(
        "Hi, very long headline there!",
        "Hello, World!\nWorld World world world :DDD\n"
        "Hello, World!\nWorld World world world :DDD",
    )


def create_wide_infobox():
    return WideInfobox(
        "Hi, very long headline there!",
        "Hello, World!\nWorld World world world :DDD\nThis is from a Wide info box.",
    )


def create_infobox(is_wide: bool) -> Infobox:
    if is_wide:
        return create_wide_infobox()
    return create_normal_infobox()


def main(screen) -> None:
    curses.curs_set(0)  # disable blinking cursor

    is_wide = False

    infobox = create_infobox(is_wide)
    draw_screen(screen, infobox, ord("x"), is_wide)

    key = None
    while key != ord("q"):
        key = stdscr.getch()

        # screen_rows, screen_cols = getmaxyx(screen)
        # infobox.draw(screen_rows, screen_cols, 3, 20)
        if infobox.on_key_event(key):
            continue

        if key == curses.KEY_RESIZE:
            screen.clear()
            draw_screen(screen, infobox, key, is_wide)
        elif key == ord("n"):
            is_wide = False
            infobox = create_infobox(is_wide)
            draw_screen(screen, infobox, key, is_wide)
        elif key == ord("w"):
            is_wide = True
            infobox = create_infobox(is_wide)
            draw_screen(screen, infobox, key, is_wide)


stdscr = curses.initscr()
curses.wrapper(main)
