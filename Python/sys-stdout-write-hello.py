"""Showcase an acceptable way to write stuff to stdout that is not `print()`."""
import sys

print("--- 1 ---\n")

sys.stdout.write("Hello World,\nThis is a test.")
sys.stdout.write(" This goes onto the same line")
