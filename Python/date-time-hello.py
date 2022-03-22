#!/usr/bin/env python3
"""Showcase the use of date and time in Python."""
import datetime as dt

print("--- 1 - now ---")
now = dt.datetime.now()
utcnow = now.utcnow()

print(f"{now=}")
print(f"{utcnow=}")

print("--- 2 - timezones ---")
print(f"{now.utcoffset()=}")
print(f"{utcnow.utcoffset()=}")

print("--- 3 - datetime differences ---")
print(f"{now - utcnow=}")  # both now and utcnow have no timezone
