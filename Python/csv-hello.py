#!/usr/env/bin python
"""Showcase the usage of Python's standard librarby package `csv`

Code based on: https://pymotw.com/3/csv/

author: andreasl
"""
import csv

print("=== 1 write CSV ===")

unicode_chars = "å∫ç"

filename = "mycsv.csv"

with open(filename, "w") as file:
    writer = csv.writer(file, quoting=csv.QUOTE_NONNUMERIC)
    writer.writerow(("Title 1", "Title 2", "Title 3", "Title 4"))
    for i in range(3):
        row = (
            i + 1,
            chr(ord("a") + i),
            "08/{:02d}/07".format(i + 1),
            unicode_chars[i],
        )
        writer.writerow(row)

print(open(filename, "r").read())
