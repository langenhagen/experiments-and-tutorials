#!/usr/bin/env python
"""A Kafka event producer."""
import logging
from contextlib import suppress
from secrets import randbelow
from time import sleep

from kafka import KafkaProducer
from kafka.errors import KafkaError, NotLeaderForPartitionError
from pronounceable import PronounceableWord

logging.basicConfig(
    format="%(asctime)s.%(msecs)d [%(levelname)s]: %(filename)s:%(lineno)d: %(message)s",
    datefmt="%T",  # %T: HH:MM:SS
    level=logging.INFO,
)

logger = logging.getLogger(__name__)

word_generator = PronounceableWord()


def publish_message(producer, topic: str, value: str):
    value_bytes = bytes(value, encoding="utf-8")
    # a hash from the key value determines into which partition a
    # message goes; but if partition=None, key=None, choose partition randomly
    future = producer.send(topic, partition=None, key=None, value=value_bytes)
    producer.flush()
    try:
        record_metadata = future.get(timeout=10)
    except KafkaError:
        logger.exception(
            "Failed to publish message: topic: %s value: %s",
            topic,
            value,
        )
    else:
        logger.info(
            "Published message %d.%d: value: %s",
            record_metadata.partition,
            record_metadata.offset,
            value,
        )


def main():
    """Run the producer."""
    topic = "my-fav-topic"

    producer = KafkaProducer(bootstrap_servers=["9.9.0.2:9092"])
    # producer = KafkaProducer(bootstrap_servers=["0.0.0.0:9092"])

    with suppress(KeyboardInterrupt):
        while True:
            msg = word_generator.length(4, 17)  # word length [4,17[
            publish_message(producer, topic=topic, value=msg)
            sleep(1 + randbelow(4))

    producer.close()


if __name__ == "__main__":
    main()
