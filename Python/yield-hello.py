#!/usr/bin/env python3
"""Fun and games with the statement "yield" which is similar to "return"."""

print("--- 1 calling a generator, a function with a yield, sev times ---\n")


def foo(i):
    print("init call to foo")
    if i < 10:
        return
    for i in range(10):
        yield i


print("calling the loops")
for a in foo(20):
    print(f"0: {a}")

for a in foo(5):
    print(f"1: {a}")

print("\n--- 2 resetting a generator function ---\n")

f = foo(122)
for a in f:
    print(f"0: {a}")
f = foo(20)
for a in f:
    print(f"1: {a}")

print("\n--- 3 calling a nested yield function ---\n")


def bar():
    print("init call to bar")
    yield from foo(16)


for i in bar():
    print(f"bar: {i}")
