#!/usr/bin/env python3
"""Check the RAM requirements for subprocess.Popen.

The experiment can show that a process spawned by `subprocess.Popen()` does not
require initially as much RAM as the mother process. I suppose this is due to
the copy-on-write behavior on Linux.
"""

import math
import os
import subprocess

import psutil


def _pretty_memory_size(n_bytes: int) -> str:
    """Return the given number of bytes in a human-readable format,
    e.g. as MBs or GBs."""
    if n_bytes == 0:
        return "0 B"

    units = ("B", "kB", "MB", "GB", "TB")
    unit_id = int(math.floor(math.log(n_bytes, 1024)))
    size = round(n_bytes / (math.pow(1024, unit_id)), 2)
    return f"{format(size, '.2f')} {units[unit_id]}"


def _print_pretty_memory_size(n_bytes: int, prefix: str = "mem_usage = "):
    """Print the given number of bytes in a human-readable format."""
    mem_usage = _pretty_memory_size(n_bytes)
    print(f"{prefix}{mem_usage}")


def _open_subprocess():
    """Open and execute a simple subprocess."""
    command = 'echo "Hello, World! My PID is $$. echo My RAM usage is:"; ps -o rss= $$;'
    process = subprocess.Popen(
        command,
        shell=True,
        stdout=subprocess.PIPE,
        stderr=subprocess.PIPE,
    )
    output, error = process.communicate()  # returns a Tuple[byes, bytes]

    print(output.decode("utf-8"))
    if error:
        print("Error:", error.decode("utf-8"))


def main():
    """Run the program"""

    # first, let's get some basics laid out
    pid = os.getpid()
    print(f"pid={pid}")

    arr = []  # a memory-waster

    proc = psutil.Process(pid)
    _print_pretty_memory_size(proc.memory_info().rss)

    while True:
        _print_pretty_memory_size(proc.memory_info().rss)

        kb_input = (
            input("Want to eat more RAM [Enter] or call subproces.Popen() [Pp]? ")
            .strip()
            .lower()
        )

        if kb_input == "p":
            # go to next stage and call subprocess.Popen()
            break

        arr.extend(range(10**8))

    # make and run a subprocess
    _print_pretty_memory_size(proc.memory_info().rss)

    _open_subprocess()


if __name__ == "__main__":
    main()
