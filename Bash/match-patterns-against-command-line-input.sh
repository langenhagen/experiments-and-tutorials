#!/bin/bash
# Match patterns against command line input.
#
# Combine all command line arguments to a pattern of the form (arg1|arg2|arg3) and match against it.
#
# author andreasl

input="$*"
patterns="$(printf '%s' "$*" | tr ' ' '|')"

if [[ 'my-fancy-ding' =~ (${patterns}) ]]; then
    echo "found something matching my my-fancy-ding"
fi
if [[ 'c1-cre-api' =~ (${patterns}) ]]; then
    echo "found something matching c1-cre-api"
fi
if [[ 'c1-custom-dumont' =~ (${patterns}) ]]; then
    echo "found something matching c1-custom-dumont"
fi
