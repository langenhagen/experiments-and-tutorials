"""Tests for `in_memory_store.py`."""
from dataclasses import dataclass, field
from typing import Generator

import pytest
from pytest import fixture

from store import InMemoryStore, NotFromStoreException, NotUniqueException


@dataclass
class Person:
    """A test object for storage."""

    name: str
    age: int
    hobbies: list[str] = field(default_factory=list)


@fixture(name="andy")
def fixture_andy():
    """The test person Andy."""
    return Person(name="Andy", age=12, hobbies=["Star Wars", "Bicycles"])


@fixture(name="pandy")
def fixture_pandy():
    """The test person Pandy."""
    return Person(name="Pandy", age=12, hobbies=["Fortnite"])


@fixture(name="candy")
def fixture_candy():
    """The test person Candy."""
    return Person(name="Candy", age=13, hobbies=["Gardening"])


@fixture(name="test_store")
def fixture_test_store(andy, pandy, candy):
    """A prepopulated store with a unique-key requirement on the field
    "name"."""
    store_ = InMemoryStore[Person](unique_keys={"name"})
    store_.add(andy)
    store_.add(pandy)
    store_.add(candy)
    return store_


def test_query_one(andy, pandy):
    """Assert that the store instantiates proper, that storing and simple
    retrieval of items works and that the store returns an item that is
    identical but deeply not the same as the initially entered item."""
    my_store = InMemoryStore[Person](unique_keys={"name"})
    my_store.add(andy)
    my_store.add(pandy)
    andy.hobbies.append("will not propagate after adding")

    items = list(my_store.get_by(name="Andy"))
    assert len(items) == 1

    queried = items[0]
    assert queried.hobbies == ["Star Wars", "Bicycles"]
    andy.hobbies.pop(-1)
    assert andy is not queried
    assert andy == queried


def test_query_with_no_matches_returns_nothing(test_store):
    """Assert that the store instantiates proper, that storing and simple
    retrieval of items works."""
    items = list(test_store.get_by(name="Sugar"))

    assert len(items) == 0


def test_query_multiple(test_store, andy, pandy):
    """Assert that querying multiple items works."""
    items = list(test_store.get_by(age=12))
    assert len(items) == 2
    assert andy in items
    assert pandy in items


def test_query_all(test_store, andy, pandy, candy):
    """Assert that querying all items works."""
    items = list(test_store.get_by())
    assert len(items) == 3
    assert andy in items
    assert pandy in items
    assert candy in items


def test_consecutive_queries_yield_different_individual_items(test_store):
    """Assert that different queries with the same arguments return distinct
    objects."""
    queried = next(test_store.get_by(name="Andy"))
    other = next(test_store.get_by(name="Andy"))

    assert queried is not other
    assert queried == other


def test_query_for_unknown_attribute_raise(test_store):
    """Assert that queries for unknown attributes raise an `AttributeError`."""
    query: Generator[Person, None, None] = test_store.get_by(not_the_droids=9000)

    with pytest.raises(AttributeError):
        next(query)


def test_update_with_no_matches(test_store, andy, pandy, candy):
    """Assert that `InMemoryStore.update()` for unmatched queries works and does
    not mess up existing items."""
    n_updated = test_store.update(fields={"age": 15}, name="Mark")
    assert n_updated == 0

    items = list(test_store.get_by())
    assert len(items) == 3
    assert andy in items
    assert pandy in items
    assert candy in items


def test_update(test_store, andy, pandy, candy):
    """Assert that `InMemoryStore.update()` works for a single item."""
    n_updated = test_store.update(fields={"age": 15}, name="Candy")
    assert n_updated == 1
    items = list(test_store.get_by())

    candy.age = 15
    assert andy in items
    assert pandy in items
    assert candy in items


def test_update_multiple(test_store, andy, pandy, candy):
    """Assert that `InMemoryStore.update()` for multiple items works."""
    n_updated = test_store.update(fields={"age": 14}, age=12)
    assert n_updated == 2
    items = list(test_store.get_by())

    andy.age = pandy.age = 14
    assert andy in items
    assert pandy in items
    assert candy in items


def test_update_all(test_store, andy, pandy, candy):
    """Assert that `InMemoryStore.update()` for all items works."""
    n_updated = test_store.update(fields={"age": 13})
    assert n_updated == 3
    items = list(test_store.get_by())

    andy.age = pandy.age = 13
    assert andy in items
    assert pandy in items
    assert candy in items


def test_add_item_with_duplicate_value_on_unique_field_raises(
    test_store, andy, pandy, candy
):
    """Assert that adding an item with a duplicate value on a "unique"-listed
    field raises a `NotUniqueException`."""

    person_with_duplicate_name = Person(name="Andy", age=80)

    with pytest.raises(NotUniqueException):
        test_store.add(person_with_duplicate_name)

    items = list(test_store.get_by())
    assert len(items) == 3
    assert andy in items
    assert pandy in items
    assert candy in items


def test_update_on_unique_field_raises(test_store):
    """Assert that updating on a unique field raises in order to avoid coming
    into hell's kitchen."""

    with pytest.raises(NotImplementedError):
        test_store.update(fields={"name": "Andy"})


def test_save(test_store, pandy, candy):
    """Assert that changing and saving an object works."""

    person = next(test_store.get_by(name="Andy"))
    assert person.age == 12
    person.age = 55

    test_store.save(person)

    items = list(test_store.get_by())
    assert len(items) == 3
    assert person in items
    assert pandy in items
    assert candy in items


def test_save_item_not_from_store_raises(test_store):
    """Assert that saving an item that the store did not create raises a
    `NotFromStoreException`."""

    sugar = Person("Sugar", age=15)
    with pytest.raises(NotFromStoreException):
        test_store.save(sugar)


def test_save_item_with_duplicate_value_on_unique_field_raises(
    test_store, andy, pandy, candy
):
    """Assert that saving an item with a duplicate value on a "unique"-listed
    field raises a `NotUniqueException`."""

    person = next(test_store.get_by(name="Andy"))
    person.name = "Pandy"

    with pytest.raises(NotUniqueException):
        test_store.save(person)

    items = list(test_store.get_by())
    assert len(items) == 3
    assert andy in items
    assert pandy in items
    assert candy in items


def test_items_from_different_stores_do_not_mingle():
    """Assert that saving an item from one store into another store raises."""

    my_store = InMemoryStore[Person]()
    carl = my_store.add(Person("Carl", age=22))
    carl.age = 23

    other_store = InMemoryStore[Person]()
    with pytest.raises(NotFromStoreException):
        other_store.save(carl)

    my_store.save(carl)
