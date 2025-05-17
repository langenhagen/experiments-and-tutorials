#!/usr/bin/env python3
"""Showcase anyio's blocking off-thread stuff."""

import time

import anyio


def blocking_fibonacci(n):
    """Simulate a blocking task."""
    print(f"Started calculating Fibonacci({n})")
    a, b = 0, 1
    for _ in range(n):
        a, b = b, a + b
    time.sleep(2)  # Simulate a long-running computation
    print(f"Finished calculating Fibonacci({n}): {a}")
    return a


async def async_task(task_id) -> None:
    """Asynchronous task simulating non-blocking work."""
    print(f"Async task {task_id} started")
    await anyio.sleep(1)  # Simulate non-blocking async operation
    print(f"Async task {task_id} completed")


async def main() -> None:
    async with anyio.create_task_group() as tg:
        # Offload blocking computations to separate threads
        tg.start_soon(anyio.to_thread.run_sync, blocking_fibonacci, 30)
        tg.start_soon(anyio.to_thread.run_sync, blocking_fibonacci, 20)

        # Start some non-blocking async tasks
        tg.start_soon(async_task, 1)
        tg.start_soon(async_task, 2)


anyio.run(main)
