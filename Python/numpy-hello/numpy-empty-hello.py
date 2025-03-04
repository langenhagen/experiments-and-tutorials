#!/usr/bin/env python3
"""Fun and games with random uninitialized values."""
import sys
from itertools import count

import cv2
import numpy as np


def main() -> int:
    """Program main entry point."""
    for i in count(0):
        key = cv2.waitKey(1)
        if key == ord("q"):
            break
        elif key == 81:  # left key
            pass
        elif key == 83:  # right key
            pass
        elif key == 82:  # up key
            pass
        elif key == 84:  # down key
            pass

        data = np.empty(shape=(30,30,3), dtype=float)
        cv2.imshow("image", data)

    return 0


if __name__ == "__main__":
    sys.exit(main())

