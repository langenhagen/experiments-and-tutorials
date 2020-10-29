"""
Showcase the builtin flexible logging module in Python.

Apparently, the kwargument stack_info does not work with Python 2.
"""
import logging
import sys

import otherfile

logging.basicConfig(
    # filename='myfile.log',  # enables logging to file
    format="%(asctime)s [%(levelname)s]: %(filename)s:%(lineno)d: %(message)s",
    datefmt="%a, %Y-%m-%d %H:%M:%S",
    level=logging.DEBUG
)

logger = logging.getLogger(__name__)


def foo():
    print('---1---')
    logger.debug("hello")
    print('---2---')
    logger.warning("world")
    print('---3---')
    logger.info('This should also print a trace',  stack_info=True)
    print('---4---')
    logger.info('This should also print an exc_info',  exc_info=True)  # exc_info yields here: NoneType None
    print('---5---')
    try:
        raise ValueError('hahahaha')
    except ValueError:
        # this is with stack_info=True; makes print the stack trace
        # to the logging function
        # exc_info=False is implicitly True here
        # stack_info=True not implicitly True here, but could make sense
        logger.exception('This Exception should also print an exc_info')
    print('---6---')
    try:
        raise ValueError('hohoho')
    except ValueError:
        # this is with stack_info=True; makes print the stack trace
        # to the logging function
        # exc_info=False is implicitly True here
        # stack_info=True not implicitly True here, but could make sense
        logger.exception('This Exception should also print an exc_info', stack_info=True)
    print('---7---')
    try:
        raise ValueError('hihihihi')
    except ValueError:
        logger.error('Using logger.error seems not to print the stack trace without saying')
foo()


def bar():
    print('---8---')
    logger.log(logging.DEBUG, 'mickey')
    print('---9---')
    logger.log(logging.WARN, 'mouse')
bar()


print('---10---')
otherfile.foo(':)')

print('---11---')
console_handler = logging.StreamHandler(sys.stdout)
logger.addHandler(console_handler)
handler2 = logging.StreamHandler(sys.stdout)
handler2.setLevel(logging.INFO)
logger.addHandler(handler2)

logger.debug('print this 2 times')
logger.info('print this 3 times')

otherfile.foo('only 1 time')

logger.removeHandler(console_handler)
logger.removeHandler(handler2)

logger.info('should only be printed once')
