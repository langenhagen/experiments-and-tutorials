#!/usr/bin/env python3
"""Showcase usage of the module multiprocessing that can create processes for
multi-core programming.

Comparison to the package `subprocess`:
`multiprocessing` is for parallelizing Python, `subprocess` is for controlling non-Python system
processes.  Use `multiprocessing` to run multiple Python processes in parallel and share Python
objects between them. Use `subprocess` to execute external programs or shell commands and
communicate via raw input/output streams.

See:
- https://www.blog.pythonlibrary.org/2020/07/15/python-101-creating-multiple-processes/
- https://docs.python.org/3/library/multiprocessing.html
"""

import multiprocessing
import random
import time


def worker(name: str) -> None:
    """Worker function."""
    print(f"Started worker {name}")
    worker_time = random.choice(range(1, 5))
    time.sleep(worker_time)
    print(f"{name} worker finished in {worker_time} seconds")


def hello_processes() -> None:
    """Showcase the usage of Processes.

    Processes create a separate Python process to run a function in parallel.
    """
    processes = []
    for i in range(5):
        process = multiprocessing.Process(target=worker, args=(f"job-{i}",))
        processes.append(process)
        process.start()

    for proc in processes:
        proc.join()


def worker2(d: dict, l: list) -> None:
    """Manipulate the given dict and list."""
    d[1] = "1"
    d["2"] = 2
    d[0.25] = None
    l.reverse()


def hello_managers() -> None:
    """Showcases the usage of Managers.

    Managers provide shared objects (like dicts, lists) that multiple processes
    can safely access and modify.
    """
    with multiprocessing.Manager() as manager:
        d = manager.dict()
        l = manager.list(range(10))

        p = multiprocessing.Process(target=worker2, args=(d, l))
        p.start()
        p.join()

        print(f"{d=}\n{d}")
        print(f"\n{l=}\n{l}")


def main() -> None:
    """Run the  program."""
    print("--- 1 Processes ---")
    hello_processes()

    print("\n--- 2 Managers ---")
    hello_managers()


if __name__ == "__main__":
    main()
