#!/usr/bin/env python
"""Showcase the usage of the common 3rd party Python library `requests`."""
import json

import requests

print("--- 1 - GETting stuff ---")
r = requests.get("https://google.com")
r.raise_for_status()  # throws an exception if the response doesn't have a 2xx success error code
print(r)  # 200
print(r.headers)
print(r.content)  # in bytes; use r.text for str content

print("--- 2 - POSTing stuff ---")
r = requests.post("https://httpbin.org/post", data={"key": "value"})
r.raise_for_status()

print(f"Type: {type(r.json())}")
assert isinstance(r.json(), dict)
print(json.dumps(r.json()))

print("--- 3 - TODO sessions ---")
s = requests.Session()
