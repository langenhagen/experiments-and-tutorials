"""Showcase the python module argparse.

use argparse.ArgumentParser.parse_args() with something specific. if nothing is
given, it uses sys.argv[1:] by default.

See:
https://pymotw.com/3/argparse/index.html
https://docs.python.org/3/library/argparse.html#nargs
https://stackoverflow.com/questions/15753701/how-can-i-pass-a-list-as-a-command-line-argument-with-argparse
"""

import pathlib
from argparse import ArgumentParser, ArgumentTypeError, FileType, Namespace

# add_help=True is the default; consider setting it to false for a custom option `-h`
parser = ArgumentParser(description="Short sample app", add_help=True)

# version
parser.add_argument(
    "-v",
    "--version",
    action="version",
    version="%(prog)s 1.0",
    help="show the version Stuff",
)
# optional flag
parser.add_argument(
    "-a",
    action="store_true",
    default=False,
    help='help string for "a"',
)
# optional argument
parser.add_argument(
    "-b",
    action="store",  # "store" is the default action
    dest="b",
    help='help string for "b"',
)
# optional argument with short and long option, type and explicit destination
parser.add_argument(
    "-c",
    "--alternative-C",
    action="store",
    dest="c",
    type=int,
    help='help string for "c"; very long help strings appear to be broken '
    "into multiple lines at runtime, but\nignore newlines\n",
)
# optional positional arg
parser.add_argument(
    "my_optional_positional_arg",
    nargs="?",  # ?: a single argument, 0 or 1
    default=pathlib.Path.cwd(),
    help="an unnamed optional argument",
)
# required positional arg
parser.add_argument(
    "my_required_positional_arg",
    # nargs=1,  # 1: list of 1 item
    help="a required unnamed argument",
)
# lists
parser.add_argument(
    "-l",
    "--my-list",
    nargs="+",  # `+` takes 1 or more args, `*` takes 0 or more args
    required=False,  # False is the default, but for showcasing, I write it out
    default=[69, 420],
    type=int,  # item-wise type
    help="an optional argument list",
)

# decide to change the defaults for an argument
# parser.set_defaults(l=[70, 421])  # that doesn't work
parser.set_defaults(my_list=[71, 422])

# append lists, basically you can set this argument several times
parser.add_argument(
    "-m",
    "--my-other-list",
    action="append",
    help="another optional argument list",
)
parser.add_argument(
    "--choice",
    dest="choice",
    choices=("ssh", "https"),
    default="ssh",
    help="Either ssh or https/",
)

group = parser.add_mutually_exclusive_group(required=False)
group.add_argument(
    "--group1",
    type=str,
    help="A string.",
)
group.add_argument(
    "--othergroup",
    action="store_true",
    help="A flag",
)


# custom typechecks - cause a nice crash with the look and feel of argparse
def __check_unsigned_int(arg: str) -> int:
    """Check if the given value is an unsigned int and return it as an int."""
    try:
        i = int(arg)
    except ValueError:
        raise ArgumentTypeError(f"{arg} is not an unsigned int value")
    if i < 0:
        raise ArgumentTypeError(f"{arg} is not an unsigned int value")
    return i


parser.add_argument(
    "--uint",
    action="store",
    dest="uint",
    type=__check_unsigned_int,
    help='help string for "uint"',
)


def _print_namespace(ns: Namespace) -> None:
    """A helper function to print a name space."""
    print(ns)
    print(f"{ns.a=}")
    print(f"{ns.b=}")
    print(f"{ns.c=}")
    print(f"{ns.my_optional_positional_arg=}")
    print(f"{ns.my_required_positional_arg=}")
    print(f"{ns.my_list=}")
    print(f"{ns.my_other_list=}")
    print(f"{ns.choice=}")
    print(f"{ns.uint=}")
    print(f"{ns.group1=}")
    print(f"{ns.othergroup=}")


print("\n--- 1 argument parsing ---")
# use parse_args() without params to parse from the command line
# namespace = parser.parse_args()

# fmt: off
namespace = parser.parse_args(
    [
        "-a",
        "-bval",
        "-c", "3",
        "miaaauuu",
        "--my-list", "1", "2", "3",
        "-m", "7", "-m", "8", "-m", "9",
        "--uint", "0",
        "--group1", "grouped"
    ]
)
# fmt: on
_print_namespace(namespace)

print("\n--- 2 another parsing---")
namespace = parser.parse_args(["-a", "kaaatz", "--othergroup"])
_print_namespace(namespace)

print("\n--- 3 with only one argument---")
namespace = parser.parse_args(["just one argument works"])
_print_namespace(namespace)

print("\n--- 4 yet another parsiong---")
namespace = parser.parse_args(["optional", "required"])  # order is important
_print_namespace(namespace)

print("\n--- 5 empty argument list might break ---")
# breaks since my_required_positional_arg is required
# namespace = parser.parse_args([])

print("\n--- 6 crash with unrecognized positional arguments ---")
# error: unrecognized arguments
# namespace = parser.parse_args(["many", "arguments", "don't", "due to nargs"])

print("\n--- 7 crash with unknown named arguments ---")
# error: unrecognized arguments: -x
# namespace = parser.parse_args(["-x", "42"])

print("\n--- 8 print help and exit ---")
# namespace = parser.parse_args(["--help"])  # prints the help and then exits the program
# namespace = parser.parse_args(["-h"])  # prints the help and then exits the program

print("\n--- 9 print version and exit ---")
# namespace = parser.parse_args(["--version"])  # prints the version and then exits the program
# namespace = parser.parse_args(["-v"])  # prints the version and then exits the program

print("\n--- 10 file IO ---")
# first, write
parser = ArgumentParser(description="Argparser for file IO writing")
parser.add_argument("-o", metavar="out-file", type=FileType("wt"))

try:
    results = parser.parse_args(["-o", "my-argparse-file.txt"])
except OSError as msg:
    parser.error(str(msg))

print("Output file:", results.o)  # returns an io.TextIOWrapper or None

results.o.write("Hi there!")  # caution: writes to file

# then read
parser = ArgumentParser(description="Argparser for file IO reading")
parser.add_argument("-i", metavar="in-file", type=FileType("rt"))

try:
    results = parser.parse_args(["-i", "my-argparse-file.txt"])
except OSError as msg:
    parser.error(str(msg))

print("Input file:", results.i)  # returns an io.TextIOWrapper or None

file_contents = results.i.read()
print(f"{file_contents=}")
