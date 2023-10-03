# Kafka Python Hello
Showcas how to use Kafka with Python applications

based on: https://towardsdatascience.com/getting-started-with-apache-kafka-in-python-604b3250aa05

Uses 3rd party package `kafka-python` from https://github.com/dpkp/kafka-python.

There are other alternative Python Kafka clients, e.g.:
- `confluent-kafka-python` https://github.com/confluentinc/confluent-kafka-python
- `pykafka` https://github.com/Parsely/pykafka



```bash
docker compose up

python producer.py

docker-compose exec kafka kafka-console-consumer.sh --topic my_fav_topic --from-beginning --bootstrap-server kafka:9092
```