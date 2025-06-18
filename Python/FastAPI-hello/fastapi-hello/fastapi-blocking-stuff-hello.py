#!/usr/bin/env python3
"""Showcase what happens when stuff blocks in FastAPI, e.g. blocking lifespans
or blocking endpoints.

Call the server e.g. via:

    curl -L http://localhost:8000/foo
    curl -L http://localhost:8000/block

author: andreasl
"""

import asyncio
import time
from typing import AsyncGenerator

import uvicorn
from fastapi import FastAPI


async def my_coroutine() -> None:
    print("Hello from my_coroutine()")
    time.sleep(10)  # this blocks `curl -L http://localhost:8000/foo`
    await asyncio.sleep(10)  # this does NOT block `curl -L http://localhost:8000/foo`
    print("Bye from my_coroutine()")


async def my_lifespan(app: FastAPI) -> AsyncGenerator[None, None]:
    """Gets executed FOR EACH WORKER across its ...lifespan!

    Each lifespan coroutine call gets its own FastAPI object, means that likely
    uvicorn simply spins up several FastAPIs.
    """
    print(f"Starting lifespan... {app=}")
    task = asyncio.create_task(my_coroutine())
    print("After starting lifespan, before yield")
    yield
    print(f"Ending Lifespan... {app=}")
    task.cancel()


app = FastAPI(lifespan=my_lifespan)


@app.get("/foo")
async def foo():
    """Some normal endpoint."""
    print("Hello from foo()!")
    return {"status": "ok", "message": "foo!"}


@app.get("/block")
async def block():
    """Some endpoint that blocks for a few seconds."""
    print("Hello from block()!")

    # this blocks `curl -L http://localhost:8000/foo`
    # when only 1 worker, but works with several workers
    time.sleep(10)

    # this does NOT block `curl -L http://localhost:8000/foo`
    # await asyncio.sleep(10)
    print("Bye from block()!")
    return {"status": "ok", "message": "unblocked!"}


if __name__ == "__main__":
    uvicorn.run(
        app="fastapi-blocking-stuff-hello:app",
        loop="asyncio",
        host="localhost",
        port=8000,
        reload=False,  # "workers" flag is ignored when reloading is enabled
        log_level="info",
        # log_level="debug",
        workers=4,
    )
