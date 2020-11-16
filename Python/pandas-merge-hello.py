#!/usr/bin/env python
"""Showcase the usage of pandas.merge()"""
import pandas as pd


print("=== 1 pd.merge(...'inner'...) multiplexes dataframes with duplicate entries in in {left,right}_on ==")

df1 = pd.DataFrame({"a":[1,2,1], "b":["a","b","c"]})
df2 = pd.DataFrame({"x": [1,2], "y":["A","B"]})
# df2 = pd.DataFrame({"x": [1,2, 1], "y":["A","B","C"]})  # also multiplexes

merged = pd.merge(left=df1, right=df2, left_on="a", right_on="x")

print(merged)
