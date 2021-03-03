#!/usr/bin/env python3
"""Detect key presses in Python:

Found here: https://www.jonwitts.co.uk/archives/896
"""
# adapted from https://github.com/recantha/EduKit3-RC-Keyboard/blob/master/rc_keyboard.py
import sys
import termios
import time
import tty


def getch() -> str:
    """Get a single pressed character."""
    file_descriptor = sys.stdin.fileno()
    old_settings = termios.tcgetattr(file_descriptor)
    try:
        tty.setraw(sys.stdin.fileno())
        char = sys.stdin.read(1)

    finally:
        termios.tcsetattr(file_descriptor, termios.TCSADRAIN, old_settings)
    return char


BUTTON_DELAY = 0.2

while True:
    char = getch()  # blocks nicely
    # print('.')  # showcases that the loop does no busy waiting

    if char == "q":
        print("Quit!")
        exit(0)
    if char == "a":
        print("Left pressed")
        time.sleep(BUTTON_DELAY)
    elif char == "d":
        print("Right pressed")
        time.sleep(BUTTON_DELAY)
    elif char == "w":
        print("Up pressed")
        time.sleep(BUTTON_DELAY)
    elif char == "s":
        print("Down pressed")
        time.sleep(BUTTON_DELAY)
    elif char == "1":
        print("Number 1 pressed")
        time.sleep(BUTTON_DELAY)
