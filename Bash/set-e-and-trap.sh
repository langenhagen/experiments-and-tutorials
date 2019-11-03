#!/bin/bash
# showcase the combination of set -x and trap

set -e

function on_exit {
    echo "Error!"
}
trap on_exit EXIT

echo "Hi!"
find -the -wrong -parameters!
echo "There"