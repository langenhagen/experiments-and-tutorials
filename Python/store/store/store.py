"""Store interface definition."""

from abc import ABC, abstractmethod
from typing import Any, Generator, Generic, TypeVar

T = TypeVar("T")


class Store(ABC, Generic[T]):
    """Defines a general interface for data persistence stores."""

    @abstractmethod
    def add(self, new: T) -> T:
        """Create a new object into persistency."""

    @abstractmethod
    def get_by(self, **kwargs) -> Generator[T, None, None]:
        """Retrieve all objects for which all given kwarg query field/value
        pairs match. If no kwarg was given, yield all objects."""

    def save(self, obj: T):
        """Update an object created by or retrieved from the store."""

    @abstractmethod
    def update(self, fields: dict[str, Any], **query) -> int:
        """Update all objects matching all the query criteria to the given
        name-value mappings."""
