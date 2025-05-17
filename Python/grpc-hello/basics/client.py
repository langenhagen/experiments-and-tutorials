#!/usr/bin/env python3
"""
The client implementation.

Usage:

    python3 client.py
"""

import datetime as dt
import logging
from collections.abc import Generator
from contextlib import suppress
from pathlib import Path
from random import randint

from common import read_in_chunks
from generated.my_service_pb2 import FileMetaData, Nested, Point, UploadFileRequest
from generated.my_service_pb2_grpc import MyServiceStub
from grpc import RpcError, StatusCode, insecure_channel

logger = logging.getLogger(__name__)

ONE_DAY_SECONDS = 60 * 60 * 24


def gen_point() -> Generator[Point, None, None]:
    for i in range(20):
        yield Point(x=randint(0, i), y=randint(0, i))


def yield_upload_file_requests(
    file_path: Path,
) -> Generator[UploadFileRequest, None, None]:
    """TODO doc."""
    metadata = FileMetaData(file_path=str(file_path))
    yield UploadFileRequest(metadata=metadata)

    with file_path.open(mode="rb") as f:
        for file_chunk in read_in_chunks(f, chunk_size_bytes=4096):
            yield UploadFileRequest(file_chunk=file_chunk)


def request_download_file(
    file_to_download: Path, target_file_path, stub: MyServiceStub
) -> None:
    metadata = FileMetaData(file_path=str(file_to_download))
    responses = stub.DownloadFile(metadata)

    with target_file_path.open(mode="wb") as f:
        for r in responses:
            logger.info(f"{r=}")
            try:
                f.write(r.file_chunk)
            except Exception:
                logger.exception(f"Unable to write to file {target_file_path}")


def main() -> None:
    """Fire some requests against the server."""

    with insecure_channel("localhost:50051") as channel:
        stub = MyServiceStub(channel)

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
        for r in response:
            logger.info(f"{r=}")

        logger.info("\n--- 3 Requesting SendRequestStream ---")
        response = stub.SendRequestStream(Point(x=i, y=-i) for i in range(5))
        logger.info(f"{type(response)=}\n{response=}")

        logger.info("\n--- 4 Requesting BidirectionalStream ---")
        for response in stub.BidirectionalStream(gen_point()):
            print(response)

        logger.info("\n--- 5 Requesting UploadFile ---")

        # absolute just for show
        file_path = (Path(__file__).parent / "README.md").absolute()
        assert file_path.exists()

        response = stub.UploadFile(
            yield_upload_file_requests(file_path),
            timeout=ONE_DAY_SECONDS,
        )

        logger.info(f"{response=}")

        logger.info("\n--- 6 Requesting DownloadFile ---")

        source_file_path = (Path(__file__).parent / "README.md").absolute()
        now = dt.datetime.now()
        target_file_path = Path(f"downloaded_{now}_{source_file_path.name}")
        request_download_file(source_file_path, target_file_path, stub=stub)

        logger.info("\n--- 7 Requesting DownloadFile with broken file ---")

        source_file_path = Path("that does not exist")
        now = dt.datetime.now()
        target_file_path = Path(f"downloaded_{now}_{source_file_path.name}")
        try:
            request_download_file(source_file_path, target_file_path, stub=stub)
        except RpcError as err:
            status_code = err.code()
            errror_details = (
                "Got an error:\n"
                f"{err.details()=}\n"  # 'File does not exist'
                f"{status_code=}\n"
                f"{status_code.name}\n"  # NOT_FOUND
                f"{status_code.value=}"
            )
            logger.error(errror_details)

            if status_code == StatusCode.NOT_FOUND:
                logger.error("Was a NOT FOUND issue. Prolly wrong file name")
                target_file_path.unlink()


if __name__ == "__main__":
    logging.basicConfig(format="%(message)s", level=logging.DEBUG)
    with suppress(KeyboardInterrupt):
        main()
