#!/bin/bash
# Start a docker-based database for local development.
#
# Usage:
#
#   start-test-database.sh [<OPTIONS>]
#
# Examples:
#
#   start-test-database.sh      # start a database service
#   start-test-database.sh -d   # start a database service in daemon mode
#   start-test-database.sh -rm  # delete container after execution

docker run \
    -e POSTGRES_USER=user \
    -e POSTGRES_PASSWORD=pass \
    -e POSTGRES_DB=mydb \
    -p 5432:5432 \
    "$@" postgres:10.6
