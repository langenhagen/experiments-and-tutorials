#!/usr/bin/env python
"""A Kafka event consumer."""
import logging
from contextlib import suppress
from time import sleep

from kafka import KafkaConsumer

logging.basicConfig(
    format="%(asctime)s.%(msecs)d [%(levelname)s]: %(filename)s:%(lineno)d: %(message)s",
    datefmt="%T",  # %T: HH:MM:SS
    level=logging.INFO,
)

logger = logging.getLogger(__name__)


def main():
    """Run the consumer."""
    topic = "my-fav-topic"

    consumer = KafkaConsumer(
        topic,
        "my_other_topic",
        group_id="my-consumer-group",
        auto_offset_reset="earliest",
        bootstrap_servers=["localhost:9092"],
        api_version=(0, 10),
        consumer_timeout_ms=1000,
        enable_auto_commit=True,
        value_deserializer=lambda v: str(v, encoding="utf-8"),
    )

    partitions = consumer.partitions_for_topic(topic)
    logger.info("Partitions: %s", str(partitions))

    with suppress(KeyboardInterrupt):
        while True:
            for msg in consumer:
                # logger.info("Got %s", msg)
                logger.info(
                    "%d.%d: %s",
                    msg.partition,
                    msg.offset,
                    msg.value,
                )

            sleep(3)

    consumer.close()


if __name__ == "__main__":
    main()
