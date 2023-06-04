"""
Showcase a a generic way of writing a Store in Python

author: andreasl
"""
from abc import ABC, abstractmethod
from copy import deepcopy
from dataclasses import dataclass
from typing import Any, Generator, Generic, Iterable, TypeVar

T = TypeVar("T")


class StoreAddException(Exception):
    """Error happening when attempting to add something to a store"""


class Store(ABC, Generic[T]):
    """Defines a general interface for data persistence stores."""

    @abstractmethod
    def add(self, new: T):
        """Create a new object into persistency."""

    @abstractmethod
    def update(self, fields: dict[str, Any], **query) -> int:
        """Update all objects matching all the query criteria to the given
        name-value mappings."""

    @abstractmethod
    def get_by(self, **kwargs) -> Generator[T, None, None]:
        """Retrieve all objects for which all given kwarg query field/value
        pairs match. If no kwarg was given, yield all objects."""


class InMemoryStore(Store[T]):
    """Defines a generic in-memory implementation of the Store ABC in a small
    versatile way. It stores deep copies of added items and yields a new deep
    copy for every query.

    Stores objects in a list. For query, traverses the list of items to find
    suitable ones. This effectively gives it linear big-O query performance. For
    addition, queries over the list of unique keys.

    The implementation emphasizes on function and simplicity but ignores costs
    of performance. Thus, this store is probably suitable only for tests.
    """

    def __init__(self, unique_keys: Iterable = frozenset()):
        """Initialize an empty store.
        Optionally specify the object attributes whose values shall be
        unique over all items in the store."""

        self.__unique_keys = frozenset(unique_keys)
        self.__objects: list[T] = []

    def __get_by_orig(self, **kwargs) -> Generator[T, None, None]:
        """Retrieve the original stored items matching the query."""
        for o in self.__objects:
            if all(getattr(o, attr) == value for attr, value in kwargs.items()):
                yield o

    def get_by(self, **kwargs) -> Generator[T, None, None]:
        """Retrieve deep copies for all objects for which all given kwargs
        match. If no kwarg was given, yield all objects."""
        for o in self.__get_by_orig(**kwargs):
            yield deepcopy(o)

    def add(self, new: T):
        """Create a new deep copied object into in-memory persistency.

        Raise a `DatabaseInsertionException` in case the new item clashes with
        any existing item on any unique key."""

        for key in self.__unique_keys:
            for _ in self.get_by(**{key: getattr(new, key)}):
                raise StoreAddException(f'Item not unique for key "{key}"')

        copied = deepcopy(new)
        self.__objects.append(copied)

    def update(self, fields: dict[str, Any], **query) -> int:
        """Update all objects matching the query to deep copies of the given
        fields."""
        count = 0
        for o in self.__get_by_orig(**query):
            count += 1
            for name, value in fields.items():
                setattr(o, name, deepcopy(value))
        return count


@dataclass
class User:
    """A simple object."""

    name: str
    age: int


if __name__ == "__main__":
    my_store = InMemoryStore[User](unique_keys={"name"})

    andy = User(name="Andy", age=12)
    my_store.add(andy)
    pandy = User(name="Pandy", age=12)
    my_store.add(pandy)
    candy = User(name="Candy", age=13)
    my_store.add(candy)

    items = list(my_store.get_by(name="Andy"))
    # print(items)  # [User(name='Andy', age=12)]
    assert len(items) == 1
    queried = items[0]
    assert andy is not queried
    assert andy == queried

    second_queried = next(my_store.get_by(name="Andy"))
    assert queried is not second_queried
    assert queried == second_queried

    items = list(my_store.get_by(age=9000))
    assert len(items) == 0

    items = list(my_store.get_by(age=12))
    assert len(items) == 2
    assert andy in items
    assert pandy in items

    items = list(my_store.get_by())
    assert len(items) == 3
    assert andy in items
    assert pandy in items
    assert candy in items

    # print("All items:")
    # for x in my_store.get_by():
    #     print(x)  # all items

    assert my_store.update(fields={"age": 15}, name="Mark") == 0

    assert my_store.update(fields={"age": 12}, name="Candy") == 1
    items = list(my_store.get_by(age=12))
    assert len(items) == 3
    candy.age = 12
    assert andy in items
    assert pandy in items
    assert candy in items

    assert my_store.update(fields={"age": 13}) == 3
    items = list(my_store.get_by())
    assert len(items) == 3
    andy.age = 13
    pandy.age = 13
    candy.age = 13
    assert andy in items
    assert pandy in items
    assert candy in items

    try:
        my_store.add(User(name="Andy", age=80))
    except StoreAddException as err:
        print(f"Well, as expected, you got yourself an errror:\n{err}")

    # drop into a BPython shell
    # from bpython import embed
    # embed(locals_=locals(), banner="\nDropping to interactive shell\n")
