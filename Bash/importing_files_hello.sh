#!/bin/bash
# Showcase how to import files and
script_dir="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"

myvar=42

source "${script_dir}/importing_files_hello.inc.sh"
. "${script_dir}/importing_files_hello.inc.sh"   # works as well

my_function

myvar=32

my_function