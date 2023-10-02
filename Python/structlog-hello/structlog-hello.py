#!/usr/bin/env python3
"""Showcase the use of the 3rd party logging library `structlog`."""
import logging

import structlog

structlog.configure(
    wrapper_class=structlog.make_filtering_bound_logger(logging.DEBUG),  # log level
    # customize logger output format
    processors=[
        # %a: weekday  %F: YYYY-MM-DD  %T: HH:MM:SS
        structlog.processors.TimeStamper(fmt="%a, %F %T"),
        # structlog.processors.TimeStamper(fmt="iso"),  # iso format
        # structlog.processors.TimeStamper(),  # unix timestamps
        structlog.processors.add_log_level,
        structlog.dev.ConsoleRenderer(),  # the renderer must come after its processors
    ],
)
logger = structlog.get_logger()

logger.debug("Ima debug mesg")
logger.info("I say info")
logger.warning("this a warning")
logger.error("this an error")
logger.critical("that is a fatal thingo")
