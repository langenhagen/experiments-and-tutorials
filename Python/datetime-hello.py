#!/usr/bin/env python3
"""Showcase the use of date and time in Python."""
import datetime as dt

print("--- 1 - now ---\n")
now = dt.datetime.now()
utcnow = now.utcnow()
utcnow2 = dt.datetime.now(tz=dt.timezone.utc)

print(f"{now=}")
print(f"{utcnow=}")

print("\n--- 2 - timezones ---\n")
print(f"{now.utcoffset()=}")
print(f"{utcnow.utcoffset()=}")
print(f"{utcnow2.utcoffset()=}")

print("\n--- 3 - datetime differences ---\n")
# if your machine is not in UTC, there will be a difference since both now and
# utcnow have no timezone
print(f"{now - utcnow=}")

try:
    print(f"{utcnow - utcnow2=}")
except TypeError:  # can't subtract offset-naive and offset-aware datetimes
    print(
        "Exception: can't subtract offset-naive and offset-aware datetimes"
        f"{utcnow=} and {utcnow2=}"
    )
