"""A basic treeview example."""

import tkinter

# from PIL import ImageTk, Image
from tkinter import ttk


class Application:
    treeview = None

    def setup_treeview(self, window):
        # apparently, tkinter only supports GIFs - and even that fails
        # logo = tkinter.PhotoImage( file = 'res/DLR-square.gif').subsample(10,10)
        # also using PIL does not work
        # logo = ImageTk.PhotoImage(Image.open('res/DLR-square.gif'))
        # treeview = self.treeview

        self.treeview = ttk.Treeview(window, columns=("size", "modified"))
        # self.treeview['columns'] = ('size', 'modified', 'owner')
        self.treeview.heading("size", text="Size")
        self.treeview.heading("modified", text="Modified")

        self.treeview.pack()
        # tree.view insert be like <parentID>, <position>, <id>, [<text>]
        self.treeview.insert("", 0, "item0", text="Item 0")
        self.treeview.insert("", 1, "item1", text="Item 1", values=("15KB tomorrow"))
        self.treeview.insert("", 0, "item2", text="Item 2", values=("10KB"))
        self.treeview.insert("", 99, "item3", text="Item 3")
        self.treeview.insert("", "end", "item4", text="Item 4")
        self.treeview.insert("", 2, "item5", text="Item 5")
        # imaging does not work here
        # self.treeview.insert('item1', 'end', 'item1.1', text = 'Item 1.1', image = logo)
        self.treeview.insert("item1", "end", "item1.1", text="Item 1.1", values=("foo bar"))
        self.treeview.insert("item1", "end", "item1.2", text="Item 1.2")
        self.treeview.insert("item1", "end", "item1.3", text="Item 1.3")

        self.treeview.move("item1.1", "item2", "end")
        self.treeview.config(height=10)

        self.treeview.item("item2", open=True)
        is_item2_open = self.treeview.item("item2", "open")

        self.treeview.detach("item1.2")  # removes item from the view but the item still exists
        self.treeview.move("item1.2", "item2", "0")  # move the detached item back

        self.treeview.delete("item5")

        self.treeview.bind("<Button-1>", self.on_click)
        self.treeview.bind("<Button-3>", self.on_right_click)
        self.treeview.bind("<Double-Button-1>", self.on_double_click)

    def on_click(self, event):
        item_id = self.treeview.identify_row(event.y)
        if item_id:
            # mouse pointer over item
            # self.treeview.selection_set(item_id)
            print(f"Clicked on {item_id}")
            self.treeview.item(item_id, open=True)
        else:
            # mouse pointer not over item
            # occurs when items do not fill frame
            # no action required
            pass

    def on_right_click(self, event):
        item_id = self.treeview.identify_row(event.y)
        if item_id:
            print(f"Right clicked on {item_id}")

    def on_double_click(self, event):
        item_id = self.treeview.identify_row(event.y)
        if item_id:
            print(f"Double clicked on {item_id}")

    def run(self):
        window = tkinter.Tk()
        # TODO change i  con
        window.title("Hello TKinter Treeview")
        window.geometry("600x600")
        self.setup_treeview(window)
        window.mainloop()


app = Application()
app.run()
