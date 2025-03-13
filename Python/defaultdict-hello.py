#!/usr/bin/env python3
"""Showcase the usage of Python's standard library object `defaultdict`."""

import datetime as dt
import time
from collections import defaultdict
from pprint import pp


def my_function():
    """Returns the current datetime."""
    return dt.datetime.now()


print("\n--- 1 defaultdict gives default value when accessing missing items ---\n")

d: defaultdict[int, int] = defaultdict(int)
print("fresh defaultdict:")
pp(d)
d[1] = 72
print(f"{d=}")
print(f"{d[1]=}")
print(f"{d[2]=}")
print("after assigning and reading defaultdict fields:")
pp(d)
print()

e: defaultdict = defaultdict(my_function)
print("fresh defaultdict:")
pp(e)
e[1] = "By The Power Of Grayskull!"
print(f"{e=}")
print(f"{e[1]=}")
print(f"{e[2]=}\n")
time.sleep(1)
print(f"{e[3]=}")
assert e[2] != e[3]
print("after assigning and reading defaultdict fields:")
pp(e)
print()

print("\n--- 2 defaultdict for easy assigning nested dicts ---\n")

f: defaultdict[int, dict] = defaultdict(dict)
f[0] = {"first": 1}
f[0]["second"] = 2
f[1]["other"] = 69  # wouldn't work as easily with normal dict.
pp(f)
