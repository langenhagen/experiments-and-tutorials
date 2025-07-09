#!/usr/bin/env python3
"""Showcase the GUI framework nicegui in conjunction with FastAPI.

Nicegui can run standalone, but here, we tie it into a FastAPI app.

Might be that async nicegui leaks async tasks, as I discovered by accident via
`pyleak.no_task_leaks`. Have no solution so far. Just making the function
non-async does not help.

"""

import asyncio

import uvicorn
from fastapi import FastAPI
from nicegui import ui

app = FastAPI()


@ui.page("/")
async def main_page() -> None:
    """Serve the nice gui."""
    dark_mode = ui.dark_mode()

    ui.page_title("My Cool App")
    ui.label("Henlo!")

    def greet() -> None:
        result.set_text(f"Hello, {name.value or 'stranger'} {num.value}!")


    async def show_notification() -> None:
        """Show notifications. Notifications are like notify() but finer
        control.
        """
        # timeout >0 can make it go away by itself
        note = ui.notification("Processing...", spinner=True, timeout=0)
        await asyncio.sleep(2)
        note.message = "Done!"
        note.spinner = False
        note.icon = "done"
        note.dismiss()

    with ui.card():
        num = ui.number("Number", value=None, min=0)
        name = ui.input(label="Enter your name")
        result = ui.label()
        with ui.row():
            ui.button("Greet", on_click=greet)
            ui.button("In The Same Row", on_click=greet)
        ui.button("Start notification", on_click=show_notification)

    def show_number() -> None:
        ui.notify(f"Number {num.value}")

    num.on("change", show_number)

    def toggle_dark() -> None:
        dark_mode.toggle()
        print(f"{dark_mode=} {dark_mode.value}")
        ui.notify(f"Dark Mode is now {'on' if dark_mode.value else 'off'}")

    ui.button("Toggle dark mode", on_click=toggle_dark)

    ui.link("A link", "https://example.com")


ui.run_with(app)


if __name__ == "__main__":
    uvicorn.run("main:app", host="localhost", port=8000, reload=True)