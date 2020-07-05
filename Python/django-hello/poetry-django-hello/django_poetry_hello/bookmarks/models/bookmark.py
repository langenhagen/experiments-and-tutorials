import dataclasses
import datetime
import pathlib
from typing import List

import yaml


@dataclasses.dataclass
class Bookmark:
    """A bookmark representation."""
    title: str
    url: str
    created: datetime.datetime
    last_access: datetime.datetime
    rating: int
    comment: str
    tags: List[str] = dataclasses.field(default_factory=list)


def load_bookmark(path: pathlib.Path) -> Bookmark:
    """Load a bookmark from the given path."""
    with open(path, 'r') as file:
        bookmark_yaml = yaml.load(file, Loader=yaml.FullLoader)
    bookmark = Bookmark(**bookmark_yaml)
    bookmark.created =  datetime.datetime.strptime(bookmark.created, '%Y-%m-%d %H:%M')
    bookmark.last_access =  datetime.datetime.strptime(bookmark.last_access, '%Y-%m-%d %H:%M')
    return bookmark


def load_bookmarks() -> List[Bookmark]:
    """Load all bookmarks from filesystem."""
    bookmarks_path = pathlib.Path.home() / ".config/barn-bookmarks/bookmarks"
    bookmarks = []
    for path in bookmarks_path.glob("*.yml"):
        bookmarks.append(load_bookmark(path))
    return bookmarks
