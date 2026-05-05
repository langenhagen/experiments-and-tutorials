# Marimo Hello

Check how multiple notebooks work in marimo at runtime.

This project has two independent notebooks in one folder:
- `notebook_a.py` (reactive inputs + Plotly chart)
- `notebook_b.py` (reactive inputs + interactive table)

## Usage

```bash
uv sync

# Edit mode (shows code cells; add/change code here)
uv run marimo edit notebook_a.py
uv run marimo edit notebook_b.py

# Run mode (app view)
# Run folder as a gallery and choose notebook A or B in the browser
uv run marimo run .
uv run marimo edit .

# Run both notebooks at the same time on different ports
uv run marimo run notebook_a.py --port 2718
uv run marimo run notebook_b.py --port 2719
```

## What To Expect

- `marimo run .` opens a folder index page where both notebooks are listed.
- Opening a notebook starts it as an app session.
- To interact with both simultaneously, run each notebook on a different port.

In `notebook_b.py`, paste JSON or a Python dict into the text area.
The notebook parses it and shows:
- parse status
- numeric sum and count
- treated output as pretty JSON (and table for list-of-dicts)

Valid example:

```json
{"name": "andreas", "level": 4, "scores": [1, 2, 3]}
```
