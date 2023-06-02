#!/usr/bin/env python3
"""
The client implementation.

Usage:

    python3 client.py
"""
import logging
from contextlib import suppress
from pathlib import Path
from random import randint
from typing import Generator

import grpc

from generated import my_service_pb2_grpc
from generated.my_service_pb2 import FileMetaData, Nested, Point, UploadFileRequest

logger = logging.getLogger(__name__)


def gen_point() -> Generator[Point, None, None]:
    for i in range(20):
        yield Point(x=randint(0, i), y=randint(0, i))


def read_in_chunks(file_object, chunk_size_bytes=1024) -> Generator[bytes, None, None]:
    """Generator function to read a file in chunks."""
    while True:
        data = file_object.read(chunk_size_bytes)
        if not data:
            break
        yield data


def yield_upload_file_requests(
    file_path: Path,
) -> Generator[UploadFileRequest, None, None]:
    """TODO doc."""
    metadata = FileMetaData(file_path=str(file_path))
    yield UploadFileRequest(metadata=metadata)

    with open(file_path, "rb") as f:
        for file_chunk in read_in_chunks(f, chunk_size_bytes=1024):
            yield UploadFileRequest(file_chunk=file_chunk)


def main():
    """Fire some requests against the server."""

    with grpc.insecure_channel("localhost:50051") as channel:
        stub = my_service_pb2_grpc.MyServiceStub(channel)

        logger.info("\n--- 1 Requesting LikeAFunction ---")
        response = stub.LikeAFunction(Point(x=0, y=0))
        logger.info(f"{type(response)=}\n{repr(response)=}")

        logger.info("\n--- 2 Requesting GetResponseStream ---")
        response = stub.GetResponseStream(
            Nested(
                one=Point(x=12, y=13),
                other=Point(x=88, y=99),
            )
        )
        logger.info(f"{type(response)=}\n{response=}")
        for p in response:
            logger.info(f"{p=}")

        logger.info("\n--- 3 Requesting SendRequestStream ---")
        response = stub.SendRequestStream(Point(x=i, y=-i) for i in range(5))
        logger.info(f"{type(response)=}\n{response=}")

        logger.info("\n--- 4 Requesting BidirectionalStream ---")
        for response in stub.BidirectionalStream(gen_point()):
            print(response)

        logger.info("\n--- 5 Requesting UploadFile ---")

        file_path = (
            Path(__file__).parent / "README.md"
        ).absolute()  # absolute just for show
        assert file_path.exists()

        response = stub.UploadFile(yield_upload_file_requests(file_path))

        logger.info(f"{response=}")


if __name__ == "__main__":
    logging.basicConfig(format="%(message)s", level=logging.DEBUG)
    with suppress(KeyboardInterrupt):
        main()
