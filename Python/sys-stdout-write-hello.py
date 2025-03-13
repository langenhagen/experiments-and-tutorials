"""Showcase an acceptable way to write stuff to stdout that is not `print()`."""

import sys

print("--- 1 write() ---\n")

sys.stdout.write("Hello World,\nThis is a test.")
sys.stdout.write(" This goes onto the same line")

print("\n--- 2 writelines() ---\n")

lines = ["Does", "Not", "break", "lines", "\nautomatically"]
sys.stdout.writelines(lines)  # no automatic line breaks
