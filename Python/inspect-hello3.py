#!/usr/bin/env python3
"""Check if the functions that exceed a given number of lines in the given file
have a docstring.

Example usage:
  python inspect-hello3.py 10 myfile.py

author: andreasl
"""

import importlib
import inspect
import logging
import pathlib
import sys
import types

log = logging.getLogger(name=__name__)


def foo(s: str, i=0) -> None:
    print("Something")

    print(f"{s} : {i}")

    def inner() -> None:
        pass

    inner()


class SomeClass:
    """Show that the mechanism can also find methods."""

    def bar(self) -> float:
        return 3.14

    def zaz() -> str:
        return "static"

    def foo(self, s: str, i=0) -> None:
        print(f"Method: {s} : {i}")


def load_module(path: pathlib.Path) -> types.ModuleType:
    """Load a Python module from the given path."""
    try:
        spec = importlib.util.spec_from_file_location("check.dynamic", path)
        module = importlib.util.module_from_spec(spec)
        spec.loader.exec_module(module)
    except (AttributeError, SyntaxError):
        log.exception(f'Failed to load the given module from "{path}"')
    except BaseException:
        log.exception(f'Unexpected error while loading the given module from "{path}"')
    return module


def check_for_docstrings(n_function_lines_threshold, symbol) -> None:
    """Check the given file for functions with more than the given lines of code
    that lack docstrings.
    """
    members = inspect.getmembers(symbol)
    for member in members:
        name = member[0]
        obj = member[1]
        # print(f"---{name}  {inspect.isbuiltin(obj)}---")
        try:
            if inspect.isclass(obj) and name != "__class__":
                check_for_docstrings(n_function_lines_threshold, obj)
            elif inspect.isfunction(obj):
                src = inspect.getsource(obj)
                sig = inspect.signature(obj)
                lineno = inspect.getsourcelines(obj)[-1]
                loc = len([s for s in src.splitlines() if s])
                doc = inspect.getdoc(obj)
                print(f"> {name}{sig}@{lineno}: loc: {loc}\n{doc}\n")

                check_for_docstrings(n_function_lines_threshold, obj)
        except OSError:
            print(f"ERROR {name}")


if __name__ == "__main__":
    n_lines = sys.argv[1]
    file = sys.argv[2]
    symbol = load_module(pathlib.Path(file))
    check_for_docstrings(n_lines, symbol)
