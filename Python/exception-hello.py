"""
Showcase usage with exceptions.
"""
import pathlib
import re
import sys
import traceback

print("---")
print("Print an exception and a stack trace")


def foo():
    try:
        raise ValueError("hahaha")
    except ValueError:
        # myvar = traceback.print_exc()
        exception_with_traceback = traceback.format_exc()
        print(">>>>{}".format(exception_with_traceback))
        print("<<<<")


def bar():
    foo()


bar()

print("---")
try:
    re.compile("abc(")
except re.error as err:
    print(err.pattern)
    print(err.msg)
    print(err.pos)
    print(err.colno)  # == pos + !

print("---")
print('Get the exception without specifying "except as ...:"')
try:
    raise IOError("miaaaauuu")
except:
    print(sys.exc_info())
    exc_class, exc_obj, traceback = sys.exc_info()
    print("exception_class: {}".format(exc_class))  # == type(exc_obj)
    print("exception_obj: {}".format(exc_obj))
    print("traceback: {}".format(traceback))

print("---")
print("Excepthook")


def patched_excepthook(type, value, traceback):
    print(
        "Gotcha, unhandled exception:\n {}\n {!r}\n {}\n sys.exc_info()={}\n".format(
            type, value, traceback, sys.exc_info()
        )
    )


sys.__excepthook__ = (
    sys.excepthook
)  # weird, but apparently,__excepthook__ wasn't stored automatically with my python
sys.excepthook = patched_excepthook

try:
    raise ValueError("this one will be try/except-catched and not hooked")
except:
    print("Handled exception\n sys.ext_info()={}".format(sys.exc_info()))

# raise IOError('This one should be hooked')  # comment this out in order to step further

sys.excepthook = sys.__excepthook__  # reset excepthook again to the original


print("<< UND ENDE >>")
