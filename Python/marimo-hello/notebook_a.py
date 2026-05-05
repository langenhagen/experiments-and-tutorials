"""Notebook A: basic marimo inputs with a Plotly chart."""

# ANN001: missing type annotations for function arguments
# ANN202: missing return type annotation for private functions
# PLC0415: imports inside marimo cells are intentional
# ruff: noqa: ANN001, ANN202, PLC0415

import marimo

__generated_with = "0.23.5"
app = marimo.App(width="medium")


@app.cell
def _():
    import marimo as mo
    import plotly.graph_objects as go

    return go, mo


@app.cell
def _(mo):
    mo.md("""
    # Notebook A

    A first marimo notebook with two reactive inputs.
    """)


@app.cell
def _(mo):
    name = mo.ui.text(label="Name", value="Andreas")
    excitement = mo.ui.slider(1, 10, value=5, label="Excitement")
    mo.hstack([name, excitement], gap=2)
    return excitement, name


@app.cell
def _(excitement, go, mo, name):
    bang = "!" * int(excitement.value)
    mo.md(f"Hello **{name.value or 'friend'}**{bang}")

    points = list(range(1, 11))
    amplified = [p * int(excitement.value) for p in points]
    fig = go.Figure(data=[go.Scatter(x=points, y=amplified, mode="lines+markers")])
    fig.update_layout(
        title="Excitement Scales the Line",
        xaxis_title="Step",
        yaxis_title="Value",
    )
    fig.update_layout(height=360)
    mo.ui.plotly(fig)


if __name__ == "__main__":
    app.run()
