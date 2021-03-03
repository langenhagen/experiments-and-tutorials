#!/usr/bin/env python
"""Showcase the usage of PyMongo.

get it via `pip install pymongo`
"""

import pymongo

print("--- 1 simple case ---")

client = pymongo.MongoClient("mongodb://localhost:27017/")

db = client["non-existing"]  # accessing non-existing dbs works
db = client.non_existing  # shortcut; works only for with allowed characters; works also with non-existing; don't use it
db = client["c1_cre"]

collection = db["non-existing"]  # accessing non-existing collections works
collection = db.mynonexisting  # shortcut; works also for non-existing collections; don't use it
collection = db["sso_page_template"]
for doc in collection.find({}):
    print(doc["name"])

