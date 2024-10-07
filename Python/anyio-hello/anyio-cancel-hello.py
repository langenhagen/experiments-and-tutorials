#!/usr/bin/env python3
"""Showcase the usage of anyio's `cancel_scope.cancel()` function to cancel task
groups.

See:
- https://anyio.readthedocs.io/en/stable/cancellation.html

author: andreasl
"""
import asyncio

import anyio

print("--- 1 simple cancel ---\n")

async def task1():
    print("Starting task...")

    try:
        await anyio.sleep_forever()
        print("After sleep_forever")  # doesn't get called
    except anyio.get_cancelled_exc_class() as e:
        print(f"Task was cancelled: {type(e).__name__}: {e}")

        # According to anyio docs:
        # Always reraise the cancellation exception if you catch it. Failing to
        # do so may cause undefined behavior in your application.
        raise

    print("Finished task")  # doesn't get called

async def foo():
    async with anyio.create_task_group() as tg:

        tg.start_soon(task1)

        await anyio.sleep(1)
        tg.cancel_scope.cancel()

anyio.run(foo)

print("\n--- 2 CancelScope with Shielding from Cancellation and Exception Eating ---\n")


async def task2():
    try:
        with anyio.CancelScope(shield=True):  # Protected from cancellation
            print("Hello from task's CancelScope!")
            await anyio.sleep(2)
            raise ValueError("Dios mio!")
    except anyio.get_cancelled_exc_class():
        print("Task was cancelled after shielded section")
        raise
    except ValueError as e:
        print(f"Caught ValueError: {e}")

async def bar():
    async with anyio.create_task_group() as tg:
        tg.start_soon(task2)
        await anyio.sleep(1)
        tg.cancel_scope.cancel()
    await asyncio.sleep(1)
    print("Bye from bar!")


anyio.run(bar)
