#!/usr/bin/env python3
"""
Showcase the use of the Python standard library tool cProfile

E.g., run a program under cProfile via:

  python -m cProfile myprogram.py

Or, alternatively, add cProfile via context manager.

See:
- https://docs.python.org/3/library/profile.html
- https://www.youtube.com/watch?v=BZzb_Wpag_M

See also `tuna`, an interactive visualizer for profile files: https://github.com/nschloe/tuna

    pipx install tuna

    tuna profile-results.prof

"""

import cProfile
import pstats
from time import sleep


def slow_add(x, y):
    """Not the fastest implementation of addition."""
    result = 0
    result += x
    result += y
    return result


def fast_add(x, y):
    """A comparably fast implementation of addition."""
    return x + y


def waste_time(n_seconds: float = 5):
    """Sleep a bit."""
    print("Going to sleep...")
    sleep(n_seconds)
    print("I woke up")


def main():
    """Program main entry point."""
    print(slow_add(1, 2))
    print(fast_add(30, 40))
    waste_time()


if __name__ == "__main__":
    # profile = cProfile.Profile()  # works, too
    # profile.enable()
    with cProfile.Profile() as profile:
        main()

    # profile.disable()

    profiling_results = pstats.Stats(profile)
    profiling_results.sort_stats(pstats.SortKey.TIME)
    profiling_results.print_stats()
    # dump to file to e.g. visualize it, e.g. via `tuna`
    profiling_results.dump_stats("profile-results.prof")
