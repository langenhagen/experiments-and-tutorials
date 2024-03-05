#!/bin/bash
# Showcase that traps are called when the script exits due to `set -e`.
set -e

function on_exit {
    # will get called
    echo 'Hello from the trap!'
}
trap on_exit EXIT

echo 'Hi!'
cd a/directory/that/does/not/exist  # will fail
echo 'There'
