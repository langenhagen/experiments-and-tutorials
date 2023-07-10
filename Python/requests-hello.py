#!/usr/bin/env python
"""Showcase the usage of the common 3rd party Python library `requests`."""
import json

import requests

print("--- 1 - GETting stuff ---\n")
r = requests.get("https://google.com")
r.raise_for_status()  # throws exception if response doesn't have a 2xx success error code
print(r)  # 200
print(r.headers)
print(r.content)  # in bytes; use r.text for str content

print("\n--- 2 - POSTing stuff ---\n")
r = requests.post("https://httpbin.org/post", json={"key": "value"})
r.raise_for_status()

print(f"Type: {type(r.json())}")
assert isinstance(r.json(), dict)
print(json.dumps(r.json()))

print("\n--- 3 - TODO sessions ---\n")
s = requests.Session()
