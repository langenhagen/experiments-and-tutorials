#!/usr/bin/env python3
"""Showcase the usage of pandas.Series.cumsum().

Also showcase that logging a dataframe prints it nicely.
"""

import pandas as pd

df = pd.DataFrame({"a": [1, 2, 1, 3]})
print("Dataframe:\n", df)

c = df["a"].cumsum()
print("Dataframe with cumsum on column 'a':\n", c)
