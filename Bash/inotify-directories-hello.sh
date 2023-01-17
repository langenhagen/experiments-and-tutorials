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
    rm -f mytestfile.txt foo
}
trap clean_up EXIT
clean_up

while true; do  # `modify` reacts to creation and changes, not to rm and mv
    inotify_output="$(inotifywait --event modify --recursive "$PWD" 2>/dev/null)"  # 2>/dev/null shadows output a la "Setting up watches. Watches established."
    printf '\ninotify_output:\n%s\n' "$inotify_output"
    inotify_line="$(grep --max-count=1 ' MODIFY ' <<< "$inotify_output")"
    filename="$(rev <<<  "$inotify_line" | cut -d' ' -f1 | rev)"
    printf 'Filename: %s\n' "$filename"
done

printf 'Done'
