#!/usr/bin/env python3
"""Get a random name by querying behindthename.com/random."""
import io

import lxml.etree
import requests


def get_random_names(n_names: int = 1) -> list[str]:
    """Query behindthename.com/random to get a random name
    Inspect the returned HTML text to find random names."""

    url = "https://www.behindthename.com/random/random.php"

    response = requests.get(
        url=url,
        params={
            "gender": "f",  # "f", "m", "both"
            "number": 1,  # number of middle names
            "sets": n_names,  # number of persons to return; 5 seems to be the max
            "randomsurname": "yes",
            # remove all those "usage_xxx" entries to get fully random names
            # adding several languages mixes middle names and surnames across
            # the different nationalities
            # "usage_ger": 1,  # German names
            "usage_geo": 1,  # Georgian names
            # "usage_alb": 1,  # Albanian names
            # "usage_per": 1,  # Persian names
        },
        timeout=1,
    )
    response.raise_for_status()

    tree = lxml.etree.parse(io.StringIO(response.text), lxml.etree.HTMLParser())
    result_elements: list[lxml.etree.Element] = tree.xpath(
        '//*[@id="body-inner"]/center/div[contains(@class, "random-results")]'
    )

    # print(f"{len(results)=}")
    names = []
    for result in result_elements:
        names.append(" ".join([n.text for n in result.getchildren()]))

    return names


def main():
    """Program main entry point."""
    for name in get_random_names(5):
        print(name)


if __name__ == "__main__":
    main()
