"""
Showcase how getLogger() works and that it will use the same configuration
as defined in the main file.
"""

import logging

logger = logging.getLogger(name=__name__)  # uses the same global config as the main file


def foo(msg=""):
    logger.info("hello from the otherfile! " + msg)
