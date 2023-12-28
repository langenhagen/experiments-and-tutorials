#!/usr/bin/env python3
"""Showcase a simple telegram bot.

Nice thing is that Telegram queues the messages when the bot is offline. When
the bot comes back on, it processes the messages.
Note: also gets messages that you send, then delete, even while the bot is
offline. This indicates that Telegram may indeed not delete messages you delete.


Use like:

    env TELEGRAM_BOT_TOKEN='<MY BOT TOKEN>' ./echobot.py


To create and manage your bots, talk with @BotFather, the one Telegram bot to
rule them all. Send the messages "/start" or "/help" to botfather to learn more.

Based on:
https://github.com/python-telegram-bot/python-telegram-bot/blob/master/examples/echobot.py
"""
import logging
import os

from dateutil import tz
from telegram import ForceReply, Update
from telegram.ext import (
    Application,
    CommandHandler,
    ContextTypes,
    MessageHandler,
    filters,
)

from common import setup_logging

log = logging.getLogger(__name__)


async def on_start(update: Update, context: ContextTypes.DEFAULT_TYPE) -> None:
    """Send a message when the command /start is issued."""
    user = update.effective_user
    await update.message.reply_html(
        rf"Hi {user.mention_html()}!",
        reply_markup=ForceReply(selective=True),
    )


async def on_help(update: Update, context: ContextTypes.DEFAULT_TYPE) -> None:
    """Send a message when the command /help is issued."""
    await update.message.reply_text("Help!")


async def on_echo(update: Update, context: ContextTypes.DEFAULT_TYPE) -> None:
    """Echo the user message."""
    log.info("Got %s", update.message)
    date = update.message.date.astimezone(tz.tzlocal())
    await update.message.reply_text(f"{date=}\n{update.message.text}")


def main() -> None:
    """Create the Application behind the bot and start listening for messages."""
    setup_logging()

    bot_token = os.environ.get("TELEGRAM_BOT_TOKEN")
    log.info("bot_token=%s", bot_token)
    app = Application.builder().token(bot_token).build()

    # on different commands - answer in Telegram
    app.add_handler(CommandHandler("start", on_start))
    app.add_handler(CommandHandler("help", on_help))

    # on non command i.e message - echo the message on Telegram
    app.add_handler(MessageHandler(filters.TEXT & ~filters.COMMAND, on_echo))

    # run the bot until the user presses Ctrl-C
    app.run_polling(allowed_updates=Update.ALL_TYPES)


if __name__ == "__main__":
    main()
