#!/bin/bash
# Generate an audiobook from an .epub ebook with the python tool `audiblez`.
# You can choose the ebook and a voice.
#
# See:
# - https://claudio.uk/posts/epub-to-audiobook.html
# - https://github.com/santinic/audiblez
#
# With `audiblez`:
# -l: option to specify the language, interesting languages: en_us, en_gb
# -v: option to specify the voice: available voices are af, af_bella, af_nicole, af_sarah,
#     af_sky, am_adam, am_michael, bf_emma, bf_isabella, bm_george, bm_lewis.
#     You can try them under https://huggingface.co/spaces/hexgrad/Kokoro-TTS
#
# Note:
# You can convert an ebook or a PDF via `calibre`'s `ebook-convert`:
#
#   sudo apt install calibre
#   ebook-convert in.pdf out.epub  # convert between .lit, .mobi, .epub, .pdf and maybe more
#
set -x

ebook_file="${1:-myebook.epub}"
voice='af_sky'

audiblez "$ebook_file" -l en-gb -v "$voice"
