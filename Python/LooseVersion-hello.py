#!/usr/bin/env python3
"""
Showcase `distutils.LooseVersion()`.

Note:
DeprecationWarning: distutils Version classes are deprecated. Use packaging.version instead.
"""

from distutils.version import LooseVersion

print ("=== 1 > greater than ===")

v1 = "1.0.0"
v2 = "1.0.1"

lv1 = LooseVersion(v1)
lv2 = LooseVersion(v2)

print(f"{lv1=}")
print(f"{lv2=}")

print(f"{lv1>lv1=}")
print(f"{lv1>lv2=}")
print(f"{lv2>lv1=}")
