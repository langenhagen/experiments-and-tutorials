#!/bin/bash
# Showcase bash variable substring removal with parameter expansion.
#
# ${var#glob}  # Remove glob from start of value (lazy)
# ${var##glob}  # Remove glob from start of value (eager)
#
# ${var%glob}  # Remove glob from end of value (lazy)
# ${var%%glob}  # Remove glob from end of value (eager)
set -x

var='/long/path/to/file.txt'

echo ${var#/*/}     # path/to/file.txt
echo ${var#/*/*/}   # to/file.txt
echo ${var#/*/*/*/} # file.txt

echo ${var##*/}     # file.txt
