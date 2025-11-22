#!/bin/bash
# Convert a given text (or Markdown) file so that it becomes human speech-friendly.
# set -x

input_file="$1"
content="$(<"$input_file")"

# param="in just 1 word."
param="in just 20 words."
# param="in just 1 word. Possible resuslts are OK, YES, NO, MAYBE."

msg="
Given following text, summarize it ${param}

The text is:

"

msg+="$content"

# time codex exec "$msg" --skip-git-repo-check --json
# exit 99

result="$(codex exec "$msg" --skip-git-repo-check --json | jq -r 'select(.type=="item.completed" and .item.type=="agent_message") | .item.text')"
echo "$result"

# result_filename="${input_file##*/}-speech-friendly.txt"
# echo "$result" >"$result_filename"
