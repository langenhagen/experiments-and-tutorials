#!/usr/bin/env python3
"""Scan a folder for python files and create a `Mermaid` class diagram.

Limitations:
- no fully qualified names; undefined behavior when having multiple classes with
  the same name.

Use like:

    python scan_classes.py ~/micropsi/micropsi2/ > diagram.md

    mmdc --width 10000 --height 10000 -i diagram.md -o output.png

TODO:
- nest modules
- have fully qualified names
"""
import ast
import re
import sys
from dataclasses import dataclass, field
from pathlib import Path
from typing import Generator


def walk(
    path: Path,
    file_regex: str | None = None,
    ignore_regex: str | None = None,
) -> Generator[Path, None, None]:
    """Recursively walk a directory in a depth-first manner.

    If given, files should match the given `file_regex`.

    Also, if a file or folder matches the given regex, ignore this item and do
    not descend further into this direction.
    """
    for p in path.iterdir():
        resolved = p.resolve()
        if ignore_regex and re.match(ignore_regex, resolved.name):
            continue
        elif resolved.is_dir():
            yield from walk(resolved, file_regex, ignore_regex)
        elif file_regex is None or re.match(file_regex, resolved.name):
            yield resolved


@dataclass
class Class:
    """Represents a class and its direct superclasses in the right order."""

    name: str
    superclasses: list[str] = field(default_factory=list)


@dataclass
class Module:
    """Represents a Python module."""

    file: Path
    classes: list[Class] = field(default_factory=list)


def extract_classes(file: Path) -> Module:
    """Extract Python classes and their superclasses from the given Python file."""
    with file.open() as f:
        tree = ast.parse(f.read())

    result = Module(file=file)

    for node in ast.walk(tree):
        if isinstance(node, ast.ClassDef):
            supers = []
            for base in node.bases:
                if isinstance(base, ast.Name):
                    supers.append(base.id)
                elif isinstance(base, ast.Attribute):
                    # IntEnum and the likes
                    supers.append(base.attr)
                elif isinstance(base, ast.Starred):
                    # arcane stuff like class DummyWA(*supers):
                    pass
                else:
                    msg = f"That should never happen {type(base)}"
                    raise TypeError(msg)

            result.classes.append(Class(name=node.name, superclasses=supers))

    return result


def n_to_alpha(n: int) -> str:
    """For the given number, return an alpha-string.

    The string should look like so that:
    1 maps to "A"
    2 maps to "B"
    ...
    27 maps to "AA"
    28 maps to "AB"
    and so on.
    """
    alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    result = []

    while n > 0:
        n, remainder = divmod(n - 1, 26)
        result.append(alphabet[remainder])

    return "".join(result[::-1])


def modulize(path: Path) -> str:
    """Convert given path to a Python module string.

    E.g., `my/module.py` should become `my.module`.
    """
    return str(path).replace("/", ".").removesuffix(".py")


def main(directory: Path) -> int:
    """Scan the given directory for python classes and their inheritance-relation."""
    generator = walk(directory, file_regex=r".*\.py$", ignore_regex=r"\..+|__pycache__")
    modules: list[Module] = [extract_classes(file) for file in generator]

    print("```mermaid")
    print("graph RL;")
    for i, m in enumerate(modules, start=1):
        if len(m.classes) == 0:
            continue

        subgraph_id = n_to_alpha(i)
        print(f'subgraph {subgraph_id}["{modulize(m.file)}"]')
        for c in m.classes:
            print(f"  {c.name}")

        print("end")

        for c in m.classes:
            for j, s in enumerate(c.superclasses, start=2):
                if s == "object":
                    continue
                print(f"  {c.name} {'-'*j}> {s}")

    print("```")
    return 0


if __name__ == "__main__":
    directory = Path("." if len(sys.argv) == 1 else sys.argv[1]).resolve()
    sys.exit(main(directory))
