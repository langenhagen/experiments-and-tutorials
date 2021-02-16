#!/usr/bin/env python
"""Showcase the usage of the python standard library `asyncio`.

Allows for single-threaded quasi-parallelism.

See docs: https://docs.python.org/3/library/asyncio-task.html
"""
import asyncio
import time

print("--- 1 simple call to asyncio.run() ---")


async def foo():
    print("Hello from foo!")
    await asyncio.sleep(1)
    print("Goodbye from foo!")


print("Go program")
asyncio.run(foo())
print("Goodbye program")


print("--- 2 nested await ---")


async def say_after(delay, what):
    await asyncio.sleep(delay)
    print(what)


async def foo():
    print(f"started at {time.strftime('%X')}")

    # Comes in as first "Hello" ... then "world"
    await say_after(2, "hello")
    await say_after(1, "world")

    print(f"finished at {time.strftime('%X')}")


asyncio.run(foo())
