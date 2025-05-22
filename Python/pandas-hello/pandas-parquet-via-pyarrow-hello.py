#!/usr/bin/env python3
"""Showcase how to read/write Apache Parquet files.

Arrow is an in-memory columnar data format:
- designed for efficient data interchange between systems/languages, like Pandas, Spark, C++
- zero-copy and SIMD-friendly
- language-agnostic

pyarrow provides:
- access to Arrow
- Parquet read/write support
- inter-process communication and filesystem access
- schema definitions, type-safe arrays/tables
- fast conversion between Arrow and Pandas
- pyarrow is apparently the most feature-rich backend

Parquet supports optional column-wise compression.

Note: You gotta need to `pip install pyarrow` when intending to use it with pandas.
"""

import pandas as pd
import pyarrow as pa
import pyarrow.parquet as pq

print("--- 1 write parquet---\n")

df = pd.DataFrame(
    {
        "name": ["Alice", "Bob"],
        "age": [25, 30],
    }
)
df.to_parquet("data.parquet", engine="pyarrow", compression="zstd")

print("\n--- 2 read parquet---\n")

df2 = pd.read_parquet("data.parquet", engine="pyarrow")
print(df2)

print("\n--- 3 write nested parquet ---\n")

nested_table: pa.Table = pa.table(
    {
        "id": [1, 2],
        "person": pa.array(
            [{"name": "Gregor", "age": 32}, {"name": "George", "age": 45}],
            type=pa.struct([("name", pa.string()), ("age", pa.int32())]),
        ),
    }
)

print(f"nested_table=\n{nested_table}\n\n")
print(f"{nested_table.schema=}")

pq.write_table(nested_table, "nested.parquet")

print("\n--- 4 read nested parquet ---\n")

nested_df: pd.DataFrame = pq.read_table("nested.parquet").to_pandas()

print(f"nested_df=\n{nested_df}\n\n")
print(f"{nested_df.dtypes=}")
