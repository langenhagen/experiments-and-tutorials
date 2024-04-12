"""Showcase context manager implementations in Python."""
import logging
from contextlib import contextmanager

logging.basicConfig(
    format="%(asctime)s.%(msecs)d [%(levelname)s]: %(filename)s:%(lineno)d: %(message)s",
    datefmt="%a, %F %T",  # %F: YYYY-MM-DD  %T: HH:MM:SS
)
logger = logging.getLogger(__name__)


print("--- 1 generator/function/yield-based context managers ---\n")


@contextmanager
def my_context_manager():
    print("Entering the context")
    yield
    print("Exiting the context")


with my_context_manager():
    print("Inside the context")


print("\n--- 2 class-based context managers ---\n")


class MyContextManager:
    def __enter__(self) -> "MyContextManager":
        print("Entering the context")
        # depending on your goal, you may return nothing,
        # the same object or another object
        return self

    def __exit__(self, exc_type, exc_value, traceback):
        # You can handle exceptions here if necessary
        print("Exiting the context")


with MyContextManager() as context:
    print("Inside the context")


print("\n--- 3 Exceptions under the context get handled by `__exit__()` ---\n")


class MyBorkedContextManager:
    def __enter__(self) -> "MyBorkedContextManager":
        print("Entering the context")

    def __exit__(self, exc_type, exc_value, traceback):
        # You can handle exceptions here if necessary
        print("Exiting the context")
        print(f"{exc_type=}\n{exc_value=}\n{traceback=}")


try:
    with MyBorkedContextManager() as context:
        print("Inside the context")
        raise RuntimeError("i am borked")
except RuntimeError as err:
    # won't be called
    logger.exception("Oh no!")
