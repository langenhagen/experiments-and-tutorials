#!/bin/bash
# Start a docker-based database for local development.
#
# Usage:
#
#   start-database.sh [<OPTIONS>]
#
# Examples:
#
#   start-database.sh      # start a database service
#   start-database.sh -d   # start a database service in daemon mode
#   start-database.sh -rm  # start a database service and remove it after shutdown
#   start-database.sh --tmpfs=/pgtmpfs -e PGDATA=/pgtmpfs  # start a database on a tmpfs

docker run \
    -e POSTGRES_USER=user \
    -e POSTGRES_PASSWORD=pass \
    -e POSTGRES_DB=mydb \
    -p 5432:5432 \
    "$@" postgres:10.6
