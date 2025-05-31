#!/usr/bin/env python3
"""Showcase the NATS client `nats-py`.

See: https://pypi.org/project/nats-py/
"""

import asyncio

import nats
from nats.errors import ConnectionClosedError, NoServersError, TimeoutError


async def main() -> None:
    # It is very likely that the demo server will see traffic from clients other than yours.
    # To avoid this, start your own locally and modify the example to use it.
    nc = await nats.connect("nats://demo.nats.io:4222")

    # You can also use the following for TLS against the demo server.
    #
    # nc = await nats.connect("tls://demo.nats.io:4443")

    async def message_handler(msg) -> None:
        subject = msg.subject
        reply = msg.reply
        data = msg.data.decode()
        print(f"Received a message on '{subject} {reply}': {data}")

    # Simple publisher and async subscriber via coroutine.
    sub = await nc.subscribe("foo", cb=message_handler)

    # Stop receiving after 2 messages.
    await sub.unsubscribe(limit=2)
    await nc.publish(subject="foo", payload=b"Hello")
    await nc.publish(subject="foo", payload=b"World")
    await nc.publish(subject="foo", payload=b"Arrives past unsubscribe is in effect")

    # Synchronous style with iterator also supported.
    sub = await nc.subscribe("bar")
    await nc.publish("bar", b"First")
    await nc.publish("bar", b"Second")

    try:
        async for msg in sub.messages:
            print(f"Received a message on '{msg.subject} {msg.reply}': {msg.data.decode()}")
            await sub.unsubscribe()
    except Exception as e:
        print(f"Dios Mio! error with message {msg}:\n{e}")  # fails bc of sub.unsubscribe() above

    async def help_request(msg) -> None:
        print(f"Received a message on '{msg.subject} {msg.reply}': {msg.data.decode()}")
        await nc.publish(msg.reply, b"I can help")

    # Use queue named 'workers' for distributing requests
    # among subscribers.
    sub = await nc.subscribe("help", "workers", help_request)

    # Send a request and expect a single response
    # and trigger timeout if not faster than 500 ms.
    try:
        response = await nc.request("help", b"help me", timeout=0.5)
        print(f"Received response: {response.data.decode()}")
    except TimeoutError as e:
        print(f"Error: Request timed out: {e}")
    except ConnectionClosedError as e:
        print(f"Error: Connection closed: {e}")
    except NoServersError as e:
        print(f"Error: No server: {e}")

    # Remove interest in subscription.
    await sub.unsubscribe()

    # Terminate connection to NATS.
    await nc.drain()


if __name__ == "__main__":
    asyncio.run(main())
