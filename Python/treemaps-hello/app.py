"""
Test the treemap module.
author: andreasl
"""

import random
import tkinter
import tkinter.font
from dataclasses import dataclass
from typing import List

import treemap


@dataclass
class Item:
    id: int
    value: float


area = treemap.Rectangle(100, 100, 500, 500)
items = [Item(x, random.randint(1, 100)) for x in range(10)]
items.sort(key=lambda i: i.value, reverse=True)
values = [i.value for i in items]
sizes = treemap.get_normalized_sizes(values, area.w * area.h)
layout = treemap.generate_treemap_layout(sizes, area)
items_and_layouts = list(zip(items, layout))

print(values)


# ##############################################


class Rectangle:
    tk_rect_id: int
    tk_text_id: int
    item: Item
    font: tkinter.font.Font = None

    @staticmethod
    def _lazy_init_class_vars():
        if Rectangle.font:
            return
        Rectangle.font = tkinter.font.Font(family="FreeMono", size=12)

    def __init__(self, item, canvas, text, treemap_rect, fill_color):
        Rectangle._lazy_init_class_vars()
        self.item = item
        self.tk_rect_id = canvas.create_rectangle(
            treemap_rect.x,
            treemap_rect.y,
            treemap_rect.x + treemap_rect.w,
            treemap_rect.y + treemap_rect.h,
            fill=fill_color,
        )
        self.tk_text_id = canvas.create_text(
            treemap_rect.x + 2,
            treemap_rect.y + 2,
            anchor="nw",
            fill="#000000",
            font=Rectangle.font,
            text=text,
        )

    def tk_ids(self):
        return {self.tk_rect_id, self.tk_text_id}


class Application:
    canvas = None
    rectangles: List[Rectangle] = None

    def redraw_canvas(self):
        self.canvas.delete("all")
        self.rectangles = []
        for i in items_and_layouts:
            blue = f"0x{int((100 - i[0].value) / 100 * 255):02x}"[2:]
            red = f"0x{int(i[0].value / 100 * 255):02x}"[2:]
            color = f"#{red}00{blue}"

            r = Rectangle(i[0], self.canvas, f"<TEXT>", i[1], color)
            self.canvas.itemconfig(
                r.tk_text_id, text=f"ID: {i[0].id}\ntk IDs: {r.tk_ids()}"
            )

            self.rectangles.append(r)

    def on_click(self, event):
        clicked_widget_id = event.widget.find_closest(event.x, event.y)[0]
        for r in self.rectangles:
            if clicked_widget_id in r.tk_ids():
                print(r.item.id)

    def on_right_click(self, event):
        print(f"You right clicked the canvas with args: {event}")

    def on_resize(self, event):
        self.redraw_canvas()

    def setup_canvas(self, window):
        self.canvas = tkinter.Canvas(window, bg="#0000ff")
        self.canvas.bind("<Button-1>", self.on_click)
        self.canvas.bind("<Button-3>", self.on_right_click)
        self.canvas.bind("<Configure>", self.on_resize)
        self.canvas.pack(expand="yes", fill="both")

    def run(self):
        window = tkinter.Tk()
        # TODO change icon
        window.title("Hello Treemaps")
        window.geometry("600x600")
        self.setup_canvas(window)
        self.redraw_canvas()
        window.mainloop()


app = Application()
app.run()
