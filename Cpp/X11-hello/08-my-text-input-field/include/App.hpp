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

struct App;

/*A representation for a text line.*/
struct Line {
    // static constexpr const size_t buf_size = 256;  /*Raw text buffer size.*/
    // char buf[buf_size];  /*Text buffer.*/
    // unsigned int len = 0;  /*Length of the string in the line.*/
    size_t buf_size;  /*Raw text buffer size.*/
    char* buf;  /*Text buffer.*/
    unsigned int len = 0;  /*Length of the string in the line.*/

    explicit Line(const size_t buf_size = 256);  /*Constructor.*/
    explicit Line(const Line& other);  /*Copy constructor.*/
    Line(Line&& other);  /*Move constructor.*/
    Line& operator=(Line&&);  /*Move assignment operator.*/
    ~Line();  /*Destructor.*/
};

/*A representation of text coordinates.*/
struct TextCoord {
    int y;  /*Row.*/
    int x;  /*Column.*/
};

bool operator==(const TextCoord& lhs, const TextCoord& rhs);  /*Test 2 TextCoords for equality.*/

/*Representation of a text input field widget.*/
struct TextBox {
    App& app;  /*Enclosing application.*/

    std::vector<Line> lines;  /*The lines containing the text.*/
    const size_t width;  /*Widget width in characters.*/
    const size_t height; /*Widget height in characters.*/
    const size_t max_n_lines;  /*The maximum number of lines that are allowed for input.*/
    TextCoord cursor = {0, 0};  /*Cursor position.*/
    TextCoord selection_start = {-1, -1};  /*Selection boundary.*/
    bool has_focus = false;  /*Specifies whether the widget should have the focus.*/

    /*Constructor.*/
    TextBox(App& app, const size_t width, const size_t height, const size_t max_n_lines = 1);

    void start_selection();  /*Set the variable selection_start to the current cursor position.*/
    void invalidate_selection();  /*Set selection-related member variables to invalid values.*/
    /*Get the TextCoords of beginning and end of the current selection.*/
    std::pair<const TextCoord&, const TextCoord&> get_selection_bounds() const;
    std::string get_selected_text() const;  /*Get the currently selected text as a string.*/
    void write_selected_text_to_clipboard() const;  /*Write the selected text to clipboard.*/

    void draw_text();  /*Draw the text.*/
    void draw_cursor();  /*Draw the text cursor.*/
    void draw_selection();  /*Draw the selection rectangles.*/
    void draw();  /*Draw the widget.*/

    void move_cursor(int inc);  /*Move the cursor by increment forward/backward.*/
    void move_cursor_vertically(const int inc);  /*Move the cursor by inc up/down*/
    void move_cursor_by_word(int n_words);  /*Move the cursor by n words.*/

    void insert_char(const char c);  /*Insert given character at the current cursor position.*/
    void insert_text(const char* str);  /*Insert given string at the current cursor position*/
    void insert_newline();  /*Insert a newline.*/
    void delete_chars(int n_chars);  /*Delete given number characters at the cursor position.*/
    /*Delete the text between given TextCoords.*/
    void delete_text(const TextCoord& start, const TextCoord& end);
    bool delete_selected_text();  /*Delete the text within the current selection.*/

    int handle_key_press(XEvent& evt);  /*Handle key press events.*/
    int handle_key_release(XEvent& evt);  /*Handle key release events.*/
};

/*Simple x11 window application for for text I/O.*/
struct App {
    /*x11 essentials*/
    Display* display;
    int screen;
    Window root_win;
    Window win;
    GC gc;

    /*Xft stuff*/
    XftDraw* xft_drawable;
    XftFont* font;

    /*application stuff*/
    unsigned int height = 300;
    unsigned int width = 500;
    unsigned int line_height;

    TextBox text_box;

    bool is_ctrl_pressed = false;
    bool is_shift_pressed = false;

    /*constructors & destructor*/

    /*Constructor.*/
    App();

    /*Desctructor.*/
    ~App();

    /*methods*/

    /*Redraw the application.*/
    void redraw();

    /*Attempt to grab the keyboard.*/
    int grab_keyboard();

    /*Specify and load the xft font.*/
    void setup_xft_font();

    /*Run the application loop and exit with an error code.*/
    int run();
};

} // namespace x11
} // namespace barn
