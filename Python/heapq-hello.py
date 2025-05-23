#!/usr/bin/env python3
"""Demonstrate Python's built-in `heapq` module for heap/priority queue operations.

A heap is a binary tree where every parent ≤ its children (min-heap) or ≥ (max-heap).
Thus, the smallest (or largest) element is always at the root.

`heapq` puts normal Python lists into a heap/binary tree format,
with children of node N at positions 2*N+1 and 2*N+2.

Also, `heapq` keeps the heap property: Means, parent nodes are smaller than
children. Esp. item at index 0 is the smallest.

Thus, guaranteed constant access to the smallest item at index 0.
Pushing a value adds it to the correct place in the heap's tree structure.

Comparing heaps with sorted lists...
- heaps are better at inserting items and popping the min item
- sorted lists are better at searching arbitrary values

Thus, if you only care for inserting items and popping the highest prio items
(as in priority queue situations), go with heaps.

For reference:
+----------------+-----------+-------------+-----------+-----------+
| Data structure |  Insert   | Delete min  |  Search   | Find min  |
+----------------+-----------+-------------+-----------+-----------+
| Sorted array   |   O(n)    |    O(n)     | O(log n)  |   O(1)    |
| Min heap       | O(log n)  |  O(log n)   |   O(n)    |   O(1)    |
+----------------+-----------+-------------+-----------+-----------+

See:
- https://docs.python.org/3/library/heapq.html
- https://realpython.com/python-heapq-module/
"""

import heapq

print("--- 1 heapify() ---\n")

data = [7, 5, 3, 1, 9, 8]
# the binary tree repr of `data` is:
#         7
#       /   \
#      5     3
#     / \   /
#    1   9 8

print(f"initial   {data=}")
heapq.heapify(data)  # now a heap; O(n)
print(f"heapified {data=}")
# now, `data` is: [1, 3, 5, 7, 9, 8]
# note: the root is now always smaller than its children:
#         1
#       /   \
#      5     3
#     / \   /
#    7   9 8

print("\n--- 2 heappush() & heappop() ---\n")

heapq.heappush(data, 2)  # O(log n)
print(f"after heappush(data, 2): {data=}")

smallest = heapq.heappop(data)  # O(log n)
print(f"heappop(data)={smallest}, {data=}")

print("\n--- 3 heappushpop() - push item, then pop smallest in one go ---\n")

smallest = heapq.heappushpop(data, 4)  # push 4, then pop & return smallest
print(f"heappushpop(data, 4)={smallest}, {data=}")

print("\n--- 4 heapreplace() - replace smallest item with given one ---\n")

replaced = heapq.heapreplace(data, 7)  # pop the smallest item, add the new one
print(f"heapreplace(data, 7)={replaced}, {data=}")

print("\n--- 5 nlargest() & nsmallest() ---\n")

largest = heapq.nlargest(3, data)
print(f"nlargest(3, data)={largest}")

smallest = heapq.nsmallest(2, data)
print(f"nsmallest(2, data)={smallest}")

print("\n--- 6 merge() - merge multiple sorted iterables ---\n")

sorted1 = [1, 4, 7]
sorted2 = (2, 5, 6)
sorted3 = [0, 3, 8, 99]

print(f"{list(heapq.merge(sorted1, sorted2, sorted3))=}")
