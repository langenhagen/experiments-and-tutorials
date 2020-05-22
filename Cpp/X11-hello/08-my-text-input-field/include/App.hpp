/* A simple x11 application for text I/O with a suckless look&feel, similar to `dmenu`.
 https://tools.suckless.org/dmenu/.

author: andreasl
*/
#pragma once

#include <string>
#include <utility>
#include <vector>

#include <X11/Xlib.h>
#include <X11/Xft/Xft.h>

namespace barn {
namespace x11 {

class App;

/*Representation of a text line.*/
struct Line {
    size_t buf_size;  /*Raw text buffer size.*/
    char* buf;  /*Text buffer.*/
    unsigned int len = 0;  /*Length of the string in the line.*/

    explicit Line(const size_t buf_size = 256);  /*Constructor.*/
    explicit Line(const Line& other);  /*Copy constructor.*/
    Line(Line&& other);  /*Move constructor.*/
    Line& operator=(Line&&);  /*Move assignment operator.*/
    ~Line();  /*Destructor.*/
};

/*Representation of integer coordinates.*/
struct IntCoord {
    int y;  /*Row.*/
    int x;  /*Column.*/
};

/*Representation of text coordinates.*/
using TextCoord = IntCoord;

bool operator==(const TextCoord& lhs, const TextCoord& rhs);  /*Test 2 TextCoords for equality.*/

/*Representation of a text input widget.*/
class TextBox {
public:  /*vars*/
    bool has_focus = false;  /*Specifies whether the widget should have the focus.*/
    const size_t y;  /*Widget y position.*/
    const size_t x;  /*Widget x position.*/
    const size_t width;  /*Widget width in pixels.*/
    const size_t height; /*Widget height in pixels.*/
    const size_t max_n_lines;  /*The maximum number of lines that are allowed for input.*/

private:  /*vars*/
    App& _app;  /*Enclosing application.*/
    std::vector<Line> _lines;  /*The lines containing the text.*/
    TextCoord _cur = {0, 0};  /*Cursor position.*/
    TextCoord _sel_start = {-1, -1};  /*Selection boundary.*/
    IntCoord _off = {0, 0};  /*View offset.*/

public:  /*methods*/
    /*Constructor.*/
    TextBox(
        App& app,
        const size_t y,
        const size_t x,
        const size_t width,
        const size_t height,
        const size_t max_n_lines = 0);

    void draw();  /*Draw the widget.*/
    void start_selection();  /*Set the variable selection_start to the current cursor position.*/
    std::string get_text();  /*Get the text from the widget.*/

    void move_cursor(int inc);  /*Move the cursor by increment forward/backward.*/
    void move_cursor_vertically(const int inc);  /*Move the cursor by inc up/down*/
    void move_cursor_by_word(int n_words);  /*Move the cursor by n words.*/

    void insert_char(const char c);  /*Insert given character at the current cursor position.*/
    void delete_chars(int n_chars);  /*Delete given number characters at the cursor position.*/
    bool delete_selected_text();  /*Delete the text within the current selection.*/

    int handle_key_press(XEvent& evt);  /*Handle key press events.*/
    int handle_key_release(XEvent& evt);  /*Handle key release events.*/

private: /*methods*/
    void _invalidate_selection();  /*Set selection-related member variables to invalid values.*/
    /*Get the TextCoords of beginning and end of the current selection.*/
    std::pair<const TextCoord&, const TextCoord&> _get_selection_bounds() const;
    std::string _get_selected_text() const;  /*Get the currently selected text as a string.*/
    void _write_selected_text_to_clipboard() const;  /*Write the selected text to clipboard.*/

    void _draw_background();  /*Draw the background and the border.*/
    void _draw_text();  /*Draw the text.*/
    void _draw_cursor();  /*Draw the text cursor.*/
    void _draw_selection();  /*Draw the selection rectangles.*/

    bool _insert_text(const char* str);  /*Insert given string at the current cursor position.*/
    bool _insert_newline();  /*Insert a newline.*/
    /*Delete the text between given TextCoords.*/
    void _delete_text(const TextCoord& start, const TextCoord& end);
};

/*Simple x11 window application for for text I/O.*/
class App {
public:  /*vars*/
    /*x11 essentials*/
    Display* dpy;
    int screen;
    Window win;
    GC gc;

    /*Xft stuff*/
    XftDraw* xft_drawable;
    XftFont* font;
    unsigned int line_height;

    /*Widgets*/
    TextBox text_box;

    /*State*/
    bool is_ctrl_pressed = false;
    bool is_shift_pressed = false;

private:  /*vars*/
    Window _root_win;

    /*application stuff*/
    unsigned int _height = 300;
    unsigned int _width = 500;

public:  /*methods*/
    App();  /*Constructor.*/
    ~App();  /*Desctructor.*/

    int run();  /*Run the application loop and exit with an error code.*/
    void redraw();  /*Redraw the application.*/

private:  /*methods*/
    int _grab_keyboard();  /*Attempt to grab the keyboard.*/
    void _setup_xft_font();  /*Specify and load the xft font.*/
};

} // namespace x11
} // namespace barn
