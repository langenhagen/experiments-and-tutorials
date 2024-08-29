#!/usr/bin/env python3
"""Tests and experiments with pathlib."""
from pathlib import Path

print("--- 1  CWD ---\n")
# yields tuple of the form: ('/', 'home', 'barn', 'Dev', 'experiments-and-tutorials', 'Python')
my_path = Path.cwd()
print(my_path)
print(my_path.parts)
# is_dir() and is_file() can both be false at the same time, e.g. for broken symlinks
print(f"is_dir: {my_path.is_dir()}")
print(f"is_file: {my_path.is_file()}")

print("\n--- 2 random potentially nonexistent file ---\n")
# = ('my', 'directory', 'structure', 'file.txt')
path = Path("my/directory/structure/file.txt")
print(path)
print(path.parts)

print("\n--- 3 windows path ---\n")
# might still suck on Unix
windows_path = Path(r"C:\Users\gahjelle\realpython\file.txt")
print(windows_path)
print(windows_path.parts)

print("\n--- 4 path concatenation via slashes `/` ---\n")
# use the slash operator to compose paths
path = Path.home() / "my" / "directory" / "file.txt"
print(path)
print(path.parts)

print("\n--- 5 The current path `.` is CWD ---\n")
path = Path(".")  # basically CWD
print(path)
print(path.parts)
absolute_path = path.absolute()
print(absolute_path)
print(absolute_path.parts)

print("\n--- 6 the current file's path ---\n")
print(f"{__file__=}")
path = Path(__file__).resolve()
print(f"{path=}")
