#!/usr/bin/env python3
"""Showcase the usage of the 3rd party package `pydantic` for data validation
and parsing using Python type annotations.

author: andreasl
"""
import json
from enum import Enum

from pydantic import BaseModel, Field, ValidationError

print("--- 1 simple model ---\n")


class Address(BaseModel):
    """A Pydantic model for an address."""

    street: str
    city: str
    zip_code: str


class Person(BaseModel):
    """A simple Pydantic model for a person."""

    name: str
    age: int
    address: Address
    hobbies: list[str] = Field(default_factory=list)


a = Person(
    name="Alice",
    age=30,
    hobbies=["reading", "hiking"],
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
    address=Address(
        street="789 Pine St",
        city="Gotham",
        zip_code="67890",
    ),
)
print(c)

print("\n--- 4 recursive JSON serialization of individual models---\n")

aj = a.model_dump_json()

print(f"{type(aj)=}\n")
print(aj)

print("\n--- 5 recursive to dict ---\n")

ad = a.model_dump()

print(f"{type(ad)=}\n")
print(json.dumps(ad, indent=4))

print("\n--- 6 take care when dumping objects with Enum values ---\n")


class Status(str, Enum):
    """Enum representing various statuses."""

    ACTIVE = "active"
    INACTIVE = "inactive"


class Worker(BaseModel):
    """A model with an enum field."""

    name: str
    status: Status
    tasks_completed: int

    # Won't be included
    @property
    def myprop(self) -> float:
        return 42


w = Worker(
    name="Dave",
    status=Status.ACTIVE,
    tasks_completed=15,
)

print(f"{w=}")

wd1 = w.model_dump()
print(f"{wd1=}")  # retains the enum; not JSON serializable

wd2 = w.model_dump(mode="json")
print(f"{wd2=}")  # serializes the enum as well


print("\n--- 7 model_dump(include=...) omits everything not included ---\n")

print(f"{w=}")

wd3 = w.model_dump(mode="json", include={"name", "myprop"})
print(f"{wd3=}")
