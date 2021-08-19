#!/usr/bin/env python3
"""Showcase how to export json objects to csv.

Works in python2 with pandas 0.24.2.

author: andreasl
"""
import json

import pandas

print("--- 1 flat json to csv")

json_data = [
    {"name": "Andreas", "age": 32, "timestamp": 1234},
    {"name": "Jimbo", "color": "red", "timestamp": 890},
]

df1 = pandas.read_json(json.dumps(json_data))
df1.insert(1, "New Column", "XXX")
df2 = pandas.read_json(json.dumps(json_data))
df = pandas.concat([df1, df2], sort=False)
df.sort_values(by="timestamp", inplace=True)
# csv = df.to_csv(index=False, header=False)
csv = df.to_csv(index=False)

print(csv)

print("--- 2 flat json to csv with defined columns")

df = pandas.read_json(json.dumps(json_data))
df = df.reindex(columns=["timestamp", "name", "age", "imaginary"])
df.sort_values(by="timestamp", inplace=True)
csv = df.to_csv(index=False)

print(csv)

print("--- 3 nested json to csv ---")

json_data = [
    {
        "age": 32,
        "fields": {"codename": "barn", "number": 42},
        "name": "Andreas",
        "timestamp": 1234,
    },
    {
        "fields": {"codename": "rrred", "hex": "0xff0000"},
        "timestamp": 23,
        "xcolor": "red",
    },
]

df = pandas.io.json.json_normalize(json_data)
df.sort_values(by="timestamp", inplace=True)
csv = df.to_csv(index=False)

print(csv)
