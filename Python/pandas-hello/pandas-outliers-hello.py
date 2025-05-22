#!/usr/bin/env python3
"""Showcase how to filter outliers from a dataframe."""

import numpy as np
import pandas as pd
import scipy.stats as stats

df = pd.DataFrame(np.random.randn(100, 3))
print(df)

print("--- 1 use a z-score via scipy to filter outliers ---\n")

print(stats.zscore(df))

# filtered = df[(np.abs(stats.zscore(df)) < 3).all(axis=1)]  # z-score < 3 is typical for filtering
filtered = df[(np.abs(stats.zscore(df)) < 2).all(axis=1)]  # smaller z-score filters more
print(filtered)
