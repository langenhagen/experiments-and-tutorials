#!/usr/bin/env python3
"""Showcase a simple FastAPI program."""

import time

import uvicorn
from fastapi import FastAPI
from pydantic import BaseModel


class MySchema(BaseModel):
    value: int


app = FastAPI(
    title="My Super Project",
    description="This is a very fancy project, with auto docs for the API and everything",
    version="0.1.0",
)


@app.post("/foo", response_model=MySchema)
def post_foo():
    print("In post_foo()")
    time.sleep(3)
    print("Returning from post_foo()")
    return MySchema(value=123)


if __name__ == "__main__":
    uvicorn.run(app, host="localhost", port=8000)
