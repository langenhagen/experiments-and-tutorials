#!/usr/bin/env python3
"""Showcase the rotation of binary trees.

author: andreasl
"""

from dataclasses import dataclass
from typing import Optional


@dataclass
class Node:
    """A simple binary tree node representation."""

    parent: Optional["Node"]
    value: str
    left: Optional["Node"]
    right: Optional["Node"]

    def __str__(self) -> str:
        """Supershitty but I can't be bothered."""
        return f"{self.value} l:({self.left}), {self.value} r:({self.right})"

    def rotate_left(self) -> "Node":
        """Rotate a tree at given node to the left and return the new root."""
        if not self.right:
            raise AssertionError("Cannot rotate left: Missing right child!")

        new_root = self.right
        self.right = new_root.left
        if self.right:
            self.right.parent = self

        new_root.left = self

        new_root.parent = self.parent
        self.parent = new_root
        return new_root

    def rotate_right(self) -> "Node":
        """Rotate a tree at given node to the right and return the new root."""
        if not self.left:
            raise AssertionError("Cannot rotate right: Missing left child!")

        new_root = self.left
        self.left = new_root.right
        if self.left:
            self.left.parent = self
        new_root.right = self

        new_root.parent = self.parent
        self.parent = new_root
        return new_root


#         A
#        / \
#       B   C
#      / \ / \
#     D  E F  G
#        /
#       H
root = Node(None, "A", None, None)
b = Node(root, "B", None, None)
c = Node(root, "C", None, None)
d = Node(b, "D", None, None)
e = Node(b, "E", None, None)
f = Node(c, "F", None, None)
g = Node(c, "G", None, None)
h = Node(e, "H", None, None)

# wire children
root.left, root.right = b, c
b.left, b.right = d, e
c.left, c.right = f, g
e.left = h

print(root)
new_root = root.rotate_left()
print(new_root)
new_root = new_root.rotate_right()
print(new_root)

print()

print(root)
new_c_parent = c.rotate_left()
print(root)
new_c_parent.rotate_right()
print(root)
