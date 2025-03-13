#!/usr/bin/env python3
"""Showcase usage of the module multiprocessing that can create processes for
multi-core programming.

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


def hello_processes():
    """Showcase the usage of Processes."""
    processes = []
    for i in range(5):
        process = multiprocessing.Process(target=worker, args=(f"job-{i}",))
        processes.append(process)
        process.start()

    for proc in processes:
        proc.join()


def worker2(d: dict, l: list):
    """Manipulate the given dict and list."""
    d[1] = "1"
    d["2"] = 2
    d[0.25] = None
    l.reverse()


def hello_managers():
    """Showcases the usage of Managers."""
    with multiprocessing.Manager() as manager:
        d = manager.dict()
        l = manager.list(range(10))

        p = multiprocessing.Process(target=worker2, args=(d, l))
        p.start()
        p.join()

        print(f"{d=}\n{d}")
        print(f"\n{l=}\n{l}")


def main():
    """Main program."""
    print("--- 1 Processes ---")
    hello_processes()

    print("\n--- 2 Managers ---")
    hello_managers()


if __name__ == "__main__":
    main()
