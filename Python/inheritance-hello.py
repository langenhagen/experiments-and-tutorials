"""Showcase inheritance."""

print("--- 1 simple inheritance ---\n")


class C:
    def __init__(self):
        print("Hello from C.__init__!")


class D(C):
    pass


c = C()  # calls the print
d = D()  # also calls the print
