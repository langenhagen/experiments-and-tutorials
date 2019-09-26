# Hello world with Pytest:
#
# Run like
# $ pytest pytest-hello.py
#
# found here:
# https://www.oreilly.com/library/view/python-testing-with/9781680502848/f_0046.xhtml
import pytest


def greet(name):
    print('Hi, {}'.format(name))


def raise_exc():
    print('Fooo')
    raise ValueError("This is an exception")


def test_greet(capsys):
    greet('Earthling')
    out, err = capsys.readouterr()
    assert out == 'Hi, Earthling\n'
    assert err == ''

    greet('Brian')
    greet('Nerd')


def test_raise_exc(capsys):
    with pytest.raises(ValueError):
        raise_exc()
    out, err = capsys.readouterr()
    assert out == 'Foo'
    assert err == ''
