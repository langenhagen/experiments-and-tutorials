#!/usr/bin/env python
"""
Showcase how events work with Python's Multithreading.
"""
import logging
import threading
import time

logging.basicConfig(level=logging.DEBUG,
                    format='[%(threadName)-12s] %(message)s',)


def wait_for_event(event):
    """Simply wait for an event."""
    logging.debug("wait_for_event starting")
    event_is_set = event.wait()
    logging.debug("event set: %s", event_is_set)


def wait_for_event_timeout(event, timeout):
    """Wait for an event until timeout runs out."""
    while not event.isSet():
        logging.debug("wait_for_event_timeout starting")
        event_is_set = event.wait(timeout)
        logging.debug("event set: %s", event_is_set)
        if event_is_set:
            logging.debug("processing event")
        else:
            logging.debug("doing other things")


if __name__ == "__main__":
    event = threading.Event()
    t1 = threading.Thread(
        name="blocking",
        target=wait_for_event,
        args=(event,))
    t1.start()

    t2 = threading.Thread(
        name="non-blocking",
        target=wait_for_event_timeout,
        args=(event, 1))
    t2.start()

    logging.debug("Waiting before calling Event.set()")
    time.sleep(3)
    event.set()
    logging.debug("Event is set")
