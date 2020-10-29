#!/usr/bin/env python3
"""Showcase Python's comparison operator resolution order."""


def nested():
    res = ""
    print("*combined*")
    if a:
        res += f"a:{a} "
        if b or c:
            res += f"b:{b} "
            res += f"c:{c} "
    print(res)
    return res


def combined():
    print("*nested*")
    res = ""
    if a and b or c:
        res += f"a:{a} "
        res += f"b:{b} "
        res += f"c:{c} "
    print(res)
    return res


print("--- 1 ---")
a = False
b = False
c = False
print(f"vars set to: a={a}, b={b}, c={c}")

n = nested()
c = combined()
if n == c:
    print("nested()/combined() have same result")
else:
    print("nested()/combined() are different!!!!!")

print("--- 2 ---")
a = True
b = False
c = False
print(f"vars set to: a={a}, b={b}, c={c}")

n = nested()
c = combined()
if n == c:
    print("nested()/combined() have same result")
else:
    print("nested()/combined() are different!!!!!")

print("--- 3 ---")
a = False
b = True
c = False
print(f"vars set to: a={a}, b={b}, c={c}")

n = nested()
c = combined()
if n == c:
    print("nested()/combined() have same result")
else:
    print("nested()/combined() are different!!!!!")

print("--- 4 ---")
a = True
b = True
c = False
print(f"vars set to: a={a}, b={b}, c={c}")

n = nested()
c = combined()
if n == c:
    print("nested()/combined() have same result")
else:
    print("nested()/combined() are different!!!!!")

print("--- 5 ---")
a = False
b = False
c = True
print(f"vars set to: a={a}, b={b}, c={c}")

n = nested()
c = combined()
if n == c:
    print("nested()/combined() have same result")
else:
    print("nested()/combined() are different!!!!!")

print("--- 6 ---")
a = True
b = False
c = True
print(f"vars set to: a={a}, b={b}, c={c}")

n = nested()
c = combined()
if n == c:
    print("nested()/combined() have same result")
else:
    print("nested()/combined() are different!!!!!")

print("--- 7 ---")
a = False
b = True
c = True
print(f"vars set to: a={a}, b={b}, c={c}")

n = nested()
c = combined()
if n == c:
    print("nested()/combined() have same result")
else:
    print("nested()/combined() are different!!!!!")

print("--- 8 ---")
a = True
b = True
c = True
print(f"vars set to: a={a}, b={b}, c={c}")

n = nested()
c = combined()
if n == c:
    print("nested()/combined() have same result")
else:
    print("nested()/combined() are different!!!!!")
