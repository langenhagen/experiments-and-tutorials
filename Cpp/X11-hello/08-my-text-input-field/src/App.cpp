/* A simple x11 application with a simple suckless interactive text widget for text I/O.

author: andreasl
*/
#include "App.hpp"

#include "XCopyPaste.hpp"

#include <X11/Xlib.h>
#include <X11/Xft/Xft.h>

#include <chrono>
#include <cstring>
#include <iostream>
#include <string>
#include <thread>
#include <utility>
#include <vector>

namespace barn {
namespace x11 {

bool operator==(const TextCoord& lhs, const TextCoord& rhs) {
    return lhs.y == rhs.y && lhs.x == rhs.x;
}

App::App() : text_box(*this) {
    this->display = XOpenDisplay(nullptr);
    this->screen = DefaultScreen(this->display);
    this->root_win = RootWindow(this->display, this->screen);

    XSetWindowAttributes attrs;
    attrs.override_redirect = True; /*if True, window manager doesn't mess with the window*/
    attrs.background_pixel = 0x282828; /*rgb values*/
    attrs.event_mask =
        ExposureMask
        | KeyPressMask
        | KeyReleaseMask
        | VisibilityChangeMask;

    const Screen* const screen DefaultScreenOfDisplay(this->display);
    this->win = XCreateWindow(
        this->display /*display*/,
        this->root_win /*parent*/,
        (screen->width - this->width) / 2 /*pos x*/,
        (screen->height - this->height) / 3 /*pos y*/,
        this->width /*width; minimal 1px*/,
        this->height /*height; minimal 1px*/,
        0 /*border width*/,
        CopyFromParent /*depth*/,
        CopyFromParent /*class*/,
        CopyFromParent /*visual*/,
        CWOverrideRedirect | CWBackPixel | CWEventMask /*valuemask; bitmask*/,
        &attrs /*attributes; values matching the valuemask*/);

    XSetStandardProperties(
        this->display,
        this->win,
        "My nice Window",
        "HI!",
        None,
        nullptr,
        0,
        nullptr);

    setup_xft_font();

    this->gc = XCreateGC(this->display, this->win, 0, 0);
    XClearWindow(this->display, this->win);
    XMapRaised(this->display, this->win);

    grab_keyboard();
}

int App::grab_keyboard() {
    /*try to grab keyboard 1000 times.
    We may have to wait for another process to ungrab.*/
    for (int i = 0; i < 1000; ++i) {
        int grab_result = XGrabKeyboard(
            this->display /*display*/,
            this->root_win /*grab-win*/,
            True /*owner events*/,
            GrabModeAsync /*pointer mode*/,
            GrabModeAsync /*keyboard mode*/,
            CurrentTime /*time*/);

        if (grab_result == GrabSuccess) {
            return 0;
        }
        std::this_thread::sleep_for(std::chrono::milliseconds(1));
    }
    std::cerr << "Could not grab keyboard" << std::endl;
    return 1;
}

App::~App() {
    XFreeGC(this->display, this->gc);
    XDestroyWindow(this->display, this->win);
    XCloseDisplay(this->display);
}

void App::setup_xft_font() {
    this->xft_drawable = XftDrawCreate(
        this->display,
        this->win /*drawable*/,
        DefaultVisual(this->display, 0),
        DefaultColormap(this->display, 0));

    this->font = XftFontOpen(
        this->display,
        this->screen,
        XFT_FAMILY,
        XftTypeString,
        "monospace",
        XFT_SIZE,
        XftTypeDouble,
        20.0,
        nullptr);

    this->line_height = this->font->ascent + this->font->descent;
}

TextBox::TextBox(App& app) : app(app)
{}

void TextBox::start_selection() {
    if (this->selection_start.y == -1) {
        this->selection_start.y = this->cursor.y;
        this->selection_start.x = this->cursor.x;
    }
}

void TextBox::invalidate_selection() {
    if (this->selection_start.y != -1) {
        this->selection_start.y = -1;
        this->selection_start.x = -1;
    }
}

std::pair<const TextCoord&, const TextCoord&> TextBox::get_selection_bounds() const {
    const TextCoord& cur = this->cursor;
    const TextCoord& sel_start = this->selection_start;
    if (cur.y < sel_start.y || (cur.y == sel_start.y && cur.x < sel_start.x)) {
        return std::pair<const TextCoord&, const TextCoord&>(cur, sel_start);
    }
    return std::pair<const TextCoord&, const TextCoord&>(sel_start, cur);
}

std::string TextBox::get_selected_text() const {
    if (this->selection_start.y == -1) {
        return "";
    }
    const auto sel = get_selection_bounds();

    int str_len = sel.second.x - sel.first.x + 1;
    for (auto i = sel.first.y; i < sel.second.y; ++i) {
        str_len += this->lines[i].len + 1;
    }
    char str[str_len];
    if (sel.first.y == sel.second.y) {
        std::memcpy(
            str,
            &this->lines[sel.first.y].buf[sel.first.x],
            str_len - 1);
    } else {
        int off = 0;
        for (auto i = sel.first.y; i <= sel.second.y; ++i) {
            const auto& line = this->lines[i];
            int col = 0;
            int len = line.len;
            if (i == sel.first.y) {
                col = sel.first.x;
                len -= sel.first.x;
            }
            if (i == sel.second.y) {
                len = sel.second.x;
            }
            std::memcpy(&str[off], &line.buf[col], len);
            off +=len;
            if (i != sel.second.y) {
                str[off++] = '\n';
            }
        }
    }
    str[str_len - 1] = '\0';
    return std::string(str);
}

void TextBox::write_selected_text_to_clipboard() const {
    ::barn::x11::cp::write_to_clipboard(get_selected_text());
}

void TextBox::draw_text() {
    XRenderColor x_color = {65535 /*red*/, 65535 /*green*/, 65535 /*blue*/, 65535 /*alpha*/};

    XftColor xft_color;
    XftColorAllocValue(
        app.display,
        DefaultVisual(app.display, app.screen) /*visual*/,
        DefaultColormap(app.display, app.screen) /*colormap*/,
        &x_color /*color*/,
        &xft_color /*result*/);

    for (size_t i = 0; i < this->lines.size(); ++i) {
        auto& line = this->lines[i];

        XftDrawString8(
            app.xft_drawable /*drawable*/,
            &xft_color /*color*/,
            app.font /*font*/,
            0 /*pos x*/,
            app.line_height * i + app.font->ascent /*pos y*/,
            (unsigned char*)line.buf,
            line.len);
    }
    XftColorFree(
        app.display,
        DefaultVisual(app.display, 0),
        DefaultColormap(app.display, 0),
        &xft_color);
}

void TextBox::draw_cursor() {
    const auto& line = this->lines[this->cursor.y];
    XGlyphInfo glyph_info_all;
    XftTextExtents8(
        app.display /*Display*/,
        app.font /*xftfont*/,
        reinterpret_cast<const XftChar8*>(line.buf) /*string*/,
        line.len /*int len*/,
        &glyph_info_all /*out glyph info*/);
    XGlyphInfo glyph_info_remaining;
    XftTextExtents8(
        app.display /*Display*/,
        app.font /*xftfont*/,
        reinterpret_cast<const XftChar8*>(&line.buf[this->cursor.x]) /*string*/,
        line.len - this->cursor.x /*int len*/,
        &glyph_info_remaining /*out glyph info*/);

    const int x = glyph_info_all.width - glyph_info_remaining.width;
    const int y = app.line_height * this->cursor.y;

    XSetForeground(
        app.display /*display*/,
        app.gc,
        0xffffff /*color*/);
    XFillRectangle(
        app.display,
        app.win /*drawable*/,
        app.gc,
        x,
        y,
        3 /*width*/,
        app.line_height /*height*/);
}

void TextBox::draw_selection() {
    if (this->selection_start.y == -1 || this->selection_start == this->cursor) {
        return;
    }

    const auto sel = get_selection_bounds();
    for (auto i = sel.first.y; i <= sel.second.y; ++i) {
        const auto& line = this->lines[i];
        int x = 0;

        XGlyphInfo glyph_info_all;
        XftTextExtents8(
            app.display,
            app.font,
            reinterpret_cast<const XftChar8*>(line.buf),
            line.len,
            &glyph_info_all);

        int width = glyph_info_all.width;
        if (i == sel.first.y) {
            XGlyphInfo glyph_info_selection;
            XftTextExtents8(
                app.display,
                app.font,
                reinterpret_cast<const XftChar8*>(&line.buf[sel.first.x]),
                line.len - sel.first.x,
                &glyph_info_selection);

            x = glyph_info_all.width - glyph_info_selection.width;
            width = glyph_info_selection.width;
        }

        if (i == sel.second.y) {
            XGlyphInfo glyph_info_remaining;
            XftTextExtents8(
                app.display,
                app.font,
                reinterpret_cast<const XftChar8*>(&line.buf[sel.second.x]),
                line.len - sel.second.x,
                &glyph_info_remaining);
             width -= glyph_info_remaining.width;
        }

        XSetForeground(app.display, app.gc, 0x444477 /*color*/);
        XFillRectangle(
            app.display,
            app.win,
            app.gc,
            x,
            app.line_height * i /*y*/,
            width,
            app.line_height);
    }
}

void TextBox::move_cursor(int inc) {
    /*Move the cursor by increment to the left/right and consider line- and text- starts & ends.*/
    if (!app.is_shift_pressed) {
        invalidate_selection();
    }
    while (true) {
        auto& row = this->cursor.y;
        auto& col = this->cursor.x;
        if (col + inc < 0) {
            /*left up*/
            if (row == 0) {
                /*to front*/
                row = 0;
                col = 0;
                return;
            } else {
                inc += col + 1;
                row -= 1;
                col = this->lines[row].len;
            }
        } else if (col + inc > this->lines[row].len) {
            /*right down*/
            if (row == this->lines.size() - 1) {
                /*past last position*/
                row = this->lines.size() - 1;
                col = this->lines[row].len;
                return;
            } else {
                inc -= this->lines[row].len - col + 1;
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

void TextBox::move_cursor_vertically(const int inc) {
    /*Move the cursor by inc up/down and consider line lengths and text beginning & end.*/
    if (!app.is_shift_pressed) {
        invalidate_selection();
    }
    auto& row = this->cursor.y;
    auto& col = this->cursor.x;
    if (row + inc < 0) {
        /*to front*/
        row = 0;
        col = 0;
        return;
    } else if (row + inc >= this->lines.size()) {
        /*past last position*/
        row = this->lines.size() - 1;
        col = this->lines[row].len;
        return;
    } else {
        /*normal movement*/
        row += inc;
        if (col > this->lines[row].len) {
            col = this->lines[row].len;
        }
    }
}

void TextBox::move_cursor_by_word(int n_words) {
    /*Move the cursor by n_words and consider line-lengths and text- starts & end.*/
    if (!app.is_shift_pressed) {
        invalidate_selection();
    }
    while (n_words != 0) {
        const auto& line = this->lines[this->cursor.y];
        const auto& buf = line.buf;
        const auto col = this->cursor.x;
        if (n_words < 0) {
            /*go back*/
            int i = col - 1;
            while (i > 0 && !(buf[i - 1] == ' ' && buf[i] != ' ')) {
                --i;
            }
            move_cursor(i - col);
            ++n_words;
        } else {
            /*go forward*/
            int i = col + 1;
            while (i < line.len && !(buf[i - 1] != ' ' && buf[i] == ' ')) {
                ++i;
            }
            move_cursor(i - col);
            --n_words;
        }
    }
}

void TextBox::insert_char(const char c) {
    /*Insert a character into the text at the current cursor's position.*/
    invalidate_selection();

    auto& line = this->lines[this->cursor.y];
    const auto col = this->cursor.x;

    std::memmove(line.buf + col + 1, line.buf + col, line.len - col);
    std::memcpy(line.buf + col, &c, 1);

    ++this->cursor.x;
    ++line.len;
}

void TextBox::insert_text(const char* str) {
    /*Insert the given string at the cursor position and handle newlines nicely.*/
    auto& cur = this->cursor;
    auto& lines = this->lines;

    /*save initial line ending*/
    Line tmp;
    tmp.len = lines[cur.y].len - cur.x;
    std::memcpy(tmp.buf, lines[cur.y].buf + cur.x, tmp.len);
    lines[cur.y].len = cur.x;

    /*copy lines*/
    int line_start = 0;
    int line_end = 0;
    while (str[line_end] != '\0') {
        if (str[line_end] == '\n') {
            std::memcpy(
                lines[cur.y].buf + lines[cur.y].len,
                str + line_start,
                line_end - line_start);
            lines[cur.y].len += line_end - line_start;
            ++cur.y;
            cur.x = 0;
            lines.emplace(lines.begin() + cur.y);
            line_start = line_end + 1;
        }
        ++line_end;
    }

    /*last line*/
    auto& line = lines[cur.y];
    std::memcpy(line.buf + line.len, str + line_start, line_end - line_start);
    std::memcpy(line.buf + line.len + line_end - line_start, tmp.buf, tmp.len);
    line.len += line_end - line_start + tmp.len;
    cur.x = line_end + (line_start == 0 ? cur.x : -line_start);
}

void TextBox::delete_chars(int n_chars) {
    /*Delete the given number characters from the text at the cursor's position.*/
    while (true) {
        auto& row = this->cursor.y;
        auto& col = this->cursor.x;
        auto& lines = this->lines;
        auto& line = lines[row];
        char* pos = line.buf + col;

        if (col + n_chars < 0) {
            /*left up*/
            if (row == 0) {
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
        } else if (col + n_chars > line.len) {
            /*right down*/
            if (row == lines.size() - 1) {
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

void TextBox::delete_text(const TextCoord& start, const TextCoord& end) {
    /*Delete the text between the given text coordinates.*/
    auto& lines = this->lines;
    const auto remaining_len = lines[end.y].len - end.x;
    std::memmove(
        lines[start.y].buf + start.x,
        lines[end.y].buf + end.x,
        remaining_len);
    lines[start.y].len = start.x + remaining_len;
    for (auto i = start.y + 1; i <= end.y; ++i) {
        lines.erase(lines.begin() + end.y);
    }
}

bool TextBox::delete_selected_text() {
    if (this->selection_start.y == -1 ||  this->selection_start == this->cursor) {
        invalidate_selection();
        return false;
    }
    const auto sel = get_selection_bounds();
    delete_text(sel.first, sel.second);
    this->cursor.y = sel.first.y;
    this->cursor.x = sel.first.x;
    invalidate_selection();
    return true;
}

void TextBox::insert_newline() {
    if (this->max_n_lines == this->lines.size()) {
        return;
    }
    auto& row = this->cursor.y;
    auto& col = this->cursor.x;
    auto& lines = this->lines;
    auto& new_line = *lines.emplace(lines.begin() + row + 1);
    auto& line = lines[row];
    char* pos = line.buf + col;

    new_line.len = line.len - col;
    line.len -= new_line.len;
    std::memcpy(new_line.buf, pos, new_line.len);
    ++row;
    col = 0;
}

void TextBox::draw() {
    draw_selection();
    draw_text();
    draw_cursor();
}

void App::redraw() {
    XClearWindow(this->display, this->win);
    text_box.draw();
}

int TextBox::handle_key_press(XEvent& evt) {
    if (!this->has_focus) {
        return 0;
    }
    const int buf_size = 8;
    char buf[buf_size];

    switch (evt.xkey.keycode) {
        case 9: /*esc*/
            return 1;
        case 37: /*ctrl left*/
        case 105: /*ctrl right*/
            app.is_ctrl_pressed = true;
            return 0;
        case 50: /*shift left*/
        case 62: /*shift right*/
            app.is_shift_pressed = true;
            start_selection();
            return 0;
        case 53: /*ctrl + x*/
            if (app.is_ctrl_pressed) {
                write_selected_text_to_clipboard();
                delete_selected_text();
            } else {
                /*normal text input*/
                if (XLookupString(&evt.xkey, buf, buf_size, nullptr, nullptr) > 0) {
                    delete_selected_text();
                    insert_char(buf[0]);
                } else {
                    return 0;
                }
            }
            break;
        case 54: /*ctrl + c*/
            if (app.is_ctrl_pressed) {
                write_selected_text_to_clipboard();
                return 0;
            } else {
                /*normal text input*/
                if (XLookupString(&evt.xkey, buf, buf_size, nullptr, nullptr) > 0) {
                    delete_selected_text();
                    insert_char(buf[0]);
                } else {
                    return 0;
                }
            }
            break;
        case 55: /*ctrl + v*/
            if (app.is_ctrl_pressed) {
                delete_selected_text();
                const auto text(::barn::x11::cp::get_text_from_clipboard());
                insert_text(text.c_str());
            } else {
                /*normal text input*/
                if (XLookupString(&evt.xkey, buf, buf_size, nullptr, nullptr) > 0) {
                    delete_selected_text();
                    insert_char(buf[0]);
                } else {
                    return 0;
                }
            }
            break;
        case 22: /*backspace*/
            if (!delete_selected_text()) {
                delete_chars(-1);
            }
            break;
        case 119: /*delete*/
            if (!delete_selected_text()) {
                delete_chars(+1);
            }
            break;
        case 36: /*enter*/
            delete_selected_text();
            insert_newline();
            break;
        case 111: /*up arrow key*/
            move_cursor_vertically(-1);
            break;
        case 116: /*down arrow key*/
            move_cursor_vertically(+1);
            break;
        case 113: /*left arrow key*/
            app.is_ctrl_pressed ? move_cursor_by_word(-1) : move_cursor(-1);
            break;
        case 114: /*right arrow key*/
            app.is_ctrl_pressed ? move_cursor_by_word(+1) : move_cursor(+1);
            break;
        case 110: /*home key*/
            this->cursor.x = 0;
            break;
        case 115: /*end key*/
            this->cursor.x = this->lines[this->cursor.y].len;
            break;
        default:
            if (XLookupString(&evt.xkey, buf, buf_size, nullptr, nullptr) > 0) {
                /*normal text input*/
                delete_selected_text();
                insert_char(buf[0]);
            } else {
                return 0;
            }
    }
    app.redraw();
    return 0;
}

int TextBox::handle_key_release(XEvent& evt) {
    if (!this->has_focus) {
        return 0;
    }
    const unsigned int key_code = evt.xkey.keycode;
    if (key_code == 37 /*ctrl left*/  || key_code == 105 /*ctrl right*/) {
        app.is_ctrl_pressed = false;
    } else if (key_code == 50 /*shift left*/ || key_code == 62 /*shift right*/) {
        app.is_shift_pressed = false;
    }
    return 0;
}

int App::run() {
    text_box.has_focus = true;

    XEvent evt;
    while (!XNextEvent(this->display, &evt)) {
        switch (evt.type) {
        case Expose:
            if (evt.xexpose.count == 0) {
                redraw();
            }
            break;
        case KeyPress:
            if (text_box.handle_key_press(evt)) {
                return 0;
            }
            break;
        case KeyRelease:
            if (text_box.handle_key_release(evt)) {
                return 0;
            }
            break;
        case VisibilityNotify:
            if (evt.xvisibility.state != VisibilityUnobscured) {
                XRaiseWindow(this->display, this->win);
            }
            break;
        }
    }
}

} // namespace x11
} // namespace barn
