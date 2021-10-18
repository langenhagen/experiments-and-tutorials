import time

from fastapi import FastAPI, HTTPException
from pydantic import BaseModel


class MySchema(BaseModel):
    pass


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
    return None
