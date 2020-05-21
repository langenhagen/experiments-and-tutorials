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

Line::Line(const size_t buf_size) : buf_size(buf_size), buf(new char[buf_size])
{}

Line::Line(const Line& other) : buf_size(other.buf_size), buf(new char[buf_size]), len(other.len) {
    std::memcpy(this->buf, other.buf, this->buf_size);
}

Line::Line(Line&& other)
:
buf_size(std::move(other.buf_size)),
buf(new char[buf_size]),
len(std::move(other.len))
{
    std::memcpy(this->buf, other.buf, this->buf_size);
}

Line& Line::operator=(Line&& other) {
    this->buf_size = std::move(other.buf_size);
    delete[] this->buf;
    this->buf = new char[this->buf_size];
    std::memcpy(this->buf, other.buf, this->buf_size);
    this->len = std::move(other.len);
    return *this;
}

Line::~Line() {
    delete[] this->buf;
}

bool operator==(const TextCoord& lhs, const TextCoord& rhs) {
    return lhs.y == rhs.y && lhs.x == rhs.x;
}

App::App()
:
dpy(XOpenDisplay(nullptr)),
screen(DefaultScreen(dpy)),
_root_win(RootWindow(dpy, screen)),
text_box(*this, 10, 20, 200, 40, 3)
{
    XSetWindowAttributes attrs;
    attrs.override_redirect = True; /*if True, window manager doesn't mess with the window*/
    attrs.background_pixel = 0x282828; /*rgb values*/
    attrs.event_mask =
        ExposureMask
        | KeyPressMask
        | KeyReleaseMask
        | VisibilityChangeMask;

    const Screen* const screen DefaultScreenOfDisplay(this->dpy);
    this->win = XCreateWindow(
        this->dpy /*display*/,
        _root_win /*parent*/,
        (screen->width - _width) / 2 /*pos x*/,
        (screen->height - _height) / 3 /*pos y*/,
        _width /*width; minimal 1px*/,
        _height /*height; minimal 1px*/,
        0 /*border width*/,
        CopyFromParent /*depth*/,
        CopyFromParent /*class*/,
        CopyFromParent /*visual*/,
        CWOverrideRedirect | CWBackPixel | CWEventMask /*valuemask; bitmask*/,
        &attrs /*attributes; values matching the valuemask*/);

    this->gc = XCreateGC(this->dpy, this->win, 0, 0);

    XSetStandardProperties(
        this->dpy,
        this->win,
        "My nice Window",
        "HI!",
        None,
        nullptr,
        0,
        nullptr);

    _setup_xft_font();

    XClearWindow(this->dpy, this->win);
    XMapRaised(this->dpy, this->win);

    _grab_keyboard();
}

App::~App() {
    XFreeGC(this->dpy, this->gc);
    XDestroyWindow(this->dpy, this->win);
    XCloseDisplay(this->dpy);
}

int App::_grab_keyboard() {
    /*try to grab keyboard 1000 times.
    We may have to wait for another process to ungrab.*/
    for (int i = 0; i < 1000; ++i) {
        int grab_result = XGrabKeyboard(
            this->dpy /*display*/,
            _root_win /*grab-win*/,
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

void App::_setup_xft_font() {
    this->xft_drawable = XftDrawCreate(
        this->dpy,
        this->win /*drawable*/,
        DefaultVisual(this->dpy, 0),
        DefaultColormap(this->dpy, 0));

    this->font = XftFontOpen(
        this->dpy,
        this->screen,
        XFT_FAMILY,
        XftTypeString,
        "monospace",
        XFT_SIZE,
        XftTypeDouble,
        11.0,
        nullptr);

    this->line_height = this->font->ascent + this->font->descent;
}


TextBox::TextBox(
    App& app,
    const size_t y,
    const size_t x,
    const size_t width,
    const size_t height,
    const size_t max_n_lines)
:
y(y),
x(x),
width(width),
height(height),
max_n_lines(max_n_lines),
_app(app)
{}

void TextBox::start_selection() {
    if (_sel_start.y == -1) {
        _sel_start.y = _cur.y;
        _sel_start.x = _cur.x;
    }
}

void TextBox::_invalidate_selection() {
    if (_sel_start.y != -1) {
        _sel_start.y = -1;
        _sel_start.x = -1;
    }
}

std::pair<const TextCoord&, const TextCoord&> TextBox::_get_selection_bounds() const {
    const TextCoord& cur = _cur;
    const TextCoord& sel_start = _sel_start;
    if (cur.y < sel_start.y || (cur.y == sel_start.y && cur.x < sel_start.x)) {
        return std::pair<const TextCoord&, const TextCoord&>(cur, sel_start);
    }
    return std::pair<const TextCoord&, const TextCoord&>(sel_start, cur);
}

std::string TextBox::_get_selected_text() const {
    if (_sel_start.y == -1) {
        return "";
    }
    const auto sel = _get_selection_bounds();

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

void TextBox::_write_selected_text_to_clipboard() const {
    ::barn::x11::cp::write_to_clipboard(_get_selected_text());
}

void TextBox::_draw_background() {
    XSetForeground(_app.dpy, _app.gc, 0x222222);
    XFillRectangle(
        _app.dpy,
        _app.win,
        _app.gc,
        this->x,
        this->y,
        this->width - 1,
        this->height -1);

    XSetForeground(_app.dpy, _app.gc, 0xaaaaaa);
    XDrawRectangle(
        _app.dpy,
        _app.win,
        _app.gc,
        this->x,
        this->y,
        this->width - 1,
        this->height -1 );
}

void TextBox::_draw_text() {
    XRenderColor x_color = {65535 /*red*/, 65535 /*green*/, 65535 /*blue*/, 65535 /*alpha*/};

    XftColor xft_color;
    XftColorAllocValue(
        _app.dpy,
        DefaultVisual(_app.dpy, _app.screen) /*visual*/,
        DefaultColormap(_app.dpy, _app.screen) /*colormap*/,
        &x_color /*color*/,
        &xft_color /*result*/);

    for (size_t i = 0; i < this->lines.size(); ++i) {
        auto& line = this->lines[i];

        XftDrawString8(
            _app.xft_drawable /*drawable*/,
            &xft_color /*color*/,
            _app.font /*font*/,
            this->x + 1,
            _app.line_height * i + _app.font->ascent + this->y + 1,
            (unsigned char*)line.buf,
            line.len);
    }
    XftColorFree(
        _app.dpy,
        DefaultVisual(_app.dpy, 0),
        DefaultColormap(_app.dpy, 0),
        &xft_color);
}

void TextBox::_draw_cursor() {
    const auto& line = this->lines[_cur.y];
    XGlyphInfo glyph_info_all;
    XftTextExtents8(
        _app.dpy /*Display*/,
        _app.font /*xftfont*/,
        reinterpret_cast<const XftChar8*>(line.buf) /*string*/,
        line.len /*int len*/,
        &glyph_info_all /*out glyph info*/);
    XGlyphInfo glyph_info_remaining;
    XftTextExtents8(
        _app.dpy /*Display*/,
        _app.font /*xftfont*/,
        reinterpret_cast<const XftChar8*>(&line.buf[_cur.x]) /*string*/,
        line.len - _cur.x /*int len*/,
        &glyph_info_remaining /*out glyph info*/);

    const int x = glyph_info_all.width - glyph_info_remaining.width;
    const int y = _app.line_height * _cur.y;

    XSetForeground(
        _app.dpy /*display*/,
        _app.gc,
        0xffffff /*color*/);
    XFillRectangle(
        _app.dpy,
        _app.win /*drawable*/,
        _app.gc,
        x + this->x + 1,
        y + this->y + 1,
        3 /*width*/,
        _app.line_height /*height*/);
}

void TextBox::_draw_selection() {
    if (_sel_start.y == -1 || _sel_start == _cur) {
        return;
    }

    const auto sel = _get_selection_bounds();
    for (auto i = sel.first.y; i <= sel.second.y; ++i) {
        const auto& line = this->lines[i];
        int x = 0;

        XGlyphInfo glyph_info_all;
        XftTextExtents8(
            _app.dpy,
            _app.font,
            reinterpret_cast<const XftChar8*>(line.buf),
            line.len,
            &glyph_info_all);

        int width = glyph_info_all.width;
        if (i == sel.first.y) {
            XGlyphInfo glyph_info_selection;
            XftTextExtents8(
                _app.dpy,
                _app.font,
                reinterpret_cast<const XftChar8*>(&line.buf[sel.first.x]),
                line.len - sel.first.x,
                &glyph_info_selection);

            x = glyph_info_all.width - glyph_info_selection.width;
            width = glyph_info_selection.width;
        }

        if (i == sel.second.y) {
            XGlyphInfo glyph_info_remaining;
            XftTextExtents8(
                _app.dpy,
                _app.font,
                reinterpret_cast<const XftChar8*>(&line.buf[sel.second.x]),
                line.len - sel.second.x,
                &glyph_info_remaining);
             width -= glyph_info_remaining.width;
        }

        XSetForeground(_app.dpy, _app.gc, 0x444477 /*color*/);
        XFillRectangle(
            _app.dpy,
            _app.win,
            _app.gc,
            x + this->x + 1,
            _app.line_height * i + this->y + 1,
            width,
            _app.line_height);
    }
}

void TextBox::move_cursor(int inc) {
    /*Move the cursor by increment to the left/right and consider line- and text- starts & ends.*/
    if (!_app.is_shift_pressed) {
        _invalidate_selection();
    }
    while (true) {
        auto& row = _cur.y;
        auto& col = _cur.x;
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
    if (!_app.is_shift_pressed) {
        _invalidate_selection();
    }
    auto& row = _cur.y;
    auto& col = _cur.x;
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
    if (!_app.is_shift_pressed) {
        _invalidate_selection();
    }
    while (n_words != 0) {
        const auto& line = this->lines[_cur.y];
        const auto& buf = line.buf;
        const auto col = _cur.x;
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
    _invalidate_selection();

    const auto& x = _cur.x;
    auto& line = this->lines[_cur.y];
    std::memmove(line.buf + x + 1, line.buf + x, line.len - x);
    line.buf[x] = c;

    ++_cur.x;
    ++line.len;
}

bool TextBox::_insert_text(const char* str) {
    {
        /*fail if the number of lines to be inserted exceeds the limits*/
        int n_lines = 0;
        const char* tmp = str;
        while ((tmp = std::strchr(tmp, '\n')) != nullptr) {
            ++n_lines;
            ++tmp;
        }
        if (this->lines.size() + n_lines > this->max_n_lines) {
            return false;
        }
    }

    auto& cur = _cur;
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
    line.len += line_end - line_start;
    cur.x = line_end + (line_start == 0 ? cur.x : -line_start);
    std::memcpy(line.buf + line.len, tmp.buf, tmp.len);
    line.len += tmp.len;
    return true;
}

void TextBox::delete_chars(int n_chars) {
    /*Delete the given number characters from the text at the cursor's position.*/
    while (true) {
        auto& row = _cur.y;
        auto& col = _cur.x;
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

void TextBox::_delete_text(const TextCoord& start, const TextCoord& end) {
    const auto remaining_len = lines[end.y].len - end.x;
    std::memmove(
        this->lines[start.y].buf + start.x,
        this->lines[end.y].buf + end.x,
        remaining_len);
    this->lines[start.y].len = start.x + remaining_len;
    for (auto i = start.y + 1; i <= end.y; ++i) {
        this->lines.erase(this->lines.begin() + end.y);
    }
}

bool TextBox::delete_selected_text() {
    if (_sel_start.y == -1 ||  _sel_start == _cur) {
        _invalidate_selection();
        return false;
    }
    const auto sel = _get_selection_bounds();
    _delete_text(sel.first, sel.second);
    _cur.y = sel.first.y;
    _cur.x = sel.first.x;
    _invalidate_selection();
    return true;
}

bool TextBox::_insert_newline() {
    if (this->lines.size() >= this->max_n_lines) {
        return false;
    }
    auto& y = _cur.y;
    auto& x = _cur.x;
    auto& lines = this->lines;
    auto& new_line = *lines.emplace(lines.begin() + y + 1);
    auto& line = lines[y];
    char* pos = line.buf + x;

    new_line.len = line.len - x;
    line.len -= new_line.len;
    std::memcpy(new_line.buf, pos, new_line.len);
    ++y;
    x = 0;
    return true;
}

void TextBox::draw() {
    _draw_background();

    XRectangle rect{
        static_cast<short>(this->x + 1),
        static_cast<short>(this->y + 1),
        static_cast<unsigned short>(this->width - 2),
        static_cast<unsigned short>(this->height - 2)};
    XSetClipRectangles(_app.dpy, _app.gc, 0, 0, &rect, 1, Unsorted);
    XftDrawSetClipRectangles(_app.xft_drawable, 0, 0, &rect, 1);

    _draw_selection();
    _draw_text();
    _draw_cursor();

    XSetClipMask(_app.dpy, _app.gc, None);
    XftDrawSetClip( _app.xft_drawable, 0 );
}

void App::redraw() {
    XClearWindow(this->dpy, this->win);
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
            _app.is_ctrl_pressed = true;
            return 0;
        case 50: /*shift left*/
        case 62: /*shift right*/
            _app.is_shift_pressed = true;
            start_selection();
            return 0;
        case 53: /*ctrl + x*/
            if (_app.is_ctrl_pressed) {
                _write_selected_text_to_clipboard();
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
            if (_app.is_ctrl_pressed) {
                _write_selected_text_to_clipboard();
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
            if (_app.is_ctrl_pressed) {
                delete_selected_text();
                const auto text(::barn::x11::cp::get_text_from_clipboard());
                _insert_text(text.c_str());
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
            _insert_newline();
            break;
        case 111: /*up arrow key*/
            move_cursor_vertically(-1);
            break;
        case 116: /*down arrow key*/
            move_cursor_vertically(+1);
            break;
        case 113: /*left arrow key*/
            _app.is_ctrl_pressed ? move_cursor_by_word(-1) : move_cursor(-1);
            break;
        case 114: /*right arrow key*/
            _app.is_ctrl_pressed ? move_cursor_by_word(+1) : move_cursor(+1);
            break;
        case 110: /*home key*/
            _cur.x = 0;
            break;
        case 115: /*end key*/
            _cur.x = this->lines[_cur.y].len;
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
    _app.redraw();
    return 0;
}

int TextBox::handle_key_release(XEvent& evt) {
    if (!this->has_focus) {
        return 0;
    }
    const unsigned int key_code = evt.xkey.keycode;
    if (key_code == 37 /*ctrl left*/  || key_code == 105 /*ctrl right*/) {
        _app.is_ctrl_pressed = false;
    } else if (key_code == 50 /*shift left*/ || key_code == 62 /*shift right*/) {
        _app.is_shift_pressed = false;
    }
    return 0;
}

int App::run() {
    text_box.lines.emplace_back();
    text_box.has_focus = true;

    XEvent evt;
    while (!XNextEvent(this->dpy, &evt)) {
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
                XRaiseWindow(this->dpy, this->win);
            }
            break;
        }
    }
}

} // namespace x11
} // namespace barn
