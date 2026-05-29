#!/usr/bin/env python3
"""Showcase the usage of Python's `collections.Counter` - a multiset/bag."""

from collections import Counter

print("\n--- 1 Creating Counters from different sources ---\n")

c1 = Counter(["a", "b", "a", "c", "b", "b"])
print(f"Counter from iterable: {c1=}")

c2 = Counter({"a": 3, "b": 1})
print(f"Counter from dict:     {c2=}")

c3 = Counter(a=4, b=2, c=0)
print(f"Counter from kwargs:   {c3=}")

c4 = Counter("hello")
print(f"Counter from string:   {c4=}")

print("\n--- 2 Accessing counts ---\n")

c = Counter(a=3, b=1, d=5)
print(f"{c=}")
print(f"{c['a']=}")
print(f"{c['b']=}")
print(f"{c['missing']=}   missing key returns 0, no KeyError!")

# Counter doesn't support `.get()` with a default, just `[]`.
print(f"{c.get('missing', 'fallback')=}")

print("\n--- 3 most_common() ---\n")

c = Counter(a=3, b=5, c=1, d=8)
print(f"{c=}")
print(f"{c.most_common()=}")
print(f"{c.most_common(2)=}")
print(f"{c.most_common()[-1]=}   # least common")

print("\n--- 4 elements() - iterate over elements with multiplicity ---\n")

c = Counter(a=3, b=2, c=0, d=-1)
print(f"list(elements()) = {list(c.elements())}")  # c and d excluded (<=0)

print("\n--- 5 total() - sum of counts ---\n")

c = Counter(a=3, b=2, c=5)
print(f"{c.total()=}")

print("\n--- 6 update() - add counts from another iterable/mapping ---\n")

c = Counter(a=3, b=1)
c.update(["a", "b", "a"])
print(f"after update(['a', 'b', 'a']): {c=}")

print("\n--- 7 subtract() - remove counts (can go negative) ---\n")

c = Counter(a=3, b=2)
c.subtract(Counter(a=5, b=1, c=4))
print(f"after subtract(Counter(a=5,b=1,c=4)): {c=}")

print("\n--- 8 Arithmetic: +, -, &, | ---\n")

x = Counter(a=3, b=1, c=2)
y = Counter(a=1, b=2, d=4)

print(f"{x + y=}    # union of counts (sum, keep all keys)")
print(f"{x - y=}    # difference (only positive results)")
print(f"{x & y=}    # intersection (min per key)")
print(f"{x | y=}    # union (max per key)")

print("\n--- 9 Multiset comparison operations ---\n")

a = Counter(a=3, b=1, c=2)
b = Counter(a=2, b=1)
c = Counter(a=5)

print(f"{a=}")
print(f"{b=}")
print(f"{c=}")
print(f"{b <= a=}   # b is a sub-multiset of a")
print(f"{c <= a=}   # c is NOT a sub-multiset of a")
print(f"{b + c <= a=}")

print("\n--- 10 Clearing and unary plus/minus ---\n")

c = Counter(a=2, b=-1, c=0)
print(f"{+c=}   # + removes non-positive counts")
print(f"{-c=}   # - negates counts, then removes non-positive")

c.clear()
print(f"after clear(): {c=}")

print("\n--- 11 Converting Counter to dict ---\n")

c = Counter(a=3, b=2, c=1)
print(f"{c=}")
print(f"{dict(c)=}")
print(f"{dict(c.items())=}")

print("\n--- 12 Counter from scratch - defaultdict-like pattern ---\n")

c = Counter()
words = ["apple", "banana", "apple", "cherry", "banana", "apple"]
for w in words:
    c[w] += 1  # no KeyError because Counter starts at 0
print(f"{c=}")
