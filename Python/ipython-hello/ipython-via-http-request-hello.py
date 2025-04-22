#!/usr/bin/env python3
"""Showcase the execution of code via ipython coming from a HTTP request.

This effectively allows HTTP clients to send code to the host to run arbitrary
Python code.

Compare to running via `exec()`:
- `exec()` just runs code in your namespace, no history, no result object, no rich display or magics
- `InteractiveShell.instance().run_cell(...)` returns an ExecutionResult (with `.result`, `.error_in_exec`) so you can inspect outputs/errors
- IPython gives history, cell magics (`%timeit`, etc.), pretty‐printing, display hooks (HTML, plots), exception formatting
- easier to capture stdout/stderr via IPython’s hooks than wrapping exec in custom StringIO
- magics let you add profiling, debugging, shell commands, without custom parsing
- safer sandboxing: you can allegedly override display and execution hooks more granularly

author: andreasl
"""

import uvicorn
from fastapi import FastAPI, HTTPException
from IPython.core.interactiveshell import InteractiveShell
from pydantic import BaseModel

app = FastAPI()
ipython = InteractiveShell.instance()


class CodeRequest(BaseModel):
    code: str


@app.post("/execute")
async def execute(req: CodeRequest):
    try:
        res = ipython.run_cell(req.code)
        output = res.result
    except Exception as e:
        raise HTTPException(status_code=400, detail=str(e))
    return {"output": output}


if __name__ == "__main__":
    uvicorn.run(app, host="localhost", port=8000)
