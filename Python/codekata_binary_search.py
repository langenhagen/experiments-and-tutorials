#!/usr/bin/env python3
"""Implement a binary search code kata."""

import random


def binary_search(list, wantedNumber):
    """This is the function's docstring."""

    idx = len(list) // 2
    stepsize = idx

    # handle one-element containers and straightaway hits
    if list[idx] == wantedNumber:
        return idx

    while stepsize > 1:
        stepsize = stepsize // 2

        if list[idx] > wantedNumber:
            idx -= stepsize
        else:
            idx += stepsize

        if list[idx] == wantedNumber:
            return idx

    if list[idx] > wantedNumber:
        idx -= 1
    else:
        idx += 1

    if idx >= 0 and idx < len(list) and list[idx] == wantedNumber:
        return idx

    return -1


random.seed()

numbers = []

for i in range(10):
    numbers.append(random.randint(0, 9))
numbers.sort()
print(numbers)

for i in range(10):
    print(i, binary_search(numbers, i))
