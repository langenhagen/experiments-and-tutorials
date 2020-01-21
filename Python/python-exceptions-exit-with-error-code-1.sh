#!/bin/bash
# Showcase that python exceptions that cause a Python program to crash set the exit code to 1.
#
# author: andreasl

python -c 'print("Hello from Python!")'
printf 'Status code from a functioning python program: %s\n\n' "$?"

python -c 'raise ValueError("Hello from a Python crash!")'
printf 'Status code from a crashing python program: %s\n' "$?"
