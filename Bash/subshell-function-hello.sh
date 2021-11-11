#!/bin/bash
# Showcase the subshell function syntax in Bash functions.
# Subshell functions spawn their own subshells. Outer functions don't see inner
# functions'svariables, but vice versa.
# Typical bash functions are dynamically scoped.
#
# I.e., subshell functions are lexically scoped, whereas typical bash functions are dynamically
# scoped.
#
# Also, subshell functions allow for traps to be activated when exiting the function, which is
# comparable to the statement `defer` in golang.
#
# See:  https://cuddly-octo-palm-tree.com/posts/2021-10-31-better-bash-functions

printf -- '--- 1 typical shell functions with style: foo(){} --\n'
typical() {
    echo 'Hi from within a common shell function!'
}
typical

printf -- '\n--- 2 subshell functions with style: foo()() --\n'
subshell() (
    echo 'Hi from within a subshell function!'
)
subshell

printf -- '\n--- 3 benchmark typical shell functions --\n'
time for _ in {0..10000}; do
    typical >/dev/null
done

printf -- '\n--- 4 benchmark subshell functions --\n'
time for _ in {0..10000}; do
    subshell >/dev/null
done
