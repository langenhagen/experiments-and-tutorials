#!/usr/bin/env python3
"""Show the __dict__ of Chromecast objects and their `MediaController` member.

Sadly no cigar when it comes to getting URLs of arbitrary internet streams.
"""
from bpython import embed
from pychromecast import CastBrowser, Chromecast, get_listed_chromecasts


def main() -> None:
    """Show the `__dict__` fields for some central pychromecast objects."""
    chromecasts: list[Chromecast]
    browser: CastBrowser

    chromecasts, browser = get_listed_chromecasts(["Living Room TV"])
    print(f"{chromecasts=}\n{browser=}\n")

    cast: Chromecast = chromecasts[0]

    cast.wait()  # wait until you find the thing; maybe not necessary

    print(f"{browser.__dict__=}")
    print()
    print(f"{cast.__dict__=}")
    print()
    print(f"{cast.media_controller.__dict__=}")

    # Play around, e.g. you can lower or increase the audio volume of a stream that plays.
    # embed(locals_=locals(), banner="\nDropping to interactive shell\n")


if __name__ == "__main__":
    main()
