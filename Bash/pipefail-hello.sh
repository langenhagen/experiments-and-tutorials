#!/bin/bash
# showcase usage of set -eo pipefail.
# Run outside a git repository to make git status caue a non-zero error code.
git status 2>&1 | echo "a"
echo 'after without pipefail will be printed'

set -eo pipefail
git status 2>&1 | echo "b"
echo 'after with pipefail will not be printed'
