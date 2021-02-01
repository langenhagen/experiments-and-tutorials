#!/usr/bin/env python
"""Showcases the usage of a Flaks app served via Gunicorn."""
import time

from flask import Flask

app = Flask(__name__)


@app.route("/")
def hello():
    """Serve an endpoint for test purposes."""
    request_time = time.asctime()
    # time.sleep(10)
    return f"Hi There!<br><br>Request came in at {request_time}"


if __name__ == "__main__":
    app.run(host="0.0.0.0")
