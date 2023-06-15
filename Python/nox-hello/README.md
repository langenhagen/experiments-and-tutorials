# Nox Hello
Showcase `nox`, a command-line tool that automates testing in multiple Python environments.

`nox` similar to `tox`, but `nox` uses standard Python to define stuff.

https://nox.thea.codes/en/stable/tutorial.html

`Pyenv` + `nox`: https://sethmlarson.dev/nox-pyenv-all-python-versions

You gotta need a `noxfile.py`. Then, just call `nox`. For each session, `Nox` will automatically
create a virtualenv with the appropriate interpreter, install the specified dependencies, and run
the commands in order. Great for CI I suppose.


## Useful commands
```bash
pyenv local 3.11.3 3.10.4 3.9.15  # be sure to have the python versions you want for this available

nox  # run nox
nox --list  # list sessions
nox --sessions lint  # run only the session called "lint"
nox --tags style  # run all sessions with the given tag
```
