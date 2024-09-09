#!/usr/bin/env python3
"""Little showcase server for Websockets via FastAPI."""
import logging

import uvicorn
from fastapi import FastAPI, WebSocket, WebSocketDisconnect

app = FastAPI()

logging.basicConfig(
    format="%(asctime)s [%(levelname)s]: line %(lineno)d: %(message)s",
    datefmt="%T",  # %T: HH:MM:SS
    level=logging.INFO,
)

logger = logging.getLogger(__name__)


class ConnectionManager:
    """Handles connects and disconnects and keeps track of active connections,
    just for fun.
    """

    def __init__(self) -> None:
        self.active_connections: list[WebSocket] = []

    async def accept(self, websocket: WebSocket) -> None:
        logger.info("Connecting client: %s", websocket)
        await websocket.accept()
        self.active_connections.append(websocket)
        await websocket.send_text("Welcome!")

    def remove_from_active_connections(self, websocket: WebSocket) -> None:
        logger.info("Removing client from active connections: %s", websocket)
        self.active_connections.remove(websocket)

    async def close(self, websocket: WebSocket) -> None:
        logger.info("Closing connection to %s", websocket)
        await websocket.send_text("Goodbye!")
        await websocket.close()
        self.remove_from_active_connections(websocket)


conman = ConnectionManager()


@app.websocket("/ws")
async def websocket_endpoint(websocket: WebSocket) -> None:
    await conman.accept(websocket)
    try:
        for i in range(1, 4):
            data = await websocket.receive_text()
            msg = f"Message {i} received: {data}"
            logger.info(msg)
            await websocket.send_text(msg)

        await conman.close(websocket)

    except WebSocketDisconnect:
        conman.remove_from_active_connections(websocket)


if __name__ == "__main__":
    uvicorn.run(app, host="localhost", port=8000)
