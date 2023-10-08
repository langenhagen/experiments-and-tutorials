# Kafka Python Hello
Showcase how to use several Kafka brokers.

```bash
docker compose up
# wait a few seconds

# describe topic; should show 2 partitions
docker-compose exec kafka1 kafka-topics.sh --zookeeper zookeeper:2181 --describe my-fav-topic

python producer.py

python consumer.py
# and a second one bc we have 2 partitions;
# if only 1 consumer is able, it takes in all partitions; nothing gets lost
python consumer.py
```
