"""
A generic data store interface and implementation.

author: andreasl
"""
from .exceptions import StoreAddException  # noqa
from .in_memory_store import InMemoryStore  # noqa
from .store import Store  # noqa
