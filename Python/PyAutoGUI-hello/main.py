#!/usr/bin/env python3
"""Showcase the 3rd-party library PyAutoGUI that can see changes on screen and
can act on it.

This example detects when a certain image appears on the screen, acts on it, and
also acts when the image disappears again.
"""

import time
from contextlib import suppress
from datetime import datetime

import pyautogui as pg

TEMPLATE = "tgt.png"  # what to detect
CONFIDENCE = 0.9  # `confidence` only works when opencv-python is installed
REGION = None  # (left, top, width, height) or None for full (sliding window?) screen
POLL_MS = 250


def timestamp():
    return datetime.now().strftime("%Y-%m-%d %H:%M:%S")


def on_detect(box):
    print(f"{timestamp()}: detected at {box=}")


def on_vanish():
    print(f"{timestamp()}: target disappeared")


def main():
    last_seen = None  # track to avoid retriggering on same match box

    while True:
        # the `confidence` param is only available when `opencv-python` is
        # installed alongside
        box = None
        try:
            box = pg.locateOnScreen(TEMPLATE, confidence=CONFIDENCE, region=REGION)
        except pg.ImageNotFoundException:
            # No detection means ImageNotFoundException
            # print(f"{e=}")
            pass

        if box and not last_seen:
            on_detect(box)
            last_seen = True
        elif not box and last_seen:
            on_vanish()
            last_seen = False

        time.sleep(POLL_MS / 1000)


if __name__ == "__main__":
    with suppress(KeyboardInterrupt):
        main()

    print("Bye!")
