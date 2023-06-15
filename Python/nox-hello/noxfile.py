"""
Nox is configured via a noxfile.py file in your project's directory. Here's a
simple noxfile that runs lint and some tests.
"""
import nox


# optionally, you can specify the default sessions;
# you can omit the statement to default to all sessions
# nox.options.sessions = ["lint", "test"]


@nox.session
def test(session):
    # Nox more or less passes session.install through to pip, so you can install
    # stuff in the usual way.
    # same as `pip install pytest requests>2.0.0`
    session.install("pytest", "requests>2.0.0")

    # same as pip install -r requirements.txt
    # session.install("-r", "requirements.txt")

    session.run("pytest")
    session.notify("coverage")  # notify the session "coverage to run"


@nox.session(python=["3.9", "3.10", "3.11"])
def lint(session):
    session.install("ruff")
    session.run("ruff", "--statistics", ".")


@nox.session
def coverage(session):
    session.install("coverage")
    session.run("coverage")


@nox.session(tags=["style", "fix"])
def black(session):
    session.install("black")
    session.run("black", "my_package")

@nox.session(tags=["style", "fix"])
def isort(session):
    session.install("isort")
    session.run("isort", "my_package")

@nox.session(tags=["style"])
def flake8(session):
    session.install("flake8")
    session.run("flake8", "my_package")
