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
text_box(*this, 10, 20, 240, 90, 7)
{
    XSetWindowAttributes attrs;
    attrs.override_redirect = True;
    attrs.background_pixel = 0x282828;
    attrs.event_mask =
        ExposureMask
        | KeyPressMask
        | KeyReleaseMask
        | VisibilityChangeMask;

    const Screen* const screen DefaultScreenOfDisplay(this->dpy);
    this->win = XCreateWindow(
        this->dpy,
        _root_win,
        (screen->width - _width) / 2,
        (screen->height - _height) / 3,
        _width,
        _height,
        0,
        CopyFromParent,
        CopyFromParent,
        CopyFromParent,
        CWOverrideRedirect | CWBackPixel | CWEventMask,
        &attrs);

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
            this->dpy,
            _root_win,
            True,
            GrabModeAsync,
            GrabModeAsync,
            CurrentTime);

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
        this->win,
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
    const int y,
    const int x,
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
{
    _lines.emplace_back();
}

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
    const TextCoord& sel_start = _sel_start;
    if (_cur.y < sel_start.y || (_cur.y == sel_start.y && _cur.x < sel_start.x)) {
        return std::pair<const TextCoord&, const TextCoord&>(_cur, sel_start);
    }
    return std::pair<const TextCoord&, const TextCoord&>(sel_start, _cur);
}

std::string TextBox::_get_selected_text() const {
    if (_sel_start.y == -1) {
        return "";
    }

    const auto sel = _get_selection_bounds();
    int str_len = sel.second.x - sel.first.x + 1;
    for (auto i = sel.first.y; i < sel.second.y; ++i) {
        str_len += _lines[i].len + 1;
    }
    char str[str_len];
    if (sel.first.y == sel.second.y) {
        std::memcpy(
            str,
            &_lines[sel.first.y].buf[sel.first.x],
            str_len - 1);
    } else {
        int off = 0;
        for (auto i = sel.first.y; i <= sel.second.y; ++i) {
            const auto& line = _lines[i];
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

std::string TextBox::get_text() {
    size_t len = 0;
    for (const auto& line : _lines) {
        len += line.len + 1;
    }
    --len;

    std::string str(len, ' ');
    size_t index = 0;
    for (const auto& line : _lines) {
        for (size_t i = 0; i < line.len; ++i) {
            str[index++] = line.buf[i];
        }
        str[index++] = '\n';
    }
    return str;
}

IntCoord TextBox::_calc_cursor_pos() const {
    const auto& line = _lines[_cur.y];
    XGlyphInfo glyph_info_all;
    XftTextExtents8(
        _app.dpy,
        _app.font,
        reinterpret_cast<const XftChar8*>(line.buf),
        line.len,
        &glyph_info_all);
    XGlyphInfo glyph_info_remaining;
    XftTextExtents8(
        _app.dpy,
        _app.font,
        reinterpret_cast<const XftChar8*>(&line.buf[_cur.x]),
        line.len - _cur.x,
        &glyph_info_remaining);

    return IntCoord{
        _app.line_height * _cur.y + _padding,
        glyph_info_all.width - glyph_info_remaining.width + _padding};
}

void TextBox::_adjust_offset(const IntCoord& cur_coords) {
    if (cur_coords.y - _off.y < _padding) {
        /*cursor above visible area*/
        const int increment = (_padding - cur_coords.y + _off.y) / _app.line_height;
        _off.y -= increment * _app.line_height;
    } else if (cur_coords.y + _app.line_height - _off.y > this->height - _padding) {
        /*cursor below visible area*/
        const int increment =
            (cur_coords.y + _app.line_height - _off.y - this->height + _padding) / _app.line_height;
        _off.y += (increment + 1) * _app.line_height;
    }
    if (cur_coords.x - _off.x < _padding) {
        /*cursor left of the visible area*/
        const int increment = _padding - cur_coords.x + _off.x;
        _off.x -= increment;
    } else if (cur_coords.x + _cur_width - _off.x > this->width - _padding) {
        /*cursor right of the visible area*/
        const int increment = cur_coords.x + _cur_width - _off.x - this->width + _padding;
        _off.x += increment;
    }
}

void TextBox::_draw_background() {
    XSetForeground(_app.dpy, _app.gc, _bc_widget);
    XFillRectangle(
        _app.dpy,
        _app.win,
        _app.gc,
        this->x,
        this->y,
        this->width,
        this->height);

    XSetForeground(_app.dpy, _app.gc, _fc_border);
    XDrawRectangle(
        _app.dpy,
        _app.win,
        _app.gc,
        this->x,
        this->y,
        this->width,
        this->height);
}

void TextBox::_draw_text() {
    XftColor xft_color;
    XftColorAllocValue(
        _app.dpy,
        DefaultVisual(_app.dpy, _app.screen),
        DefaultColormap(_app.dpy, _app.screen),
        &_fc_text,
        &xft_color);

    for (size_t i = 0; i < _lines.size(); ++i) {
        auto& line = _lines[i];
        XftDrawString8(
            _app.xft_drawable,
            &xft_color,
            _app.font,
            this->x + _padding - _off.x,
            _app.line_height * i + _app.font->ascent + this->y + _padding - _off.y,
            reinterpret_cast<unsigned char*>(line.buf)  ,
            line.len);
    }
    XftColorFree(
        _app.dpy,
        DefaultVisual(_app.dpy, 0),
        DefaultColormap(_app.dpy, 0),
        &xft_color);
}

void TextBox::_draw_cursor(const IntCoord& coords) {
    XSetForeground(_app.dpy, _app.gc, _fc_cursor);
    XFillRectangle(
        _app.dpy,
        _app.win,
        _app.gc,
        coords.x + this->x - _off.x,
        coords.y + this->y - _off.y,
        _cur_width,
        _app.line_height);
}

void TextBox::_draw_selection() {
    if (_sel_start.y == -1 || _sel_start == _cur) {
        return;
    }

    const auto sel = _get_selection_bounds();
    for (auto i = sel.first.y; i <= sel.second.y; ++i) {
        const auto& line = _lines[i];
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

        XSetForeground(_app.dpy, _app.gc, _bc_selection);
        XFillRectangle(
            _app.dpy,
            _app.win,
            _app.gc,
            x + this->x + _padding - _off.x,
            _app.line_height * i + this->y + _padding - _off.y,
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
        if (_cur.x + inc < 0) {
            /*left up*/
            if (_cur.y == 0) {
                /*to front*/
                _cur.y = 0;
                _cur.x = 0;
                return;
            } else {
                inc += _cur.x + 1;
                _cur.y -= 1;
                _cur.x = _lines[_cur.y].len;
            }
        } else if (_cur.x + inc > _lines[_cur.y].len) {
            /*right down*/
            if (_cur.y == _lines.size() - 1) {
                /*past last position*/
                _cur.y = _lines.size() - 1;
                _cur.x = _lines[_cur.y].len;
                return;
            } else {
                inc -= _lines[_cur.y].len - _cur.x + 1;
                _cur.y += 1;
                _cur.x = 0;
            }
        } else {
            /*in same line*/
            _cur.x += inc;
            return;
        }
    }
}

void TextBox::move_cursor_vertically(const int inc) {
    /*Move the cursor by inc up/down and consider line lengths and text beginning & end.*/
    if (!_app.is_shift_pressed) {
        _invalidate_selection();
    }

    if (_cur.y + inc < 0) {
        /*to front*/
        _cur.y = 0;
        _cur.x = 0;
        return;
    } else if (_cur.y + inc >= _lines.size()) {
        /*past last position*/
        _cur.y = _lines.size() - 1;
        _cur.x = _lines[_cur.y].len;
        return;
    } else {
        /*normal movement*/
        _cur.y += inc;
        if (_cur.x > _lines[_cur.y].len) {
            _cur.x = _lines[_cur.y].len;
        }
    }
}

void TextBox::move_cursor_by_word(int n_words) {
    /*Move the cursor by n_words and consider line-lengths and text- starts & end.*/
    if (!_app.is_shift_pressed) {
        _invalidate_selection();
    }
    while (n_words != 0) {
        const auto& line = _lines[_cur.y];
        const auto& buf = line.buf;
        if (n_words < 0) {
            /*go back*/
            int i = _cur.x - 1;
            while (i > 0 && !(buf[i - 1] == ' ' && buf[i] != ' ')) {
                --i;
            }
            move_cursor(i - _cur.x);
            ++n_words;
        } else {
            /*go forward*/
            int i = _cur.x + 1;
            while (i < line.len && !(buf[i - 1] != ' ' && buf[i] == ' ')) {
                ++i;
            }
            move_cursor(i - _cur.x);
            --n_words;
        }
    }
}

void TextBox::insert_char(const char c) {
    _invalidate_selection();

    auto& line = _lines[_cur.y];
    std::memmove(line.buf + _cur.x + 1, line.buf + _cur.x, line.len - _cur.x);
    line.buf[_cur.x] = c;

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
        if (_lines.size() + n_lines > this->max_n_lines) {
            return false;
        }
    }

    /*save initial line ending*/
    Line tmp;
    tmp.len = _lines[_cur.y].len - _cur.x;
    std::memcpy(tmp.buf, _lines[_cur.y].buf + _cur.x, tmp.len);
    _lines[_cur.y].len = _cur.x;

    /*copy lines*/
    int line_start = 0;
    int line_end = 0;
    while (str[line_end] != '\0') {
        if (str[line_end] == '\n') {
            std::memcpy(
                _lines[_cur.y].buf + _lines[_cur.y].len,
                str + line_start,
                line_end - line_start);
            _lines[_cur.y].len += line_end - line_start;
            ++_cur.y;
            _cur.x = 0;
            _lines.emplace(_lines.begin() + _cur.y);
            line_start = line_end + 1;
        }
        ++line_end;
    }

    /*last line*/
    auto& line = _lines[_cur.y];
    std::memcpy(line.buf + line.len, str + line_start, line_end - line_start);
    line.len += line_end - line_start;
    _cur.x = line_end + (line_start == 0 ? _cur.x : -line_start);
    std::memcpy(line.buf + line.len, tmp.buf, tmp.len);
    line.len += tmp.len;
    return true;
}

void TextBox::delete_chars(int n_chars) {
    /*Delete the given number characters from the text at the cursor's position.*/
    while (true) {
        auto& line = _lines[_cur.y];
        char* pos = line.buf + _cur.x;
        if (_cur.x + n_chars < 0) {
            /*left up*/
            if (_cur.y == 0) {
                n_chars = -_cur.x;
            } else {
                auto& line_above = _lines[_cur.y - 1];
                const auto new_col = line_above.len;
                std::memcpy(line_above.buf + line_above.len, pos, line.len - _cur.x);
                line_above.len += line.len - _cur.x;
                _lines.erase(_lines.begin() + _cur.y);
                n_chars += _cur.x + 1;
                --_cur.y;
                _cur.x = new_col;
            }
        } else if (_cur.x + n_chars > line.len) {
            /*right down*/
            if (_cur.y == _lines.size() - 1) {
                n_chars = line.len - _cur.x;
            } else {
                n_chars -= line.len - _cur.x + 1;
                auto& line_below = _lines[_cur.y + 1];
                std::memcpy(pos, line_below.buf, line_below.len);
                line.len = _cur.x + line_below.len;
                _lines.erase(_lines.begin() + _cur.y + 1);
            }
        } else {
            /*in same line*/
            if (n_chars < 0) {
                std::memmove(pos + n_chars, pos, line.len - _cur.x);
                _cur.x += n_chars;
                line.len += n_chars;
            } else {
                std::memmove(pos, pos + n_chars, line.len - _cur.x + n_chars);
                line.len -= n_chars;
            }
            return;
        }
    }
}

void TextBox::_delete_text(const TextCoord& start, const TextCoord& end) {
    const auto remaining_len = _lines[end.y].len - end.x;
    std::memmove(
        _lines[start.y].buf + start.x,
        _lines[end.y].buf + end.x,
        remaining_len);
    _lines[start.y].len = start.x + remaining_len;
    for (int i = start.y + 1; i <= end.y; ++i) {
        _lines.erase(_lines.begin() + end.y);
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
    if (_lines.size() >= this->max_n_lines) {
        return false;
    }
    auto& new_line = *_lines.emplace(_lines.begin() + _cur.y + 1);
    auto& line = _lines[_cur.y];
    char* pos = line.buf + _cur.x;

    new_line.len = line.len - _cur.x;
    line.len -= new_line.len;
    std::memcpy(new_line.buf, pos, new_line.len);
    ++_cur.y;
    _cur.x = 0;
    return true;
}

void TextBox::draw() {
    _draw_background();

    XRectangle rect{
        static_cast<short>(this->x + _padding),
        static_cast<short>(this->y + _padding),
        static_cast<unsigned short>(this->width - 2 * _padding),
        static_cast<unsigned short>(this->height - 2 * _padding)};
    XSetClipRectangles(_app.dpy, _app.gc, 0, 0, &rect, 1, Unsorted);
    XftDrawSetClipRectangles(_app.xft_drawable, 0, 0, &rect, 1);

    IntCoord cur_pos(_calc_cursor_pos());
    _adjust_offset(cur_pos);
    _draw_selection();
    _draw_cursor(cur_pos);
    _draw_text();

    XSetClipMask(_app.dpy, _app.gc, None);
    XftDrawSetClip( _app.xft_drawable, 0);
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
            std::cout << "---\n" << _app.text_box.get_text() << "\n---" << std::endl;
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
            _cur.x = _lines[_cur.y].len;
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
