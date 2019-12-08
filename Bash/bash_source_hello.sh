#!/bin/bash
# Showcase how ${BASH_SOURCE[0]} works and how not.

printf -- '--- 1 without cd ---\n'
script_dir="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
printf '> %s\n' "$script_dir"

printf -- '\n--- 2 with cd ---\n'
cd '/usr/bin'  # should break the effect of BASH_SOURCE
not_script_dir="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
printf '> %s\n' "$not_script_dir"
