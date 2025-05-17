#!/usr/bin/env python3
"""
Showcase exception tracing.

No silver bullet.

with trace_exception, one can spot the immediate occurence of an exception,
  but it may be slow, i.e. rather suited for profiling
with sys.exc_info() one gets unhandled apparently only exceptions

See: https://pymotw.com/2/sys/tracing.html
"""

import sys
from typing import NoReturn


def trace_exceptions(frame, event, arg):
    if not "exception-hello2.py" in frame.f_code.co_filename:
        return trace_exceptions  # return itself to keep tracing
    if event != "exception":
        return trace_exceptions

    co = frame.f_code
    func_name = co.co_name
    line_no = frame.f_lineno
    filename = co.co_filename
    exc_type, exc_value, exc_traceback = arg
    print(
        f'!!! Trace: {exc_type.__name__} "{exc_value}" on line {line_no} of function {func_name}() in file {filename}'
    )
    # print('  sys.exc_info={}'.format(sys.exc_info()))  # would be (None, None, None)
    return None


def c() -> NoReturn:
    raise RuntimeError("generating exception in c()")


def b() -> None:
    c()


def a() -> None:
    b()


sys.settrace(trace_exceptions)

print("---")
try:
    a()
except Exception as e:
    print(":: Handler:", e)
    print("   Fix w/ except")
    print(sys.exc_info())

print("---")
try:
    a()
except Exception as e:
    print(":: Handler:", e)
    print("   Fix w/ except")
    print("  ", sys.exc_info())

print("---")
a()
