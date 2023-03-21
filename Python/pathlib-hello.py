#!/usr/bin/env python3
"""Tests and experiments with pathlib."""
from pathlib import Path

print("--- 1  CWD ---")
# yields tuple of the form: ('/', 'home', 'barn', 'Dev', 'experiments-and-tutorials', 'Python')
my_path = Path.cwd()
print(my_path)
print(my_path.parts)
# is_dir() and is_file() can both be false at the same time, e.g. for broken symlinks
print(f"is_dir: {my_path.is_dir()}")
print(f"is_file: {my_path.is_file()}")

print("--- 2 random potentially nonexistent file ---")
# = ('my', 'directory', 'structure', 'file.txt')
path = Path("my/directory/structure/file.txt")
print(path)
print(path.parts)

print("--- 3 windows path ---")
# might still suck on Unix
windows_path = Path(r"C:\Users\gahjelle\realpython\file.txt")
print(windows_path)
print(windows_path.parts)

print("--- 4 path concatenation via slashes `/` ---")
# use the slash operator to compose paths
path = Path.home() / "my" / "directory" / "file.txt"
print(path)
print(path.parts)

print("--- 5 The current path `.` is CWD ---")
path = Path(".")  # basically CWD
print(path)
print(path.parts)
absolute_path = path.absolute()
print(absolute_path)
print(absolute_path.parts)

print("--- 6 the current file's path ---")
print(f"{__file__=}")
path = Path(__file__).resolve()
print(f"{path=}")
