#!/bin/bash
#
# Showcase the usage of inotify on directories.
#
# Write something into the directory (e.g. from another terminal) in the meantime.
# E.g., execute, one after another:
#   echo "Hallo" > mytestfile.txt
#   cat mytestfile.txt
#   mv mytestfile.txt foo
#   cp foo mytestfile.txt
#   rm foo
#   rm mytestfile.txt

function clean_up {
    rm mytestfile.txt foo
}
trap clean_up EXIT
clean_up

while inotifywait -e modify .; do  # modify reacts to creation and changes, not to rm and mv
  ls
done

printf 'Done'
