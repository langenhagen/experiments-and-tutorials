#!/usr/bin/env python3
"""Showcase third-party library pynput
and how to use it as a key logger.

See:
https://pythonhosted.org/pynput/keyboard.html#monitoring-the-keyboard
https://www.youtube.com/watch?v=TbMKwl11itQ
"""
import pynput

KeyboardListener = pynput.keyboard.Listener


def on_press(key):
    """Do some action on keyboard key press."""
    print(f"Key {key} pressed")
    if key is pynput.keyboard.Key.enter:
        print("Key is also enter!")


def on_release(key):
    """Do some action on keyboard key release."""
    print(f"Key {key} released")
    if key == pynput.Key.esc:
        # Stop listener
        return False


with KeyboardListener(on_press=on_press, on_release=on_release) as listener:
    print("Pre-Join")
    listener.join()
    print("Post-Join")
