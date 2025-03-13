#!/usr/bin/env python3
"""
Dynamically import the given module.

should work via:

    python dynamic-imports-hello.py treemaps-hello/treemap.py
    r = my_module.Rectangle(1,2,3,4)
    print(r)
"""

# import importlib
import importlib.util
import sys

print(f"{sys.argv=}")
print(f"{len(sys.argv)=}")
module_path = sys.argv[1]
print(f"Given Module path: {module_path}")

# Import module via module path specificaion, path.to.my.module
# my_module = importlib.import_module(module_path)

# Import module via file location
module_spec = importlib.util.spec_from_file_location("my.module.name", module_path)
my_module = importlib.util.module_from_spec(module_spec)
module_spec.loader.exec_module(my_module)

print("*** Module loaded ***")

print(f"{module_spec=}\n---")
print(f"{dir(my_module)=}\n---")
print(f"my_module.__doc__:\n{my_module.__doc__}---\n")

r = my_module.Rectangle(1, 2, 3, 4)
print(r)
