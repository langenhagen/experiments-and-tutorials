#!/usr/bin/env python
"""Showcase how to catch keyboard input with the third-party library pynput
and how to use it as a key logger.

The window doesn't even need focus to grab the keyboard input.

See:
https://pythonhosted.org/pynput/keyboard.html#monitoring-the-keyboard
https://www.youtube.com/watch?v=TbMKwl11itQ

Tested with pynput 1.7.1.

author: andreasl
"""

import pynput

KeyboardListener = pynput.keyboard.Listener


def on_press(key) -> None:
    """Do some action on keyboard key press."""
    print(f"Key {key} pressed")
    if key is pynput.keyboard.Key.enter:
        print("Moreover, key is also enter!")


def on_release(key) -> bool:
    """Do some action on keyboard key release."""
    print(f"Key {key} released")
    if key == pynput.keyboard.Key.esc:
        # Stop listener
        return False
    return True


with KeyboardListener(on_press=on_press, on_release=on_release) as listener:
    print("Pre-Join")
    listener.join()
    print("Post-Join")
