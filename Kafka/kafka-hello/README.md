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
docker-compose exec kafka kafka-topics.sh --zookeeper zookeeper:2181 --create --replication-factor 1 --partitions 1 --topic mynewtopic

# list topics
docker-compose exec kafka kafka-topics.sh --zookeeper zookeeper:2181 --list

# describe topics
docker-compose exec kafka kafka-topics.sh --zookeeper zookeeper:2181 --describe mynewtopic

# other things to try out
docker exec -it kafka  /bin/bash
find / | grep '.*0000.log'
du /kafka/kafka-logs-e232da6c7c3b/baeldung_linux-0/00000000000000000000.log
more /kafka/kafka-logs-e232da6c7c3b/baeldung_linux-0/00000000000000000000.log
```
