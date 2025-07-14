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

    # custom theming
    # ui.add_head_html("""
    #     <style>
    #     :root {
    #     --q-primary: #7e2eea;
    #     --q-secondary: #f953c6;
    #     --q-accent: #20e3b2;
    #     --q-dark-page: #191a21;
    #     --q-dark: #23243a;
    #     --q-positive: #2af598;
    #     --q-negative: #ff6a6a;
    #     --q-warning: #ffe156;
    #     --q-info: #28c7fa;
    #     --q-font: 'Fira Mono', 'JetBrains Mono', 'Consolas', 'monospace';
    #     }
    #     body {
    #     font-family: var(--q-font);
    #     background: linear-gradient(135deg, #23243a 0%, #191a21 100%);
    #     color: #eee;
    #     }
    #     .q-card {
    #     background: linear-gradient(120deg, #7e2eea88 0%, #20e3b288 100%);
    #     border-radius: 18px !important;
    #     box-shadow: 0 4px 28px 0 #0006;
    #     border: 1.5px solid #7e2eea44;
    #     padding: 1.5rem;
    #     }
    #     .q-btn {
    #     background: linear-gradient(90deg, #7e2eea 0%, #20e3b2 100%);
    #     color: #fff !important;
    #     border-radius: 10px;
    #     box-shadow: 0 2px 8px 0 #20e3b233;
    #     font-weight: bold;
    #     letter-spacing: 0.05em;
    #     transition: transform 0.1s;
    #     }
    #     .q-btn:hover {
    #     transform: translateY(-2px) scale(1.04);
    #     box-shadow: 0 4px 24px 0 #7e2eea66;
    #     }
    #     .q-field__native {
    #     background: #23243a99;
    #     color: #e9e6fc;
    #     border-radius: 6px;
    #     border: 1.2px solid #7e2eea55;
    #     padding: 0.5rem;
    #     }
    #     ::-webkit-scrollbar {
    #     width: 9px;
    #     background: #23243a;
    #     }
    #     ::-webkit-scrollbar-thumb {
    #     background: linear-gradient(120deg, #7e2eea, #20e3b2);
    #     border-radius: 6px;
    #     }
    #     a {
    #     color: #f953c6;
    #     text-decoration: underline dotted;
    #     transition: color 0.2s;
    #     }
    #     a:hover {
    #     color: #ffe156;
    #     }
    #     </style>
    # """)

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

    def on_toggle(e):
        ui.notify(f"Checked = {e.value}")

    ui.checkbox("Agree?", value=False, on_change=on_toggle)
    ui.switch("Enable feature", value=True, on_change=on_toggle)


ui.run_with(app)


if __name__ == "__main__":
    uvicorn.run("main:app", host="localhost", port=8000, reload=True)
