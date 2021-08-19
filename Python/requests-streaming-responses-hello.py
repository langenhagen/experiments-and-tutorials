#!/usr/bin/env python
"""Showcase the usage of a streaming requests with Python's common 3rd party
library `requests."""

import json

import requests

# url = "https://httpbin.org/stream/20"  # returns http streaming data
url = "https://cashcog.xcnt.io/stream"  # returns http streaming data

response = requests.get(url, stream=True)

for line in response.iter_lines():
    # ignore possible keep-alive newlines
    if line:
        decoded_line = line.decode("utf-8")
        print(f"Nice, innit?: {json.loads(decoded_line)}")
