#!/usr/bin/env python3
"""Minimal Kokoro TTS: read text from a file, a pipe, or the clipboard and
play it back as speech directly (no file is written).

Usage:

    kokoro.py FILE          read the text file and speak it
    kokoro.py --clipboard   speak the text from the clipboard
    echo "hi" | kokoro.py   speak text piped to stdin
    kokoro.py --help        show help
"""

import argparse
import shutil
import subprocess
import sys
from contextlib import suppress
from pathlib import Path

# The script is named kokoro.py, so drop its own directory from sys.path to
# avoid shadowing the installed "kokoro" package while importing it.
script_dir = Path(__file__).resolve().parent
sys.path = [p for p in sys.path if p and Path(p).resolve() != script_dir]

import sounddevice as sd  # noqa: E402  (imports follow the sys.path fix)

from kokoro import KPipeline  # noqa: E402  (imports follow the sys.path fix)

SAMPLERATE = 24000
VOICE = "af_heart"
LANG_CODE = "a"
XCLIP = shutil.which("xclip") or "xclip"


def read_clipboard() -> str:
    """Return the text currently stored in the clipboard."""
    return subprocess.check_output(
        [XCLIP, "-selection", "clipboard", "-o"],
        text=True,
    )


def main() -> None:
    """Synthesize and play the text from a file, clipboard, or stdin pipe."""
    parser = argparse.ArgumentParser(description=__doc__.splitlines()[1])
    parser.add_argument("file", nargs="?", help="text file to speak")
    parser.add_argument(
        "--clipboard",
        "-c",
        action="store_true",
        help="speak text from the clipboard",
    )
    args = parser.parse_args()

    if args.file:
        text = Path(args.file).read_text(encoding="utf-8")
    elif args.clipboard:
        text = read_clipboard()
    elif not sys.stdin.isatty():
        text = sys.stdin.read()
    else:
        parser.print_usage()
        sys.exit(2)

    pipeline = KPipeline(lang_code=LANG_CODE)
    generator = pipeline(text, voice=VOICE, speed=1, split_pattern=r"\n+")

    for i, (gs, ps, audio) in enumerate(generator):
        print(i)  # i: index
        print(gs)  # gs: graphemes/text
        print(ps)  # ps: phonemes
        sd.play(audio, SAMPLERATE)
        sd.wait()


if __name__ == "__main__":
    with suppress(KeyboardInterrupt):
        main()
