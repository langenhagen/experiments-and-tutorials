/* Render an X11 win for multiple lines of text input
and have a text cursor that you can control via (ctrl +) arrow keys, and home/end
as well as a selection via pressing shift.

Apparently, x11 provides own cursors https://tronche.com/gui/x/xlib/pixmap-and-cursor/cursor.html
but they seem inferior. I use a simple filled rectangle

- based on my prior learnings and the source code of dmenu.

author: andreasl
*/
#include <algorithm>
#include <cstring>
#include <iostream>
#include <vector>
#include <thread>
#include <utility>

#include <X11/Xlib.h>
#include <X11/Xutil.h>
#include <X11/Xos.h>
#include <X11/Xft/Xft.h>

struct Line {
    static const unsigned int buf_size = 255;
    int len = 0;
    char buf[buf_size];
};

struct TextCoord {
    int row;
    int col;
};

inline bool operator==(const TextCoord& lhs, const TextCoord & rhs) {
    return lhs.row == rhs.row && lhs.col == rhs.col;
}

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
};

static int grab_keyboard(App* app) {
    using namespace std::chrono_literals;
    /*try to grab keyboard 1000 times.
    we may have to wait for another process to ungrab*/
    for(int i = 0; i < 1000; ++i) {
        int grab_result = XGrabKeyboard(
            app->display /*display*/,
            app->root_win /*grab-win*/,
            True /*owner events*/,
            GrabModeAsync /*pointer mode*/,
            GrabModeAsync /*keyboard mode*/,
            CurrentTime /*time*/);

        if(grab_result == GrabSuccess) {
            return 0;
        }
        std::this_thread::sleep_for(1ms);
    }
    std::cerr << "Could not grab keyboard" << std::endl;
    return 1;
}

static App* setup_x() {
    App* app = new App();
    app->display = XOpenDisplay(nullptr);
    app->screen = DefaultScreen(app->display);
    app->root_win = RootWindow(app->display, app->screen);

    grab_keyboard(app);

    XSetWindowAttributes attrs;
    attrs.override_redirect = True; /*if True, win manager doesn't mess with the win*/
    attrs.background_pixel = 0x000000; /*rgb values*/
    attrs.event_mask =
        ExposureMask
        | KeyPressMask
        | KeyReleaseMask
        | VisibilityChangeMask;

    Screen* screen DefaultScreenOfDisplay(app->display);

    app->win = XCreateWindow(
        app->display /*display*/,
        app->root_win /*parent*/,
        (screen->width - app->width) / 2 /*pos x*/,
        (screen->height - app->height) / 3 /*pos y*/,
        app->width /*width; minimal 1px*/,
        app->height /*height; minimal 1px*/,
        0 /*border width*/,
        CopyFromParent /*depth*/,
        CopyFromParent /*class*/,
        CopyFromParent /*visual*/,
        CWOverrideRedirect | CWBackPixel | CWEventMask /*valuemask; bitmask*/,
        &attrs /*attributes; values matching the valuemask*/);

    XSetStandardProperties(
        app->display,
        app->win,
        "My nice Window",
        "HI!",
        None,
        nullptr,
        0,
        nullptr);

    app->gc = XCreateGC(app->display, app->win, 0, 0);

    const unsigned long white = WhitePixel(app->display, app->screen);
    XSetForeground(app->display, app->gc, white);
    XClearWindow(app->display, app->win);
    XMapRaised(app->display, app->win);

    return app;
};

static void close_x(App* app) {
    XFreeGC(app->display, app->gc);
    XDestroyWindow(app->display, app->win);
    XCloseDisplay(app->display);
}

static void setup_xft_font(App* app) {
    app->xft_drawable = XftDrawCreate(
        app->display,
        app->win /*drawable*/,
        DefaultVisual(app->display, 0),
        DefaultColormap(app->display, 0));

    app->font = XftFontOpen(
        app->display,
        app->screen,
        XFT_FAMILY,
        XftTypeString,
        "monospace",
        XFT_SIZE,
        XftTypeDouble,
        20.0,
        nullptr);

    app->line_height = app->font->ascent + app->font->descent;
}

static void set_window_height(App* app) {
    app->height = app->line_height * 10;
    XResizeWindow(
        app->display /*display*/,
        app->win /*win*/,
        app->width /*width*/,
        app->height /*height*/);
}

static void start_selection(App* app) {
    if(app->selection_start.row == -1) {
        app->selection_start.row = app->cursor.row;
        app->selection_start.col = app->cursor.col;
    }
}

static void invalidate_selection(App* app) {
    if(app->selection_start.row != -1) {
        app->selection_start.row = -1;
        app->selection_start.col = -1;
    }
}

static std::pair<TextCoord&, TextCoord&> get_selection_bounds(App* app) {
    auto& cur = app->cursor;
    auto& sel_start = app->selection_start;
    if(cur.row < sel_start.row) {
        return std::pair<TextCoord&, TextCoord&>(cur, sel_start);
    } else if(cur.row > sel_start.row) {
        return std::pair<TextCoord&, TextCoord&>(sel_start, cur);
    }
    if(cur.col < sel_start.col) {
        return std::pair<TextCoord&, TextCoord&>(cur, sel_start);
    }
    return std::pair<TextCoord&, TextCoord&>(sel_start, cur);
}

static const char* get_selected_chars(App *app) {
    if(app->selection_start.row == -1) {
        return "";
    }
    const auto sel = get_selection_bounds(app);

    int str_len = sel.second.col - sel.first.col + 1;
    for(auto i = sel.first.row; i < sel.second.row; ++i) {
        str_len += app->lines[i].len + 1;
    }
    char* str = new char[str_len];
    if(sel.first.row == sel.second.row) {
        std::memcpy(
            str,
            &app->lines[sel.first.row].buf[sel.first.col],
            str_len - 1);
    } else {
        int off = 0;
        for(auto i = sel.first.row; i <= sel.second.row; ++i) {
            const auto& line = app->lines[i];
            int col = 0;
            int len = line.len;
            if(i == sel.first.row) {
                col = sel.first.col;
                len -= sel.first.col;
            }
            if(i == sel.second.row) {
                len = sel.second.col;
            }
            std::memcpy(&str[off], &line.buf[col], len);
            off +=len;
            if(i != sel.second.row) {
                str[off++] = '\n';
            }
        }
    }
    str[str_len - 1] = '\0';
    return str;
}

static void draw_text(App* app) {
    XRenderColor x_color = {65535 /*red*/, 65535 /*green*/, 65535 /*blue*/, 65535 /*alpha*/};

    XftColor xft_color;
    XftColorAllocValue(
        app->display,
        DefaultVisual(app->display, 0) /*visual*/,
        DefaultColormap(app->display, 0) /*colormap*/,
        &x_color /*color*/,
        &xft_color /*result*/);

    for(size_t i = 0; i < app->lines.size(); ++i) {
        auto& line = app->lines[i];

        XftDrawString8(
            app->xft_drawable /*drawable*/,
            &xft_color /*color*/,
            app->font /*font*/,
            0 /*pos x*/,
            app->line_height * i + app->font->ascent /*pos y*/,
            (unsigned char*)line.buf,
            line.len);
    }
    XftColorFree(
        app->display,
        DefaultVisual(app->display, 0),
        DefaultColormap(app->display, 0),
        &xft_color);
}

static void draw_cursor(App* app) {
    const auto& line = app->lines[app->cursor.row];
    XGlyphInfo glyph_info_all;
    XftTextExtents8(
        app->display /*Display*/,
        app->font /*xftfont*/,
        (XftChar8*)line.buf /*string*/,
        line.len /*int len*/,
        &glyph_info_all /*out glyph info*/);
    XGlyphInfo glyph_info_remaining;
    XftTextExtents8(
        app->display /*Display*/,
        app->font /*xftfont*/,
        (XftChar8*)&line.buf[app->cursor.col] /*string*/,
        line.len - app->cursor.col /*int len*/,
        &glyph_info_remaining /*out glyph info*/);

    const int x = glyph_info_all.width - glyph_info_remaining.width;
    const int y = app->line_height * app->cursor.row;

    XSetForeground(
        app->display /*display*/,
        app->gc,
        0xffffff /*color*/);
    XFillRectangle(
        app->display,
        app->win /*drawable*/,
        app->gc,
        x,
        y,
        3 /*width*/,
        app->line_height /*height*/);
}

static void draw_selection(App* app) {
    if(app->selection_start.row == -1 || app->selection_start == app->cursor) {
        return;
    }
    const auto sel = get_selection_bounds(app);
    for(auto i = sel.first.row; i <= sel.second.row; ++i) {
        const auto& line = app->lines[i];
        int x = 0;

        XGlyphInfo glyph_info_all;
        XftTextExtents8(
            app->display,
            app->font,
            (XftChar8*)line.buf,
            line.len,
            &glyph_info_all);

        int width = glyph_info_all.width;
        if(i == sel.first.row) {
            XGlyphInfo glyph_info_selection;
            XftTextExtents8(
                app->display,
                app->font,
                (XftChar8*)&line.buf[sel.first.col],
                line.len - sel.first.col,
                &glyph_info_selection);

            x = glyph_info_all.width - glyph_info_selection.width;
            width = glyph_info_selection.width;
        }

        if(i == sel.second.row) {
            XGlyphInfo glyph_info_remaining;
            XftTextExtents8(
                app->display,
                app->font,
                (XftChar8*)&line.buf[sel.second.col],
                line.len - sel.second.col,
                &glyph_info_remaining);
             width -= glyph_info_remaining.width;
        }

        XSetForeground(app->display, app->gc, 0x444477 /*color*/);
        XFillRectangle(
            app->display,
            app->win,
            app->gc,
            x,
            app->line_height * i /*y*/,
            width,
            app->line_height);
    }
}

static void move_cursor(App* app, int inc) {
    /*Move the cursor by increment to the left/right and consider line- and text- starts & ends.*/
    if(!app->is_shift_pressed) {
        invalidate_selection(app);
    }
    while(true) {
        auto& row = app->cursor.row;
        auto& col = app->cursor.col;
        if(col + inc < 0) {
            /*left up*/
            if( row == 0) {
                /*to front*/
                row = 0;
                col = 0;
                return;
            } else {
                inc += col + 1;
                row -= 1;
                col = app->lines[row].len;
            }
        } else if(col + inc > app->lines[row].len) {
            /*right down*/
            if(row == app->lines.size() - 1) {
                /*past last position*/
                row = app->lines.size() - 1;
                col = app->lines[row].len;
                return;
            } else {
                inc -= app->lines[row].len - col + 1;
                row += 1;
                col = 0;
            }
        } else {
            /*in same line*/
            col += inc;
            return;
        }
    }
}

static void move_cursor_vertically(App* app, int inc) {
    /*Move the cursor by inc up/down and consider line lengths and text beginning & end.*/
    if(!app->is_shift_pressed) {
        invalidate_selection(app);
    }
    auto& row = app->cursor.row;
    auto& col = app->cursor.col;
    if(row + inc < 0) {
        /*to front*/
        row = 0;
        col = 0;
        return;
    } else if(row + inc >= app->lines.size()) {
        /*past last position*/
        row = app->lines.size() - 1;
        col = app->lines[row].len;
        return;
    } else {
        /*normal movement*/
        row += inc;
        if( col > app->lines[row].len) {
            col = app->lines[row].len;
        }
    }
}

static void move_cursor_by_word(App* app, int n_words) {
    /*Move the cursor by n_words and consider line-lengths and text- starts & end.*/
    if(!app->is_shift_pressed) {
        invalidate_selection(app);
    }
    while(n_words != 0) {
        const auto& line = app->lines[app->cursor.row];
        const auto& buf = line.buf;
        const auto col = app->cursor.col;
        if(n_words < 0) {
            /*go back*/
            int i = col - 1;
            while(i > 0 && !(buf[i - 1] == ' ' && buf[i] != ' ')) {
                --i;
            }
            move_cursor(app, i - col);
            ++n_words;
        }
        else {
            /*go forward*/
            int i = col + 1;
            while(i < line.len && !(buf[i - 1] != ' ' && buf[i] == ' ')) {
                ++i;
            }
            move_cursor(app, i - col);
            --n_words;
        }
    }
}

static void insert_char(App* app, const char c) {
    /*Insert a character into the text at the current cursor's position.*/
    invalidate_selection(app);

    auto& line = app->lines[app->cursor.row];
    const auto col = app->cursor.col;

    std::memmove(line.buf + col + 1, line.buf + col, line.len - col);
    std::memcpy(line.buf + col, &c, 1);

    ++app->cursor.col;
    ++line.len;
}

static void insert_text(App* app, char* str) {
    /*Insert the given string at the cursor position.*/
    auto& cur = app->cursor;
    auto& lines = app->lines;

    /*save initial line ending*/
    Line tmp;
    tmp.len = lines[cur.row].len - cur.col;
    std::memcpy(tmp.buf, lines[cur.row].buf + cur.col, tmp.len);
    lines[cur.row].len = cur.col;

    /*copy lines*/
    int line_start = 0;
    int line_end = 0;
    while(str[line_end] != '\0') {
        if(str[line_end] == '\n') {
            std::memcpy(
                lines[cur.row].buf + lines[cur.row].len,
                str + line_start,
                line_end - line_start);
            lines[cur.row].len += line_end - line_start;
            ++cur.row;
            cur.col = 0;
            lines.emplace(lines.begin() + cur.row);
            line_start = line_end + 1;
        }
        ++line_end;
    }

    /*last line*/
    auto& line = lines[cur.row];
    std::memcpy(line.buf + line.len, str + line_start, line_end - line_start);
    std::memcpy(line.buf + line.len + line_end - line_start, tmp.buf, tmp.len);
    line.len += line_end - line_start + tmp.len;
    cur.col = line_end - line_start;
}

static void delete_chars(App* app, int n_chars) {
    /*Delete the given number characters from the text at the cursor's position.*/
    while(true) {
        auto& row = app->cursor.row;
        auto& col = app->cursor.col;
        auto& lines = app->lines;
        auto& line = lines[row];
        char* pos = line.buf + col;

        if(col + n_chars < 0) {
            /*left up*/
            if(row == 0) {
                n_chars = -col;
            } else {
                auto& line_above = lines[row - 1];
                const auto new_col = line_above.len;
                std::memcpy(line_above.buf + line_above.len, pos, line.len - col);
                line_above.len += line.len - col;
                lines.erase(lines.begin() + row);
                n_chars += col + 1;
                --row;
                col = new_col;
            }
        } else if(col + n_chars > line.len) {
            /*right down*/
            if(row == lines.size() - 1) {
                n_chars = line.len - col;
            } else {
                n_chars -= line.len - col + 1;
                auto& line_below = lines[row + 1];
                std::memcpy(pos, line_below.buf, line_below.len);
                line.len = col + line_below.len;
                lines.erase(lines.begin() + row + 1);
            }
        } else {
            /*in same line*/
            if (n_chars < 0) {
                std::memmove(pos + n_chars, pos, line.len - col);
                col += n_chars;
                line.len += n_chars;
            } else {
                std::memmove(pos, pos + n_chars, line.len - col + n_chars);
                line.len -= n_chars;
            }
            return;
        }
    }
}

static void delete_text(App *app, const TextCoord& start, const TextCoord& end) {
    /*Delete the text between the given text coordinates.*/
    auto& lines = app->lines;
    const auto remaining_len = lines[end.row].len - end.col;
    std::memmove(
        lines[start.row].buf + start.col,
        lines[end.row].buf + end.col,
        remaining_len);
    lines[start.row].len = start.col + remaining_len;
    for(auto i = start.row + 1; i <= end.row; ++i) {
        lines.erase(lines.begin() + end.row);
    }
}

static bool delete_selected_text(App *app) {
    if(app->selection_start.row == -1 ||  app->selection_start == app->cursor) {
        invalidate_selection(app);
        return false;
    }
    const auto sel = get_selection_bounds(app);
    delete_text(app, sel.first, sel.second);
    app->cursor.row = sel.first.row;
    app->cursor.col = sel.first.col;
    invalidate_selection(app);
    return true;
}

static void insert_newline(App* app) {
    auto& row = app->cursor.row;
    auto& col = app->cursor.col;
    auto& lines = app->lines;
    auto& new_line = *lines.emplace(lines.begin() + row + 1);
    auto& line = lines[row];
    char* pos = line.buf + col;

    new_line.len = line.len - col;
    line.len -= new_line.len;
    std::memcpy(new_line.buf, pos, new_line.len);
    ++row;
    col = 0;
}

static void redraw(App* app) {
    XClearWindow(app->display, app->win);
    draw_selection(app);
    draw_text(app);
    draw_cursor(app);
}

static int handle_key_press(App* app, XEvent& evt) {
    const unsigned int key_code = evt.xkey.keycode;
    const int buf_size = 8;
    char buf[buf_size];

    if(key_code == 9 /*esc*/) {
        return 1;
    } else if(key_code == 37 /*ctrl left*/ || key_code == 105 /*ctrl right*/) {
        app->is_ctrl_pressed = true;
        return 0;
    } else if(key_code == 50 /*shift left*/ || key_code == 62 /*shift right*/) {
        app->is_shift_pressed = true;
        start_selection(app);
        return 0;
    } else if(app->is_ctrl_pressed && key_code == 54 /*ctrl + c*/) {
        std::cout << get_selected_chars(app) << std::endl;
    } else if(app->is_ctrl_pressed && key_code == 55 /*ctrl + v*/) {
        // TODO insert text from clipboard
        char c[255] = ":this is\n\na small \ntest:";
        insert_text(app, c);
    } else if(key_code == 22 /*backspace*/) {
        if(!delete_selected_text(app)) {
            delete_chars(app, -1);
        }
    } else if(key_code == 119 /*delete*/) {
        if(!delete_selected_text(app)) {
            delete_chars(app, +1);
        }
    } else if(key_code == 36 /*enter*/) {
        delete_selected_text(app);
        insert_newline(app);
    } else if(key_code == 111 /*up arrow key*/) {
        move_cursor_vertically(app, -1);
    } else if(key_code == 116 /*down arrow key*/) {
        move_cursor_vertically(app, +1);
    } else if(key_code == 113 /*left arrow key*/) {
        app->is_ctrl_pressed ? move_cursor_by_word(app, -1) : move_cursor(app, -1);
    } else if(key_code == 114 /*right arrow key*/) {
        app->is_ctrl_pressed ? move_cursor_by_word(app, +1) : move_cursor(app, +1);
    } else if(key_code == 110 /*home key*/) {
        app->cursor.col = 0;
    } else if(key_code == 115 /*end key*/) {
        app->cursor.col = app->lines[app->cursor.row].len;
    } else if(XLookupString(
            &evt.xkey,
            buf,
            buf_size,
            nullptr,
            nullptr) > 0) {
        /* normal text input*/
        delete_selected_text(app);
        insert_char(app, buf[0]);
    } else {
        return 0;
    }
    redraw(app);
    return 0;
}

static int handle_key_release(App* app, XEvent evt) {
    const unsigned int key_code = evt.xkey.keycode;
    if(key_code == 37 /*ctrl left*/  || key_code == 105 /*ctrl + right*/) {
        app->is_ctrl_pressed = false;
    } else if(key_code == 50 /*shift left*/ || key_code == 62 /*shift right*/) {
        app->is_shift_pressed = false;
    }
    return 0;
}

static int run(App* app) {
    XEvent evt;
    while(!XNextEvent(app->display, &evt)) {
        switch(evt.type) {
        case Expose:
            if(evt.xexpose.count == 0) {
                redraw(app);
            }
            break;
        case KeyPress:
            if(handle_key_press(app, evt)) {
                return 0;
            }
            break;
        case KeyRelease:
            if(handle_key_release(app, evt)) {
                return 0;
            }
            break;
        case VisibilityNotify:
            if (evt.xvisibility.state != VisibilityUnobscured) {
                XRaiseWindow(app->display, app->win);
            }
            break;
        }
    }
}

int main(int argc, const char* argv[]) {
    App* app = setup_x();
    setup_xft_font(app);
    app->lines.emplace_back();
    set_window_height(app);
    run(app);
    close_x(app);
    return 0;
}
