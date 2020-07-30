#!/bin/bash

virtualenv --python python2 .venv
source .venv/bin/activate
pip install -r requirements.txt
