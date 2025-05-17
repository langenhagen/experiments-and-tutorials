#!/usr/bin/env python
"""
Showcase the lookup performance for lists, tuples, sets and arrays.

In particular, showcase how much the constant-time lookup of set-items O(1) may
be considerably faster than the linear lookup O(n) of lists.

Spoiler:
    - Sets and tuples are orders of magnitude faster
    - Lists and arrays are comparably slow

author: andreasl
"""

import array
from time import perf_counter


def long_operation(numbers) -> None:
    """Some busy function."""
    for i in range(50000):
        if i in numbers:
            print("found", i)
        else:
            print("missed", i)


def do_and_measure_op(numbers) -> float:
    """Call a slow operation and return its duration in seconds"""
    start = perf_counter()
    long_operation(numbers)
    return perf_counter() - start


def main() -> None:
    """Main program entry point."""
    n_items = 10000000

    # list slow
    l = list(range(n_items))
    list_duration_s = do_and_measure_op(l)

    # tuples
    t = (i for i in range(n_items))
    tuple_duration_s = do_and_measure_op(t)

    # orders of magnitude faster
    s = set(range(n_items))
    set_duration_s = do_and_measure_op(s)

    la = array.array("l", list(range(n_items)))
    long_array_duration_s = do_and_measure_op(la)

    sa = array.array("i", list(range(n_items)))
    short_array_duration_s = do_and_measure_op(sa)

    print(f"Stuff with list took {list_duration_s} seconds.")
    print(f"Same stuff with tuple took {tuple_duration_s} seconds.")
    print(f"Same Stuff with set took {set_duration_s} seconds.")
    print(f"Same Stuff with long-array took {long_array_duration_s} seconds.")
    print(f"Same Stuff with short-array took {short_array_duration_s} seconds.")


if __name__ == "__main__":
    main()
