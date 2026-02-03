#!/usr/bin/env python
"""Showcase the usage of Python's standard librarby package `csv`

Code based on: https://pymotw.com/3/csv/

author: andreasl
"""

import csv

print("--- 1 write CSV ---\n")

unicode_chars = "å∫ç"

filename = "mycsv.csv"

with open(filename, "w") as file:
    writer = csv.writer(file, quoting=csv.QUOTE_NONNUMERIC)
    writer.writerow(("Title 1", "Title 2", "Title 3", "Title 4"))
    for i in range(3):
        row = (
            i + 1,
            chr(ord("a") + i),
            f"08/{i + 1:02d}/07",
            unicode_chars[i],
        )
        writer.writerow(row)

print(open(filename).read())
