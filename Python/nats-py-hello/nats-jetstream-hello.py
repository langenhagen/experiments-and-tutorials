#!/usr/bin/env python3
"""Showcase the NATS client `nats-py` with Jetstream functionality for
publishing and subscribing to persistent message streams.

See: https://pypi.org/project/nats-py/
"""

import asyncio

import nats
import nats.errors
from nats.errors import ConnectionClosedError, NoServersError, TimeoutError


async def main() -> None:
    try:
        nc = await nats.connect("nats://demo.nats.io:4222")
        js = nc.jetstream()
        try:
            await js.add_stream(name="sample-stream", subjects=["foo"])
        except nats.errors.Error as e:
            print(f"Stream may already exist: {e}")

        for i in range(10):
            try:
                ack = await js.publish("foo", f"hello world: {i}".encode())
                print(f"Published message {i}, ack: {ack}")
            except nats.js.errors.Error as e:
                print(f"Error publishing message {i}: {e}")

        # Read back messages from the stream
        print("\nReading back messages:")
        try:
            # Fetch all messages using pull-based consumer
            # use `js.subscribe()` for push-based subscription, then you don't need to fetch
            consumer = await js.pull_subscribe("foo", "reader")
            msgs = await consumer.fetch(10, timeout=1)
            for msg in msgs:
                print(f"Received: {msg.data.decode()}")
                await msg.ack()
        except nats.errors.Error as e:
            print(f"Error reading messages: {e}")

        await nc.close()

    except TimeoutError as e:
        print(f"Error: Connection timed out: {e}")
    except ConnectionClosedError as e:
        print(f"Error: Connection closed: {e}")
    except NoServersError as e:
        print(f"Error: No server: {e}")
    except Exception as e:
        print(f"Unexpected error: {e}")


if __name__ == "__main__":
    asyncio.run(main())
