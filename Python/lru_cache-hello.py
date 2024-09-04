#!/usr/bin/env python3
"""Showcase that Python's `lru_cache` works on methods."""
from functools import lru_cache

print("--- 1 LRU cache size considers `self` parameter in methods ---")


class C:
    @lru_cache(maxsize=2)
    def my_method(self, x):
        """Prints a message when the cache is missed, otherwise only returns the result."""
        print(f"Calculating for {self} {x}")
        return x * 2


obj1 = C()
obj2 = C()
obj3 = C()

print(obj1.my_method(10))  # Cache miss, will calculate
print(obj1.my_method(10))  # Cache hit, will use cached result
print(obj2.my_method(10))  # Cache miss
print(obj2.my_method(10))  # Cache hit
print(obj3.my_method(10))  # Cache miss
print(obj3.my_method(10))  # Cache hit

print(obj1.my_method(10))  # Cache miss, cache limit reached

print(obj3.my_method(10))  # Cache hit
print(obj1.my_method(10))  # Cache hit
print(obj2.my_method(10))  # Cache miss
