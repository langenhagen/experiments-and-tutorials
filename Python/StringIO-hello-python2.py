"""
Showcase the allegedly fast and little memory consuming string builder StringIO
from the standard lib.

See:
https://waymoot.org/home/python_string/
"""
from StringIO import StringIO

print('---1---')
io = StringIO()
io.write('hello')
io.write(' dear\n')
io.write('world!')
print(io.getvalue())

print('---2---')
io = StringIO("this will be overwritten")
print(io.getvalue())
io.write(123456)
print(io.getvalue())
