#!/usr/bin/env python3
"""Homegrown implementation of heapify, that transforms a list into a heap.

A heap is a binary tree where every parent ≤ its children (min-heap) or ≥
(max-heap). Thus, the smallest (or largest) element is always at the root.

Thus, guaranteed constant access to the smallest (largest) item at index 0.

If you work with Python, rather use the standard library's package `heapq`
to do thing.
"""

import heapq

print("--- 1 homegrown heapify() ---\n")


def min_heapify(arr: list, index: int) -> None:
    """Make sure the subtree at index i is a max-heap by checking if left or
    right child are smaller than parent. If so, swap parent with the smaller
    child. Recursively do the same for for the child's new position, to keep
    fixing downwards.
    """
    smallest = index
    left_child = 2 * index + 1
    right_child = 2 * index + 2

    # find smallest item out of given item and its children
    if left_child < len(arr) and arr[left_child] < arr[smallest]:
        smallest = left_child
    if right_child < len(arr) and arr[right_child] < arr[smallest]:
        smallest = right_child
    if smallest != index:
        # Swap index with the smallest item smallest, then recurse heapify.
        # Note that we only recurse into the swapped and not item and not into
        # both children. When you look into `build_min_heap()`, realize that we
        # build bottom up, means the lower subtrees are already heaps; we only
        # have to fix the heap property for the item we swapped.
        arr[index], arr[smallest] = arr[smallest], arr[index]
        min_heapify(arr, smallest)


def build_min_heap(arr: list) -> None:
    """Walk all non-leaf nodes from the bottom up and call `min_heapify()` on
    each. This fixes the heap property for all subtrees, resulting in a valid
    heap. Only non-leaf nodes can violate the heap property - leaves are always
    heaps by definition.
    """
    for i in range(len(arr) // 2 - 1, -1, -1):
        min_heapify(arr, i)


data = [7, 5, 3, 1, 9, 8]
# the binary tree repr of `data` is:
#         7
#       /   \
#      5     3
#     / \   /
#    1   9 8

print(f"initial:                 {data=}")
build_min_heap(data)
# now, `data` is: [1, 3, 5, 7, 9, 8]
# note: the root is now always smaller than its children:
#         1
#       /   \
#      5     3
#     / \   /
#    7   9 8
print(f"after build_min_heap():  {data=}")
heapq.heapify(data)
print(f"after heapq.heapify():   {data=}")
