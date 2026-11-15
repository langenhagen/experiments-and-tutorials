#!/usr/bin/env python3
"""Speak a text file with Qwen3-TTS and write the audio to a file.

Offers the same settings as gradio-qwen3-tts-hello.py. Each line of the text
is spoken as its own piece, so put one sentence per line. The output format
follows the extension of --out-file, for example .mp3 or .wav.

The voice comes from exactly one of these, which is required:
- --voice NAME: the name of a saved voice clone, i.e. a folder name in
  voices/ such as andreas-de, not a file path; save one with the Gradio app's
  "Save voice" button
- --ref-audio FILE, optionally with --ref-text: clone the voice of a recording
- --speaker NAME, optionally with --instruct: a preset speaker and its style
- --voice-design DESCRIPTION: a new voice described in words

Usage:

    qwen3-speak.py FILE --voice andreas-de --language german
    qwen3-speak.py FILE --speaker ryan --instruct "Calm audiobook narration"
    qwen3-speak.py FILE --ref-audio me.wav --ref-text "What I say in me.wav"
    qwen3-speak.py FILE --voice-design "A deep, calm male voice" --out-file x.wav
    qwen3-speak.py --help
"""

import argparse
import time
from pathlib import Path

import numpy as np
import soundfile as sf

import qwen3_backend


def parse_args() -> argparse.Namespace:
    parser = argparse.ArgumentParser(
        description=__doc__, formatter_class=argparse.RawDescriptionHelpFormatter
    )
    parser.add_argument("file", type=Path, help="text file, one sentence per line")
    parser.add_argument(
        "--out-file",
        type=Path,
        help="audio file to write; default: FILE with the extension .mp3",
    )

    voice = parser.add_mutually_exclusive_group(required=True)
    voice.add_argument(
        "--voice",
        help="name of a saved voice clone, i.e. a folder name in voices/ such as "
        "andreas-de; not a file path, use --ref-audio for that",
    )
    voice.add_argument("--ref-audio", help="recording to clone the voice from")
    voice.add_argument(
        "--speaker",
        choices=qwen3_backend.SPEAKERS.values(),
        help="preset speaker of the CustomVoice model",
    )
    voice.add_argument(
        "--voice-design", help="description of a new voice, e.g. 'A deep, calm voice'"
    )
    parser.add_argument("--ref-text", help="transcript of --ref-audio")
    parser.add_argument("--instruct", help="style instruction for --speaker")

    parser.add_argument(
        "--language",
        default="auto",
        choices=qwen3_backend.LANGUAGES,
        help="auto detects the language from the text (default: auto)",
    )
    parser.add_argument(
        "--precision",
        default=qwen3_backend.PRECISIONS[0],
        choices=qwen3_backend.PRECISIONS,
        help="lower bits are smaller and faster; Mac only (default: bf16)",
    )
    parser.add_argument(
        "--temperature",
        type=float,
        default=0.9,
        help="lower is steadier, higher livelier (default: 0.9)",
    )
    parser.add_argument(
        "--top-k", type=int, default=50, help="candidate sounds per step (default: 50)"
    )
    parser.add_argument(
        "--top-p",
        type=float,
        default=1.0,
        help="share of likely sounds to pick from (default: 1.0)",
    )
    parser.add_argument(
        "--repetition-penalty",
        type=float,
        default=1.05,
        help="discourages repeated sounds (default: 1.05)",
    )
    parser.add_argument(
        "--max-tokens",
        type=int,
        default=2048,
        help="limit per line, 12.5 per second of audio (default: 2048)",
    )
    parser.add_argument(
        "--seed", type=int, default=0, help="random seed; 0 for random (default: 0)"
    )
    return parser.parse_args()


def main() -> None:
    args = parse_args()

    if args.ref_audio:
        kind, ref_audio, ref_text = "Base", args.ref_audio, args.ref_text
    elif args.speaker:
        kind, ref_audio, ref_text = "CustomVoice", None, None
    elif args.voice_design:
        kind, ref_audio, ref_text = "VoiceDesign", None, None
    else:
        kind = "Base"
        ref_audio, ref_text = qwen3_backend.load_saved_voice(args.voice)

    if args.seed != 0:
        qwen3_backend.seed(args.seed)

    lines = [
        line.strip() for line in args.file.read_text().splitlines() if line.strip()
    ]
    out_file = args.out_file or args.file.with_suffix(".mp3")

    pieces = []
    start = time.monotonic()
    for index, line in enumerate(lines, start=1):
        audio, sample_rate = qwen3_backend.generate(
            kind,
            line,
            args.language,
            precision=args.precision,
            speaker=args.speaker,
            instruct=args.voice_design or args.instruct,
            ref_audio=ref_audio,
            ref_text=ref_text,
            temperature=args.temperature,
            top_k=args.top_k,
            top_p=args.top_p,
            repetition_penalty=args.repetition_penalty,
            max_new_tokens=args.max_tokens,
        )
        pieces.append(audio)
        elapsed = time.monotonic() - start
        print(f"[{index}/{len(lines)}] {elapsed:.0f} s: {line[:60]}", flush=True)

    sf.write(out_file, np.concatenate(pieces), sample_rate)
    print(f"Wrote {out_file}")


if __name__ == "__main__":
    main()
