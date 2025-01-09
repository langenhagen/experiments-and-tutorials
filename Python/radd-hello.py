"""Showcase `__radd__()` which allows for additions in case the left-hand operand does not support
the addition with the (right hand operand's type).
"""


class C:
    def __add__(self, o):
        return f"calling __add__! {o}"

    def __radd__(self, o):
        print("calling __radd__!")
        return self.__add__(o)


c = C()

print(f"{c + 4}")
print(f"{3 + c}")
