#!/usr/bin/env python3
"""Showcase simple keyboard input."""

import logging
import threading
import time


def wait_for_event(event):
    """Simply wait for an event."""
    print("Thread Start")
    starttime = time.time()
    event_is_set = event.wait()
    endtime = time.time()
    print(f"Thread End: Time: {endtime - starttime}")


if __name__ == "__main__":
    print("Wait for key press ENTER to start...")
    input()

    event = threading.Event()
    t1 = threading.Thread(name="blocking", target=wait_for_event, args=(event,))
    t1.start()

    print("Wait for key press ENTER to end...")
    input()

    event.set()
    logging.debug("Event is set")
