#!/usr/bin/env python
"""
The server implementation.

Usage:

    python3 server.py
"""
import logging
import time
from concurrent.futures import ThreadPoolExecutor
from contextlib import suppress
from random import randint
from typing import Generator

import grpc

from generated import my_service_pb2_grpc
from generated.my_service_pb2 import Nested, Point

logger = logging.getLogger(__name__)


class MyServiceServicer(my_service_pb2_grpc.MyServiceServicer):
    """Provides methods that implement functionality of the server."""

    def __init__(self):
        pass

    def LikeAFunction(self, request: Point, context) -> Point:
        logger.info(
            f"requested LikeAFunction\n{type(request)=}\n{request=}\n{type(context)=}\n{context=}\n{type(request.x)=}\n---\n"
        )
        return Point(x=request.x + 1, y=request.y + 2)

    def GetResponseStream(
        self, request: Nested, context
    ) -> Generator[Point, None, None]:
        logger.info(
            f"requested GetResponseStream\n{type(request)=}\n{request=}\n{type(context)=}\n{context=}\n---\n"
        )
        logger.info(f"{type(request)=}")
        for i in range(10):
            yield Point(x=request.one.x + i, y=request.other.y - i)

    def SendRequestStream(self, request_iterator, context) -> Point:
        logger.info(
            f"requested SendRequestStream\n{type(request_iterator)=}\n{request_iterator=}\n{type(context)=}\n{context=}\n---\n"
        )

        start_time = time.time()

        min_x: int | None = None
        max_y: int | None = None

        for point in request_iterator:
            print(point)
            if min_x is None:
                min_x = point.x
                max_y = point.y

            if point.x < min_x:
                min_x = point.x
            if point.y < max_y:
                max_y = point.y

        elapsed_time = time.time() - start_time
        logger.info(f"{elapsed_time=}")

        return Point(x=min_x, y=max_y)

    def BidirectionalStream(
        self, request_iterator, context
    ) -> Generator[Point, None, None]:
        logger.info(
            f"requested BidirectionalStream\n{type(request_iterator)=}\n{request_iterator=}\n{type(context)=}\n{context=}\n---\n"
        )
        for in_point in request_iterator:
            if randint(0, 10) > 7:
                logger.info(f"sending etwas back: {in_point}")
                yield in_point
            else:
                logger.info("sending nix back")


def serve():
    """Start the server and blocking wait for termination."""
    server = grpc.server(ThreadPoolExecutor(max_workers=10))
    my_service_pb2_grpc.add_MyServiceServicer_to_server(
        servicer=MyServiceServicer(),
        server=server,
    )

    server.add_insecure_port("[::]:50051")
    server.start()
    server.wait_for_termination()


if __name__ == "__main__":
    logging.basicConfig(format="%(message)s", level=logging.DEBUG)
    with suppress(KeyboardInterrupt):
        serve()
