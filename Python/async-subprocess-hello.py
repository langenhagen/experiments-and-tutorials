#!/usr/bin/env python3
"""Showcase how to create an async subprocess.

Also showcase, that when an executable is not found, it
`create_subprocess_exec()` will raise a `FileNotFoundError`.

author: andreasl
"""

import asyncio
from asyncio.subprocess import PIPE


async def run_hello() -> None:
    try:
        proc = await asyncio.create_subprocess_exec(
            "echo",
            "Hello, world!",
            stdout=PIPE,
            stderr=PIPE,
        )
        print(f"{proc.pid=}")

        out, err = await proc.communicate()
        print(f"stdout: {out.decode().strip()}")
        print(f"stderr: {err.decode().strip()}")

    except FileNotFoundError as e:
        print(f"Error: `echo` not found: {e}")


if __name__ == "__main__":
    asyncio.run(run_hello())
