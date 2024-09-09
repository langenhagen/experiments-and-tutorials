#!/usr/bin/env python3
"""Little showcase client for Websockets in Python."""
import asyncio
import logging
from contextlib import suppress
from itertools import count

import websockets
from websockets.exceptions import ConnectionClosedOK

logging.basicConfig(
    format="%(asctime)s [%(levelname)s]: line %(lineno)d: %(message)s",
    datefmt="%T",  # %T: HH:MM:SS
    level=logging.INFO,
)

logger = logging.getLogger(__name__)


async def main() -> None:
    uri = "ws://localhost:8000/ws"

    async with websockets.connect(uri) as websocket:

        try:
            response = await websocket.recv()
            logger.info("Received: %s", response)
            for i in count(1):
                await websocket.send(f"Hello WebSocket {i}!")
                response = await websocket.recv()
                logger.info("Received: %s", response)
                await asyncio.sleep(1)

        except ConnectionClosedOK:
            logger.info("Connection disconnected")


if __name__ == "__main__":
    with suppress(KeyboardInterrupt):
        asyncio.run(main())
