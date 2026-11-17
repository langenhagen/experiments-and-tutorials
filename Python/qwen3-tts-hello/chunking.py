#!/usr/bin/env python3
"""Split a text into chunks of at most --max-chars characters for text-to-speech.

Blank lines separate paragraphs; a chunk never crosses one. Within a
paragraph, line breaks become spaces, so hard-wrapped lines are joined. Then,
until the paragraph is used up: take the first max-chars characters, find the
last of ". ", "? ", "! ", ", ", or "; " in them, and cut after its
punctuation mark. Without any of these, cut at the last space, or at
max-chars if there is no space. Once the rest of the paragraph fits, it is
the last chunk. So short sentences are packed together greedily, in order.

Usage:

    chunking.py FILE                   print the chunks of the text file
    chunking.py --clipboard            print the chunks of the clipboard text
    echo "Hi. Ho." | chunking.py       print the chunks of the piped text
    chunking.py FILE --max-chars 300   allow chunks of up to 300 characters
    chunking.py --help                 show help

Prints one chunk per line and a blank line between paragraphs.

qwen3_backend.py, qwen3-speak.py, gradio-qwen3-tts-hello.py, and
create-audiobook-from-textfiles.py use split_chunks() from here.
"""

import argparse
import re
import shutil
import subprocess
import sys
from pathlib import Path

DEFAULT_MAX_CHARS = 500
SENTENCE_ENDS = (". ", "? ", "! ", ", ", "; ")


def split_paragraph(paragraph: str, max_chars: int) -> list[str]:
    """Cut one paragraph into chunks; see the module docstring."""
    rest = " ".join(paragraph.split())
    chunks = []
    while len(rest) > max_chars:
        window = rest[: max_chars + 1]
        end = max(window.rfind(mark) for mark in SENTENCE_ENDS)
        space = window.rfind(" ")
        if end != -1:
            chunk, rest = rest[: end + 1], rest[end + 2 :]
        elif space != -1:
            chunk, rest = rest[:space], rest[space + 1 :]
        else:
            chunk, rest = rest[:max_chars], rest[max_chars:]
        chunks.append(chunk)
    if rest:
        chunks.append(rest)
    return chunks


def split_paragraphs(text: str, max_chars: int) -> list[list[str]]:
    """Return the chunks of each paragraph of the text."""
    return [
        split_paragraph(paragraph, max_chars)
        for paragraph in re.split(r"\n\s*\n", text)
        if paragraph.strip()
    ]


def split_chunks(text: str, max_chars: int) -> list[str]:
    """Return the chunks of all paragraphs of the text in one list."""
    return [chunk for chunks in split_paragraphs(text, max_chars) for chunk in chunks]


def _clipboard_read_command() -> list[str]:
    """Return the OS-appropriate command that prints the clipboard to stdout."""
    if sys.platform == "darwin":
        return ["pbpaste"]
    if shutil.which("wl-paste"):
        return ["wl-paste", "--no-newline"]
    if shutil.which("xclip"):
        return ["xclip", "-selection", "clipboard", "-o"]
    if shutil.which("xsel"):
        return ["xsel", "--clipboard", "--output"]
    msg = "No clipboard utility found (need pbpaste, wl-paste, xclip, or xsel)"
    raise RuntimeError(msg)


def main() -> None:
    """Print the chunks of the text from a file, the clipboard, or a pipe."""
    parser = argparse.ArgumentParser(
        description=__doc__, formatter_class=argparse.RawDescriptionHelpFormatter
    )
    parser.add_argument("file", nargs="?", type=Path, help="text file to split")
    parser.add_argument(
        "--clipboard",
        "-c",
        action="store_true",
        help="split the text from the clipboard",
    )
    parser.add_argument(
        "--max-chars",
        type=int,
        default=DEFAULT_MAX_CHARS,
        help="chunk size limit in characters (default: %(default)s)",
    )
    args = parser.parse_args()

    if args.file:
        text = args.file.read_text(encoding="utf-8")
    elif args.clipboard:
        text = subprocess.check_output(_clipboard_read_command(), text=True)
    elif not sys.stdin.isatty():
        text = sys.stdin.read()
    else:
        parser.print_usage()
        sys.exit(2)

    paragraphs = split_paragraphs(text, args.max_chars)
    print("\n\n".join("\n".join(chunks) for chunks in paragraphs))


if __name__ == "__main__":
    main()
