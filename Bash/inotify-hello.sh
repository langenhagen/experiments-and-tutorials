#!/bin/bash
#
# Showcase the usage of inotify.
#
# Write something into the file mytestfile.txt (e.g. from another terminal) in the meantime
#
# E.g., execute, one after another:
#   printf "Hello!" > mytestfile.txt
#   printf "Hello!" > mytestfile.txt
#   printf "Hello!" > mytestfile.txt
#   printf "Hello!" > mytestfile.txt
#   printf "Hello!" > mytestfile.txt
#   rm mytestfile.txt



function clean_up {
    rm mytestfile.txt
}
trap clean_up EXIT
clean_up

# if the file is missing, gives an error:
# Couldn't watch mytestfile.txt: No such file or directory
while inotifywait -e close_write mytestfile.txt; do  # should fail & break the loop
    printf '>> Close write mytestfile.txt 1\n'
done

touch mytestfile.txt

# another way
inotifywait -e close_write mytestfile.txt && printf '>> Close write mytestfile.txt 2\n'

# should stick in the loop as long as the file mytestfile.txt is there
while inotifywait -e close_write mytestfile.txt; do
    printf '>> Close write mytestfile.txt 3\n'
done

printf 'Done'
