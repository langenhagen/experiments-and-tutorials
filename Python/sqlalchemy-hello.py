#!/usr/env/bin python
"""Showcase the usage of the external package sqlalchemy to execute queries.

Tested with sqlalchemy 1.3.20."""
import sqlalchemy as db

print("=== 1 - connect to a db ===")

engine = db.create_engine("mysql://<user>:<pass>:<port>/<database>", pool_pre_ping=True)
connection = engine.connect()
metadata = db.MetaData()

print("=== 2 - query via sql statements ===")

result_proxy = connection.execute("select * from mytable LIMIT 1000")
print(f"type(result_proxy) {type(result_proxy)}")

result_set = result_proxy.fetchall()
print(f"type(result_set) {type(result_set)}")
print(result_set)

print("\n=== 3 - compose a query via functions ===")

ankauf_items = db.Table("mytable", metadata, autoload=True, autoload_with=engine)
query = db.select([ankauf_items]).limit(10)

result_proxy = connection.execute(query)
print(f"type(result_proxy) {type(result_proxy)}")

result_set = result_proxy.fetchall()
print(f"type(result_set) {type(result_set)}")
print(result_set)
