"""Notebook B: marimo controls, parsing, and tabular output."""

# ANN001: missing type annotations for function arguments
# ANN202: missing return type annotation for private functions
# PLC0415: imports inside marimo cells are intentional
# PLR0913: marimo injects many reactive dependencies by design
# ruff: noqa: ANN001, ANN202, PLC0415, PLR0913

import marimo

app = marimo.App(width="medium")


@app.cell
def _():
    import ast
    import json

    import marimo as mo

    return ast, json, mo


@app.cell
def _(mo):
    mo.md("""# Notebook B\n\nA second marimo notebook with different controls.""")


@app.cell
def _(mo):
    theme = mo.ui.dropdown(
        options=["calm", "focus", "play"],
        value="focus",
        label="Theme",
    )
    repeat = mo.ui.slider(1, 5, value=2, label="Repeat")
    mo.hstack([theme, repeat], gap=2)
    return repeat, theme


@app.cell
def _(mo, repeat, theme):
    line = f"Notebook B is running in **{theme.value}** mode."
    message = "\n\n".join([line] * int(repeat.value))
    mo.md(message)

    rows = [
        {"task": "write", "theme": theme.value, "minutes": 25 * int(repeat.value)},
        {"task": "review", "theme": theme.value, "minutes": 15 * int(repeat.value)},
        {"task": "break", "theme": theme.value, "minutes": 5 * int(repeat.value)},
    ]
    mo.ui.table(rows, label="Session Plan")


@app.cell
def _(mo):
    payload = mo.ui.text_area(
        label="Paste JSON or Python dict",
        value='{"name": "andreas", "lang": "python", "level": 3, "scores": [1, 2, 3]}',
        rows=8,
    )
    return (payload,)


@app.cell
def _(ast, json, payload):
    raw = payload.value.strip()
    if not raw:
        parsed = None
        error = "Input is empty."
        parser_used = None
    else:
        try:
            parsed = ast.literal_eval(raw)
            error = None
            parser_used = "python-literal"
        except (SyntaxError, ValueError) as py_exc:
            try:
                parsed = json.loads(raw)
                error = None
                parser_used = "json"
            except json.JSONDecodeError as exc:
                parsed = None
                parser_used = None
                error = f"{py_exc}; {exc}"
    return error, parsed, parser_used


@app.cell
def _(parsed):
    def collect_numbers(obj):
        numbers = []
        if isinstance(obj, dict):
            for value in obj.values():
                numbers.extend(collect_numbers(value))
        elif isinstance(obj, list):
            for value in obj:
                numbers.extend(collect_numbers(value))
        elif isinstance(obj, (int, float)) and not isinstance(obj, bool):
            numbers.append(float(obj))
        return numbers

    if parsed is None:
        treated = None
        stats = None
    elif isinstance(parsed, dict):
        nums = collect_numbers(parsed)
        treated = {
            **parsed,
            "_meta": {
                "key_count": len(parsed),
                "keys_sorted": sorted(str(k) for k in parsed),
                "numeric_sum": round(sum(nums), 6),
                "numeric_count": len(nums),
            },
        }
        stats = treated["_meta"]
    elif isinstance(parsed, list):
        nums = collect_numbers(parsed)
        treated = {
            "items": parsed,
            "_meta": {
                "count": len(parsed),
                "numeric_sum": round(sum(nums), 6),
                "numeric_count": len(nums),
            },
        }
        stats = treated["_meta"]
    else:
        treated = {"value": parsed}
        nums = collect_numbers(parsed)
        stats = {
            "numeric_sum": round(sum(nums), 6),
            "numeric_count": len(nums),
        }

    return stats, treated


@app.cell
def _(error, json, mo, parser_used, payload, stats, treated):
    if error:
        status = mo.md(f"## Result\n\nStatus: **Parse Error**\n\n`{error}`")
        treated_view = mo.md("")
    elif treated is None:
        status = mo.md("## Result\n\nStatus: **Waiting for input**")
        treated_view = mo.md("")
    else:
        status = mo.md(
            "\n".join(
                [
                    "## Result",
                    "",
                    "Status: **Parse OK**",
                    f"Parser: `{parser_used}`",
                    f"Numeric sum: `{stats['numeric_sum']}`",
                    f"Numeric count: `{stats['numeric_count']}`",
                ],
            ),
        )

        if isinstance(treated, dict) and isinstance(treated.get("items"), list):
            items = treated["items"]
            if items and all(isinstance(item, dict) for item in items):
                treated_view = mo.ui.table(items, label="Parsed Items")
            else:
                treated_view = mo.md(
                    f"```json\n{json.dumps(treated, indent=2, ensure_ascii=True)}\n```",
                )
        else:
            treated_view = mo.md(
                f"```json\n{json.dumps(treated, indent=2, ensure_ascii=True)}\n```",
            )

    mo.hstack(
        [
            mo.vstack([mo.md("## Input"), payload]),
            mo.vstack([status, treated_view]),
        ],
        widths=[1, 1],
        gap=2,
    )


if __name__ == "__main__":
    app.run()
