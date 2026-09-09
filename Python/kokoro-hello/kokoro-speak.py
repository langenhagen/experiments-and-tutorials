#!/usr/bin/env python3
"""Minimal Kokoro TTS: read text from a file, a pipe, or the clipboard and
play it back as speech directly (no file is written).

Usage:

    kokoro-speak.py FILE              read the text file and speak it
    kokoro-speak.py --clipboard       speak the text from the clipboard
    echo "hi" | kokoro-speak.py       speak text piped to stdin
    kokoro-speak.py FILE --out-file x.wav
        write the synthesized speech from FILE to x.wav instead of speaking

    kokoro-speak.py --help        show help
"""

import argparse
import shutil
import subprocess
import sys
from contextlib import suppress
from pathlib import Path

import sounddevice as sd
import soundfile as sf
from kokoro import KPipeline

DESCRIPTION = (
    "Minimal Kokoro TTS: read text from a file, a pipe, or the clipboard and "
    "play it back as speech directly (no file is written), or write it to a "
    "file with --out-file."
)
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
    parser = argparse.ArgumentParser(description=DESCRIPTION)
    parser.add_argument("file", nargs="?", help="text file to speak")
    parser.add_argument(
        "--clipboard",
        "-c",
        action="store_true",
        help="speak text from the clipboard",
    )
    parser.add_argument(
        "--out-file",
        "--out",
        "-o",
        metavar="FILE",
        help="write the synthesized speech to FILE instead of playing it",
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

    if args.out_file:
        with sf.SoundFile(
            args.out_file,
            "w",
            channels=1,
            samplerate=SAMPLERATE,
        ) as outfile:
            for i, (gs, ps, audio) in enumerate(generator):
                print(i)  # i: index
                print(gs)  # gs: graphemes/text
                print(ps)  # ps: phonemes
                outfile.write(audio)  # concat all audio fragments to one file
        return

    for i, (gs, ps, audio) in enumerate(generator):
        print(i)  # i: index
        print(gs)  # gs: graphemes/text
        print(ps)  # ps: phonemes
        sd.play(audio, SAMPLERATE)
        sd.wait()


if __name__ == "__main__":
    with suppress(KeyboardInterrupt):
        main()
