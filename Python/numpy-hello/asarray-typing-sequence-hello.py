#!/usr/bin/env python3
"""Showcase np.asarray with typing.Sequence.

Fyi, ndarray is not a Sequence: https://stackoverflow.com/questions/76082807/why-numpy-array-is-not-of-sequence-type.
"""

from typing import TYPE_CHECKING
from collections.abc import Sequence

import numpy as np

if TYPE_CHECKING:
    from numpy.typing import NDArray


def to_numpy_array(seq: Sequence[float]) -> NDArray:
    return np.asarray(seq)


# Example usage:
my_list = [1.0, 2.0, 3.0, 4.0]  # This is a Sequence[float]
array = to_numpy_array(my_list)

print(array)  # Output: [1. 2. 3. 4.]
print(type(array))  # Output: <class 'numpy.ndarray'>
