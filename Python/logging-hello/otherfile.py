"""
Showcase how getLogger() works and that it will use the same configuration
as defined in the main file.
"""
import logging

log = logging.getLogger(name=__name__)  # will use the same configuration

def foo(msg=''):
    log.info('hello from the otherfile! ' + msg)
