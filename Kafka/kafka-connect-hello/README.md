# Kafka Connect Hello
Showcase the use of Kafka Connect.


```bash
docker-compose up

PGPASSWORD=mypass psql -h localhost -U myuser -d mydb -a -c "insert into mytable (value) values ('andi'),('mandi');"
PGPASSWORD=mypass psql -h localhost -U myuser -d mydb -a -c "select * from mytable;"


# inspect connectors
curl -X GET http://9.9.0.5:8083/connectors

curl -X GET http://9.9.0.5:8083/connectors/my-postgres-connector/status
```


TODO
```
# scale partitions; not necessary here because the partition count is set to 2 in the docker-compose.yml already
docker-compose exec kafka kafka-topics.sh --zookeeper zookeeper:2181 --alter --topic my-fav-topic --partitions 2

# describe topic
docker-compose exec kafka kafka-topics.sh --zookeeper zookeeper:2181 --describe my-fav-topic
```




docker-compose exec kafka kafka-topics --bootstrap-server kafka:9092 --list


docker-compose exec kafka kafka-console-consumer.sh --topic baeldung_linux --from-beginning --bootstrap-server kafka:9092

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
        "topic.prefix": "my-prefix"
    }
}' http://9.9.0.5:8083/connectors





https://docs.confluent.io/platform/current/installation/configuration/connect/index.html
https://docs.confluent.io/platform/current/installation/docker/config-reference.html