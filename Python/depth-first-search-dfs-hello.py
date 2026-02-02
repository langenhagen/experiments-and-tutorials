#!/usr/bin/env python3
"""Showcase implementations of Depth-first-search (DFS) for graph traversal.

author: andreasl
"""

from typing import Generator

Node = str
AdjacencyList = dict[Node, list[Node]]

# A ◄───────────────┐
# ├─► B             │
# │  ├─► D          │
# │  │  └─► I       │
# │  └─► E          │
# │     ├─► J ───┐  │
# │     └─► K    │  │
# ├─► C          │  │
# │  ├─► F       │  │
# │  │  └─► L    │  │
# │  └─► H       │  │
# └─► G          │  │
#    └─► M       │  │
#       ├─► N ◄──┘  │
#       └─► O ──────┘
graph: AdjacencyList = {
    "A": ["B", "C", "G"],
    "B": ["D", "E"],
    "C": ["F", "H"],
    "D": ["I"],
    "E": ["J", "K"],
    "F": ["L"],
    "G": ["M"],
    "H": [],
    "I": [],
    "J": ["N"],
    "K": [],
    "L": [],
    "M": ["N", "O"],
    "N": [],
    "O": ["A"],
}

print("--- 1 naive DFS ---\n")


def process(node: Node) -> None:
    """Do something with given node."""
    print(f"{node=}")


def dfs(
    node: Node,
    graph: AdjacencyList,
    visited: set[Node] | None = None,
) -> None:
    """Simple depth-first search through an adjacency list."""
    visited = visited or set()
    if node in visited:
        return
    visited.add(node)

    process(node)

    for child in graph[node]:
        dfs(child, graph, visited)


dfs("A", graph)

print("\n--- 2 DFS as Generator ---\n")


def dfs_generator(
    node: Node,
    graph: AdjacencyList,
    visited: set[Node] | None = None,
) -> Generator[Node, None, None]:
    """Depth-first search as a Generator."""
    visited = visited or set()
    if node in visited:
        return
    visited.add(node)

    yield node

    for child in graph[node]:
        yield from dfs_generator(child, graph, visited)


for node in dfs_generator("A", graph):
    print(f"{node=}")

print("\n--- 3 DFS with goal ---\n")


def is_goal(node: Node, goal: Node) -> bool:
    """Process given node, return `True` if node fulfills goal condition,
    otherwise return `False`.
    """
    print(f"{node=}")
    if node == goal:
        print("Yay!")
        return True
    return False


def dfs_with_goal(
    node: Node,
    graph: AdjacencyList,
    goal: Node,
    visited: set[Node] | None = None,
) -> bool:
    """Depth-first search that stops at a given goal. Return `True` if the goal
    was found, otherwise return `False`.
    """
    visited = visited or set()
    if node in visited:
        return False
    visited.add(node)

    if is_goal(node, goal=goal):
        return True

    return any(dfs_with_goal(child, graph, goal, visited) for child in graph[node])


found = dfs_with_goal("A", graph, goal="K")
print(f"{found=}")

print()

found = dfs_with_goal("A", graph, goal="not available")
print(f"{found=}")  # false

print("\n--- 4 DFS with path to goal ---\n")


def dfs_with_path(
    node: Node,
    graph: AdjacencyList,
    goal: Node,
    visited: set[Node] | None = None,
    path: list[Node] | None = None,
) -> list[Node] | None:
    """Depth-first search that stops at a given goal. Return the path to the
    goal or `None` if no Node with matching the goal criterion found.
    """
    visited = visited or set()
    path = path or []
    if node in visited:
        return None
    visited.add(node)

    path.append(node)

    if is_goal(node, goal=goal):
        return path

    for child in graph[node]:
        result = dfs_with_path(child, graph, goal, visited, path)
        if result:
            return result

    path.pop()
    return None


path_to_goal = dfs_with_path("A", graph, goal="K")
print(f"{path_to_goal=}")

print()
path_to_goal = dfs_with_path("A", graph, goal="not available")
print(f"{path_to_goal=}")
