# Nox Hello
Showcase `nox`, a command-line tool that automates testing in multiple Python environments.

`nox` similar to `tox`, but `nox` uses standard Python to define stuff.

https://nox.thea.codes/en/stable/tutorial.html

You gotta need a `noxfile.py`. Then, just call `nox`. For each session, `Nox` will automatically
create a virtualenv with the appropriate interpreter, install the specified dependencies, and run
the commands in order. Great for CI I suppose.


## Setup
```bash
uv sync  # install dependencies
```


## Useful commands
```bash
nox  # run nox
nox --list  # list sessions
nox --sessions lint  # run only the session called "lint"
nox --tags style  # run all sessions with the given tag
```
