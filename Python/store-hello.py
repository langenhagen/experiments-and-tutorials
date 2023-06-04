"""Showcase a a generic way of writing a Store in Python"""
from abc import ABC, abstractmethod
from dataclasses import dataclass
from typing import Generator, Generic, Iterable, TypeVar

T = TypeVar("T")


class Store(ABC, Generic[T]):
    """Defines a general interface for data persistence stores."""

    @abstractmethod
    def add(self, new_user: T):
        """Create a new object into persistency."""

    @abstractmethod
    def get_by(self, **kwargs) -> Generator[T, None, None]:
        """Retrieve all objects for which all given kwargs match. If no kwarg
        was given, return all objects."""


class StoreInsertionException(Exception):
    """Error happening when attempting to add something to a store"""


class InMemoryStore(Store[T]):
    """Defines a generic in-memory implementation of the Store ABC in a small
    versatile way.

    Stores objects in a list. For query, traverses the list of items to find
    suitable ones. This effectively gives it linear big-O query performance.
    For appending, queries over the list of unique keys
    """

    def __init__(self, unique_keys: Iterable = frozenset()):
        """Initialize an empty store.
        Optionally specify the object attributes whose values shall be
        unique over all items in the store."""

        self.__unique_keys = frozenset(unique_keys)
        self.__objects: list[T] = []

    def get_by(self, **kwargs) -> Generator[T, None, None]:
        """Retrieve all objects for which all given kwargs match. If no kwarg
        was given, return all objects."""
        for o in self.__objects:
            if all(getattr(o, attr) == value for attr, value in kwargs.items()):
                yield o

    def add(self, new: T):
        """Create a new object into in-memory persistency.

        Raise a `DatabaseInsertionException` in case the new item clashes with
        any existing item on any unique key."""

        for key in self.__unique_keys:
            for _ in self.get_by(**{key: getattr(new, key)}):
                raise StoreInsertionException(f'Item not unique for key "{key}"')

        self.__objects.append(new)


@dataclass
class User:
    """A simple object."""

    name: str
    age: int


if __name__ == "__main__":
    my_store = InMemoryStore[User](unique_keys={"name"})

    my_store.add(User(name="Andi", age=12))
    my_store.add(User(name="Mandi", age=12))
    my_store.add(User(name="Candi", age=13))

    items = list(my_store.get_by(name="Andi"))
    print(items)  # [User(name='Andi', age=12)]
    items =  list(my_store.get_by(age="Andi"))
    print(items)  # []
    items = list(my_store.get_by(age=12))
    print(items)  # [User(name='Andi', age=12), User(name='Mandi', age=12)]
    items = list(my_store.get_by(name="Mandi", age=12))
    print(items)  # [User(name='Mandi', age=12)]

    print("All items:")
    for x in my_store.get_by():
        print(x)  # all items

    try:
        my_store.add(User(name="Andi", age=80))
    except StoreInsertionException as err:
        print(f"Well, you got yourself an errror:\n{err}")

    # drop into a BPython shell
    # from bpython import embed
    # embed(locals_=locals(), banner="\nDropping to interactive shell\n")
