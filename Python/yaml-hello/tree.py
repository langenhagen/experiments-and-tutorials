#!/usr/bin/env python3
"""A simple n-ary tree implementation.

Contains a simple type and functions for working with generic directed graphs
and n-ary trees.
"""

import dataclasses
from collections.abc import Callable, Iterable


@dataclasses.dataclass
class Node:
    """A simple Node in an n-ary tree or directed graph."""

    children: list["Node"] = dataclasses.field(default_factory=list)


def iterate_breadth_first(root: Node) -> Iterable[Node]:
    """Yield elements via breadth first search."""
    queue = [root]
    while queue:
        node = queue.pop(0)
        queue.extend(node.children)
        yield node


def iterate_depth_first(root: Node) -> Iterable[Node]:
    """Yield elements via depth first search."""
    yield root
    for child in root.children:
        yield from iterate_depth_first(child)


def find_path(root: Node, predicate: Callable[[type[Node]], bool]):
    """Return the first path to a node that fulfills the given predicate."""
    result = [root]
    if predicate(root):
        return result

    for child in root.children:
        results = find_path(child, predicate)
        if results and predicate(results[-1]):
            return result + results
    return []


def yield_leaves(root: Node) -> Iterable[Node]:
    """Yield the leaves in a given tree."""
    for node in iterate_depth_first(root):
        if node.children:
            continue
        yield node


def get_paths_to_leaves(
    root: Node, path: list[Node] = None, paths: list[list[Node]] = None
) -> list[list[Node]]:
    """Get the paths to all leaves in a given tree."""
    if paths is None:
        paths = []
    if path is None:
        path = []
    path.append(root)
    if not root.children:
        paths.append(path.copy())
    else:
        for child in root.children:
            get_paths_to_leaves(child, path, paths)
    path.pop()
    return paths
