#!/usr/bin/env python3
"""Showcase the package 'inspect'
and how it can be used in conjunction with exec()
to redefine source code at run time.

author: andreasl
version: 19-07-02
"""
import inspect

def foo(s: str, i=0):

    print(f"{s} : {i}")



foo('hey there', 42)

print('---')
print(inspect.signature(foo))

print('---')
foo_source = inspect.getsource(foo);  # only possible when not called exec on it(), i.e. only once possible
print(f"foo_source:\n{foo_source}\n[o]")

modified_foo_source = foo_source.replace('{i}', 'cheap trick!');
exec(modified_foo_source)  # redefine foo()
foo('hey there', 43)

# should raise OSError('could not get source code')
# foo_source = inspect.getsource(foo);  # only possible when not called exec on it(), i.e. only once possible
modified_foo_source = modified_foo_source.replace('{s}', 'very!');
exec(modified_foo_source)  # redefine foo()
foo('hey there', 44)
