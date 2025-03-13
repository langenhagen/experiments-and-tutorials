#!/usr/bin/env python3
"""Showcase np.asarray with numpy.typing.NDArray."""

import numpy as np
from numpy.typing import NDArray


def array_mean(arr: NDArray[np.float64]) -> float:
    """Calculate the mean of a NumPy array with float64 elements."""
    return arr.mean()


# Example usage:
my_array = np.array([1.0, 2.0, 3.0, 4.0], dtype=np.float64)
mean_value = array_mean(my_array)

print(mean_value)  # Output: 2.5
