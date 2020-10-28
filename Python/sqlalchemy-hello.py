#!/usr/env/bin python
"""Showcase the usage of the external package sqlalchemy to execute queries.

SQL ALchemy has a big documentation. I found the tutorial here
https://towardsdatascience.com/sqlalchemy-python-tutorial-79a577141a91 concise and helpful.

Tested with sqlalchemy 1.3.20."""
import datetime

import sqlalchemy as db

print("=== 1 - connect to a db ===")
# engine = db.create_engine("mysql://<user>[:<pass>]@<url>:<port>/<database>")
engine = db.create_engine("mysql://root@127.0.0.1:3306/lesemops")
connection = engine.connect()
metadata = db.MetaData()

print("\n=== 2 get table information ===")
ankauf_anfragen = db.Table(
    "ankauf_anfragen", metadata, autoload=True, autoload_with=engine
)

print(f"str(ankauf_anfragen): {str(ankauf_anfragen)}")
print(f"repr(ankauf_anfragen): {repr(ankauf_anfragen)}")
print(f"ankauf_anfragen.columns.keys(): {ankauf_anfragen.columns.keys()}")

print("\n=== 3 - query via sql statements ===")
result_proxy = connection.execute("select * from ankauf_anfragen LIMIT 1000")
print(f"type(result_proxy) {type(result_proxy)}")

result_set = result_proxy.fetchall()
print(f"type(result_set) {type(result_set)}")
print(result_set)

print("\n=== 4 - compose a query via functions ===")
ankauf_items = db.Table("ankauf_anfragen", metadata, autoload=True, autoload_with=engine)
query = db.select([ankauf_items]).limit(10)  # select * FROM ankauf_items LIMIT 10
print(f"str(query): {str(query)}")

result_proxy = connection.execute(query)
print(f"type(result_proxy) {type(result_proxy)}")

result_set = result_proxy.fetchall()
print(f"type(result_set) {type(result_set)}")
print(result_set)

print("\n=== 5 - insert 1 row ===")
ankauf_brands = db.Table("ankauf_brands", metadata, autoload=True, autoload_with=engine)
query = db.insert(ankauf_brands).values(
    brand="Mybrand", counter=69, last_search=datetime.datetime.now()
)
print(f"query: {query}")
result_proxy = connection.execute(query)
query = db.select([ankauf_brands])
print(f"query: {query}")
results = connection.execute(query).fetchall()
print(f"Results: {results}")


print("\n=== 6 insert many rows ===")
query = db.insert(ankauf_brands)
values = [
    {"brand": "my2ndbrand", "counter": 42, "last_search": datetime.datetime.now()},
    {"brand": "my3rdbrand", "counter": 32, "last_search": datetime.datetime.now()},
]
result_proxy = connection.execute(query, values)
query = db.select([ankauf_brands])
print(f"query: {query}")
results = connection.execute(query).fetchall()
print(f"Results: {results}")

print("\n=== 7 delete rows ===")
query = db.delete(ankauf_brands)
print(f"query: {query}")
result_proxy = connection.execute(query, values)
query = db.select([ankauf_brands])
print(f"query: {query}")
results = connection.execute(query).fetchall()
print(f"Results: {results}")
