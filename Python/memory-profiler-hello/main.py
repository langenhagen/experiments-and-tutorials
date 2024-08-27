#!/usr/bin/env python3
"""Showcase the usage of the 3rd party library `memory-profiler` which allows
for inline monitoring memory usage of Python code.

Adding the `@profile` decorator to a function makes `memory_profiler` log a
table with line-by-line info about memory allocations inside the function
function.

You can also run the tool `mprof` as an executable and look into the overall
memory usage of any given executable, not just Python, over time:

    mprof                       # get help
    mprof run <EXECUTABLE>
    mprof plot                  # show the memory graph, needs matplotlib
    mprof clean                 # remove files mprof put in place

Note that, as of 2024-08-28, `memory-profiler` is no longer actively maintained.
Works, though.

See: https://pypi.org/project/memory-profiler/
"""
import time

from memory_profiler import profile

print("\n--- 1 profiling functions ---\n")


@profile
def foo():
    """A simple function to demonstrate memory profiling."""
    print("Hello from foo!")
    time.sleep(1)
    data = [i for i in range(10000)]
    print("Goodbye from foo!")
    return data


@profile
def bar():
    """Another function with different memory usage."""
    print("Hello from bar!")
    time.sleep(1)
    data = {i: i * 2 for i in range(10000)}
    print("Goodbye from bar!")
    return data


@profile
def baz():
    """Function calling other functions."""
    print("Hello from baz!")
    data1 = foo()
    data2 = bar()
    print("Goodbyefrom baz!")
    return data1, data2


baz()

print("\n--- 2 profiling class methods ---\n")


class MyClass:
    def __init__(self):
        self.data = []

    @profile
    def fill_data(self):
        """Fills the data with large amounts of integers."""
        self.data = [i * i for i in range(100000)]
        print("Data filled in MyClass")

    @profile
    def clear_data(self):
        """Clears the data."""
        self.data = []
        print("Data cleared in MyClass")


obj = MyClass()
obj.fill_data()
obj.clear_data()

print("\n--- 3 exceptions stop profiling ---\n")


@profile
def problematic_function():
    """Function that allocates a lot of memory and raises an exception."""
    print("Entering problematic_function")
    data = [i for i in range(100000)]
    if len(data) > 50000:
        raise MemoryError("Too much memory allocated!")
    print("Exiting problematic_function")


try:
    problematic_function()
except MemoryError as e:
    print(f"Caught an exception: {e}")


print("\n--- 4 logging profiles to dedicated files ---\n")

fp = open("my-memory-profiler.log", "w+")


@profile(stream=fp)
def qux():
    """A simple function to demonstrate memory profiling."""
    print("Hello from qux!")
    time.sleep(1)
    data = [i for i in range(10000)]
    print("Goodbye from qux!")
    return data


qux()

fp.close()
