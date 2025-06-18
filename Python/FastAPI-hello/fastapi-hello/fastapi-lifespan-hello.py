#!/usr/bin/env python3
"""Showcase the use of lifespans in FastAPI that start before the app starts and
anc end after the app ends, potentially good for preparing stuff, cleaning up or
kicking off async tasks.

Lifespans get executed for every worker.

Call the server e.g. via:

    curl -L http://localhost:8000/start  # resume the coroutine
    curl -L http://localhost:8000/stop   # pause the coroutine

author: andreasl
"""

import asyncio
from typing import AsyncGenerator

import uvicorn
from fastapi import FastAPI

resume_event = asyncio.Event()
resume_event.set()  # start unpaused


async def my_coroutine() -> None:
    i = 0
    while True:
        await resume_event.wait()
        print(f"Hello from my_coroutine() {i=}")
        i += 1
        await asyncio.sleep(1)


async def my_lifespan(app: FastAPI) -> AsyncGenerator[None, None]:
    """Gets executed FOR EACH WORKER across its ...lifespan!"""
    print(f"Starting lifespan... {app=}")
    task = asyncio.create_task(my_coroutine())
    print("After starting lifespan, before yield")
    yield
    print(f"Ending Lifespan... {app=}")
    task.cancel()


app = FastAPI(lifespan=my_lifespan)


@app.get("/start")
async def start():
    """Some endpoint."""
    print("Hello from start()!")
    resume_event.set()
    return {"status": "ok", "message": "started"}


@app.get("/stop")
async def stop():
    """Some endpoint."""
    print("Hello from stop()!")
    resume_event.clear()
    return {"status": "ok", "message": "stopped"}


if __name__ == "__main__":
    uvicorn.run(
        app="fastapi-lifespan-hello:app",
        loop="asyncio",
        host="localhost",
        port=8000,
        reload=False,  # "workers" flag is ignored when reloading is enabled
        log_level="info",
        # log_level="debug",
        # workers=1,
        workers=4,  # workers are processes
    )
