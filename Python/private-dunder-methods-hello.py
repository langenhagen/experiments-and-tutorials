"""Showcase private dunder methods in Python."""


class C:
    def __foo(self):
        """A private function in python. Only visible inside the class, not
        outside and not to subclasses."""
        print("Hello from __foo()")

    def foo(self):
        self.__foo()  # works


c = C()


print("--- 1 calling __foo() on an object  ---")

try:
    c.__foo()  # breaks with AttributeError: thing is not known
except AttributeError as e:
    print(f"Error:  {e}")

print("--- 2 calling __foo() inside a public method works ---")

c.foo()  # works


print("--- 3 calling _C__foo() on an object works ---")

c._C__foo()


print("--- 4 calling __foo() in a child class breaks ---")


class D(C):
    def bar(self):
        self.__foo()


d = D()

try:
    d.bar()
except AttributeError as e:
    print(f"Error:  {e}")
