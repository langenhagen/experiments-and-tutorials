#!/usr/bin/env python3
"""Create per-chapter WAV audiobook files from a folder of text files.

Reads every chapter_NN.txt file from the text directory (default:
./text-clean/, as produced by clean-chapter-textfiles.py), splits each
chapter at sentence boundaries into chunks of at most --max-chars
characters, synthesizes every chunk with Kokoro TTS, and stitches the
chunk audio into one WAV file per chapter.

Paths created:
    audio/chapter_NN/NNNN.wav    intermediate chunk audio (kept so bad
                                 chunks can be regenerated individually)
    audio/chapter_NN/manifest.json
    audiobook/chapter_NN.wav     final stitched chapter file

Usage:
    uv run create-audiobook-from-textfiles.py                    # whole book
    uv run create-audiobook-from-textfiles.py --only-chapters 8,9

Synthesis is resumable: existing chunk WAVs are skipped, so an
interrupted run can simply be restarted.
"""

import argparse
import json
import re
import time
from pathlib import Path

import numpy as np
import soundfile as sf
from kokoro import KPipeline

SAMPLERATE = 24000
MIN_CHUNK_SEC_PER_WORD = 0.27
MAX_CHUNK_SEC_PER_WORD = 0.75
MANIFEST_NAME = "manifest.json"
CHAPTER_PATTERN = re.compile(r"^chapter_(\d+)\.txt$")
SENTENCE_END = re.compile(r"[.!?]+[\"'\u2019\u201d)\]]*")


def parse_args() -> argparse.Namespace:
    """Parse command-line arguments."""
    parser = argparse.ArgumentParser(
        description=(
            "Synthesize an audiobook from chapter_NN.txt files using "
            "Kokoro TTS and write one stitched WAV file per chapter."
        ),
    )
    parser.add_argument(
        "--text-dir",
        type=Path,
        default=Path("text-clean"),
        help="Directory containing chapter_NN.txt input files (default: text-clean)",
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
        "--voice",
        default="af_heart",
        help=(
            "Kokoro voice name; the first letter selects the language "
            "('a' American English, 'b' British English), e.g. af_heart, "
            "am_michael, bf_emma, bm_george. Default: af_heart"
        ),
    )
    parser.add_argument(
        "--speed",
        type=float,
        default=1.0,
        help=(
            "Speech speed multiplier passed to Kokoro. Values above 1 "
            "read faster, below 1 slower. Affects the QC duration-per-"
            "word thresholds. Default: 1.0"
        ),
    )
    parser.add_argument(
        "--max-chars",
        type=int,
        default=300,
        help=(
            "Maximum characters per synthesis call. Long inputs degrade "
            "quality and complicate targeted repair, so each chapter is "
            "split at sentence boundaries into chunks up to this size - "
            "roughly 2-4 sentences or 10-20 seconds of audio per chunk. "
            "Default: 300"
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


def split_sentences(text: str) -> list[str]:
    """Split text at sentence-ending punctuation, keeping the punctuation."""
    sentences = []
    start = 0
    for match in SENTENCE_END.finditer(text):
        piece = text[start : match.end()].strip()
        if piece:
            sentences.append(piece)
        start = match.end()
    tail = text[start:].strip()
    if tail:
        sentences.append(tail)
    return sentences


def hard_split(text: str, max_chars: int) -> list[str]:
    """Split an oversized single sentence at word boundaries."""
    if len(text) <= max_chars:
        return [text]
    parts = []
    rest = text
    while len(rest) > max_chars:
        cut = rest.rfind(" ", 0, max_chars)
        if cut <= 0:
            cut = max_chars
        parts.append(rest[:cut].strip())
        rest = rest[cut:].strip()
    if rest:
        parts.append(rest)
    return parts


def build_chunks(paragraphs: list[str], max_chars: int) -> list[str]:
    """Group sentences into chunks of at most max_chars characters.

    Chunks never span paragraphs, so the stitching gap marks paragraph
    boundaries with a pause while sentences inside a chunk stay joined.
    """
    chunks: list[str] = []
    for paragraph in paragraphs:
        current = ""
        for sentence in split_sentences(paragraph):
            if not current:
                current = sentence
            elif len(current) + 1 + len(sentence) <= max_chars:
                current = f"{current} {sentence}"
            else:
                chunks.append(current)
                current = sentence
        if current:
            chunks.append(current)
    return [part for chunk in chunks for part in hard_split(chunk, max_chars)]


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
    pipeline: KPipeline,
    chunks: list[str],
    chunk_dir: Path,
    voice: str,
    speed: float,
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
            segments = [
                result.audio.detach().cpu().numpy()
                for result in pipeline(
                    text,
                    voice=voice,
                    speed=speed,
                    split_pattern=r"\n+",
                )
                if result.audio is not None
            ]
            gen_seconds += time.monotonic() - tick
            generated += 1
            sf.write(path, np.concatenate(segments), SAMPLERATE)
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
        message = (
            f"No chapter_*.txt files found in {text_dir}. Run "
            f"clean-chapter-textfiles.py first or adjust --text-dir."
        )
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

    lang_code = args.voice[0]
    if lang_code not in ("a", "b"):
        message = (
            f"Unsupported voice '{args.voice}': expected a name starting "
            f"with af/am (American English) or bf/bm (British English)."
        )
        raise SystemExit(message)

    chapters = discover_chapters(args.text_dir, only)
    print(f"Processing {len(chapters)} chapter(s) with voice '{args.voice}'.")

    pipeline = KPipeline(lang_code=lang_code)

    total_start = time.monotonic()
    for _number, text_path in chapters:
        label = text_path.stem
        print(f"\n== {label} ({text_path}) ==")
        paragraphs = [
            line
            for line in text_path.read_text(encoding="utf-8").split("\n\n")
            if line.strip()
        ]
        if not paragraphs:
            print("  Skipped: no usable text.")
            continue
        chunks = build_chunks(paragraphs, args.max_chars)
        print(f"  {len(chunks)} chunk(s)")
        chunk_paths = synthesize_chapter(
            pipeline,
            chunks,
            args.audio_dir / label,
            args.voice,
            args.speed,
        )
        report_suspect_chunks(chunk_paths, chunks)
        out_path = args.out_dir / f"{label}.wav"
        stitch_chapter(chunk_paths, out_path, args.gap)
        duration = sf.info(out_path).duration
        print(f"  Wrote {out_path} ({format_duration(duration)})")

    print(f"\nDone in {format_duration(time.monotonic() - total_start)}.")


if __name__ == "__main__":
    main()
