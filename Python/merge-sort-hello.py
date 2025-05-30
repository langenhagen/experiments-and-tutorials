#!/usr/bin/env python3
"""Implementation mergesort.

Merge sort is a divide-and-conquer recursive, stable sorting algorithm with
O(n log n) time complexity. It is not in-place, means it needs extra memory.

author: andreasl
"""

from collections.abc import Sequence


def merge(a: Sequence, b: Sequence) -> list:
    """Merge two ascending sorted lists and return the result."""
    result = []

    i = j = 0
    while i < len(a) and j < len(b):
        if a[i] < b[j]:
            result.append(a[i])
            i += 1
        else:
            result.append(b[j])
            j += 1

    result.extend(a[i:])
    result.extend(b[j:])

    return result


def merge_sort(arr: Sequence) -> list:
    """Sort given list with the Merge Sort algorithm and return the list."""

    if len(arr) <= 1:
        return list(arr)

    middle = len(arr) // 2
    left = arr[:middle]
    right = arr[middle:]
    return merge(merge_sort(left), merge_sort(right))


print(f"{merge_sort([1,3,2,6,3,1,4]) = }")
print(f"{merge_sort([1, 2, 3]) = }")
print(f"{merge_sort([4, 5]) = }")
print(f"{merge_sort(["c", "b", "a", "d"]) = }")
print(f"{merge_sort([]) = }")
print(f"{merge_sort([3,1,4,1,5,9,2,6,5,3,5,9]) = }")
print(f"{merge_sort([0, 0, 0]) = }")
