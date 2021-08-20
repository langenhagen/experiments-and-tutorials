"""
Builds the custom cffi module that we need to interface with the C code.

author: andreasl
"""
from setuptools import setup

setup(
    name="my_uuid_cffi_module",
    version="0.1.0",
    py_modules=["my_cffi_module"],
    install_requires=["cffi"],
    setup_requires=["cffi"],
    cffi_modules=["build_cffi_module.py:ffibuilder"],
)
