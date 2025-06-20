#!/usr/bin/env python3
"""Showcase the use of date and time in Python."""

import datetime as dt

print("--- 1 - now ---\n")
now = dt.datetime.now()
# naive: datetime.datetime(2025, 6, 1, 11, 37, 7, 649943)
utcnow = now.utcnow()
# better version: datetime.datetime(2025, 6, 1, 11, 38, 6, 582678, tzinfo=datetime.timezone.utc)
now_with_tz_utc = dt.datetime.now(tz=dt.UTC)

print(f"{now=}")
print(f"{utcnow=}")
print(f"{now_with_tz_utc=}")

print("\n--- 2 - timezones ---\n")

print(f"{now.utcoffset()=}")
print(f"{utcnow.utcoffset()=}")
print(f"{now_with_tz_utc.utcoffset()=}")

print("\n--- 3 - datetime differences ---\n")

# if your machine is not in UTC, there will be a difference since both now and
# utcnow have no timezone
print(f"{now - utcnow=}")

try:
    print(f"{utcnow - now_with_tz_utc=}")
except TypeError:  # can't subtract offset-naive and offset-aware datetimes
    print(
        "Exception: can't subtract offset-naive and offset-aware datetimes"
        f"{utcnow=} and {now_with_tz_utc=}"
    )

print("\n--- 4 string from time ---\n")

now_str = now.strftime("%Y-%m-%d--%H-%M-%S")
print(f'{now.strftime("%Y-%m-%d--%H-%M-%S") = }')
print(now_str)
