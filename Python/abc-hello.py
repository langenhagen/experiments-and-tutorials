"""Showcase usage of the module Abstract Base Class."""
import abc

# @abc.abstractstaticmethod is deprecated

class C(abc.ABC):

    @staticmethod
    @abc.abstractmethod
    def foo():
        print("hello")

class D(C):
    pass

class E(D):
    @staticmethod
    def foo():
        print("world")


# c=C()  # fails: can't instantiate abstract class C
C.foo()  # prints hello

# d=D()  # fails: TypeError: Can't instantiate abstract class D with abstract methods foo
D.foo()  # prints hello

e=E()    # works
E.foo()  # prints world
