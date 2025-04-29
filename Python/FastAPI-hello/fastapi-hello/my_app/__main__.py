#!/usr/bin/env python3
"""Showcase a simple FastAPI program.

Use like:

    python myapp

    curl http://localhost:8000/foo
    curl -X POST http://localhost:8000/foo -H "Content-Type: application/json" -d '{}'
"""

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


@app.get("/", status_code=200)
async def root():
    return {"status": "ok"}


@app.get("/foo", response_model=MySchema)
def get_foo():
    print("In get_foo()")
    return MySchema(value=101)


@app.post("/foo", response_model=MySchema)
async def post_foo():  # stuff can be async
    print("In post_foo()")
    time.sleep(3)
    print("Returning from post_foo()")
    return MySchema(value=123)


if __name__ == "__main__":
    uvicorn.run(app, host="localhost", port=8000)
