#!/usr/bin/env python
"""Showcase pickling of exceptions and exceptions.

author: andreasl
"""
import pickle
import sys

try:
    raise ValueError("hahaw")
except Exception as ex:
    with open("exception.log", "wb") as file:
        pickle.dump(ex, file)
    with open("exc_info.log", "wb") as file:
        # pickle.dump(sys.exc_info(), file)  # throws: you cannot pickle tracebacks natively
