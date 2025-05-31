"""collections.namedtuple  vs  typing.NamedTuple  vs dataclasses.dataclass

namedtuples / Namedtuples are immutable.

General suggestion: use dataclasses

author: andreasl
"""

from abc import abstractmethod
from dataclasses import dataclass


class Event:
    @abstractmethod
    def to_json(self) -> dict:
        """:return:"""


@dataclass
class CreateEvent(Event):
    purchase_id: str
    # user_id: str
    # store_id: str
    # reseller_id: Optional[str]

    def to_json(self) -> dict:
        """:return: a json object to be sent to the engine. Object should have a certain structure
        See also Envelope docblock comment for more details
        """
        return {
            "transid": self.purchase_id,
            "ssoid": self.user_id,
            "shopid": self.store_id,
            "resellerid": self.reseller_id,
        }


EEE = CreateEvent(purchase_id="foo")
