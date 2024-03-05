#!/usr/bin/env python3
"""Showcase the usage of Python's locale module."""
import datetime
import locale

print("--- 1 get current locales ---")

now = weekday = datetime.datetime.now()

time_locale = locale.getlocale(category=locale.LC_TIME)
print(f"{time_locale=}")  # e.g.: ('de_AT', 'UTF-8') or (None, None)

print(f"{now.strftime('%A')=}")  # Tuesday

print("\n--- 2 set locales ---\n")

locale.setlocale(locale.LC_TIME, "de_AT.utf8")
time_locale = locale.getlocale(category=locale.LC_TIME)
print(f"{time_locale=}")  # e.g.: ('de_AT', 'UTF-8') or (None, None)

print(f"{now.strftime('%A')=}")  # Dienstag
