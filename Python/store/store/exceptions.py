"""Exception definitions for the store."""


class NotUniqueException(Exception):
    """To be raised when storing an item would break uniqueness-contraints."""


class NotFromStoreException(Exception):
    """To be raised when an operation expects an item created or retrieved by
    the store but got another."""
