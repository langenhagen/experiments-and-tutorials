#!/usr/bin/env python3
"""Showcase the usage of the 3rd party package `pydantic` for data validation
and parsing using Python type annotations.

author: andreasl
"""
import json

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


alice = Person(
    name="Alice",
    age=30,
    hobbies=["reading", "hiking"],
    address=Address(
        street="123 Maple St",
        city="Wonderland",
        zip_code="12345",
    ),
)
print(alice)

print("\n--- 2 invalid models ---\n")

try:
    bob = Person(
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

charlie = Person(
    name="Charlie",
    age="40",  # hand in age number as a string, gets parsed automatically
    hobbies=["gaming", "coding"],
    address=Address(
        street="789 Pine St",
        city="Gotham",
        zip_code="67890",
    ),
)
print(charlie)

print("\n--- 4 recursive JSON serialization of individual models---\n")

aj = alice.model_dump_json()

print(f"{type(aj)=}\n")
print(aj)

print("\n--- 5 recursive to dict ---\n")

ad = alice.model_dump()

print(f"{type(ad)=}\n")
print(json.dumps(ad, indent=4))
