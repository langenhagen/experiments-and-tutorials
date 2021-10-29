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


class Customers(Base):
    """A model class."""

    __tablename__ = "customers"

    id = Column(Integer, primary_key=True)
    name = Column(String)
    address = Column(String)
    email = Column(String)


Base.metadata.create_all(engine)

print("\n=== 3 adding objects ===\n")

Session = sessionmaker(bind=engine)
session = Session()

c = Customers(name="Ravi Kumar", address="Station Road Nanded", email="ravi@gmail.com")

session.add(c)
session.commit()

session.add_all(
    [
        Customers(
            name="Komal Pande", address="Koti, Hyderabad", email="komal@gmail.com"
        ),
        Customers(
            name="Rajender Nath", address="Sector 40, Gurgaon", email="nath@gmail.com"
        ),
        Customers(
            name="S.M.Krishna", address="Budhwar Peth, Pune", email="smk@gmail.com"
        ),
    ]
)
session.commit()

print("\n=== 4 queries ===\n")
# for different query types see:
# https://www.tutorialspoint.com/sqlalchemy/sqlalchemy_orm_using_query.htm

# all customers
result = session.query(Customers).all()
print(f"{result=}")

for row in result:
    print("Name: ", row.name, "Address:", row.address, "Email:", row.email)

# filtered query
result = session.query(Customers).filter(Customers.name == "Komal Pande").all()
print(f"{result=}")

for row in result:
    print("Name: ", row.name, "Address:", row.address, "Email:", row.email)

# count
result = session.query(Customers).filter(Customers.name == "Komal Pande").count()
print(f"{result=}")
