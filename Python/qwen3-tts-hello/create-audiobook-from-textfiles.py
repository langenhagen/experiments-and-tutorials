#!/usr/bin/env python3
"""Create per-chapter WAV audiobook files from a folder of text files.

Reads every chapter_NN.txt file from the text directory (default:
./text/, override with --text-dir), drops page-number lines, splits
each chapter into chunks of at most --max-chars characters with
chunking.py, synthesizes every chunk with Qwen3-TTS, and
stitches the chunk audio into one WAV file per chapter.

Runs on Linux with an NVIDIA GPU or on an Apple Silicon Mac; see
qwen3_backend.py. The voice comes from a preset speaker (default), a saved
voice clone (--voice), a cloned reference recording (--ref-audio), or a voice
description (--voice-design).

Paths created:
    audio/chapter_NN/NNNN.wav    intermediate chunk audio (kept so bad
                                 chunks can be regenerated individually)
    audio/chapter_NN/manifest.json
    audiobook/chapter_NN.wav     final stitched chapter file

Usage:
    uv run create-audiobook-from-textfiles.py                    # whole book
    uv run create-audiobook-from-textfiles.py --only-chapters 8,9
    uv run create-audiobook-from-textfiles.py --language german --voice andreas-de
    uv run create-audiobook-from-textfiles.py --language german \
        --ref-audio my-voice.wav --ref-text "Transcript of my-voice.wav"

Synthesis is resumable: existing chunk WAVs are skipped, so an
interrupted run can simply be restarted.
"""

import argparse
import json
import re
import time
from pathlib import Path
from typing import NamedTuple

import numpy as np
import soundfile as sf

import chunking
import qwen3_backend

MIN_CHUNK_SEC_PER_WORD = 0.27
MAX_CHUNK_SEC_PER_WORD = 0.75
MANIFEST_NAME = "manifest.json"
CHAPTER_PATTERN = re.compile(r"^chapter_(\d+)\.txt$")


class Voice(NamedTuple):
    """Model kind and voice settings for synthesis; see qwen3_backend.generate()."""

    kind: str
    precision: str
    language: str
    speaker: str
    instruct: str | None
    ref_audio: str | None
    ref_text: str | None
    temperature: float
    max_chars: int


def parse_args() -> argparse.Namespace:
    """Parse command-line arguments."""
    parser = argparse.ArgumentParser(
        description=(
            "Synthesize an audiobook from chapter_NN.txt files using "
            "Qwen3-TTS and write one stitched WAV file per chapter."
        ),
    )
    parser.add_argument(
        "--text-dir",
        type=Path,
        default=Path("text"),
        help="Directory containing chapter_NN.txt input files (default: text)",
    )
    parser.add_argument(
        "--audio-dir",
        type=Path,
        default=Path("audio"),
        help="Directory for intermediate per-chunk WAV files (default: audio)",
    )
    parser.add_argument(
        "--out-dir",
        type=Path,
        default=Path("audiobook"),
        help="Directory for the final stitched chapter WAV files (default: audiobook)",
    )
    parser.add_argument(
        "--speaker",
        default="ryan",
        choices=qwen3_backend.SPEAKERS.values(),
        help="Preset speaker of the CustomVoice model (default: ryan)",
    )
    parser.add_argument(
        "--language",
        default="english",
        choices=qwen3_backend.LANGUAGES,
        help="Language of the texts (default: english)",
    )
    parser.add_argument(
        "--instruct",
        default=None,
        help=(
            "Optional style instruction for the preset speaker, "
            "for example 'Read like an audiobook narrator'. Default: none"
        ),
    )
    parser.add_argument(
        "--voice",
        default=None,
        help=(
            "Name of a saved voice clone, i.e. a folder name in voices/ such "
            "as andreas-de; not a file path, use --ref-audio for that. Uses "
            "the Base model instead of a preset speaker. Default: none"
        ),
    )
    parser.add_argument(
        "--ref-audio",
        default=None,
        help=(
            "Reference recording to clone the voice from, a few seconds to "
            "about 30 s of clean speech. Uses the Base model instead of a "
            "preset speaker. Default: none"
        ),
    )
    parser.add_argument(
        "--ref-text",
        default=None,
        help=(
            "Transcript of --ref-audio; makes the clone closer to the "
            "original. Default: none"
        ),
    )
    parser.add_argument(
        "--voice-design",
        default=None,
        help=(
            "Description of a new voice, for example 'A deep, calm male "
            "voice'. Uses the VoiceDesign model instead of a preset "
            "speaker. Default: none"
        ),
    )
    parser.add_argument(
        "--precision",
        default="bf16",
        choices=qwen3_backend.PRECISIONS,
        help=(
            "Model precision; 8, 6, 5, and 4-bit are smaller and faster "
            "and only available on the Mac. Default: bf16"
        ),
    )
    parser.add_argument(
        "--temperature",
        type=float,
        default=0.9,
        help=(
            "Randomness of the delivery. Lower is steadier but can sound "
            "monotone, and very low values can make a chunk run away into "
            "silence; higher is livelier but slips become likelier. "
            "Default: 0.9"
        ),
    )
    parser.add_argument(
        "--max-chars",
        type=int,
        default=chunking.DEFAULT_MAX_CHARS,
        help=(
            "Maximum characters per synthesis call. The TTS model degrades "
            "on long inputs (voice drift, repeated words), so each chapter "
            "is split by chunking.py into chunks up to this size - "
            "roughly 3-6 sentences or half a minute of audio per chunk. "
            "Lower values give a more stable voice but slower runtime and "
            "more stitch points; higher values reduce seams but risk "
            "mid-chunk artifacts. Default: %(default)s"
        ),
    )
    parser.add_argument(
        "--gap",
        type=float,
        default=0.25,
        help=(
            "Seconds of silence inserted between chunk WAVs when stitching "
            "a chapter, so consecutive chunks do not run together. "
            "Default: 0.25"
        ),
    )
    parser.add_argument(
        "--only-chapters",
        default=None,
        help=(
            "Comma-separated chapter numbers to process, matching the NN in "
            "chapter_NN.txt filenames. Example: '--only-chapters 1,8' runs "
            "only those two chapters - intended for test runs before "
            "committing to the full book. Default: every chapter found in "
            "--text-dir"
        ),
    )
    return parser.parse_args()


def read_text(path: Path) -> str:
    """Return the text of a chapter file without its page-number lines."""
    lines = path.read_text(encoding="utf-8").splitlines()
    return "\n".join(line for line in lines if not line.strip().isdigit())


def format_duration(seconds: float) -> str:
    """Format seconds as H:MM:SS or M:SS."""
    seconds = round(seconds)
    hours, remainder = divmod(seconds, 3600)
    minutes, secs = divmod(remainder, 60)
    if hours:
        return f"{hours}:{minutes:02d}:{secs:02d}"
    return f"{minutes}:{secs:02d}"


def ensure_manifest(chunks: list[str], chunk_dir: Path) -> None:
    """Store the chunk list, failing loudly if an existing manifest mismatches."""
    manifest_path = chunk_dir / MANIFEST_NAME
    if manifest_path.exists():
        stored = json.loads(manifest_path.read_text(encoding="utf-8"))
        if stored.get("chunks") != chunks:
            message = (
                f"{manifest_path} does not match the current chunking of its "
                f"source text. Delete {chunk_dir} to regenerate this chapter."
            )
            raise SystemExit(message)
    else:
        manifest_path.write_text(
            json.dumps({"chunks": chunks}, ensure_ascii=False),
            encoding="utf-8",
        )


def progress_line(
    index: int,
    total: int,
    name: str,
    generated: int,
    gen_seconds: float,
) -> str:
    """Build the per-chunk progress line including a naive ETA."""
    line = f"  [{index}/{total}] {name}"
    if generated:
        avg = gen_seconds / generated
        remaining = (total - index) * avg
        line += f" | ETA {format_duration(remaining)}"
    return line


def synthesize_chapter(
    chunks: list[str],
    chunk_dir: Path,
    voice: Voice,
) -> list[Path]:
    """Generate one WAV per chunk, skipping files that already exist."""
    chunk_dir.mkdir(parents=True, exist_ok=True)
    ensure_manifest(chunks, chunk_dir)

    paths: list[Path] = []
    generated = 0
    gen_seconds = 0.0
    for index, text in enumerate(chunks, start=1):
        path = chunk_dir / f"{index:04d}.wav"
        if not path.exists():
            tick = time.monotonic()
            audio, sample_rate = qwen3_backend.generate(
                voice.kind,
                text,
                voice.language,
                precision=voice.precision,
                speaker=voice.speaker,
                instruct=voice.instruct,
                ref_audio=voice.ref_audio,
                ref_text=voice.ref_text,
                temperature=voice.temperature,
                max_chars=voice.max_chars,
            )
            gen_seconds += time.monotonic() - tick
            generated += 1
            sf.write(path, audio, sample_rate)
        paths.append(path)
        print(
            progress_line(index, len(chunks), path.name, generated, gen_seconds),
            flush=True,
        )
    return paths


def report_suspect_chunks(paths: list[Path], chunks: list[str]) -> None:
    """Warn about chunks whose duration-per-word looks abnormal."""
    suspects = []
    for path, text in zip(paths, chunks, strict=True):
        info = sf.info(path)
        words = max(1, len(text.split()))
        sec_per_word = info.frames / info.samplerate / words
        if (
            sec_per_word < MIN_CHUNK_SEC_PER_WORD
            or sec_per_word > MAX_CHUNK_SEC_PER_WORD
        ):
            suspects.append((path.name, sec_per_word))
    if suspects:
        print("  QC warnings (listen before accepting):")
        for name, sec_per_word in suspects:
            print(f"    {name}: {sec_per_word:.2f} s/word")


def stitch_chapter(paths: list[Path], out_path: Path, gap_seconds: float) -> None:
    """Stream chunk WAVs into a single chapter WAV with silence gaps."""
    out_path.parent.mkdir(parents=True, exist_ok=True)
    samplerate = sf.info(paths[0]).samplerate
    silence = np.zeros(int(gap_seconds * samplerate), dtype=np.float32)
    with sf.SoundFile(
        out_path,
        "w",
        samplerate=samplerate,
        channels=1,
        subtype="PCM_16",
    ) as out:
        for position, path in enumerate(paths):
            data, rate = sf.read(path, dtype="float32")
            if rate != samplerate:
                message = f"Sample rate mismatch in {path}: {rate} != {samplerate}"
                raise SystemExit(message)
            out.write(data)
            if position < len(paths) - 1:
                out.write(silence)


def discover_chapters(
    text_dir: Path,
    only: set[int] | None,
) -> list[tuple[int, Path]]:
    """Return sorted (number, path) pairs for the chapters to process."""
    found: dict[int, Path] = {}
    for path in sorted(text_dir.glob("chapter_*.txt")):
        match = CHAPTER_PATTERN.match(path.name)
        if match:
            found[int(match.group(1))] = path
    if not found:
        message = f"No chapter_*.txt files found in {text_dir}."
        raise SystemExit(message)
    if only is None:
        return sorted(found.items())
    missing = sorted(only - found.keys())
    if missing:
        names = ", ".join(str(number) for number in missing)
        message = f"No text file found for chapter(s): {names}"
        raise SystemExit(message)
    return [(number, found[number]) for number in sorted(only)]


def main() -> None:
    """Entry point."""
    args = parse_args()

    only: set[int] | None = None
    if args.only_chapters:
        try:
            only = {int(part) for part in args.only_chapters.split(",")}
        except ValueError:
            message = (
                f"--only-chapters expects comma-separated integers, "
                f"got: {args.only_chapters}"
            )
            raise SystemExit(message) from None

    if sum(map(bool, [args.voice, args.ref_audio, args.voice_design])) > 1:
        raise SystemExit("--voice, --ref-audio, and --voice-design exclude each other.")
    if args.voice:
        args.ref_audio, args.ref_text = qwen3_backend.load_saved_voice(args.voice)
        kind, voice_label = "Base", f"saved voice '{args.voice}'"
    elif args.ref_audio:
        kind, voice_label = "Base", f"voice cloned from {args.ref_audio}"
    elif args.voice_design:
        kind, voice_label = "VoiceDesign", f"designed voice '{args.voice_design}'"
    else:
        kind, voice_label = "CustomVoice", f"speaker '{args.speaker}'"

    chapters = discover_chapters(args.text_dir, only)
    print(f"Processing {len(chapters)} chapter(s) with {voice_label}.")

    voice = Voice(
        kind=kind,
        precision=args.precision,
        language=args.language,
        speaker=args.speaker,
        instruct=args.voice_design or args.instruct,
        ref_audio=args.ref_audio,
        ref_text=args.ref_text,
        temperature=args.temperature,
        max_chars=args.max_chars,
    )

    total_start = time.monotonic()
    for _number, text_path in chapters:
        label = text_path.stem
        print(f"\n== {label} ({text_path}) ==")
        chunks = chunking.split_chunks(read_text(text_path), args.max_chars)
        if not chunks:
            print("  Skipped: no usable text.")
            continue
        print(f"  {len(chunks)} chunk(s)")
        chunk_paths = synthesize_chapter(
            chunks,
            args.audio_dir / label,
            voice,
        )
        report_suspect_chunks(chunk_paths, chunks)
        out_path = args.out_dir / f"{label}.wav"
        stitch_chapter(chunk_paths, out_path, args.gap)
        duration = sf.info(out_path).duration
        print(f"  Wrote {out_path} ({format_duration(duration)})")

    print(f"\nDone in {format_duration(time.monotonic() - total_start)}.")


if __name__ == "__main__":
    main()
