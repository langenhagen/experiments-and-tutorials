#!/usr/bin/env python3
"""Showcase a simple one-shot Telegram bot using the low-level API of
`python-telegram-bot`.

This thing processes all so far unprocessed messages but does not idle/wait for
new messages.

Telegram queues the messages when the bot is offline. When the bot comes back
on, it processes the messages.

Note that the bot/app also gets messages that you send, then delete, even while
the bot is offline.

Use like:

    env TELEGRAM_BOT_TOKEN='<MY BOT TOKEN>' ./onehsot.py

Loosely based on an apparently not-so-well crafted example:
https://github.com/python-telegram-bot/python-telegram-bot/blob/master/examples/rawapibot.py
"""
import asyncio
import logging
import os

from telegram import Bot, Update

from common import setup_logging

log = logging.getLogger(__name__)


async def main() -> None:
    """Run the bot."""
    setup_logging()

    bot_token = os.environ.get("TELEGRAM_BOT_TOKEN")
    log.info("bot_token=%s", bot_token)

    async with Bot(bot_token) as bot:
        update_id = None
        updates = (None,)
        # for Telegram to acknowledge that all messages have been processed, the
        # high level library apparently needs to call `get_updates()` with once
        # with empty results.
        while len(updates) > 0:
            updates = await bot.get_updates(
                offset=update_id,
                timeout=1,
                allowed_updates=Update.ALL_TYPES,
            )
            for update in updates:
                update_id = update.update_id + 1
                if update.message and update.message.text:
                    log.info("Received: %s", update.message.text)
                    await update.message.reply_text(update.message.text[::-1])


if __name__ == "__main__":
    asyncio.run(main())
