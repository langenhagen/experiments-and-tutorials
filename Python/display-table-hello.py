#!/usr/bin/env python3
"""Showcase how to flexibly print nicely formatted tables just with standard
Python.

author: andreasl
"""

from dataclasses import dataclass, fields
from typing import TYPE_CHECKING

if TYPE_CHECKING:
    from collections.abc import Iterable


@dataclass
class Item:
    name: str
    estimated: int  # estimated price
    saved: int = 0  # money saved up for the item
    actual: int | None = None  # actual cost
    buyer: str | None = None


def display_table(items: Iterable[Item]) -> None:
    # Get all fields from the Item dataclass except for 'name'
    item_fields = [f.name for f in fields(Item) if f.name != "name"]

    # Define headers based on item fields
    headers = ["Name"] + [
        f"{field.replace('_', ' ').title()}{' (€)' if isinstance(getattr(items[0], field), (int, float, type(None))) else ''}"
        for field in item_fields
    ]

    # Determine the width of each column
    col_widths = [max(len(str(item.name)) for item in items)] + [
        max(
            [
                len(str(getattr(item, field)))
                for item in items
                if getattr(item, field) is not None
            ]
            or [len("N/A")]
        )
        for field in item_fields
    ]
    col_widths = [max(len(header), width) for header, width in zip(headers, col_widths)]

    # Create the header row
    header_row = "  ".join(
        f"{header:<{col_width}}" for header, col_width in zip(headers, col_widths)
    )
    print(header_row)
    print("-" * len(header_row))

    totals = {
        field: 0
        for field in item_fields
        if isinstance(getattr(items[0], field), (int, float, type(None)))
    }

    # Create the rows for each item and calculate sums
    for item in items:
        row_values = [item.name]
        for field in item_fields:
            value = getattr(item, field)
            if value is not None:
                row_values.append(value)
                if field in totals:
                    totals[field] += value if isinstance(value, (int, float)) else 0
            else:
                row_values.append("N/A")

        row = "  ".join(
            f"{str(value):<{col_width}}"
            for value, col_width in zip(row_values, col_widths)
        )
        print(row)

    # Print the footer row with sums
    footer_row_values = ["Total"] + [
        totals[field] if field in totals and totals[field] != 0 else ""
        for field in item_fields
    ]
    footer_row = "  ".join(
        f"{str(value):<{col_width}}"
        for value, col_width in zip(footer_row_values, col_widths)
    )
    print("-" * len(header_row))
    print(footer_row)


items = [
    Item(name="Bluetooth Speaker", estimated=80, saved=40, actual=75, buyer="Alex"),
    Item(name="Gaming Laptop", estimated=1200, saved=500, actual=1150, buyer="Berta"),
    Item(
        name="Electric Scooter",
        estimated=600,
    ),
    Item(name="Smart Thermostat", estimated=250, saved=100),
    Item(name="Noise Cancelling Headphones", estimated=300, saved=300),
]

display_table(items)
