#!/bin/bash
#
# Showcase that set -e makes command -v kill the application if the given program does not exist.

set -x

set -e

printf 'Before\n'

if ! command -v bioegnrew; then
    printf "Within if should be reached\n"
fi
printf 'right after if-clause, this should be reached\n'

set +e

command -v 'this should also not fail due to set +e'

set -e
command -v 'this should finally fail'

printf 'Should never be reached 2\n'
