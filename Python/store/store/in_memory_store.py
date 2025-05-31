"""A simple deepcopy-based in-memory implementation of the `Store` interface."""

from collections.abc import Generator, Iterable
from copy import deepcopy
from typing import Any, TypeVar

from store.exceptions import NotFromStoreException, NotUniqueException
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

    def __init__(self, unique_keys: Iterable = frozenset()) -> None:
        """Initialize an empty store.
        Optionally specify the object attributes whose values shall be
        unique over all items in the store.
        """

        self.__store_marker = f"_in_memory_store_id{id(self)}"
        self.__unique_keys = frozenset(unique_keys)
        self.__objects: list[T] = []

    def __get_by_orig(self, **kwargs) -> Generator[T, None, None]:
        """Retrieve the original stored items matching the query."""
        for o in self.__objects:
            if all(getattr(o, attr) == value for attr, value in kwargs.items()):
                yield o

    def get_by(self, **kwargs) -> Generator[T, None, None]:
        """Retrieve deep copies for all objects for which all given kwargs
        match. If no kwarg was given, yield all objects.
        """
        for o in self.__get_by_orig(**kwargs):
            yield deepcopy(o)

    def add(self, new: T) -> T:
        """Create a new deep copied object into in-memory persistency and return
        an updateable copy of it.

        Raise a `DatabaseInsertionException` in case the new item clashes with
        any existing item on any unique key.
        """

        for key in self.__unique_keys:
            for _ in self.__get_by_orig(**{key: getattr(new, key)}):
                raise NotUniqueException(f'Item not unique for key "{key}"')

        copied = deepcopy(new)
        id_ = getattr(new, self.__store_marker, None) or id(copied)
        object.__setattr__(copied, self.__store_marker, id_)
        self.__objects.append(copied)
        return copied

    def update(self, fields: dict[str, Any], **query) -> int:
        """Update all objects matching the query to deep copies of the given
        fields.
        """

        if any(k in self.__unique_keys for k in fields):
            raise NotImplementedError("Updating unique keys is currently not supported")

        count = 0
        for o in self.__get_by_orig(**query):
            count += 1
            for name, value in fields.items():
                setattr(o, name, deepcopy(value))
        return count

    def save(self, obj: T) -> None:
        """Throw away an the old store record and add the given updated item."""
        obj_id = getattr(obj, self.__store_marker, None)
        if obj_id is None:
            raise NotFromStoreException("Item not created by store.")

        for i, orig in enumerate(self.__objects):
            if obj_id != getattr(orig, self.__store_marker):
                continue

            self.__objects.pop(i)

            try:
                self.add(obj)
            except NotUniqueException:
                self.__objects.append(orig)
                raise
