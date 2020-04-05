/* A simple x11 application for text I/O with a suckless look&feel, similar to `dmenu`
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

/*A representation for a text line.*/
struct Line {
    static const unsigned int buf_size = 255;
    unsigned int len = 0;
    char buf[buf_size];
};

/*A representation of text coordinates.*/
struct TextCoord {
    int y;  //< row
    int x;  //< column
};

/*Compare two text coordinates for equality.*/
inline bool operator==(const TextCoord& lhs, const TextCoord& rhs) {
    return lhs.y == rhs.y && lhs.x == rhs.x;
}

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

    std::vector<Line> lines;
    TextCoord cursor = {0, 0};
    TextCoord selection_start = {-1, -1};

    bool is_ctrl_pressed = false;
    bool is_shift_pressed = false;

    /*constructors & destructor*/

    /*Constructor.*/
    App();

    /*Desctructor.*/
    ~App();

    /*methods*/

    /*Attempt to grab the keyboard.*/
    int grab_keyboard();

    /*Specify and load the xft font.*/
    void setup_xft_font();

    /*Set the variable selection_start to the current cursor position.*/
    void start_selection();

    /*Set the selection member variables to invalid values.*/
    void invalidate_selection();

    /*Retrieve the Text coordinates of beginning and end of the current selection.*/
    std::pair<const TextCoord&, const TextCoord&> get_selection_bounds() const;

    /*Retrieve the currently selected text with newlines handled nicely.*/
    std::string get_selected_text() const;

    /*Write the selectd text to clipboard with newlines handled nicely.*/
    void write_selected_text_to_clipboard() const;

    /*Draw the text.*/
    void draw_text();

    /*Draw the text cursor.*/
    void draw_cursor();

    /* Draw the selection rectangles.*/
    void draw_selection();

    /*Move the cursor by increment to the left/right
    and consider line- and text- starts & ends.*/
    void move_cursor(int inc);

    /*Move the cursor by inc up/down and consider
    line lengths and text beginning & end.*/
    void move_cursor_vertically(const int inc);

    /*Move the cursor by n_words and consider
    line-lengths and text- starts & end.*/
    void move_cursor_by_word(int n_words);

    /*Insert the given character at the cursor position.*/
    void insert_char(const char c);

    /*Insert the given string at the cursor position
    and handle newlines nicely.*/
    void insert_text(const char* str);

    /*Delete the given number characters from the text
    at the cursor position.*/
    void delete_chars(int n_chars);

    /*Delete the text between the given text coodrinates.*/
    void delete_text(const TextCoord& start, const TextCoord& end);

    /*Delete the text within the current selection.*/
    bool delete_selected_text();

    /*Insert a newline.*/
    void insert_newline();

    /*Clear the view and redraw the app's elements.*/
    void redraw();

    /*Handle key press events.*/
    int handle_key_press(XEvent& evt);

    /*Handle key release events.*/
    int handle_key_release(XEvent& evt);

    /*Run the application loop and exit with an error code.*/
    int run();
};

} // namespaces x11
} // namespace barn
