#!/usr/bin/env python3
"""Showcase implementations of Breadth-first-search (BFS) for graph traversal.

author: andreasl
"""

from collections import deque
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

print("--- 1 naive BFS ---\n")


def process(node: Node) -> None:
    """Do something with given node."""
    print(f"{node=}")


def bfs(start: Node, graph: AdjacencyList) -> None:
    """Iterate over a graph in Breadth-first search manner."""
    visited = set()
    queue: deque[Node] = deque()
    queue.append(start)
    while queue:
        node = queue.popleft()
        if node in visited:
            continue
        visited.add(node)

        process(node)
        queue.extend(graph[node])


bfs("A", graph)

print("\n--- 2 BFS as Generator ---\n")


def bfs_generator(start: Node, graph: AdjacencyList) -> Generator[str, None, None]:
    """Breadth-first search as a Generator."""
    visited = set()
    queue: deque[Node] = deque()
    queue.append(start)
    while queue:
        node = queue.popleft()
        if node in visited:
            continue
        visited.add(node)
        queue.extend(graph[node])
        yield node


for node in bfs_generator("A", graph):
    print(f"{node=}")

print("\n--- 3 BFS with goal ---\n")


def is_goal(node: Node, goal: Node) -> bool:
    """Process given node, return `True` if node fulfills goal condition,
    otherwise return `False`."""
    print(f"{node=}")
    if node == goal:
        print("Yay!")
        return True
    return False


def bfs_with_goal(
    start: Node, graph: AdjacencyList, goal: Node
) -> Generator[str, None, None]:
    """Breadth-first search that stops at given goal as a Generator."""
    visited = set()
    queue: deque[Node] = deque()
    queue.append(start)
    while queue:
        node = queue.popleft()
        if node in visited:
            continue
        visited.add(node)

        yield node
        if is_goal(node, goal):
            return

        queue.extend(graph[node])


traversal_order = list(bfs_with_goal("A", graph, goal="K"))
print(f"{traversal_order=}")

print()
traversal_order = list(bfs_with_goal("A", graph, goal="not available"))
print(f"{traversal_order=}")


print("\n--- 4 BFS with path to goal ---\n")


def bfs_with_path_to_goal(
    start: Node,
    graph: AdjacencyList,
    goal: Node,
) -> list[Node] | None:
    """Breadth-first search that stops at given goal. Return the first found
    path to the goal or `None` if no Node with matching the goal criterion
    found."""
    visited = set()
    queue: deque[tuple[Node, list[Node]]] = deque()
    queue.append((start, [start]))
    while queue:
        node, path = queue.popleft()
        if node in visited:
            continue
        visited.add(node)

        if is_goal(node, goal):
            return path

        queue.extend((neighbor, path + [neighbor]) for neighbor in graph[node])

    return None


path_to_goal = bfs_with_path_to_goal("A", graph, goal="K")
print(f"{path_to_goal=}")

print()
path_to_goal = bfs_with_path_to_goal("A", graph, goal="not available")
print(f"{path_to_goal=}")
