"""Common general functionality."""

from typing import Generator


def read_in_chunks(file_object, chunk_size_bytes=4096) -> Generator[bytes, None, None]:
    """Generator function to read a file in chunks of the given chunk size."""
    while True:
        data = file_object.read(chunk_size_bytes)
        if not data:
            break
        yield data
