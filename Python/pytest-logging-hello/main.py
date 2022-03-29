#!/usr/bin/env python3
"""Showcase how to logging in pytest tests.

Pytest ignores logging settings in tests. Configure the logger for pytest
separately, e.g. via pyproject.toml.

Also test the logging a request while testing.

I was unable to include the test name into each log line, which renders the
pytest.log to file less useful than I like it to be.

Run via e.g.:

  pytest main.py
  pytest main.py | tee my.log
  unbuffer pytest main.py | tee my.log

See: https://stackoverflow.com/questions/4673373/logging-within-pytest-tests
and: https://stackoverflow.com/questions/16337511/log-all-requests-from-the-python-requests-module
"""
import logging

import requests


def log_something() -> int:
    """Log a statement."""
    logging.info("Hi from foo()!")
    return 42


def test_true():
    """Showcase logging in tests."""
    logging.debug("This a debug msg")
    logging.info("This an info msg")
    logging.warning("This a warning msg")
    logging.error("This an error msg")
    logging.exception("This an exception msg")

    assert True


def test_log_something():
    """Showscase indirect logging in tests."""
    r = log_something()
    assert r == 42


def test_request():
    """Showcase logging requests in tests."""
    r = requests.get("https://httpbin.org")
    assert r.ok
