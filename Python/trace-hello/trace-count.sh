#!/bin/bash
# Creates files `main.cover` and `hello.cover`.
set -x

python3 -m trace --count main.py
