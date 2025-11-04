#!/usr/bin/env python3
"""Convert a bunchof ndjson files to .parquet files."""

from io import BytesIO
import json
from pathlib import Path

import pandas as pd
import pyarrow as pa
import pyarrow.parquet as pq
from flatten_json import flatten

indir = Path.home() / "Desktop/foo/2025-10-21-andreasl-infinite/relay-all"
outdir = Path.home() / "Desktop/bar"
outdir.mkdir(exist_ok=True)



def _to_columnar(dict_list: list[dict], separator: str = ".") -> dict:
    """Flatten and align a list of (possibly heterogeneous) dicts for columnar
    storage.

    Each key in the result is a flattened key path using given `separator`. Each
    value is a list, one entry per input dict. Missing keys are set to `None`.
    """

    flat_subdicts: list[dict] = [flatten(d, separator) for d in dict_list]
    all_keys: set = {key for d in flat_subdicts for key in d}
    return {k: [d.get(k) for d in flat_subdicts] for k in all_keys}


def _sanitize_columnar(d: dict[str, list]) -> dict[str, list]:
    """To avoid issues like:

    "Cannot write struct type 'data.global_limits' with no child field to Parquet. Consider adding a dummy child field."
    """
    def _fix(v):
        if isinstance(v, dict) and not v:  # {}
            return None
        return v
    return {k: [_fix(v) for v in vals] for k, vals in d.items()}


for ndjson_file in indir.glob("*.ndjson"):
    with open(ndjson_file, "r") as f:
        data = [json.loads(line) for line in f]

    columnar_data = _to_columnar(data)
    sanitized_data = _sanitize_columnar(columnar_data)

    df = pd.DataFrame(sanitized_data)

    table: pa.Table = pa.table(sanitized_data)

    result = BytesIO()
    try:
        pq.write_table(table=table, where=result, compression="snappy")
    except Exception as e:
        print(data)
        print(table)
        print(e)
        exit(99)

    with open(outdir / f"{ndjson_file.stem}.parquet", "wb") as fout:
        fout.write(result.getbuffer())