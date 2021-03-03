#!/bin/python
from abc import ABC, abstractmethod

from flask import Flask

app = Flask(__name__)

class AbstractFlaskServer(ABC):

    def __init__(self):
        self.value = value
        super().__init__()

    @abstractmethod
    def do_something(self):
        pass

class GithubbyFlaskServer(AbstractFlaskServer):

    def do_something(self):
        return self.value + 42
