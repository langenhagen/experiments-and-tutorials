#!/usr/bin/env python
"""Showcase the usage of pandas.Series.cumsum()"""
import pandas as pd

df = pd.DataFrame({"a":[1,2,1,3]})
print("Dataframe:")
print(df)

c = df["a"].cumsum()
print("Dataframe with cumsum on column 'a':")
print(c)
