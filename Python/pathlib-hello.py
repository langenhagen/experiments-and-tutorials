#!/usr/bin/env python3
"""Tests and experiments with pathlib."""
import pathlib

print('---1---')
my_path = pathlib.Path.cwd()  # yields tuple of the form: ('/', 'home', 'barn', 'Dev', 'experiments-and-tutorials', 'Python')
print(my_path)
print(my_path.parts)
print(f"is_dir: {my_path.is_dir()}")  # is_dir() and is_file() can both be false at the same time, e.g. for broken symlinks
print(f"is_file: {my_path.is_file()}")

print('---2---')
path = pathlib.Path('my/directory/structure/file.txt')  # = ('my', 'directory', 'structure', 'file.txt')
print(path)
print(path.parts)

print('---3---')
windows_path = pathlib.Path(r'C:\Users\gahjelle\realpython\file.txt')  # might still suck on Unix
print(windows_path)
print(windows_path.parts)

print('---4---')
path = pathlib.Path.home() / 'my' / 'directory' / 'file.txt'  # use the slash operator to compose paths
print(path)
print(path.parts)

print('---5---')
path = pathlib.Path('.')
print(path)
print(path.parts)
absolute_path = path.absolute()
print(absolute_path)
print(absolute_path.parts)
