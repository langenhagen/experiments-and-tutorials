#!/usr/bin/env python3
"""
Showcase the usage of the 3rd party library `requests-futures` for async HTTP calls.

Get it via:

    pip install requests-futures

Then test it out via:

     python -m http.server 8000

    python requests-futures-hello.py

"""
from requests_futures.sessions import FuturesSession

def post():
    """Make a POST call."""
    session = FuturesSession()
    print ("Calling POST http://0.0.0.0:8000/foo ...")
    session.post("http://0.0.0.0:8000/foo")  # this endpoint takes a few seconds to respond
    print ("Finishing in `post()")


print("=== 1 fire and forget === ")

post()
print("End")
