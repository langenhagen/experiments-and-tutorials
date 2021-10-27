#!/bin/bash
# Showcase the forwarding of command line options and the issues with whitespaces
#
# Usage:
#
#   bash option-forwarding.sh -a -1

printf 'Options are:\n'
printf '  %s\n' "$@"

printf '\n\nCommand ls as an example\n\n'

ls "$@"
