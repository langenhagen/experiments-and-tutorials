#!/usr/bin/env python3
"""Showcase Python script execution from a string with parallel output.

author: andreasl
"""

import sys
from subprocess import PIPE, Popen
from threading import Thread

code = """
print(1 + 2)

from time import sleep
sleep(1)

print(3 + 4)

import asyncio
print(dir(asyncio))
"""


def stream(pipe, out):
    for line in iter(pipe.readline, ""):
        out.write(f"{out.name}: {line}")
    pipe.close()


proc = Popen(
    ["python3", "-u", "-c", code],
    stdout=PIPE,
    stderr=PIPE,
    text=True,
)

t_out = Thread(target=stream, args=(proc.stdout, sys.stdout))
t_err = Thread(target=stream, args=(proc.stderr, sys.stderr))
t_out.start()
t_err.start()

proc.wait()
t_out.join()
t_err.join()
