# Flask Gunicorn Hello
An example application that serves a Flask Webserver via Gunicorn.

Run e.g. with:
```bash
gunicorn --workers 4 --bind 0.0.0.0:5000 wsgi:app
```
