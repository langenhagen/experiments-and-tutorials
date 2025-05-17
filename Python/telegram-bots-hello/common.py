"""Common functionality."""

import logging


def setup_logging() -> None:
    """Setup a basic logging config."""
    logging.basicConfig(
        format="%(asctime)s - %(name)s - %(levelname)s - %(message)s",
        datefmt="%T",  # %T: HH:MM:SS,
        level=logging.INFO,
    )
    # if you want, set higher log level for httpx
    logging.getLogger("httpx").setLevel(logging.WARNING)
