#!/usr/bin/env python3
"""
Showcase the usage of a Python3 ThreadPoolExecutor.

Based on: https://docs.python.org/3/library/concurrent.futures.html#threadpoolexecutor-example
"""
from concurrent.futures import ThreadPoolExecutor
from urllib.request import urlopen

URLS = [
    "http://www.foxnews.com/",
    "http://www.cnn.com/",
    "http://europe.wsj.com/",
    "http://www.bbc.co.uk/",
    "http://some-made-up-domain.com/",
]


def load_url(url, timeout):
    """Retrieve a single page and report the URL and contents."""
    with urlopen(url, timeout=timeout) as conn:
        return conn.read()


# We can use a with statement to ensure threads are cleaned up promptly
with ThreadPoolExecutor(max_workers=5) as executor:
    # Start the load operations and mark each future with its URL
    future_to_url = {executor.submit(load_url, url, 60): url for url in URLS}
    for future in concurrent.futures.as_completed(future_to_url):
        url = future_to_url[future]
        try:
            data = future.result()
        except Exception as exc:
            print("%r generated an exception: %s" % (url, exc))
        else:
            print("%r page is %d bytes" % (url, len(data)))

print("DONE.")  # appears after all threads are done; the context manager is blocking.