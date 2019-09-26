"""Showcase the python module argparse.

use argparse.ArgumentParser.parse_args() with something specific. if nothing is
given, it uses sys.argv[1:] by default.

See: https://pymotw.com/3/argparse/index.html
"""
import argparse
import pathlib

parser = argparse.ArgumentParser(description='Short sample app')

parser.add_argument(
    '-a',
    action="store_true",
    default=False,
    help='help string for "a"'
)
parser.add_argument(
    '-b',
    action="store",
    dest="b",
    help='help string for "b"'
)
parser.add_argument(
    '-c', '--alternative-C',
    action="store",
    dest="c",
    type=int,
    help='help string for "c"; very long help strings appear to be broken into multiple lines at runtime, but\nignore newlines\n'
)
parser.add_argument(
    '-d',
    action="store",
    dest="d",
    help='help string for "d"'
)
parser.add_argument(
    '--noarg',
    action="store_true",
    default=False,
    help='help string for "noarg"'
)
parser.add_argument(
    '-v', '--version',
    action='version',
    version='%(prog)s 1.0',
    help='show the version Stuff'
)
parser.add_argument(
    'my_optional_positional_arg',
    nargs='?',  # 0 or 1
    default=pathlib.Path.cwd(),
    help='an unnamed optional argument'
)
parser.add_argument(
    'my_mandatory_positional_arg',
    nargs=1,  # 0 or 1
    help='an unnamed mandatory argument'
)

print('---1---')
# namespace = parser.parse_args()  # use parse_args() without params to parse from the command line
namespace = parser.parse_args(['-a', '-bval', '-c', '3', '-d', 'moo', 'miaaauuu'])
print(namespace)
print(f"a: {namespace.a}")
print(f"b: {namespace.b}")
print(f"c: {namespace.d}")
print(f"noarg: {namespace.noarg}")
print(f"my_optional_positional_arg: {namespace.my_optional_positional_arg}")
print(f"my_mandatory_positional_arg: {namespace.my_mandatory_positional_arg}")

print('---2---')
namespace = parser.parse_args(['-a', '--noarg', 'kaaatz'])
print(namespace)
print(f"a: {namespace.a}")
print(f"b: {namespace.b}")
print(f"c: {namespace.d}")
print(f"noarg: {namespace.noarg}")
print(f"my_optional_positional_arg: {namespace.my_optional_positional_arg}")
print(f"my_mandatory_positional_arg: {namespace.my_mandatory_positional_arg}")

print("---3---")
namespace = parser.parse_args(['just one argument works'])
print(namespace)
print(f"a: {namespace.a}")
print(f"b: {namespace.b}")
print(f"c: {namespace.d}")
print(f"noarg: {namespace.noarg}")
print(f"my_optional_positional_arg: {namespace.my_optional_positional_arg}")
print(f"my_mandatory_positional_arg: {namespace.my_mandatory_positional_arg}")

print("---4---")
namespace = parser.parse_args(['optional', 'mandatory'])  # order is important
print(namespace)
print(f"a: {namespace.a}")
print(f"b: {namespace.b}")
print(f"c: {namespace.d}")
print(f"noarg: {namespace.noarg}")
print(f"my_optional_positional_arg: {namespace.my_optional_positional_arg}")
print(f"my_mandatory_positional_arg: {namespace.my_mandatory_positional_arg}")

print("---5---")
# namespace = parser.parse_args([])  # would break sinde my_mandatory_positional_arg is mandatory
# print(namespace)
# print(f"a: {namespace.a}")
# print(f"b: {namespace.b}")
# print(f"c: {namespace.d}")
# print(f"noarg: {namespace.noarg}")
# print(f"my_optional_positional_arg: {namespace.my_optional_positional_arg}")
# print(f"my_mandatory_positional_arg: {namespace.my_mandatory_positional_arg}")

print("---6---")
# namespace = parser.parse_args(['many', 'arguments', 'don\'t', 'due to nargs'])  # error: unrecognized arguments

print("---7---")
# namespace = parser.parse_args(['-x', 42])  # crashes: TypeError: 'int' object is not subscriptable

print('---8---')
namespace = parser.parse_args(['--help'])  # prints the help and then exits
# namespace = parser.parse_args(['-h'])  # prints the help and then exits

print('---9---')
# namespace = parser.parse_args(['--version']) # prints the program name with the version and then exits
# print(namespace)
# print(f"a: {namespace.a}")
# print(f"b: {namespace.b}")
# print(f"c: {namespace.d}")
# print(f"noarg: {namespace.noarg}")
# print(f"my_optional_positional_arg: {namespace.my_optional_positional_arg}")
# print(f"my_mandatory_positional_arg: {namespace.my_mandatory_positional_arg}")
