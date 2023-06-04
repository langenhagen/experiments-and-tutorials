"""A simple deepcopy-based in-memory implementation of the `Store` interface."""
from copy import deepcopy
from typing import Any, Generator, Iterable, TypeVar

from store.exceptions import StoreAddException
from store.store import Store

T = TypeVar("T")


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
