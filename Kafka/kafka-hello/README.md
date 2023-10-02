# Kafka Hello

based on https://www.baeldung.com/ops/kafka-new-topic-docker-compose

```bash
docker-compose up

# enable a consumer
docker-compose exec kafka kafka-console-consumer.sh --topic baeldung_linux --from-beginning --bootstrap-server kafka:9092

# enable a producer
docker-compose exec kafka kafka-console-producer.sh --topic baeldung_linux --broker-list kafka:9092
# type something to send it over to the consumer


# create a topic
docker-compose exec kafka kafka-topics.sh --create --zookeeper zookeeper:2181 --replication-factor 1 --partitions 1 --topic mynewtopic

# list topics
docker-compose exec kafka kafka-topics.sh --list --zookeeper zookeeper:2181
```
