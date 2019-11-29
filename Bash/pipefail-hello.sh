#!/bin/bash
# showcase usage of set -eo pipefail.
# Run outside a git repository to make git status caue a non-zero error code.
#
# See:
# https://vaneyckt.io/posts/safer_bash_scripts_with_set_euxo_pipefail/
git status 2>&1 | echo "a"
echo 'after without pipefail will be printed'

# -e: die on error;
# pipefail: die after pipe finishes if intermediate steps in pipes exit nonzero
# -E: take traps into consideration; set -e alone can avoid calling traps, e.g. on ERR
set -Eeo pipefail
git status 2>&1 | echo "b"
echo 'after with pipefail will not be printed'
