#!/usr/bin/env python
"""Showcase the usage of the python standard library `asyncio`.

Allows for single-threaded quasi-parallelism.

See docs: https://docs.python.org/3/library/asyncio-task.html

Also partially inspired by: https://guicommits.com/effective-python-async-like-a-pro/
"""
import asyncio
import random
import time

print("--- 1 simple call to asyncio.run() ---")


async def foo():
    """A coroutine."""
    print("Hello from foo!")
    await asyncio.sleep(1)
    print("Goodbye from foo!")


print("Go program")
asyncio.run(foo())
print("Goodbye program")


print("--- 2 nested await ---")


async def say_after(delay: float, what: str):
    """Another coroutine."""
    await asyncio.sleep(delay)
    print(what)
    return delay


async def foo():
    print(f"started at {time.strftime('%X')}")

    # Comes in as first "Hello" ... then "world"
    await say_after(2, "hello")
    await say_after(1, "world")

    print(f"finished at {time.strftime('%X')}")


asyncio.run(foo())

print("--- 3 async context managers ---")


class AsyncContextManager:
    async def __aenter__(self):
        print("entering context")

    async def __aexit__(self, exc_type, exc, tb):
        print("exiting context")


async def foo():
    async with AsyncContextManager():
        print("in the with block")


asyncio.run(foo())


print("--- 4 create_task() and gather() ---")


async def foo():
    tasks = set()
    for i in range(5):
        task = asyncio.create_task(say_after(delay=random.random(), what=f"Hello {i}"))
        tasks.add(task)

        # To prevent keeping references to finished tasks forever,
        # make each task remove its own reference from the set after
        # completion:
        task.add_done_callback(tasks.discard)

    results = await asyncio.gather(*tasks)

    print(f"Results:\n{results}")


asyncio.run(foo())

print("--- 5 as_completed() - act on tasks like in first-done-first-serve fashion ---")


async def foo():
    tasks = set()
    for i in range(5):
        task = asyncio.create_task(say_after(delay=random.random(), what=f"Hello {i}"))
        tasks.add(task)

        # To prevent keeping references to finished tasks forever,
        # make each task remove its own reference from the set after
        # completion:
        task.add_done_callback(tasks.discard)

    for i, coro in enumerate(asyncio.as_completed(tasks)):
        result = await coro
        print(f"Result {i}: {result}")


asyncio.run(foo())
