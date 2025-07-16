#!/usr/bin/env python3
"""Showcase how to implement asynchronous updating in nicegui, bia polling a
global variable.
"""

import asyncio

from nicegui import app, ui

progress = 0


@ui.page("/")
async def main():
    label = ui.label(f"Progress: {progress}%")

    async def updater():
        """Updates the view periodically."""
        while True:
            label.text = f"Progress: {progress}%"
            await asyncio.sleep(0.1)

    updater_task = asyncio.create_task(updater())

    await updater_task


@app.on_startup
async def start_background_task():
    """Just an exemplaric background task"""
    global progress
    for i in range(200000):
        progress = i
        await asyncio.sleep(0.05)


ui.run()
