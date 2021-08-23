"""
Showcase the usage of a for-else clause.

see: https://book.pythontips.com/en/latest/for_-_else.html

author: andreasl
"""

l = [1, 2, 3]

for i in l:
    if i == 4:
        print("Found the 4!")
        break
else:
    print("Didn't find a 4!")
