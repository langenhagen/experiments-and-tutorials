#!/usr/bin/env python3
"""Showcase the usage of the 3rd party library `python-decouple` which allows
for managing environment variables and configuration settings.

With `python-decouple`, you can store configuration settings in a `.env` file
and access them in your Python code using a simple API.

The `.env` file typically resides in the root of your project and contains
key-value pairs for configuration.

See: https://pypi.org/project/python-decouple/
"""
from decouple import Config, Csv, RepositoryEnv, UndefinedValueError, config

print("--- 1 basic usage ---\n")

hello = config("HELLO", default="Hello, default world!")
debug = config("DEBUG", cast=bool, default=False)
port = config("PORT", cast=int, default=5000)
# `Csv` parses comma-separated values into a list
allowed_hosts = config("ALLOWED_HOSTS", cast=Csv(), default="localhost")

print(f"{hello=}")
print(f"{debug=}")
print(f"{port=}")
print(f"{allowed_hosts=}")

print("\n--- 2 Config class ---\n")

# Using the Config class directly (alternative to `config()`)

config_instance = Config(repository=RepositoryEnv(".env"))

secret_key = config_instance("SECRET_KEY", default="default_secret")
print(f"{secret_key=}")

print("\n--- 3 handling missing values with UndefinedValueError ---\n")

try:
    missing_value = config("MISSING_KEY")  # without default
    print(f"{missing_value=}")
except UndefinedValueError as e:
    print(f"Caught an exception for missing key: {e}")
