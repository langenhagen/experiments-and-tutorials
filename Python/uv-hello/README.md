# UV Hello
A quick back-to-basics how to set up a project `uv`, a package and project manager for Python.

I intend this to be a work-in-progress project to bootstrap further projects in my opinionated
style. Things I see that can be improved are linter settings and `pre-commit` setup.


# Steps to Reproduce
Commands necessary to reproduce the project as-is.

Note that this project uses a `uv`-managed Python version, not a `pyenv`-managed one. Means, unless
you activate your `venv` or use `uv run` commands etc., the Python version noted in
`.python-version` may not be the selected one. When cd-ing into the folder.


Bootstrap the project via:
```bash
uv python list
# See `uv python --help` for more command line options

uv venv --pytnon 3.13.3
# See `uv venv --help` for more options; probably interesting: --index, --prompt

# Set .python-version file; note: this is uv-managed, pyenv may not be able to resolve this
uv python pin 3.13.3

uv init --description 'Hello from uv-hello!' --author-from auto
# See `uv init --help` for more options

uv add --dev bpython mypy pytest pre-commit ruff ty vulture

uv add fastapi httpx loguru pydantic uvicorn

uv tree | less

# Run my very opinionated ruff command with fixes and unsafe fixes activated
ruff check --select UP,ANN,B,C4,EXE,FA,ISC,ICN,LOG,G,PIE,Q,RSE,SIM,TC,I,NPY,W,FURB,RET --ignore B905,D203,D213,UP015,UP035 --fix --unsafe-fixes .
```

The `.gitignore` is manually crafted. Complete it for your own project.


# Further Reading
See also info in following links:

- https://docs.astral.sh/uv/concepts/
