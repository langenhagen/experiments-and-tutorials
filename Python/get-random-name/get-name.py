#!/usr/bin/env python3
"""Get a random name by querying behindthename.com/random."""
import io

import lxml.etree
import requests


def process_results(html: str):
    """Inspect the html text to find random names."""
    tree = lxml.etree.parse(io.StringIO(html), lxml.etree.HTMLParser())
    results: list = tree.xpath(
        '//*[@id="body-inner"]/center/div[contains(@class, "random-results")]'
    )

    # print(f"{len(results)=}")

    for result in results:
        name = " ".join([n.text for n in result.getchildren()])
        print(name)


def main():
    """Query some ebay kleinanzeigen searches and process the results."""

    url = "https://www.behindthename.com/random/random.php"

    response = requests.get(
        url=url,
        params={
            "gender": "f",  # "f", "m", "both"
            "number": 1,  # number of middle names
            "sets": 5,  # number of persons to return; 5 seems to be the max
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

    process_results(response.text)


if __name__ == "__main__":
    main()
