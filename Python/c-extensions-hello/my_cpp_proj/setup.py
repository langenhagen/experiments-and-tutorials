"""
apparently necessary for C/C++ extensions

author: andreasl
see: https://www.youtube.com/watch?v=a65JdvOaygM&ab_channel=DrapsTV
"""

from distutils.core import Extension, setup

module = Extension("myModule", sources=["fibonaccimodule.cpp"])

setup(
    name="MyPackageName",
    version="1.0",
    description="Yeah, this a package for myModule",
    ext_modules=[module],
)
