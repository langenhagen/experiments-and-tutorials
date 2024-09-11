#!/usr/bin/env python
"""
Showcase exception tracing.

No silver bullet.

with trace_exception, one can spot the immediate occurence of an exception,
  but it may be slow, i.e. rather suited for profiling
with sys.exc_info() one gets unhandled apparently only exceptions

See: https://pymotw.com/2/sys/tracing.html
"""
import sys


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
        '!!! Trace: {} "{}" on line {} of function {}() in file {}'.format(
            exc_type.__name__, exc_value, line_no, func_name, filename
        )
    )
    # print('  sys.exc_info={}'.format(sys.exc_info()))  # would be (None, None, None)
    return


def c():
    raise RuntimeError("generating exception in c()")


def b():
    c()


def a():
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
