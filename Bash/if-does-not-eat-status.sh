#!/bin/bash
# Showcase that if [ ] does not eat the last exit status variable $?
#
# author: andreasl
set -x

if [ 1 -ne 2 ]; then
    find shouldfail 1>/dev/null 2>&1
fi
echo Exit status 1: $?

if [ 1 -ne 2 ]; then
    find 1>/dev/null 2>&1
fi
echo Exit status 2: $?

