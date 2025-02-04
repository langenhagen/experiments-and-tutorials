#!/usr/bin/env python3
"""Compare two guppy3/heapy heap dumps from command-line arguments.

author: andreasl
"""

import sys
from argparse import ArgumentParser
from pathlib import Path

from guppy import hpy


def main() -> int:
    """Program main entry point."""
    parser = ArgumentParser(description="Compare two guppy3/heapy heap dumps.")
    parser.add_argument("dump1", type=Path, help="First heap dump file")
    parser.add_argument("dump2", type=Path, help="Second heap dump file")
    args = parser.parse_args()

    if not args.dump1.exists() or not args.dump2.exists():
        print("Error: One or both heap dump files do not exist.")
        return 1

    h = hpy()
    h1 = h.load(str(args.dump1))
    h2 = h.load(str(args.dump2))

    diff = h2 - h1
    print(diff)

    return 0


if __name__ == "__main__":
    sys.exit(main())