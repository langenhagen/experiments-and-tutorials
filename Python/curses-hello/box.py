import curses


def main():
    stdscr = curses.initscr()

    curses.noecho()
    curses.curs_set(False)
    stdscr.keypad(True)

    window = curses.newwin(3, 10, 20, 30)
    window.box()
    window.addstr("Hey there!!")

    running = True
    while running:
        key = window.getch()
        if key == 27:
            running = False
            break

    curses.endwin()


main()
