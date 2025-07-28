#!/usr/bin/env python3
"""Showcase the usage of the 3rd party package `pydantic` for data validation
and parsing using Python type annotations.

author: andreasl
"""

import json
from enum import Enum

from bpython import embed
from pydantic import BaseModel, Field, ValidationError

print("--- 1 simple model ---\n")


class Address(BaseModel):
    """A Pydantic model for an address."""

    street: str
    city: str
    zip_code: str


class Status(str, Enum):
    """Enum representing various statuses."""

    ACTIVE = "active"
    INACTIVE = "inactive"


class Person(BaseModel):
    """A simple Pydantic model for a person."""

    name: str
    age: int
    status: Status
    address: Address
    hobbies: list[str] = Field(default_factory=list)

    @property
    def myprop(self) -> float:
        """Won't ever be included in dumps."""
        return 42


a = Person(
    name="Alice",
    age=30,
    hobbies=["reading", "hiking"],
    status=Status.ACTIVE,
    address=Address(
        street="123 Maple St",
        city="Wonderland",
        zip_code="12345",
    ),
)
print(a)

print("\n--- 2 invalid models ---\n")

try:
    b = Person(
        name="Bob",
        age="thirty",  # error: this should be an int or int-parseable
        status=Status.ACTIVE,
        address=Address(
            street="456 Oak St",
            city="Springfield",
            zip_code="54321",
        ),
    )
except ValidationError as e:
    print(f"Error: {e}")

print("\n--- 3 Pydantic can automatically validate and parse inputs ---\n")

c = Person(
    name="Charlie",
    age="40",  # hand in age number as a string, gets parsed automatically
    hobbies=["gaming", "coding"],
    status="active",  # hand in a string, get according enum value
    address=Address(
        street="789 Pine St",
        city="Gotham",
        zip_code="67890",
    ),
)
print(c)

print("\n--- 4 recursive JSON serialization of individual models---\n")

aj = (a.model_dump_json())  # string!
print(f"{type(aj)=}\n{aj=}")

amj = a.model_dump(mode="json")  # dict
print(f"{type(amj)=}\n{amj=}")

print("\n--- 5 recursive to dict ---\n")

ad = a.model_dump()  # retains the enum; not JSON serializable

print(f"{type(ad)=}\n")
print(json.dumps(ad, indent=4))

print("\n--- 6 model_dump(include=...) omits everything not included ---\n")

ad2 = a.model_dump(
    mode="json", include={"name", "myprop"}
)  # will only contain `name` field
print(f"{ad2=}")

print("\n--- 8 introspection ---\n")

print(f"{Person.model_fields=}")

print("\n--- 9 interactive playground ---\n")

embed(locals_=locals(), banner="\nDropping to interactive shell\n")
