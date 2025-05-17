"""Showcase that deepcopy() appears to not call a class's `__init__()`"""

from copy import deepcopy


class C:
    my_class_var = 0

    def __init__(self) -> None:
        print("Hi!")
        C.my_class_var += 1


assert C.my_class_var == 0

c = C()
assert C.my_class_var == 1

d = deepcopy(c)  # no "Hi!"
assert C.my_class_var == 1
