#!/usr/bin/env python3
"""Fun and games with random uninitialized values.

Initially done with numpy==1.26.4 - there the values seemed random, still seems
the case with numpy>2, but is ...flaky? and sometimes just black, apparently
esp. for higher matrix resolutions.
"""

import sys
from itertools import count

import cv2
import numpy as np


def main() -> int:
    """Program main entry point."""
    for _ in count(0):
        key = cv2.waitKey(1)
        if key == ord("q"):
            break
        if key == 81 or key == 83 or key == 82 or key == 84:  # left key
            pass

        data = np.empty(shape=(30, 30, 3), dtype=float)
        cv2.imshow("image", data)

    return 0


if __name__ == "__main__":
    sys.exit(main())
