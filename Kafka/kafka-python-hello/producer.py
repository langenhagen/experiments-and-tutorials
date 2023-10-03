#!/usr/bin/env python
"""A Kafka event producer."""
import logging
from contextlib import suppress
from secrets import randbelow
from time import sleep

from kafka import KafkaProducer
from pronounceable import PronounceableWord

logging.basicConfig(
    format="%(asctime)s.%(msecs)d [%(levelname)s]: %(filename)s:%(lineno)d: %(message)s",
    datefmt="%F %T",  # %F: YYYY-MM-DD  %T: HH:MM:SS
    level=logging.INFO,
)

logger = logging.getLogger(__name__)

word_generator = PronounceableWord()


def connect_kafka_producer() -> KafkaProducer:
    try:
        producer = KafkaProducer(
            bootstrap_servers=["0.0.0.0:9092"],
            api_version=(0, 10),
        )
    except Exception:
        logger.exception("Exception while connecting Kafka")
        raise
    else:
        return producer


def create_message() -> str:
    """Create a message for an event"""
    return word_generator.length(4, 17)  # word length [4,13[


def publish_message(producer, topic: str, key: str, value: str):
    try:
        key_bytes = bytes(key, encoding="utf-8")
        value_bytes = bytes(value, encoding="utf-8")
        producer.send(topic, key=key_bytes, value=value_bytes)
        producer.flush()
        logger.info("Published message: key: %s  value: %s", key, value)
    except Exception:
        logger.exception("Failed to publish message: key: %s, value: %s", key, value)


def main():
    """Run the producer."""
    kafka_producer = connect_kafka_producer()

    with suppress(KeyboardInterrupt):
        while True:
            msg = create_message()
            publish_message(kafka_producer, topic="my_fav_topic", key="raw", value=msg)
            sleep(randbelow(5))

    kafka_producer.close()


if __name__ == "__main__":
    main()
