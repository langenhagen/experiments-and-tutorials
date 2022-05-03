#!/bin/bash
# Start or restart a docker-based database for local development.
#
# Usage:
#
#   start-database.sh [<OPTIONS>]
#
# Examples:
#
#   start-database.sh       # start a database service
#   start-database.sh -d    # start a database service in daemon mode
#   start-database.sh --rm  # start a database service and remove it after shutdown
#   start-database.sh --tmpfs=/pgtmpfs -e PGDATA=/pgtmpfs  # start a database on a tmpfs

container_name='my_test_postgres_db'

if [ -z "$(docker ps --all --quiet --filter "name=${container_name}")" ]; then
    docker run \
        --env POSTGRES_USER=user \
        --env POSTGRES_PASSWORD=pass \
        --env POSTGRES_DB=mydb \
        --name "$container_name" \
        --publish 5432:5432 \
        "$@" postgres:10.6
else
    docker start --attach "$container_name"
fi
