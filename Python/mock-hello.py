#!/usr/bin/env python3
"""Showcase simple mocking in python.

In Python 3 there is unittest.mock. In Python 2, get the same library via:
pip install mock

See:
https://realpython.com/python-mock-library/#the-python-mock-library

Run this file like:
$ python mock-hello.py
$ pytest -s mock-hello.py  # suppress capturing/hiding ouput
"""
import json
import unittest.mock

import pytest

print("--- 1 The Mock object ---")
mock = unittest.mock.Mock(name="My optional name")
print(f"mock.name= {mock.name}")  # does not resolve to the mock's name but to a new mock!
# Mocks return mocks; mock is a mock, so is mock.foo, and mock.bar()
print(f"mock.foo= {mock.foo}")
print(f"mock.bar()= {mock.bar()}")
print(f"mock.soo('some string', 42)= {mock.soo('some string', 42)}")

print("--- 2 Mocking assertions ---")
# mock.foo.assert_called_once()  # breaks mock.foo([...]) was never called
mock.bar.assert_called_once()
mock.soo('some string', 42)
mock.soo.assert_called_with('some string', 42)
# soo.assert_called_once_with('some string', 42)  # breaks: called 2 times

print("--- 3 Getting Usage Information ---")
print(mock.call_args)  # None
print(mock.call_args_list)  # []
print(mock.method_calls)  # [call.bar(), call.soo('some string', 42), call.soo('some string', 42)]


print("--- 4 Mock Return Values ---")
mock.foo.return_value = 42
print(f"mock.foo()= {mock.foo()}")
print(f"mock.foo('somearg')= {mock.foo('somearg')}")

print("---5 Mock Side Effects ---")


def test_mock_sideeffect():
    """Showcase example of  how to use side effects in tests."""
    mock.doo.side_effect = ValueError
    with pytest.raises(ValueError):
        mock.doo()


# Also:
def my_side_effect_fun(myarg):
    """Implement another side effect."""
    print(f"> Hello from my_side_effect_fun({myarg})")


mock.bar.side_effect = my_side_effect_fun
mock.bar("See some sideeffects!")


# Also also with iterables
def my_other_side_effect_fun(myarg):
    """Implement another side effect."""
    print(f"> Hello from my_other_side_effect_fun({myarg})")


# This doesn't work on my machine but should
# mock.bar.side_effect = [my_other_side_effect_fun, my_side_effect_fun]
# mock.bar("See some iterable sideeffects 1")
# mock.bar("See some iterable sideeffects 2")
# #mock.bar("See some iterable sideeffects 3") # breaks; out of side_effects

mock.bar.side_effect = None
mock.bar("See some iterable sideeffects 3")  # works

print("--- 4 @patch Decorator ---")
# use a patch decorator when you mock something that lies outside the file
# mock where you use it, not where it is defined!
@unittest.mock.patch("json.loads")  # point to where it is used, not where it is defined
def test_with_patch(mock_loads):  # param mock_loads comes from the decorator
    """Test showcasing @patch decorator."""
    mock_loads.return_value = "My mocked return value"
    res = json.loads({"this": "works"})
    print(f"json.loads({{'this': 'works'}})= {res}")
    assert res == "My mocked return value"

@unittest.mock.patch("json.dumps")
@unittest.mock.patch("json.loads")
def test_with_two_patches(mock_loads, mock_dumps):  # upper/outer patch-decorator related params come later
    """Test showcasing @patch decorator."""
    mock_dumps.return_value = '{"a": "123"}'
    res = json.dumps("soeinquatsch")
    print(f"json.dumps(\"soeinquatsch\")= {res}")
    assert res == '{"a": "123"}'

    mock_loads.return_value = "My mocked return value"
    res = json.loads({"this": "works"})
    print(f"json.loads({{'this': 'works'}})= {res}")
    assert res == "My mocked return value"


print("--- 5 Alternative: Patch with a Context Manager")


def test_with_contextmanager():
    """Test showcasing @patch decorator."""
    res = json.loads('{"a":"1"}')
    print(f"json.loads('{{\"a\":\"1\"}}')= {res}")
    assert res == {"a": "1"}

    with unittest.mock.patch("json.loads") as mock_loads:
        mock_loads.return_value = "Quatsch"
        res = json.loads('{"x":"9"}')
        print(f"json.loads('{{\"x\":\"9\"}}')= {res}")
        assert res == "Quatsch"

    res = json.loads('{"b":"2"}')
    print(f"json.loads('{{\"a\":\"1\"}}')= {res}")
    assert res == {"b": "2"}


print("--- 6 @patch.object ---")
# see: https://stackoverflow.com/questions/29152170/what-is-the-difference-between-mock-patch-object-and-mock-patch

# mock.patch() doesn't require that you import the object before patching
# mock.patch.object() does require that you import the object
# mock.patch.object() is good when you have a reference to the object already
# also, mock.patch.object() would complain if the given object does not have the patched fied,
# whereas mock.patch() would simply add it

@unittest.mock.patch.object(json, "loads", return_value="big quatsch")
def test_with_partial_mock(mock_dumps):
    res = json.dumps({})
    print(f"json.dumps({{}})= {res}")
    assert res == "{}"

    res = json.loads({})
    print(f"json.loads({{}})= {res}")
    assert res == "big quatsch"

    # beware that mocks shadow original interfaces
    res = json.loads({}, "some-non-conformant-interface")
    print(f"json.loads({{}})= {res}")
    assert res == "big quatsch"


print("--- 7 Provide an Interface Spec for a Mock ---")
# apparently as good as using mock.patch.object()
mock = unittest.mock.Mock(spec=["foo", "bar", "soo"])

mock.foo()
mock.foo("yeah")
mock.bar()
mock.soo
try:
    mock.boom # crashes, since not specified
except AttributeError:
    print("mock.boom has caused an AttributeError")
try:
    mock.fazz() # crashes, since not specified
except AttributeError:
    print("mock.fazz() has caused an AttributeError")


# take the interface of a given object
mock = unittest.mock.Mock(spec=json)
mock.loads()
mock.dumps("yeah", "yeah")
try:
    mock.boom # crashes, since not specified
except AttributeError:
    print("mock.boom has caused an AttributeError")
try:
    mock.fazz() # crashes, since not specified
except AttributeError:
    print("mock.fazz() has caused an AttributeError")

# create_autospect() is a convenience method for using a given objects interface
# create_autospec() also takes function arguments into account, thus the best
mock = unittest.mock.create_autospec(json)
mock.loads({})
mock.dumps("yeah")
try:
    mock.boom # crashes, since not specified
except AttributeError:
    print("mock.boom has caused an AttributeError")
try:
    mock.fazz() # crashes, since not specified
except AttributeError:
    print("mock.fazz() has caused an AttributeError")


print("--- 8 patch() and autospec ---")
# use autospec - use it
with unittest.mock.patch("json.loads", autospec=True) as mock_json_loads:
    mock_json_loads("arg")
    try:
        mock_json.foo("shouldbreak")
    except AttributeError:
        print("Calling json.foo('shouldbreak') caused an AttributeError")