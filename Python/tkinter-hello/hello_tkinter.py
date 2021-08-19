import random
import tkinter
import tkinter.font


class Rectangle:
    tk_rect_id: int
    tk_text_id: int
    font: tkinter.font.Font = None

    @staticmethod
    def _lazy_init_class_vars():
        if Rectangle.font:
            return
        Rectangle.font = tkinter.font.Font(family="FreeMono", size=12)

    def __init__(self, canvas, fill_color):
        Rectangle._lazy_init_class_vars()

        if canvas.winfo_height() > 50:
            max_y = canvas.winfo_height()
            max_x = canvas.winfo_width()
        else:
            max_y = 200
            max_x = 200

        w = random.randint(50, max_x // 2)
        h = random.randint(50, max_y // 2)
        x = random.randint(0, max_x - w)
        y = random.randint(0, max_y - h)

        self.tk_rect_id = canvas.create_rectangle(x, y, x + w, y + h, fill=fill_color)
        self.tk_text_id = canvas.create_text(
            x + 2,
            y + 2,
            anchor="nw",
            fill="#000000",
            font=Rectangle.font,
            text=f"ID: {self.tk_rect_id}",
        )

    def as_set(self):
        return {self.tk_rect_id, self.tk_text_id}


class Application:

    canvas = None
    red_rectangle = None
    yellow_rectangle = None

    def redraw_canvas(self):
        self.canvas.delete("all")
        self.red_rectangle = Rectangle(self.canvas, "#ff0000")
        self.yellow_rectangle = Rectangle(self.canvas, "#ffff00")

    def on_click(self, event):
        # breakpoint()
        # print(f"You clicked a rectangle with args: {event}")
        # print(f"At: {event.x}, {event.y}")
        # print(f"Widget: {event.widget.find_closest(event.x, event.y)}")

        clicked_widget_id = event.widget.find_closest(event.x, event.y)[0]

        # print(clicked_widget_id)
        if clicked_widget_id in self.red_rectangle.as_set():
            print("RED")
        elif clicked_widget_id in self.yellow_rectangle.as_set():
            print("YELLOW")
        else:
            raise ValueError(
                f"clicked_widget_id={clicked_widget_id}\n"
                f"Red Rectangle={self.red_rectangle}\n"
                f"Yellow Rectangle={self.yellow_rectangle}"
            )

        self.redraw_canvas()

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
        window.title("Hello TKinter")
        window.geometry("300x300")
        self.setup_canvas(window)
        self.redraw_canvas()
        window.mainloop()


app = Application()
app.run()
