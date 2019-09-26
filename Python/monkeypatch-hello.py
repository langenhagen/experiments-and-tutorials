"""
Showcase Monkeypatching in Python.

Apparently, Monkeypatching only works with non-builtins.
It seems not possible to monkeypatch default exceptions.
"""
################################################################################
# from: https://stackoverflow.com/questions/13550194/python-override-base-class

#---- This is a third-party module ----#
class Base(object):
  def foo(self):
    print('original foo')

class One(Base):
  def bar(self):
    self.foo()

class Two(Base):
  def bar(self):
    self.foo()

#---- This is your module ----#

# Test the original
One().bar()
Two().bar()

# Monkey-patch and test
def patched_Base_foo(self):
  print('monkey-patched foo')

original_Base_foo = Base.foo
Base.foo = patched_Base_foo

One().bar()

# reset the patch again
Base.foo = original_Base_foo
Two().bar()One().bar()
Two().bar()