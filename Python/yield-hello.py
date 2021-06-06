"""
Fun and games with the statement "yield" which is similar to "return".
"""

print("=== 1 Calling a Generator, a function with a yield, sev times ===")


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

print("=== 2 Resetting a Generator function ===")
f = foo(122)
for a in f:
    print(f"0: {a}")
f = foo(20)
for a in f:
    print(f"1: {a}")

print("=== 3 Calling a nested yield function ===")


def bar():
    print("init call to bar")
    yield from foo(16)


for i in bar():
    print(f"bar: {i}")
