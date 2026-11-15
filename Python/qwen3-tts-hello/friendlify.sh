#!/usr/bin/env bash
# Convert a text (or Markdown) file into a speech-friendly version, using Claude.
#
# Same rules as ../kokoro-hello/friendlify.sh, plus one sentence per line: the
# Qwen3-TTS Gradio app speaks each line as its own piece, so every piece stays
# short.
#
# Prints the result and writes it to <input file name>-speech-friendly.txt in
# the current directory.
#
# Usage: ./friendlify.sh FILE
set -euo pipefail

input_file="$1"
result_filename="${input_file##*/}-speech-friendly.txt"

prompt='
Convert a text file into a plain spoken version:
- put exactly one sentence per line; never break a sentence across lines
- separate paragraphs with a blank line
- remove artificial line breaks
- convert ALL_CAPS to normal spoken capitalization (e.g. GIT->Git, NASA->Nasa, but ABC remains ABC, and URI remains URI)
- convert acronyms and command line tools to formats that match how they are usually spoken (e.g. kubectl->"kubeCTL")
- spell out common abbreviations as spoken in the language of the text (e.g. "e.g."->"for example", "z. B."->"zum Beispiel")
- remove Markdown formatting
- the rest of the content shall remain verbatim, in its original language
- do not add an introductory sentence. Just the verbatim, converted text file

The text is:

'

{
    printf '%s' "$prompt"
    cat "$input_file"
} | claude --print | tee "$result_filename"
