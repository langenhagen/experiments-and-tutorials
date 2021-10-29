#!/usr/bin/env python3
"""
Showcase the usage of the external package sqlalchemy with its ORM
functionality.

Install like:

  pip install sqlalchemy


Based on:
- https://www.tutorialspoint.com/sqlalchemy/sqlalchemy_orm_declaring_mapping.htm and ff.
- https://www.tutorialspoint.com/sqlalchemy/sqlalchemy_orm_using_query.htm

Tested with sqlalchemy 1.4.26.
"""
from sqlalchemy import Column, Integer, String, create_engine
from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy.orm import sessionmaker

print("\n=== 1 setting up an sqlite db ===\n")

engine = create_engine("sqlite:///sales.db", echo=True)

print("\n=== 2 have a model ===\n")

Base = declarative_base()


class Customer(Base):
    """A model class."""

    __tablename__ = "customers"

    id = Column(Integer, primary_key=True)
    name = Column(String, unique=True)
    address = Column(String)
    email = Column(String)


Base.metadata.create_all(engine)

print("\n=== 3 adding objects ===\n")

Session = sessionmaker(bind=engine)
session = Session()

c = Customer(name="Ravi Kumar", address="Station Road Nanded", email="ravi@gmail.com")
session.add(c)

d = Customer(name="Andi L", address="Perth")
session.add(d)

# would cause an sqlalchemy.exc.IntegrityError bc unique constraint on the name
e = Customer(name="Andi L")
# session.add(e)

session.commit()

# c.name = "Andi L"  # also violates the uniqueness error

session.add_all(
    [
        Customer(name="Komal", address="Hyderabad", email="komal@gmail.com"),
        Customer(name="Rajender Nath", address="Sector 7", email="nath@gmail.com"),
        Customer(name="S.M.Krishna", address="Perth", email="smk@gmail.com"),
    ]
)
session.commit()

print("\n=== 4 queries ===\n")
# for different query types see:
# https://www.tutorialspoint.com/sqlalchemy/sqlalchemy_orm_using_query.htm

# all customers
result = session.query(Customer).all()
print(f"{result=}")

for row in result:
    print("Name: ", row.name, "Address:", row.address, "Email:", row.email)

# filtered query
result = session.query(Customer).filter(Customer.name == "Andi L").all()
print(f"{result=}")

for row in result:
    print("Name: ", row.name, "Address:", row.address, "Email:", row.email)

# count
result = session.query(Customer).filter(Customer.name == "Andi L").count()
print(f"{result=}")

# combined filters
result = session.query(Customer).filter(Customer.name != "Andi L", Customer.address == "Perth").all()
print(f"{result=}")
for row in result:
    print("Name: ", row.name, "Address:", row.address, "Email:", row.email)