#!/bin/bash
# Showcase that echoing ~ as under sudo returns the root's home dir.
# Run like: sudo bash echo-home.sh
set -x

echo ~  # /root
