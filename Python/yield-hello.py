"""
Fun and games with the statement "yield" which is similar to "return".
"""

def foo(i):
    if i < 10:
        return
    for i in range(10):
        yield i


for a in foo(20):
    print(a)


for a in foo(5):
    print(a)
