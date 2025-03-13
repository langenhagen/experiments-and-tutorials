"""Showcase that access via member vars is slower than acces by local references."""

from timeit import timeit

n_repetitions = 10000000


class O:
    """Container object to do call by reference"""

    def __init__(self, v):
        self.v = v


class AccessViaLocalVar:
    def __init__(self):
        for i in range(n_repetitions):
            o = self.o = O(i)
            o.v = o.v % 5


class AccessViaMemberVar:
    def __init__(self):
        for i in range(n_repetitions):
            o = self.o = O(i)
            self.o.v = self.o.v % 5


print("--- Access via local var ---")
duration = timeit(lambda: AccessViaLocalVar(), number=1)
print(f"Took {duration} seconds")

print("--- Access via member var ---")
duration = timeit(lambda: AccessViaMemberVar(), number=1)
print(f"Took {duration} seconds")
