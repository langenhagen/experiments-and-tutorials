#!/usr/bin/env python3
"""A function to deep-check types in a dict."""

from collections.abc import Generator


def typescan(obj: dict, root_path: str = "") -> Generator:
    """Recursively traverse nested dicts and lists and
    yield (path, key_type, value_type) tuples.

    Note: This function is likely incomplete for iterable types.
    """
    if isinstance(obj, dict):
        for k, v in obj.items():
            key_path = f"{root_path}.{k}" if root_path else str(k)
            yield (key_path, type(k).__name__, type(v).__name__)
            yield from typescan(v, key_path)
    elif isinstance(obj, (list, tuple)):
        for i, v in enumerate(obj):
            index_path = f"{root_path}[{i}]"
            yield (index_path, "int", type(v).__name__)
            yield from typescan(v, index_path)
    elif isinstance(obj, (set, frozenset)):
        for i, v in enumerate(obj):
            idx_path = f"{root_path}{{{i}}}"
            yield (idx_path, "<set item>", type(v).__name__)
            yield from typescan(v, idx_path)


class C:
    """Random simple class for show."""

    def __init__(self, x: int) -> None:
        """Initialize C."""
        self.x = x


d = {
    "a": 1,
    "b": {
        "ba": [
            10,
            20.5,
            "foo",
            {
                "baa": 999,
            },
        ],
        "bb": {
            "bba": 12.3,
            "bbb": [
                {
                    "x": 1,
                },
                {"y": [2, 3]},
                C(42),
            ],
        },
    },
    "c": [
        1.1,
        {
            "ca": "bar",
            "cb": [C(42), 7],
        },
        [
            {"cc": 5},
            [6, {"cd": 7.77}],
        ],
    ],
    5: "int key",
    (1, 2): {
        "somekey": C(3),
    },
    6: (1, "yay!"),
}


t = list(typescan(d))

print(t)
