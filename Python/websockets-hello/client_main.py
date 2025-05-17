#!/usr/bin/env python
"""Showcase a simple websockets client.

Taken from: https://websockets.readthedocs.io/"""

import asyncio

import websockets


async def hello() -> None:
    async with websockets.connect("ws://localhost:8765") as websocket:
        await websocket.send("Hello world!")
        await websocket.recv()


asyncio.run(hello())
