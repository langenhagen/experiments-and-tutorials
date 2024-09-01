#!/usr/bin/env python3
"""Showcase the usage of the 3rd party `objgraph` library.

Allows for visualizing and tracking object graphs in memory. Useful for
detecting memory leaks.

See: https://mg.pov.lt/objgraph/

author: andreasl
"""
import objgraph

print("--- 1 display growth of object types ---\n")


class MyClass:
    pass


objects = [MyClass() for _ in range(42)]
objgraph.show_growth()

print("\n--- 2 visualize references ---\n")

a = MyClass()
b = MyClass()
a.x = b
b.y = a

print(f"{hex(id(a))=}")
print(f"{hex(id(b))=}")

objgraph.show_refs([a, b], filename="refs-graph.png")

print("Generated reference graph for 'a'. Check 'refs-graph.png'.")

print("\n--- 3 find most common object types ---\n")

objgraph.show_most_common_types()

print("\n--- 4 identify memory leaks ---\n")

leaking_objects = objgraph.by_type("MyClass")
objgraph.show_chain(
    objgraph.find_backref_chain(leaking_objects[0], objgraph.is_proper_module),
    filename="chain-graph.png",
)

print("Generated backreference chain graph. Check 'chain-graph.png'.")
