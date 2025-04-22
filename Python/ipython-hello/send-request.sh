#!/bin/bash
# Send a simple proof-of-concept request to the IPython server.

curl \
    --request POST "http://localhost:8000/execute" \
    --header "Content-Type: application/json" \
    --data '{"code":"sum([i*i for i in range(1,6)])"}'
