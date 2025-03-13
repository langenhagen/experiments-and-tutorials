#!/usr/bin/env python3
"""Showcase the usage of the third party library click for CLI apps.

Click lets you specify parameters and options easilily as decorators and can
print a hep message using --help.
Click also uses the docstring specified in the function.

install e.g. via pip install click

See:
https://www.geeksforgeeks.org/click-module-in-python-making-awesome-command-line-utilities/
"""
import click


@click.command()  # necessary
@click.argument("myarg1")  # does not have an argument "help"
@click.argument("myarg2")  # second argument
@click.option(
    "-o",
    "--myoption",
    default="My Default",
    help="This is an optional option",
)
def hello(myoption, myarg1, myarg2):
    """The docstring of the function may be used in the help string."""
    click.echo(f"myoption={myoption}\nmyarg1={myarg1}\nmyarg2={myarg2}")


if __name__ == "__main__":
    hello()
