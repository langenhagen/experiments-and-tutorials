#!/usr/bin/env python
"""
The server implementation.

Usage:

    python3 server.py
"""

import datetime as dt
import logging
import time
from collections.abc import Generator
from concurrent.futures import ThreadPoolExecutor
from contextlib import suppress
from pathlib import Path
from random import randint

import grpc
from grpc import StatusCode

from common import read_in_chunks
from generated import my_service_pb2_grpc
from generated.my_service_pb2 import FileResponse, Nested, Point, UploadFileResponse

logger = logging.getLogger(__name__)


class MyServiceServicer(my_service_pb2_grpc.MyServiceServicer):
    """Provides methods that implement functionality of the server."""

    def __init__(self) -> None:
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

    def UploadFile(self, request_iterator, context) -> UploadFileResponse:
        """TODO Miau miau miau"""
        logger.info("\n ***requested UploadFile... ***")
        first_request = next(request_iterator)
        assert first_request.metadata is not None, "is never none"
        assert first_request.file_chunk is not None, "is never none"

        if not first_request.metadata.file_path:
            msg = f"Something's not right:\n{first_request=}"
            logger.error(msg)
            context.set_details(msg)
            context.set_code(StatusCode.UNKNOWN)
            return UploadFileResponse()

        logger.info("gut gut")
        now = dt.datetime.now()
        orig_filenname = Path(first_request.metadata.file_path).name
        file_name = Path(f"uploaded_{now}_{orig_filenname}")

        with file_name.open(mode="wb") as f:
            for request in request_iterator:
                assert request.metadata is not None, "is never None"
                assert request.file_chunk is not None, "is never None"

                try:
                    f.write(request.file_chunk)
                except Exception as err:
                    logger.exception(f"Unable to write to file {file_name}")
                    context.set_details(str(err))
                    context.set_code(StatusCode.INTERNAL)
                    return UploadFileResponse()

        return UploadFileResponse()

    def DownloadFile(self, request, context) -> Generator[FileResponse, None, None]:
        """TODO Miau miau miau"""
        logger.info("\n ***requested DownloadFile... ***")

        file_path = Path(request.file_path)

        if not file_path.exists():
            logger.error(f'File "{file_path}" does not exist')
            context.set_details("File does not exist")
            context.set_code(StatusCode.NOT_FOUND)
            return FileResponse()

        logger.info("File exists; sending")

        try:
            with file_path.open(mode="rb") as f:
                for file_chunk in read_in_chunks(f, chunk_size_bytes=4096):
                    yield FileResponse(file_chunk=file_chunk)
        except Exception as err:
            logger.exception("Got an exception")
            logger.info("End of Stack trace")
            context.set_details(str(err))
            context.set_code(StatusCode.INTERNAL)
            return FileResponse()

        return None


def serve() -> None:
    """Start the server and blocking wait for termination."""
    server = grpc.server(ThreadPoolExecutor(max_workers=10))
    my_service_pb2_grpc.add_MyServiceServicer_to_server(
        servicer=MyServiceServicer(),
        server=server,
    )

    server.add_insecure_port("[::]:50051")
    server.start()

    with suppress(KeyboardInterrupt):
        server.wait_for_termination()

    server.stop(0)


if __name__ == "__main__":
    logging.basicConfig(format="%(message)s", level=logging.DEBUG)
    serve()
