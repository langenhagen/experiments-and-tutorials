#!/usr/bin/env python3
"""Showcase builtin ExceptionGroups that help handle multiple exceptions that
occur simultaneously, e.g. in separate threads or in async code.

Works starting with Python 3.11. Below that, there is a 3rd party backport
PyPi-package.

author: andreasl
"""
import asyncio


print("--- 1 multiple exceptions ---\n")


def raise_exceptions():
    """Raise and handle 2 exceptions and return/raise them late as an
    ExceptionGroup."""
    exceptions: list[Exception] = []
    try:
        1 / 0  # ZeroDivisionError
    except ZeroDivisionError as e:
        exceptions.append(e)

    try:
        [][2]  # IndexError
    except IndexError as e:
        exceptions.append(e)

    if exceptions:
        raise ExceptionGroup("Multiple exceptions occurred", exceptions)


try:
    raise_exceptions()
except ExceptionGroup as eg:
    print(f"Caught ExceptionGroup with {len(eg.exceptions)} exceptions:")
    for exc in eg.exceptions:
        print(f" - {exc.__class__.__name__}: {exc}")


print("\n--- 2 in asyncio code ---\n")


async def task_1():
    await asyncio.sleep(1)
    raise ValueError("Error in task 1")


async def task_2():
    await asyncio.sleep(2)
    raise KeyError("Error in task 2")


async def async_main():
    tasks = [asyncio.create_task(task_1()), asyncio.create_task(task_2())]

    exceptions = []
    for task in tasks:
        try:
            await task
        except Exception as e:
            exceptions.append(e)

    if exceptions:
        raise ExceptionGroup(
            "Multiple exceptions occurred in asyncio tasks", exceptions
        )


try:
    asyncio.run(async_main())
except ExceptionGroup as eg:
    print(f"Caught ExceptionGroup with {len(eg.exceptions)} exceptions:")
    for exc in eg.exceptions:
        print(f" - {exc.__class__.__name__}: {exc}")