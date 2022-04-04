"""Showcase the python module argparse.

use argparse.ArgumentParser.parse_args() with something specific. if nothing is
given, it uses sys.argv[1:] by default.

See:
https://pymotw.com/3/argparse/index.html
https://docs.python.org/3/library/argparse.html#nargs
https://stackoverflow.com/questions/15753701/how-can-i-pass-a-list-as-a-command-line-argument-with-argparse
"""
import argparse
import pathlib
from argparse import Namespace

parser = argparse.ArgumentParser(description="Short sample app")

parser.add_argument(
    "-a",
    action="store_true",
    default=False,
    help='help string for "a"',
)
parser.add_argument(
    "-b",
    action="store",
    dest="b",
    help='help string for "b"',
)
parser.add_argument(
    "-c",
    "--alternative-C",
    action="store",
    dest="c",
    type=int,
    help='help string for "c"; very long help strings appear to be broken '
    "into multiple lines at runtime, but\nignore newlines\n",
)
parser.add_argument("-d", action="store", dest="d", help='help string for "d"')
parser.add_argument(
    "--noarg", action="store_true", default=False, help='help string for "noarg"'
)
parser.add_argument(
    "-v",
    "--version",
    action="version",
    version="%(prog)s 1.0",
    help="show the version Stuff",
)
parser.add_argument(
    "my_optional_positional_arg",
    nargs="?",  # ?: a single argument, 0 or 1
    default=pathlib.Path.cwd(),
    help="an unnamed optional argument",
)
parser.add_argument(
    "my_required_positional_arg",
    # nargs=1,  # 1: list of 1 item
    help="a required unnamed argument",
)
parser.add_argument(
    "-l",
    "--my-list",
    nargs="+",  # `+` takes 1 or more args, `*` takes 0 or more args
    required=False,  # False is the default, but for showcasing, I write it out
    help="an optional argument list",
)
parser.add_argument(
    "-m", "--my-other-list", action="append", help="another optional argument list"
)


def __print_namespace(ns: Namespace):
    """A helper function to print a name space."""
    print(ns)
    print(f"{ns.a=}")
    print(f"{ns.b=}")
    print(f"{ns.c=}")
    print(f"{ns.d=}")
    print(f"{ns.noarg=}")
    print(f"{ns.my_optional_positional_arg=}")
    print(f"{ns.my_required_positional_arg=}")
    print(f"{ns.my_list=}")
    print(f"{ns.my_other_list=}")


print("\n---1 argument parsing ---\n")
# use parse_args() without params to parse from the command line
# namespace = parser.parse_args()

# fmt: off
namespace = parser.parse_args(
    [
        "-a",
        "-bval",
        "-c", "3",
        "-d", "moo",
        "miaaauuu",
        "--my-list", "1", "2", "3",
        "-m", "7", "-m", "8", "-m", "9",
    ]
)
# fmt: on
__print_namespace(namespace)

print("\n---2 another parsing---\n")
namespace = parser.parse_args(["-a", "--noarg", "kaaatz"])
__print_namespace(namespace)

print("\n---3 with only one argument---\n")
namespace = parser.parse_args(["just one argument works"])
__print_namespace(namespace)

print("\n---4 yet another parsiong---\n")
namespace = parser.parse_args(["optional", "required"])  # order is important
__print_namespace(namespace)

print("\n---5 empty argument list might break ---\n")
# breaks since my_required_positional_arg is required
# namespace = parser.parse_args([])

print("\n---6 crash with unrecognized positional arguments ---\n")
# error: unrecognized arguments
# namespace = parser.parse_args(["many", "arguments", "don't", "due to nargs"])

print("\n---7 crash with unknown named arguments ---\n")
# namespace = parser.parse_args(["-x", "42"])

print("\n---8 print help and exit ---\n")
# namespace = parser.parse_args(["--help"])  # prints the help and then exits
# namespace = parser.parse_args(["-h"])  # prints the help and then exits

print("\n---9 print version and exit ---\n")
# namespace = parser.parse_args(["--version"])  # prints the version and then exits
# namespace = parser.parse_args(["-v"])  # prints the version and then exits
