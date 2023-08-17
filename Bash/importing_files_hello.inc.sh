# A file to be imported by `importing_files_hello.sh`
# shellcheck shell=bash
# shellcheck disable=SC2154  # disable shellcheck waring for unassigned symbols

my_function() {
    echo 'Hi from my_function!'
    echo "myvar = ${myvar}"  # bash picks this up nicely and dynamically
    echo 'Bye from my_function!'
}
