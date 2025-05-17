#!/usr/bin/env python3
"""Showcase how to track memory usage via Python's 3rd party library `psutil`.

The `psutil` module provides an interface for retrieving information on system
utilization, including memory, disk, and network, for processes and system
uptime.

It can monitor application performance and system resource usage in real-time.

author: andreasl
"""

import os

import psutil

print("--- 1 check memory usage of own process ---\n")

process = psutil.Process(os.getpid())
mem_usage = process.memory_info().rss  # in bytes
print(f"Initial memory usage: {mem_usage / 1024**2:.2f} MB")


print("--- 2 memory usage after defining a function ---\n")


def hello_world() -> str:
    return "Hello, World!"


mem_usage = process.memory_info().rss  # in bytes
print(f"Memory usage after defining a function: {mem_usage / 1024**2:.2f} MB\n")

print(hello_world())
mem_usage = process.memory_info().rss
print(f"Final memory usage after function call: {mem_usage / 1024**2:.2f} MB")

print("\n--- 3 memory usage after defining a big struct ---\n")

l = list(range(10000000))
mem_usage = process.memory_info().rss
print(f"Mem Usage after defining a big array: {mem_usage / 1024**2:.2f} MB")


print("\n--- 4 memory usage after deleting the big struct ---\n")

del l
mem_usage = process.memory_info().rss
print(f"Mem Usage after deleting the array: {mem_usage / 1024**2:.2f} MB")


print("\n--- 4 psutil.virtual_memory() ---\n")

vmem = psutil.virtual_memory()
print(f"Total Memory: {vmem.total / 1024**2:.2f} MB")  # Total physical memory
print(f"Available Memory: {vmem.available / 1024**2:.2f} MB")  # Unused memory
print(f"Used Memory: {vmem.used / 1024**2:.2f} MB")  # Memory in active use
print(f"Free Memory: {vmem.free / 1024**2:.2f} MB")  # Memory free for allocation
print(f"Active Memory: {vmem.active / 1024**2:.2f} MB")  # Currently active memory
print(f"Inactive Memory: {vmem.inactive / 1024**2:.2f} MB")  # Not currently used
print(f"Cached Memory: {vmem.cached / 1024**2:.2f} MB")  # Memory held in cache
print(f"Buffers: {vmem.buffers / 1024**2:.2f} MB")  # Disk buffers

print("\n--- 5 psutil.virtual_memory() is a snapshot; create new one for updates ---\n")

l = list(range(10000000))

print(f"vmem's Available Memory: {vmem.available / 1024**2:.2f} MB")

vmem2 = psutil.virtual_memory()
print(f"vmem2's Available Memory: {vmem2.available / 1024**2:.2f} MB")

del l
