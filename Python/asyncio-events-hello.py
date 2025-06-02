#!/usr/bin/env python3
"""Showcase the usage of Events in the standard library `asyncio`."""

import asyncio


async def worker(event: asyncio.Event):
    while not event.is_set():
        print(f"Hello from worker loop  {event=}")
        await asyncio.sleep(1)

    print(f"Event set, exited loop  {event=}")


async def main():
    my_event = asyncio.Event()
    my_task = asyncio.create_task(worker(my_event))  # task starts right here

    await asyncio.sleep(3)
    my_event.set()  # signal to exit loop
    await my_task

    if my_task:
        print(f"my_task is truthy: {my_task=}")  # this holds
    else:
        print(f"my_task is falsy: {my_task=}")  # this does not hold


asyncio.run(main())
