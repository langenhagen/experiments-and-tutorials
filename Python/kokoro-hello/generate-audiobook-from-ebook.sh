#!/bin/bash
# Generate an audiobook from an .epub ebook with the python tool `audiblez`.
# You can choose the ebook and a voice.
#
# See:
# - https://claudio.uk/posts/epub-to-audiobook.html
# - https://github.com/santinic/audiblez
#
# see `audiblez --help` for options
#
# You can try some voices under https://huggingface.co/spaces/hexgrad/Kokoro-TTS
#
# Note:
# You can convert an ebook or a PDF via `calibre`'s `ebook-convert`:
#
#   sudo apt install calibre
#   ebook-convert in.pdf out.epub  # convert between .lit, .mobi, .epub, .pdf, .txt and maybe more
#
set -x

ebook_file="${1:-myebook.epub}"
voice="${2:-af_sky}"

time audiblez "$ebook_file" -v "$voice"
