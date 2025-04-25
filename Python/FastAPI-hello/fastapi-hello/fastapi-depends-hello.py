#!/usr/bin/env python3
"""Showcase that we cannot use `Depends` in deeper level functions.

curl -L http://localhost:8000/foo

curl -L http://localhost:8000/bar  # will fail

author: andreasl
"""

from typing import Annotated

import uvicorn
from fastapi import Depends, FastAPI

app = FastAPI()


async def dependency(q: str | None = None, skip: int = 0, limit: int = 100):
    return {"q": q, "skip": skip, "limit": limit}


async def other_dependency(q: str | None = None, value: float = 1.2):
    return {"q": q, "value": value}


@app.get("/foo/")
async def foo(params: Annotated[dict, Depends(dependency)]):
    """Uses Depends directly"""
    return params


# following doesn't work:
def bar_impl(params: Annotated[dict, Depends(other_dependency)]):
    return params


@app.get("/bar/")
async def bar():
    return bar_impl()


if __name__ == "__main__":
    uvicorn.run("fastapi-depends-hello:app", host="localhost", port=8000, reload=True)
