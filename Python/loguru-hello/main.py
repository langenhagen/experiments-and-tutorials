#!/usr/bin/env python3
"""Showcase logging with the 3rd party library Loguru."""

import sys

from loguru import logger

# Optional default sink removal and adding your own sink
# logger.remove()
# logger.add(
#     sys.stdout,
#     format="{time:YYYY-MM-DD HH:mm:ss.SSS} [{level}] {file}:{line}: {message}",
#     level="DEBUG",
# )

print("--- 1 simple logging ---\n")
logger.debug("hello")
logger.warning("world")
logger.info("!!!")
logger.error("Oh geez!")

print("\n--- 2 exceptions ---\n")
try:
    raise ValueError("ohno!")
except ValueError:
    logger.exception("This Exception logs a traceback")

print("\n--- 3 dynamic severity levels ---")
logger.log("DEBUG", "mickey")
logger.log("WARNING", "mouse")

print("\n--- 4 variable logging ---\n")


class C:
    def __init__(self) -> None:
        self.val = 9


a = "42"
b = C()

logger.info("Log variables: {}, {}, {}; yes like this", "mystring", 42, b)

print("\n--- 5 multiple sinks ---\n")
extra_sink = logger.add(sys.stdout, level="INFO")
logger.debug("appears once")
logger.info("appears twice")

logger.remove(extra_sink)
logger.info("only once now")
