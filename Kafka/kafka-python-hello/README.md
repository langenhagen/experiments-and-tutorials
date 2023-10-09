# Kafka Python Hello
Showcase how to use Kafka with Python applications with the 3rd party library `kafka-python`.

based on: https://towardsdatascience.com/getting-started-with-apache-kafka-in-python-604b3250aa05

Find `kafka-python` on https://github.com/dpkp/kafka-python and
Find its docs under https://kafka-python.readthedocs.io/.

There are other alternative Python Kafka clients, e.g.:
- `confluent-kafka-python` https://github.com/confluentinc/confluent-kafka-python
- `pykafka` https://github.com/Parsely/pykafka

```bash
docker-compose up

python producer.py

python consumer.py
# and a second one bc we have 2 partitions;
# if only 1 consumer is able, it takes in all partitions; nothing gets lost
python consumer.py

# scale partitions; not necessary here because the partition count is set to 2 in the docker-compose.yml already
docker-compose exec kafka kafka-topics.sh --zookeeper zookeeper:2181 --alter --topic my-fav-topic --partitions 2

# describe topic
docker-compose exec kafka kafka-topics.sh --zookeeper zookeeper:2181 --describe my-fav-topic
```
