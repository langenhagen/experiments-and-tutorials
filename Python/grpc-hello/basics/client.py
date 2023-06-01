#!/usr/bin/env python3
"""
The client implementation.

Usage:

    python3 client.py
"""
import logging
from contextlib import suppress
from random import randint

import grpc

from generated import my_service_pb2_grpc
from generated.my_service_pb2 import Point, Rectangle

logger = logging.getLogger(__name__)


def gen_point():
    while True:
        yield Point(x=randint(0, 100), y=randint(0, 100))


def main():
    """Fire some requests against the server."""

    with grpc.insecure_channel("localhost:50051") as channel:
        stub = my_service_pb2_grpc.MyServiceStub(channel)

        logger.info("\n---\nRequesting LikeAFunction...")
        response = stub.LikeAFunction(Point(x=11, y=12))
        logger.info(f"{type(response)=}\n{response=}")

        logger.info("\n---\nRequesting GetResponseStream...")
        response = stub.GetResponseStream(
            Rectangle(lo=Point(x=12, y=13), hi=Point(x=88, y=99))
        )
        logger.info(f"{type(response)=}\n{response=}")
        for p in response:
            logger.info(p)

        logger.info("\n---\nRequesting SendRequestStream...")
        response = stub.SendRequestStream(
            Point(x=randint(-5, 5), y=randint(100, 120)) for _ in range(5)
        )
        logger.info(f"{type(response)=}\n{response=}")

        logger.info("\n---\nRequesting BidirectionalStream...")
        # for response in stub.BidirectionalStream(Point(x=10,y=49) for _ in range(1)):  # only 1 item
        for response in stub.BidirectionalStream(gen_point()):  # endless items
            print(response)


if __name__ == "__main__":
    logging.basicConfig(format="%(message)s", level=logging.DEBUG)
    with suppress(KeyboardInterrupt):
        main()
