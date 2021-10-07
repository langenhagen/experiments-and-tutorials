#!/bin/bash
# Test if a default argument can be an evaluation of a function
#
# author: andreasl

existing="Hi There!"
default1=${existing:-(echo "yess1")}  # prints `Hi There!`
echo "$default1"

default2=${nonexisting:-$(uname)}  # prints `uname`
echo "$default2"
