#!/bin/bash
# Showcase how to source, i.e. import files in bash. The approach is simple but powerful.
# Basically, the mechanism concatenates the files together into one comprehensive script

script_dir="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
echo "script_dir = ${script_dir}"

myvar=42
my_other_var='easy'

source "${script_dir}/subdir/importing_files_hello.inc.sh"
# . "${script_dir}/subdir/importing_files_hello.inc.sh"   # works as well

echo "my_var_from_import = ${my_var_from_import}"  # 69
echo "my_other_var = ${my_other_var}"  # got overridden

my_function  # uses myvar

myvar=32
my_function  # user updated myvar
