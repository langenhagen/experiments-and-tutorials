#!/bin/bash
# Showcase how cd-ing with paths with glob stars works.

printf 'This showcase ,ight only work with an activated Python virtual environment!\n';

python_dir="$(dirname $(command -v python3))/.."

dir="${python_dir}/lib/python3*/site-packages/"

printf 'Variable with quotes:\n'
echo "$dir"
printf '\nExpands without quotes:\n'
echo $dir

printf '\ncd-ing into `site-packages` with quotes fails...\n'
cd "$dir"  # fails

printf '\ncd-ing into `site-packages` without quotes works if the path is unambiguous...\n'
cd $dir  # works if the path is unambiguous

pwd

echo done
