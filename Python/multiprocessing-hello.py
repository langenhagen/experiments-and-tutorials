#!/usr/bin/env python3
"""Showcase usage of the module multiprocessing that can create processes for
multi-core programming.

See: https://www.blog.pythonlibrary.org/2020/07/15/python-101-creating-multiple-processes/
"""
import multiprocessing
import random
import time


def worker(name: str) -> None:
    print(f'Started worker {name}')
    worker_time = random.choice(range(1, 5))
    time.sleep(worker_time)
    print(f'{name} worker finished in {worker_time} seconds')


if __name__ == '__main__':
    processes = []
    for i in range(5):
        process = multiprocessing.Process(target=worker, args=(f'computer_{i}',))
        processes.append(process)
        process.start()

    for proc in processes:
        proc.join()
