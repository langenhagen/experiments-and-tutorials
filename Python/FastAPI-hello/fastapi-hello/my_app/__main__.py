#!/usr/bin/env python3
"""Showcase a simple FastAPI program.

Use like:

    python myapp

    curl http://localhost:8000/foo
    curl -X POST http://localhost:8000/foo -H "Content-Type: application/json" -d '{}'

    curl 'http://localhost:8000/withdatetimequerystrings?start=2024-06-01T17:20:22' | jq  # local timezone
    curl 'http://localhost:8000/withdatetimequerystrings?start=2024-06-01T17:20:22Z' | jq  # Z: UTC timezone
    curl 'http://localhost:8000/withdatetimequerystrings?start=2024-06-01T17:23:22%2B01:00' | jq  # '%2B01:00': CET timezone; %2B properly escapes '+'

Refresher on Timezones in isoformat datetime strings:

    UTC      2025-06-02T12:34:56Z
    CET      2025-06-02T12:34:56+01:00
    CEST     2025-06-02T12:34:56+02:00
    Local    2025-06-02T12:34:56

"""

import datetime as dt
import time

import uvicorn
from fastapi import FastAPI
from fastapi.responses import JSONResponse
from pydantic import BaseModel


class MySchema(BaseModel):
    value: int


app = FastAPI(
    title="My Super Project",
    description="This is a very fancy project, with auto docs for the API and everything",
    version="0.1.0",
)


@app.get("/", status_code=200)
async def root() -> JSONResponse:
    return {"status": "ok"}


@app.get("/foo", response_model=MySchema)
def get_foo() -> MySchema:
    print("In get_foo()")
    return MySchema(value=101)


# stuff can be async; you can apparently define the response_model also as return type hint
@app.post("/foo")
async def post_foo() -> MySchema:
    print("In post_foo()")
    time.sleep(3)
    print("Returning from post_foo()")
    return MySchema(value=123)


@app.get("/withdatetimequerystrings", summary="With datetime querystrings")
async def withdatetimequerystrings(
    start: dt.datetime | None = None,
    end: dt.datetime | None = None,
) -> JSONResponse:
    """Allegedly, better use isoformat querystrings instead of
    integer-timestamps. Crazy, but OK.
    """
    end = end or dt.datetime.now(tz=dt.UTC)
    start = start or (end - dt.timedelta(hours=1))

    return {
        "start.isoformat()": start.isoformat(),
        "UTC start.isoformat():": start.astimezone(tz=dt.UTC).isoformat(),
        "end.isoformat()": end.isoformat(),
        "UTC end.isoformat():": end.astimezone(tz=dt.UTC).isoformat(),
    }


if __name__ == "__main__":
    uvicorn.run(app, host="localhost", port=8000)
