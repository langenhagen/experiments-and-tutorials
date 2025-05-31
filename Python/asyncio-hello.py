#!/usr/bin/env python
"""Showcase the usage of the python standard library `asyncio`.

Allows for single-threaded quasi-parallelism.

See docs: https://docs.python.org/3/library/asyncio-task.html

Also partially inspired by: https://guicommits.com/effective-python-async-like-a-pro/
"""

import asyncio
import random
import time
from typing import NoReturn

print("--- 1 simple call to asyncio.run() ---\n")


async def foo() -> int:
    """A coroutine."""
    print("Hello from foo!")
    await asyncio.sleep(0.5)
    print("Goodbye from foo!")
    return 42


print("Go program")
result = asyncio.run(foo())
print(f"Goodbye program {result=}")


print("\n--- 2 nested await ---\n")


async def say_after(delay: float, what: str):
    """Another coroutine."""
    await asyncio.sleep(delay)
    print(what)
    return delay


async def foo() -> None:
    print(f"started at {time.strftime('%X')}")

    # Comes in as first "Hello" ... then "world"
    await say_after(2, "hello")
    await say_after(1, "world")

    print(f"finished at {time.strftime('%X')}")


asyncio.run(foo())

print("\n--- 3 async context managers ---\n")


class AsyncContextManager:
    async def __aenter__(self):
        print("entering context")

    async def __aexit__(self, exc_type, exc, tb):
        print("exiting context")


async def foo() -> None:
    async with AsyncContextManager():
        print("in the with block")


asyncio.run(foo())


print("\n--- 4 create_task() and gather() ---\n")


async def foo() -> None:
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

print("\n--- 5 as_completed() - act on tasks in first-done-first-serve fashion ---\n")


async def foo() -> None:
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

print("\n--- 6 not handling exceptions: Task exception was never retrieved  ---\n")


async def failing_task() -> NoReturn:
    """A task that raises an exception."""
    print("Hello from failing_task(), before failure!")
    await asyncio.sleep(1)
    raise ValueError("Something went wrong!")
    print("Hello from failing_task(), after failure!")  # never gets here


async def bar() -> None:
    """Create the task but do not await or handle it.

    We don't await or gather the task, causing the "Task exception was never retrieved"
    error.

    Unhandled exceptions like this are also commonly called "unhandled rejection".
    """
    asyncio.create_task(failing_task())
    # Add a delay to give the unhandled exception time to be raised.
    # This ensures that the task has time to run before the program exits
    await asyncio.sleep(1)


asyncio.run(bar())


print("\n--- 7 handling exceptions ---\n")


async def foo() -> None:
    """Don't handle the exception of `failing_task` inside the coroutine."""
    try:
        task = asyncio.create_task(failing_task())
        await task  # Exception will propagate
        await asyncio.sleep(2)
    except Exception as e:
        print(f"Caught exception: {e}")


asyncio.run(foo())

print("\n--- 8 handling exceptions with gather() ---\n")


async def foo() -> None:
    """Use gather(return_exceptions=True) to avoid raising exceptions from failed tasks."""

    task1 = asyncio.create_task(failing_task())
    task2 = asyncio.create_task(say_after(0.5, "This task works fine"))

    await asyncio.sleep(1)
    results = await asyncio.gather(task1, task2, return_exceptions=True)

    for i, result in enumerate(results):
        if isinstance(result, Exception):
            print(f"Task {i} raised an exception: {result}")
        else:
            print(f"Task {i} completed with result: {result}")


asyncio.run(foo())

print("\n--- 9 exception handler functions ---\n")


def handle_exception(loop, context) -> None:
    """Custom exception handler for the asyncio event loop."""

    print("Hello from handle_exception()!")
    print(f"  param `loop` of type {type(loop)}\n{loop}\n")
    print(f"  param `context` of type {type(context)}\n{context}\n")

    msg = context.get("exception", context["message"])
    print(f"\n  Caught exception in custom handler: {msg}")
    print("---")


loop = asyncio.new_event_loop()
loop.set_exception_handler(handle_exception)
asyncio.set_event_loop(loop)
loop.run_until_complete(bar())
