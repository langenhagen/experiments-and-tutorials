"""
Experimental locust https://locust.io/ test.

Apparently, locust is a neat thing to load-test response times including graphs
and stuff.

Install locust via:

    pip install locust


based on: https://www.youtube.com/watch?v=SOu6hgklQRA

author: andreasl
"""
from locust import HttpUser, between, task


class WebsiteUser(HttpUser):
    wait_time = between(1, 5)

    @task
    def live_stock(self):
        self.client.get(url="/storage/live_stock")
