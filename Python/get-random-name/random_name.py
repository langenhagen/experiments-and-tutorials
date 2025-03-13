#!/usr/bin/env python3
"""Get a random names by querying behindthename.com/random."""

import io
import random

import lxml.etree
import requests
from requests import HTTPError, ReadTimeout


def get_random_nationality() -> str:
    """Randomly choose a nationality string."""
    return random.choice(
        [
            "alb",  # Albanian names
            "arm",  # Armenian
            "cat",  # Catalan
            "chi",  # Chinese
            "geo",  # Georgian
            "ger",  # German
            "ind",  # Indian
            "swe",  # Swedish
            "per",  # Persian
            "rus",  # Russian
            "ukr",  # Ukrainian
        ]
    )


def get_random_names(n_names: int = 1) -> str | list[str] | None:
    """Query behindthename.com/random to get a random name
    Inspect the returned HTML text to find random names.

    Remove all those "usage_xxx" entries to get fully random names. Adding
    several languages mixes middle names and surnames across the different
    nationalities.

    Return None in case of Error.
    """
    url = "https://www.behindthename.com/random/random.php"

    try:
        response = requests.get(
            url=url,
            params={
                "gender": "both",  # "f", "m", "both"
                "number": 1,  # number of middle names
                "sets": n_names,  # number of persons to return; 5 seems to be the max
                "randomsurname": "yes",
                f"usage_{get_random_nationality()}": 1,
            },
            timeout=1,
        )
        response.raise_for_status()
    except (HTTPError, ReadTimeout):
        return None

    tree = lxml.etree.parse(io.StringIO(response.text), lxml.etree.HTMLParser())
    result_elements: list[lxml.etree.Element] = tree.xpath(
        '//*[@id="body-inner"]/center/div[contains(@class, "random-results")]'
    )

    names = []
    for result in result_elements:
        names.append(" ".join([n.text for n in result.getchildren()]))

    return names[0] if n_names == 1 else names


def main():
    """Program main entry point."""
    for name in get_random_names(5):
        print(name)


if __name__ == "__main__":
    main()
