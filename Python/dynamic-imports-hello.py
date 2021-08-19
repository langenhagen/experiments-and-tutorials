#!/usr/bin/env python3
"""Dynamically import the given module."""
import importlib
import sys

print(f"len(sys.argv) = {len(sys.argv)}")
print(f"sys.argv = {sys.argv}")

module_path = sys.argv[1]
print(f"Given Module path: {module_path}")

# Import module via module path specificaion, path.to.my.module
# my_module = importlib.import_module(module_path)

# Import module via file location
import importlib.util

module_spec = importlib.util.spec_from_file_location("my.module.name", module_path)
my_module = importlib.util.module_from_spec(module_spec)
module_spec.loader.exec_module(my_module)

print("***Module loaded***")

print()
print(f"dir({my_module})")
print(dir(my_module))

print()
print(f"{my_module}.__doc__")
print(my_module.__doc__)

# should work via: python hello-dynamic-imports.py treemaps-hello/treemap.py
# r = my_module.Rectangle(1,2,3,4)
# print(r)
