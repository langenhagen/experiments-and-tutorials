#!/usr/bin/env python3
"""Showcase simple data sharing that works in Python

Naive approach but works in simple scenarios.

based on: https://www.pythonforthelab.com/blog/handling-and-sharing-data-between-threads/
"""

from threading import Event, Thread
from time import sleep

event = Event()


def worker(var):
    while True:
        for i in range(len(var)):
            var[i] += 1
        if event.is_set():
            break
        sleep(0.5)
    print("Stop printing")


my_var = [1, 2, 3]  # some mutable container to share data in and out
t = Thread(target=worker, args=(my_var,))
t.start()
while True:
    try:
        print(f"in while {my_var}")
        sleep(1)
    except KeyboardInterrupt:
        event.set()
        break

t.join()

print(f"after join {my_var}")
