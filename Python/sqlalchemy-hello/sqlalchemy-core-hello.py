#!/usr/bin/env python3
"""
Showcase the usage of the external package sqlalchemy with its core
functionality.

Install like:

  pip install sqlalchemy


Based on:
- https://www.tutorialspoint.com/sqlalchemy/sqlalchemy_introduction.htm


Tested with sqlalchemy 1.4.26.
"""
from sqlalchemy import Column, Integer, MetaData, String, Table, create_engine, select

print("\n--- 1 setting up an sqlite db ---\n")

engine = create_engine("sqlite:///college.db", echo=True)
meta = MetaData()

print("\n--- 2 setting up a table ---\n")

students = Table(
    "students",
    meta,
    Column("id", Integer, primary_key=True),
    Column("name", String),
    Column("lastname", String),
)
meta.create_all(engine)

print("\n--- 3 insert stuff ---\n")

ins = students.insert().values(name="Karan")

print(f"{str(ins)=}")

print(f"{ins.compile().params=}")

conn = engine.connect()
result = conn.execute(ins)

conn.execute(
    students.insert(),
    [
        {"name": "Rajiv", "lastname": "Khanna"},
        {"name": "Komal", "lastname": "Bhandari"},
        {"name": "Abdul", "lastname": "Sattar"},
        {"name": "Priya", "lastname": "Rajhans"},
    ],
)

print("\n--- 4 selecting ---\n")

# select all
s = students.select()
print(f"{s=}")

result = conn.execute(s)
print(f"{result=}")

for row in result:
    print(row)

# select where
s = students.select().where(students.c.id > 2)
print(f"{s=}")

result = conn.execute(s)

for row in result:
    print(row)

# select via select function
s = select([students])
print(f"{s=}")

result = conn.execute(s)

for row in result:
    print(row)
