# Kafka Connect Hello
Showcase the use of Kafka Connect.

See for more:
- https://docs.confluent.io/platform/current/installation/configuration/connect/index.html
- https://docs.confluent.io/platform/current/installation/docker/config-reference.html


```bash
docker-compose up

# create a connector
curl -X POST -H "Content-Type: application/json" --data '{
    "name": "my-postgres-connector",
    "config": {
        "connector.class": "io.debezium.connector.postgresql.PostgresConnector",
        "tasks.max": "1",
        "database.hostname": "postgres",
        "database.port": "5432",
        "database.user": "myuser",
        "database.password": "mypass",
        "database.dbname": "mydb",
        "database.server.name": "my-postgres-db",
        "database.whitelist": "mydb.mytable",
        "config.storage.topic": "my-config-topic",
        "offset.storage.topic": "my-offset-topic",
        "status.storage.topic": "my-status-topic",
        "topic.creation.enable": "true",
        "topic.creation.default.replication.factor": 1,
        "topic.creation.default.partitions": 1,
        "topic.prefix": "my-prefix",
        "plugin.name": "pgoutput"
    }
}' http://9.9.0.5:8083/connectors

# inspect connectors
curl -X GET http://9.9.0.5:8083/connectors
curl -X GET http://9.9.0.5:8083/connectors/my-postgres-connector/status

# list topics
docker-compose exec kafka kafka-topics --bootstrap-server kafka:9092 --list  # a.o. my-prefix.public.mytable

# describe topic
docker-compose exec kafka kafka-topics --bootstrap-server kafka:9092 --describe my-prefix.public.mytable

# open a simple consumer
docker-compose exec kafka kafka-console-consumer --topic my-prefix.public.mytable --from-beginning --bootstrap-server kafka:9092

# add something to the db and watch the consumer return
PGPASSWORD=mypass psql -h localhost -U myuser -d mydb -a -c "insert into mytable (value) values ('andi'),('mandi');"
PGPASSWORD=mypass psql -h localhost -U myuser -d mydb -a -c "select * from mytable;"

# delete a connector
curl -X DELETE http://9.9.0.5:8083/connectors/my-postgres-connector
```
