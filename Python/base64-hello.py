#!/usr/bin/env python
"""Showcase the standard library package `base64` to convert data from and to
base64-encoded strings."""
import base64

print("--- 1 - encode strings ---\n")

msg = "Be excellent\nto each other"
msg_bytes = msg.encode("ascii")
base64_bytes = base64.b64encode(msg_bytes)
base64_msg = base64_bytes.decode("ascii")

print(base64_msg)

print("\n--- 2 - decode strings ---\n")

base64_bytes = base64_msg.encode("ascii")
msg_bytes = base64.b64decode(base64_bytes)
msg = msg_bytes.decode("ascii")

print(msg)
