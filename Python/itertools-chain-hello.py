"""
Fun with itertools.
itertools.chain() is apparently able to chain even iterator objects.

"""

import itertools

import requests

a = requests.get("http://google.com")
b = requests.get("http://twitter.com")
c = requests.get("http://instagram.com")

total_abc = 0

chain = itertools.chain(a.iter_lines(), b.iter_lines(), c.iter_lines())
with open("abc.log", "w") as file:
    for i, e in enumerate(chain):
        print(i)
        file.write(str(e) + "\n")
        total_abc += 1

print("*" * 100)

total_a = 0

chain = itertools.chain(a.iter_lines())
with open("a.log", "w") as file:
    for i, e in enumerate(chain):
        print(i)
        file.write(str(e) + "\n")
        total_a += 1

print("*" * 100)

total_b = 0

chain = itertools.chain(b.iter_lines())
with open("b.log", "w") as file:
    for i, e in enumerate(chain):
        print(i)
        file.write(str(e) + "\n")
        total_b += 1

print("*" * 100)

total_c = 0

chain = itertools.chain(c.iter_lines())
with open("c.log", "w") as file:
    for i, e in enumerate(chain):
        print(i)
        file.write(str(e) + "\n")
        total_c += 1

print(
    f"total_abc: {total_abc}\n"
    f"total_a: {total_a}\n"
    f"total_b: {total_b}\n"
    f"total_c: {total_c}\n"
    f"sum total_{{a,b,c}}': {total_a + total_b + total_c}\n"
)
