#!/usr/bin/env python3
"""Showcase the use of the 3rd party logging library `structlog`.

based on: https://betterstack.com/community/guides/logging/structlog/
"""

import asyncio
import logging
import logging.config
from pathlib import Path
from random import randint

import structlog
from structlog.processors import (
    CallsiteParameter,
    CallsiteParameterAdder,
    EventRenamer,
    JSONRenderer,
    StackInfoRenderer,
    TimeStamper,
    add_log_level,
    dict_tracebacks,
)


def divide_by_zero():
    """Throws an exception"""
    some_local_var = 42
    try:
        some_local_var / 0
    except ZeroDivisionError:
        logger.exception("Oh no!")


print("--- 1 structlog 101 ---")

structlog.configure(
    wrapper_class=structlog.make_filtering_bound_logger(logging.DEBUG),  # log level
    # customize logger output format
    processors=[
        TimeStamper(fmt="%a, %F %T"),  # %a: weekday  %F: YYYY-MM-DD  %T: HH:MM:SS
        # TimeStamper(fmt="iso"),  # iso format
        # TimeStamper(),  # unix timestamps
        add_log_level,
        structlog.dev.ConsoleRenderer(),  # the renderer must come after its processors
    ],
)
logger = structlog.get_logger()

logger.debug("Ima debug mesg")
logger.info("I say info")
logger.warning("this a warning")
logger.error("this an error")
logger.critical("that is a fatal thingo")
# without exception info, prints a trailing "NoneType: None"
logger.exception("ima exception")
divide_by_zero()


print("\n--- 2 custom processors ---\n")


def my_custom_processor(logger, method_name: str, event_dict: dict) -> dict:
    """A custom processor.

    Good for e.g. logging process ID, Thread ID, stuff like that

    Structlog processors receive 3 arguments:
        1. the logger
        2. method name (such as info)
        3. event dictionary."""
    some_custom_message = randint(100, 999)
    event_dict["my custom msg"] = some_custom_message
    event_dict["my_severity"] = method_name
    event_dict["loggger"] = type(logger).__name__
    return event_dict


structlog.configure(
    wrapper_class=structlog.make_filtering_bound_logger(logging.DEBUG),  # log level
    processors=[
        TimeStamper(fmt="iso"),  # iso format
        my_custom_processor,
        structlog.dev.ConsoleRenderer(),
    ],
)

logger.info("I say info")


print("\n--- 3 JSON output ---\n")

structlog.configure(
    processors=[
        add_log_level,
        TimeStamper(),
        my_custom_processor,
        JSONRenderer(),
    ]
)

# note that the log message is under the key "event"
logger.info("Log entry in JSON format under key `event`")


print("\n--- 3 JSON output with EventRenamer ---\n")

structlog.configure(
    processors=[
        add_log_level,
        TimeStamper(),
        my_custom_processor,
        EventRenamer("msg"),
        JSONRenderer(),
    ]
)

logger.info("Now this is under the key `msg`")


print("\n--- 4 exception logging with JSON ---\n")

structlog.configure(
    processors=[
        add_log_level,
        dict_tracebacks,  # this is important
        JSONRenderer(),
    ],
)

logger.info("I say info")
# logger.exception("ima exception")  # this would crash now
divide_by_zero()


print("\n--- 5 add contextual data ---\n")

structlog.configure(
    processors=[
        TimeStamper(),
        JSONRenderer(),  # works as well with `ConsoleRenderer`
    ]
)

logger_with_bound_field = logger.bind(my_bound_field=42)

logger_with_bound_field.info("Beep boop", name="John Doe")
logger_with_bound_field.info("Something smthng", name="Jane Foster", comment_id="2")


print("\n--- 6 filter/drop messages ---\n")


def foo():
    logger.info("Logging from foo()")


def bar():
    logger.info("Logging from bar(); should be filtered")


def filter_function(_, __, event_dict):
    """Filter/Drop messages by raising `DropEvent` for stuff you don't want."""
    if event_dict.get("route") == "login":
        raise structlog.DropEvent
    if event_dict.get("func_name") == "bar":
        # relies on this `CallsiteParameterAdder`
        raise structlog.DropEvent
    return event_dict


structlog.configure(
    processors=[
        # this guy adds func_name to the event_dict for the `filter_function()`
        CallsiteParameterAdder([CallsiteParameter.FUNC_NAME]),
        filter_function,
        JSONRenderer(),
    ]
)
logger = structlog.get_logger()
logger.info("This gets dropped", name="John Doe", route="login")
logger.info("This gets retained", title="My first post", route="post")

foo()
bar()


print("\n--- 7 async logging with a<level>() ---\n")

structlog.configure(processors=[JSONRenderer()])


async def foo():
    await logger.ainfo("This is an asynchronous log", name="file1.txt")


asyncio.run(foo())


print("\n--- 8 log to file ---\n")

structlog.configure(
    processors=[
        add_log_level,
        StackInfoRenderer(),
        structlog.dev.set_exc_info,
        JSONRenderer(),
    ],
    logger_factory=structlog.WriteLoggerFactory(
        file=Path("myfirst").with_suffix(".log").open("wt")  # at: append, wt: write
    ),
)

logger.info("Info message")
logger.error("Error message")
logger.exception("Boom badaboom")
divide_by_zero()


print("\n--- 9 more comprehensive config ---\n")

logging.config.dictConfig(
    {
        "version": 1,
        "disable_existing_loggers": True,
        "formatters": {
            "json_formatter": {
                "()": structlog.stdlib.ProcessorFormatter,
                "processor": JSONRenderer(),
            },
            "plain_console": {
                "()": structlog.stdlib.ProcessorFormatter,
                "processor": structlog.dev.ConsoleRenderer(),
            },
        },
        "handlers": {
            "console": {
                "class": "logging.StreamHandler",
                "formatter": "plain_console",
            },
            "json_file": {
                "class": "logging.handlers.WatchedFileHandler",
                "filename": "mysecond.json",
                "formatter": "json_formatter",
            },
        },
        "loggers": {
            "my_structlog": {
                "handlers": ["console", "json_file"],
                "level": "WARNING",
            },
            "root": {
                "handlers": ["console", "json_file"],
                "level": "DEBUG",
            },
        },
    }
)

structlog.configure(
    processors=[
        structlog.contextvars.merge_contextvars,  # no clue
        structlog.stdlib.filter_by_level,  # no clue
        structlog.stdlib.add_logger_name,  # [__main__]
        structlog.stdlib.PositionalArgumentsFormatter(),  # no clue
        structlog.processors.StackInfoRenderer(),  # no clue
        structlog.processors.format_exc_info,  # for "exception"-info in JSON renderer
        structlog.processors.UnicodeDecoder(),  # no clue
        structlog.stdlib.ProcessorFormatter.wrap_for_formatter,  # no clue but necessary
    ],
    logger_factory=structlog.stdlib.LoggerFactory(),
    cache_logger_on_first_use=True,  # no clue
)

logger = structlog.get_logger()
# logger = structlog.get_logger("my_structlog")  # would use the other configured logger

logger.debug("Ima debug mesg")
logger.info("I say info smiley 😊")
logger.warning("this a warning")
logger.error("this an error")
logger.critical("that is a fatal thingo")
divide_by_zero()
