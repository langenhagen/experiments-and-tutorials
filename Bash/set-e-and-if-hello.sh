#!/bin/bash
#
# Showcase that set -e makes command -v kill the application if the given program does not exist.

set -e

printf 'Before\n'

if ! command -v bioegnrew; then
    printf "Within if should be reached\n"
fi
printf 'right after if-clause\n'

command -v abcedpief

printf 'Should never be reached 2\n'
