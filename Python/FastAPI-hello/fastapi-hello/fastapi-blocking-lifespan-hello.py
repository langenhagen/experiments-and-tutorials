#!/usr/bin/env python3
"""Showcase what happens when lifespans block.

Call the server e.g. via:

    curl -L http://localhost:8000/foo

author: andreasl
"""

import asyncio
import time
from typing import AsyncGenerator

import uvicorn
from fastapi import FastAPI


async def my_coroutine() -> None:
    print("Hello from my_coroutine()")
    time.sleep(10)  # this blocks  `curl -L http://localhost:8000/foo`
    await asyncio.sleep(10)  # this does NOT block `curl -L http://localhost:8000/foo`
    print("Bye from my_coroutine()")


async def my_lifespan(app: FastAPI) -> AsyncGenerator[None, None]:
    print(f"Starting lifespan... {app=}")
    task = asyncio.create_task(my_coroutine())
    print("After starting lifespan, before yield")
    yield
    print("Ending Lifespan...")
    task.cancel()


app = FastAPI(lifespan=my_lifespan)


@app.get("/foo")
async def foo():
    """Some endpoint."""
    print("Hello from foo()!")
    return {"status": "ok", "message": "foo!"}


if __name__ == "__main__":
    uvicorn.run(
        app="fastapi-blocking-lifespan-hello:app",
        loop="asyncio",
        host="localhost",
        port=8000,
        reload=True,
        log_level="info",
        # log_level="debug",
    )
