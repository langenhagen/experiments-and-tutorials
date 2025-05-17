"""Showcase how to stop long-running python functions with the standard library
module `signal`.

based on:
https://medium.com/@chamilad/timing-out-of-long-running-methods-in-python-818b3582eed6

Note:
Apparently, the signal only works in the main thread. The callback has to be on
the main thread and the main thread also receives the signal. Related error
message:

    "signal only works in main thread"
"""

import signal
import time
from contextlib import suppress
from typing import NoReturn


class SIGALRMException(Exception):
    """Custom Exception."""


def timeout_handler(num, stack) -> NoReturn:
    """Handler when receiving SIGALRM."""
    print(f"\n*** Received SIGALRM ***\n{num=}\n{stack=}\n---\n")
    raise SIGALRMException


def long_running_function(seconds: int) -> None:
    """A function that takes some time"""
    while seconds > 0:
        seconds -= 1
        print(f"in long_running_function {seconds}")
        time.sleep(1)

    print("Long running function done.")


print("--- 1 cut off a long running function via SIGALRM ---")

signal.signal(signal.SIGALRM, timeout_handler)
signal.alarm(5)  # send an alarm signal in 10 seconds

print(f"Before: {time.strftime('%T')}")
with suppress(SIGALRMException):
    long_running_function(7)

# cancel and unset the alarm; doesn't really matter here bc alarm went already off
signal.alarm(0)
print(f"After: {time.strftime('%T')}")


print("\n--- 2 handler still set ---")

signal.alarm(5)  # send an alarm signal in 10 seconds

print(f"Before: {time.strftime('%T')}")
with suppress(SIGALRMException):
    long_running_function(7)

signal.alarm(0)
print(f"After: {time.strftime('%T')}")


print("\n--- 3 quick functions finish before SIGALRM ---")

# again, but this time the function runs shorter than the alarm
signal.alarm(5)  # send an alarm signal in 10 seconds

print(f"Before: {time.strftime('%T')}")
with suppress(SIGALRMException):
    long_running_function(3)

signal.alarm(0)  # cancel and unset the alarm
print(f"After: {time.strftime('%T')}")
