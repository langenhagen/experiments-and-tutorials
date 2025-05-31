"""Showcase usage with exceptions."""

import re
import sys
import traceback

print("--- 1 print an exception and a stack trace ---\n")


def foo() -> None:
    try:
        raise ValueError("hahaha")
    except ValueError:
        exception_with_traceback = traceback.format_exc()
        print(f">>>>\n{exception_with_traceback}\n<<<<")
        # traceback.print_exc()  # prints the same info directly


def bar() -> None:
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

print('\n--- 2 get the exception without specifying "except as ...:" ---\n')

try:
    raise OSError("miaaaauuu")
except:
    print(sys.exc_info())
    exc_class, exc_obj, traceback = sys.exc_info()
    print(f"exception_class: {exc_class}")  # == type(exc_obj)
    print(f"exception_obj: {exc_obj}")
    print(f"traceback: {traceback}")

print("\n--- 3 Excepthook ---\n")


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
    print(f"Handled exception\n sys.ext_info()={sys.exc_info()}")

# raise IOError('This one should be hooked')  # comment this out in order to step further

sys.excepthook = sys.__excepthook__  # reset excepthook again to the original


print("\n--- 4 - Catching a set of exceptions ---\n")

try:
    raise ValueError("hahaha")
except (KeyError, ValueError) as e:
    print(f"Caught {type(e).__name__}: {e}")


print("\n<< UND ENDE >>\n")
