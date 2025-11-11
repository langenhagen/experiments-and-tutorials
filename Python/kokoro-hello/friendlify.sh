#!/bin/bash
# Convert a given text (or Markdown) file so that it becomes human speech-friendly.
# set -x

input_file="$1"
content="$(<"$input_file")"

msg='
Convert a text file into a plain spoken version:
- remove artificial line breaks
- convert ALL_CAPS to normal spoken capitalization (e.g. GIT→Git, NASA→Nasa, but ABC remains ABC, and URI remains URI)
- convert acronyms and command line tools to formats that match how they are usually spoken (e.g. kubectl→"kubeCTL")
- spell out common abbreviations as spoken (e.g.: "e.g."→"for example")
- remove Markdown formatting
- the rest of the content shall remain verbatim
- do not add an introductory sentence. Just the verbatim, converted text file

Basically, translate to plain file as it would be spoken out, without artificial line breaks.

The text is:

'

msg+="$content"

# time codex exec "$msg" --skip-git-repo-check --json
# exit 99

result="$(codex exec "$msg" --skip-git-repo-check --json | jq -r 'select(.type=="item.completed" and .item.type=="agent_message") | .item.text')"
echo "$result"

result_filename="${input_file##*/}-speech-friendly.txt"
echo "$result" >"$result_filename"
