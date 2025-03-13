"""Showcase private dunder methods in Python."""


class C:
    def __private_method(self):
        """A private function in python. Only visible inside the class, not
        outside and not to subclasses."""
        print("Hello from __private_method()")

    def method(self):
        self.__private_method()  # works

    @staticmethod
    def my_staticmethod():
        C.__private_staticmethod()

    @staticmethod
    def __private_staticmethod():
        print("Hello from __private_staticmethod()")


class D(C):
    def bar(self):
        self.__private_method()


c = C()
d = D()


print("--- 1 calling __private_method() on an object breaks ---\n")
try:
    c.__private_method()  # breaks with AttributeError: thing is not known
except AttributeError as e:
    print(f"Error:  {e}")

print("\n--- 2 calling __private_method() inside a public method works ---\n")
c.method()  # works

print("\n--- 3 calling _C__private_method() on an object works ---\n")
c._C__private_method()

print("\n--- 4 calling __private_method() in a child class breaks ---\n")


try:
    d.bar()
except AttributeError as e:
    print(f"Error:  {e}")

print("\n--- 5 calling __private_staticmethod() on a class breaks ---\n")
try:
    C.__private_staticmethod()  # breaks with AttributeError: thing is not known
except AttributeError as e:
    print(f"Error:  {e}")

print("\n--- 6 calling __private_staticmethod() from a public static method works ---\n")
C.my_staticmethod()
