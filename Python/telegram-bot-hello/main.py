#!/usr/bin/env python3
"""Showcase a simple telegram bot.

Nice thing is that Telegram queues the messages when the bot is offline. When
the bot comes back on, it processes the messages.

Use like:

    env TELEGRAM_BOT_TOKEN='<MY BOT TOKEN>' ./main.py


To create and manage your bots, talk with @BotFather, the one Telegram bot to rule them all.
Send the messages "/start" or "/help" to botfather to learn more.

Based on: https://github.com/python-telegram-bot/python-telegram-bot/blob/master/examples/echobot.py
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

logging.basicConfig(
    format="%(asctime)s - %(name)s - %(levelname)s - %(message)s",
    datefmt="%T",  # %T: HH:MM:SS,
    level=logging.INFO,
)
# if you want, set higher log level for httpx
# logging.getLogger("httpx").setLevel(logging.WARNING)
logger = logging.getLogger(__name__)


async def start(update: Update, context: ContextTypes.DEFAULT_TYPE) -> None:
    """Send a message when the command /start is issued."""
    user = update.effective_user
    await update.message.reply_html(
        rf"Hi {user.mention_html()}!",
        reply_markup=ForceReply(selective=True),
    )


async def help_command(update: Update, context: ContextTypes.DEFAULT_TYPE) -> None:
    """Send a message when the command /help is issued."""
    await update.message.reply_text("Help!")


async def echo(update: Update, context: ContextTypes.DEFAULT_TYPE) -> None:
    """Echo the user message."""
    logger.info("Got %s", update.message)
    date = update.message.date.astimezone(tz.tzlocal())
    await update.message.reply_text(f"{date=}\n{update.message.text}")


def main() -> None:
    """Create the Application behind the bot and start listening for messages."""
    bot_token = os.environ.get("TELEGRAM_BOT_TOKEN")
    logger.info("bot_token=%s", bot_token)
    application = Application.builder().token(bot_token).build()

    # on different commands - answer in Telegram
    application.add_handler(CommandHandler("start", start))
    application.add_handler(CommandHandler("help", help_command))

    # on non command i.e message - echo the message on Telegram
    application.add_handler(MessageHandler(filters.TEXT & ~filters.COMMAND, echo))

    # run the bot until the user presses Ctrl-C
    application.run_polling(allowed_updates=Update.ALL_TYPES)


if __name__ == "__main__":
    main()
