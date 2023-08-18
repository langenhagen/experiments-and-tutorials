# A file to be imported by `importing_files_hello.sh`
# shellcheck shell=bash
# shellcheck disable=SC2154  # disable shellcheck warning for referenced but not assigned
# shellcheck disable=SC2034  # disable shellcheck warning for unused variables

import_script_dir="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"  # dir of importing_files_hello.inc.sh
echo "import_script_dir = ${import_script_dir}"

my_var_from_import=69
my_other_var='wh0at!'  # overrides upon import

my_function() {
    echo "Hi from my_function: myvar = ${myvar}"  # bash picks this up nicely and dynamically
}
