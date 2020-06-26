#!/usr/env/bin python3
"""Showcase how to export json objects to csv.

Works in python2 with pandas 0.24.2.

author: andreasl
"""
import json

import pandas

print("--- 1 flat json to csv")

json_data = [
    {
        "name": "Andreas",
        "age": 32,
        "timestamp": 1234
    },
    {
        "name": "Jimbo",
        "color": "red",
        "timestamp": 890
    }
]

dataframe1 = pandas.read_json(json.dumps(json_data))
dataframe1.insert(1, "New Column", "XXX")
dataframe2 = pandas.read_json(json.dumps(json_data))
dataframe = pandas.concat([dataframe1, dataframe2])
dataframe.sort_values(by="timestamp", inplace=True)
csv = dataframe.to_csv(index=False)

print(csv)

print("--- 2 nested json to csv ---")

json_data = [
    {
        "age": 32,
        "fields": {
            "codename": "barn",
            "number": 42
        },
        "name": "Andreas",
        "timestamp": 1234
    },
    {
        "fields": {
            "codename": "rrred",
            "hex": "0xff0000"
        },
        "timestamp": 23,
        "xcolor": "red"
    }
]

dataframe = pandas.io.json.json_normalize(json_data)
dataframe.sort_values(by="timestamp", inplace=True)
csv = dataframe.to_csv(index=False)

print(csv)
