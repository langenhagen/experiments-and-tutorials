#!/bin/bash
# see: http://pylint-messages.wikidot.com/all-codes

cd ~/Dev/experiments-and-tutorials/Python

error_code_array=(
    'C0112'  # Empty %s docstring
    'C0303'  # Trailing whitespace
    'E0104'  # Return outside function
    'E0105'  # Yield outside function
    'E0106'  # Return with argument inside generator
    'E0108'  # Duplicate argument name %s in function definition
    'E0602'   # undefined var
    'E0602'  # Undefined variable %r
    'W0101'  # Unreachable code
    'W0301'  # Unnecessary semicolon
    'W0311'  # Bad indentation. Found %s %s, expected %s
    'W0401'  # Wildcard import %s
    'W0410'  # __future__ import is not the first non docstring statement
    'W0611'  # indentation contains mixed spaces and tabs'
    'W0611'  # Unused import %s
    'W0612'  # Unused variable %r
    'W0613'  # Unused argument %r
    'W0703'  # Catching too general exception %s
)
error_codes="$(printf '%s,' "${error_code_array[@]}")"
pylint -j0 --disable=all --enable="$error_codes" --score=no 'erroneous-script-for-linter-tests.py'
echo "Status: $?"
