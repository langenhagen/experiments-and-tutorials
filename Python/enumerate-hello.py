"""
Experiments wth enumerate().

Showcase that nested enumerate() works as expected.

"""

l = ["a","b","c"]
m = ["x","y","z"]

for i, x in enumerate(l):
    for j, y in enumerate(m):
        print(f"{i}:{j}  {x}{y}")  # works as expected
