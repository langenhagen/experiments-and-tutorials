#!/usr/bin/env python
"""Showcase the usage of the python standard library `asyncio` in connection
with HTTP requests through the 3rd party library`aiohttp` as opposed to
Sequential requests via the 3rd party library `requests`.

`aiohttp` is almost a drop-in replacement for `requests`.

Allows for single-threaded parallelism.

Install the 3rd party librariies `requests` and `aiohttp` via:

  pip install aiohttp requests

See:
https://towardsdatascience.com/fast-and-async-in-python-accelerate-your-requests-using-asyncio-62dafca83c33
"""
import asyncio
from timeit import default_timer as timer

import requests
from aiohttp import ClientSession

n_requests = 10

print("--- 1 calling requests synchronously ---\n")


def make_request(i: int):
    """Make 1 POST request to httpbin.org and post the given number."""
    response = requests.post("https://httpbin.org/post", json={"number": i})
    print(f"{i}: {response.status_code}")


def run_sequentially(n: int):
    """Request n websites sequentially and print their response status."""
    for i in range(n):
        make_request(i)


start_time = timer()
run_sequentially(n_requests)
end_time = timer()

print(f"Sequential experiment took {round(end_time - start_time, 3)} seconds")

print("\n--- 2 calling requests asynchronously ---\n")


async def make_async_request(i: int, session: ClientSession):
    """Make async 1 POST request to httpbin.org and post the given number."""
    response = await session.post("https://httpbin.org/post", json={"number": i})
    print(f"{i}: {response.status}")


async def run_asynchronously(n: int):
    """Request n websites asynchronously and print their response status."""
    async with ClientSession() as session:
        futures = [make_async_request(i, session) for i in range(n)]
        await asyncio.gather(*futures)


start_time = timer()
asyncio.run(run_asynchronously(n_requests))
end_time = timer()

print(f"Asynchronous experiment took {round(end_time - start_time, 3)} seconds")
