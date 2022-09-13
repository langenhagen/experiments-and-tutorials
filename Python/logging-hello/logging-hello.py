"""
Showcase the builtin flexible logging module in Python.

Apparently, the kwargument stack_info does not work with Python 2.
"""
import logging
import sys

import otherfile

logging.basicConfig(
    # filename='myfile.log',  # reroutes the default logging stream to file instead of to stdout
    format="%(asctime)s.%(msecs)d [%(levelname)s]: %(filename)s:%(lineno)d: %(message)s",
    datefmt="%a, %Y-%m-%d %H:%M:%S",
    level=logging.DEBUG,
)

logger = logging.getLogger(__name__)


def foo():
    """Wraps calls to log in order to fill the stack info with stuff to show."""
    print("--- 1 debug ---")
    logger.debug("hello")

    print("\n--- 2 warning ---")
    logger.warning("world")

    print("\n--- 3 info ---")
    logger.info("This should also print a trace", stack_info=True)

    print("\n--- 4 info with exc_info ---")
    # exc_info yields here: NoneType None
    logger.info("This should also print an exc_info", exc_info=True)

    print("\n--- 5 exception ---")
    try:
        raise ValueError("hahahaha")
    except ValueError:
        # this is with stack_info=True; makes print the stack trace
        # to the logging function
        # exc_info=False is implicitly True here
        # stack_info=True not implicitly True here, but activating it could make sense
        logger.exception("This Exception should also print an exc_info")

    print("\n--- 6 exception with stack_info ---")
    try:
        raise ValueError("hohoho")
    except ValueError:
        # this is with stack_info=True; makes print the stack trace
        # to the logging function
        # exc_info=False is implicitly True here
        # stack_info=True not implicitly True here, but could make sense
        logger.exception(
            "This Exception should also print an exc_info", stack_info=True
        )

    print("\n--- 7 error ---")
    try:
        raise ValueError("hihihihi")
    except ValueError:
        logger.error("Using logger.error does not print the stack trace by default")

    print("\n--- 8 dynamic severity levels ---")
    logger.log(logging.DEBUG, "mickey")
    logger.log(logging.WARN, "mouse")

    print("\n--- 9 variable logging ---")

    class C:
        """A custom class with custom __str__ function."""

        def __init__(self, a, b):
            self.a = a
            self.b = b

        def __str__(self):
            return f"C: {self.a} - {self.b}"

    obj = C(1, "andi")

    logger.info("Log some variables efficiently: %s, %d, %s", "this", 42, obj)


foo()

print("\n--- 10 call logs from other files ---")
otherfile.foo(":)")

print("\n--- 11 log stream handlers ---")
console_handler = logging.StreamHandler(sys.stdout)
logger.addHandler(console_handler)

other_console_handler = logging.StreamHandler(sys.stdout)
other_console_handler.setLevel(logging.INFO)
logger.addHandler(other_console_handler)

logger.debug("print this 2 times")
logger.info("print this 3 times")

otherfile.foo("only 1 time")

logger.removeHandler(console_handler)
logger.removeHandler(other_console_handler)

logger.info("should only be printed once")
