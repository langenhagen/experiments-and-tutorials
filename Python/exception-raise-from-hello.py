#!/usr/bin/env python
"""Showcase the usage of raise ... from to raise sequences of exceptions."""

try:
    raise ValueError("The first message")
except ValueError as err:
    # following raise logs both the first exception and the second exception
    raise KeyError("Yep, the second message") from err
