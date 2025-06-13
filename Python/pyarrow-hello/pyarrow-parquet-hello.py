#!/usr/bin/env python3
"""Showcase the usage of the 3rd party package `pyarrow`
for dealing with Apache Parquet files.

Parquet files are columnar, thus best for "flat" data.

Install with:

  pip install pyarrow


To view parquet files, consider installing `parquet-viewer`:

    pip install parquet-viewer

Then, you can inspect the file via:

    pqview view example.parquet


See:
- https://arrow.apache.org/docs/python/parquet.html
- https://pypi.org/project/pyarrow/
- https://pypi.org/project/parquet-viewer/
"""

import json
from io import BytesIO

import pyarrow as pa
import pyarrow.parquet as pq

print("--- 1 create & write table to Parquet ---\n")

data = {
    "name": ["Alice", "Bob", "Carol"],
    "age": [25, 30, 22],
    "score": [88.5, 92.0, 79.5],
}

print(f"data=\n{json.dumps(data, indent=2)}")

table: pa.Table = pa.table(data)
pq.write_table(table=table, where="example.parquet")

print("\n --- 2 create Parquet file in-memory ---\n")

buffer = BytesIO()
pq.write_table(table=table, where=buffer)
with open("from-buffer.parquet", "wb") as file:
    file.write(buffer.getvalue())

print("\n--- 3 read Parquet file ---\n")

in_table = pq.read_table("example.parquet")

print(f"in_table=\n{in_table}\n")
print(f"in_table.to_pydict()=\n{in_table.to_pydict()}\n")
print(f"in_table.to_pylist()=\n{in_table.to_pylist()}\n")
print(f"in_table.to_pandas()=\n{in_table.to_pandas()}\n")  # pandas must be installed separately

print("\n--- 4 select specific columns while reading ---\n")

selective_in_table = pq.read_table("example.parquet", columns=["name", "score"])
print(f"selective_in_table=\n{selective_in_table}\n")

print("\n--- 5 nested data ---\n")

nested_data = {
    "user": [
        {"name": "Alice", "age": 25},
        {"name": "Bob", "age": 30},
        {"name": "Carol", "age": 22},
    ],
    "score": [88.5, 92.0, 79.5],
}

print(f"nested_data=\n{json.dumps(nested_data, indent=2)}")

table_with_nested_data: pa.Table = pa.table(nested_data)

print(f"table_with_nested_data=\n{table_with_nested_data}\n")
print(f"table_with_nested_data.to_pydict()=\n{table_with_nested_data.to_pydict()}\n")
print(f"table_with_nested_data.to_pylist()=\n{table_with_nested_data.to_pylist()}\n")
print(f"table_with_nested_data.to_pandas()=\n{table_with_nested_data.to_pandas()}\n")
