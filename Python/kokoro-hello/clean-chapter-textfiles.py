#!/usr/bin/env python3
"""Clean chapter_NN.txt files by removing PDF conversion leftovers.

Reads every chapter_NN.txt file from --text-dir (default: text), drops
standalone page-number lines, reflows hard-wrapped lines into real
paragraphs, collapses excess whitespace, and writes the result to
--out-dir (default: text-clean) using the same filenames. Source files
are never modified.

The source files put a blank line after every visual line, so the
original paragraph boundaries are lost. They are reconstructed
heuristically: a line closes a paragraph when it ends in terminal
punctuation and is clearly shorter than the text column (ragged final
line, ignoring single-letter initials); a short all-caps line followed
by another uppercase line becomes a standalone heading paragraph.
Everything else is joined with single spaces.

This is the preparation step for create-audiobook-from-textfiles.py,
which expects already-cleaned files in its default --text-dir.

Usage:
    uv run clean-chapter-textfiles.py
"""

import argparse
import re
from pathlib import Path

CHAPTER_PATTERN = re.compile(r"^chapter_(\d+)\.txt$")
TERMINAL_END = re.compile(r"[.!?][\"'\u2019\u201d)\]]*$")
INITIAL_END = re.compile(r"\b[A-Z]\.$")


def parse_args() -> argparse.Namespace:
    """Parse command-line arguments."""
    parser = argparse.ArgumentParser(
        description=(
            "Join hard-wrapped lines into paragraphs, drop page-number "
            "lines, and collapse whitespace in chapter_NN.txt files."
        ),
    )
    parser.add_argument(
        "--text-dir",
        type=Path,
        default=Path("text"),
        help="Directory containing the raw chapter_NN.txt files (default: text)",
    )
    parser.add_argument(
        "--out-dir",
        type=Path,
        default=Path("text-clean"),
        help="Directory for the cleaned files, same filenames (default: text-clean)",
    )
    return parser.parse_args()


def content_lines(path: Path) -> list[str]:
    """Return stripped lines, dropping blanks and page-number lines."""
    lines = []
    for raw in path.read_text(encoding="utf-8").splitlines():
        stripped = raw.strip()
        if not stripped or stripped.isdigit():
            continue
        lines.append(stripped)
    return lines


def column_width(lines: list[str]) -> float:
    """Median character count of the given lines (the text column width)."""
    lengths = sorted(len(line) for line in lines)
    middle = len(lengths) // 2
    if len(lengths) % 2:
        return float(lengths[middle])
    return (lengths[middle - 1] + lengths[middle]) / 2


def is_heading(line: str, width: float, next_line: str | None) -> bool:
    """Short all-caps lines followed by an uppercase line are headings.

    The lookahead keeps small-caps paragraph lead-ins ("EVERYONE HAS
    something interesting ...") glued to their lowercase continuation.
    """
    return (
        len(line) < 0.5 * width
        and line == line.upper()
        and any(char.isalpha() for char in line)
        and next_line is not None
        and next_line[:1].isupper()
    )


def closes_paragraph(line: str, width: float) -> bool:
    """Make a ragged line ending in terminal punctuation close the paragraph.

    Lines ending in a single-letter initial ("John F.") stay open.
    """
    return (
        len(line) < 0.8 * width
        and TERMINAL_END.search(line) is not None
        and INITIAL_END.search(line) is None
    )


def read_paragraphs(path: Path) -> list[str]:
    """Reflow hard-wrapped lines into paragraphs via column-width heuristics."""
    lines = content_lines(path)
    if not lines:
        return []
    width = column_width(lines)
    paragraphs: list[str] = []
    current = ""
    for position, line in enumerate(lines):
        next_line = lines[position + 1] if position + 1 < len(lines) else None
        if is_heading(line, width, next_line):
            if current:
                paragraphs.append(current)
                current = ""
            paragraphs.append(line)
            continue
        current = f"{current} {line}" if current else line
        if closes_paragraph(line, width):
            paragraphs.append(current)
            current = ""
    if current:
        paragraphs.append(current)
    return paragraphs


def main() -> None:
    """Entry point."""
    args = parse_args()
    paths = sorted(
        path
        for path in args.text_dir.glob("chapter_*.txt")
        if CHAPTER_PATTERN.match(path.name)
    )
    if not paths:
        message = f"No chapter_*.txt files found in {args.text_dir}."
        raise SystemExit(message)

    args.out_dir.mkdir(parents=True, exist_ok=True)
    raw_total = 0
    clean_total = 0
    for path in paths:
        paragraphs = read_paragraphs(path)
        cleaned = "\n\n".join(paragraphs) + "\n"
        (args.out_dir / path.name).write_text(cleaned, encoding="utf-8")
        raw_total += path.stat().st_size
        clean_total += len(cleaned.encode("utf-8"))
        print(f"{path.name}: {len(paragraphs)} paragraph(s)")
    print(
        f"\nWrote {len(paths)} file(s) to {args.out_dir} "
        f"({raw_total} -> {clean_total} bytes).",
    )


if __name__ == "__main__":
    main()
