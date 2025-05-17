"""
Squarified Treemap Layout.

Given a list of descending sorted float-values,
compute a squarified treemap representation.
Implements an algorithm presented by Bruls, Huizing, van Wijk in "Squarified Treemaps".

The algorithm composes an overall representation from several row- or column-wise layouts.
Orders a given list of descending sorted sizes into rows or columns

inspired by:
https://github.com/laserson/squarify

author: andreasl

TODO make docs with c1 standards compliant.
"""

from dataclasses import dataclass


@dataclass
class Rectangle:
    """Rectangle representation used by treemap functions."""

    x: float
    y: float
    w: float  # width
    h: float  # height


Layout = list[Rectangle]


def _layout_row_wise(sizes: list[float], container: Rectangle) -> Layout:
    """Calculate a rectangle for each given size if the given container is rather tall."""
    covered_area = sum(sizes)
    width = covered_area / container.h
    rects = []
    y = container.y
    for size in sizes:
        height = size / width
        rects.append(Rectangle(container.x, y, width, height))
        y += height
    return rects


def _layout_column_wise(sizes: list[float], container: Rectangle) -> Layout:
    """Calculate a rectangle for each given size if the the container is rather wide."""
    covered_area = sum(sizes)
    height = covered_area / container.w
    rects = []
    x = container.x
    for size in sizes:
        width = size / height
        rects.append(Rectangle(x, container.y, width, height))
        x += width
    return rects


def _layout(sizes: list[float], container: Rectangle) -> Layout:
    """Given a container, calculate a rectangle for each given size."""
    if container.w >= container.h:
        return _layout_row_wise(sizes, container)
    return _layout_column_wise(sizes, container)


def _get_max_ratio(layout: Layout) -> float:
    """Get the highest dimension-ratio of all given rectangles."""
    worst_ratio = 0
    for rect in layout:
        worst_ratio = max(worst_ratio, rect.w / rect.h, rect.h / rect.w)
    return worst_ratio


def _layout_partially(sizes: list[float], container: Rectangle) -> Layout:
    """Given a container, calculate rectangles for an optimal sublist of given sizes."""
    layout = _layout(sizes[:1], container)
    worst_ratio = _get_max_ratio(layout)
    next_layout = _layout(sizes[:2], container)
    next_worst_ratio = _get_max_ratio(next_layout)
    i = 1
    while i < len(sizes) and worst_ratio >= next_worst_ratio:
        i += 1
        layout = next_layout
        worst_ratio = next_worst_ratio
        next_layout = _layout(sizes[: i + 1], container)
        next_worst_ratio = _get_max_ratio(next_layout)
    return layout


def _get_leftover_row(sizes: list[float], container: Rectangle) -> Rectangle:
    """Compute remaining area when container.w >= container.h."""
    covered_area = sum(sizes)
    width = covered_area / container.h
    return Rectangle(container.x + width, container.y, container.w - width, container.h)


def _get_leftover_col(sizes: list[float], container: Rectangle) -> Rectangle:
    """Compute remaining area when container.w < container.h."""
    covered_area = sum(sizes)
    height = covered_area / container.w
    return Rectangle(
        container.x, container.y + height, container.w, container.h - height
    )


def _get_leftover_container(sizes: list[float], container: Rectangle) -> Rectangle:
    """Compute leftover."""
    if container.w >= container.h:
        return _get_leftover_row(sizes, container)
    return _get_leftover_col(sizes, container)


def generate_treemap_layout(sizes: list[float], container: Rectangle) -> Layout:
    """
    Compute a treemap representation of given sizes that fits the given container.

    Implements an algorithm presented by Bruls, et al. in "Squarified Treemaps".

    Parameters
    ----------
    sizes : List[float]
        The list of values to compute a treemap for. `sizes` must be positive
        values sorted in descending order and they should be normalized to the
        total area (i.e., `container.w * container.h == sum(sizes)`)
    container : Rectangle
        A Rectangle with the coordinates of the "origin"
        and the full width (`w`) and height (`h`) of the treemap.
    Returns
    -------
    Layout
        Each dict in the returned list represents a single rectangle in the
        treemap. The order corresponds to the input order.
    """

    if not sizes:
        return []
    if len(sizes) == 1:
        return _layout(sizes, container)

    layout = _layout_partially(sizes, container)
    leftover_container = _get_leftover_container(sizes[: len(layout)], container)
    return layout + generate_treemap_layout(sizes[len(layout) :], leftover_container)


def get_normalized_sizes(sizes: list[float], area: float) -> list[float]:
    """
    Normalize a given list of float sizes so that they fit onto the given area.

    Parameters
    ----------
    sizes : List[float]
        Input list of numeric values to normalize.
    area : float
        Surface area of a rectangle to which to normalize the sizes to.
    Returns
    -------
    List[float]
        The normalized sizes.
    """
    area_to_size_ratio = area / sum(sizes)
    return [size * area_to_size_ratio for size in sizes]
