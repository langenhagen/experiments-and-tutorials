"""Contains the WSGI entry point.

Start e.g. via:

  gunicorn --workers 4 --bind 0.0.0.0:5000 wsgi:app

"""

from flask_app import app

if __name__ == "__main__":
    app.run()
