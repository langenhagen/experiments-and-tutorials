"""Showcase celery w/ redis:

based from: https://docs.celeryproject.org/en/stable/getting-started/first-steps-with-celery.html


Run the following to get a redis instance:

    docker run -d -p 6379:6379 redis

Then start celery:

    celery -A tasks worker --loglevel=INFO

Then call the task:

    python tasks.py

"""

from celery import Celery

BROKER_TRANSPORT = "redis"

BROKER_HOST = "localhost"  # Maps to redis host.
BROKER_PORT = 6379         # Maps to redis port.
BROKER_VHOST = "0"         # Maps to database number.


# app = Celery('tasks', backend='redis://localhost', broker='redis://localhost')
# call tasks inline; doesn't need any backend
app = Celery(
    "tasks",
    backend="redis://localhost",
    broker="redis://localhost",
    task_always_eager=True,
)


@app.task
def add(x, y):
    # return x + y / 0  that would die in the celery.log
    print(f"x + y = {x + y}")
    return x + y


if __name__ == "__main__":
    print("Hello from Hello Celery w/ Redis!")

    result = add.delay(4, 4)
    print(result)
