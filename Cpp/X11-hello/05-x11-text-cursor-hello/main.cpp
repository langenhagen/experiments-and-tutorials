/* Render an X11 window for multiple lines of text input
   and have a text cursor that you can control via arrow keys.

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

#include <X11/Xlib.h>
#include <X11/Xutil.h>
#include <X11/Xos.h>
#include <X11/Xft/Xft.h>

struct Line {
    static const unsigned int buffer_size = 255;
    unsigned int length;
    char buffer[buffer_size];
};

struct App {
     /*x11 essentials*/
    Display *display;
    int screen;
    Window root_window;
    Window window;
    GC gc;

    /*Xft stuff*/
    XftDraw *xft_drawable;
    XftFont* font;

    /*application stuff*/
    unsigned int height = 300;
    unsigned int width = 500;
    unsigned int line_height;
    std::vector<Line> lines;
    unsigned int text_cursor_row = 0;
    unsigned int text_cursor_col = 0;
    unsigned long window_background_color = 0x000000;
};

static void grab_keyboard(App *app) {
    using namespace std::chrono_literals;
    /* try to grab keyboard 1000 times.
       we may have to wait for another process to ungrab*/
    for(int i = 0; i < 1000; ++i) {
        int grab_result = XGrabKeyboard(
            app->display /*display*/,
            app->root_window /*grab-window*/,
            True /*owner events*/,
            GrabModeAsync /*pointer mode*/,
            GrabModeAsync /*keyboard mode*/,
            CurrentTime /*time*/);

        if(grab_result == GrabSuccess) {
            return;
        }
        std::this_thread::sleep_for(1ms);
    }
    std::cerr << "Could not grab keyboard" << std::endl;
}

static App* setup_x() {
    App *app = new App();
    app->display = XOpenDisplay(nullptr);
    app->screen = DefaultScreen(app->display);
    app->root_window = RootWindow(app->display, app->screen);

    grab_keyboard(app);

    XSetWindowAttributes set_window_attributes;
    set_window_attributes.override_redirect = True; /*if True, window manager doesn't mess with the window*/
    set_window_attributes.background_pixel = app->window_background_color;  /*rgb values*/
    set_window_attributes.event_mask =
        ExposureMask
        | KeyPressMask
        | KeyReleaseMask
        | VisibilityChangeMask;

    app->window = XCreateWindow(
        app->display /*display*/,
        app->root_window /*parent*/,
        100 /*pos x*/,
        100 /*pos y*/,
        app->width /*width; minimal 1px*/,
        app->height /*height; minimal 1px*/,
        0 /*border width*/,
        CopyFromParent /*depth*/,
        CopyFromParent /*class*/,
        CopyFromParent /*visual*/,
        CWOverrideRedirect | CWBackPixel | CWEventMask /*valuemask; bitmask*/,
        &set_window_attributes /*attributes; values matching the valuemask*/);

    XSetStandardProperties(
        app->display,
        app->window,
        "My nice Window",
        "HI!",
        None,
        nullptr,
        0,
        nullptr);

    app->gc = XCreateGC(app->display, app->window, 0, 0);

    const unsigned long white = WhitePixel(app->display, app->screen);
    XSetForeground(app->display, app->gc, white);
    XClearWindow(app->display, app->window);
    XMapRaised(app->display, app->window);

    return app;
};

static void close_x(App *app) {
    XFreeGC(app->display, app->gc);
    XDestroyWindow(app->display, app->window);
    XCloseDisplay(app->display);
}

static void setup_xft_font(App *app) {
    app->xft_drawable = XftDrawCreate(
        app->display,
        app->window /*drawable*/,
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

static void set_window_height(App *app) {
    app->height = app->line_height * 10;

    XResizeWindow(
        app->display /*display*/,
        app->window /*window*/,
        app->width /*width*/,
        app->height /*height*/);
}

static void draw_text(App *app) {
    XRenderColor x_render_color;
    x_render_color.red = 65535;
    x_render_color.green = 65535;
    x_render_color.blue = 65535;
    x_render_color.alpha = 65535;
    XftColor xft_color;

    XftColorAllocValue(
        app->display,
        DefaultVisual(app->display, 0) /*visual*/,
        DefaultColormap(app->display, 0) /*colormap*/,
        &x_render_color /*color*/,
        &xft_color /*result*/);

    for(size_t i = 0; i < app->lines.size(); ++i) {
        auto& line = app->lines[i];
        XftDrawString8(
            app->xft_drawable /*drawable*/,
            &xft_color /*color*/,
            app->font /*font*/,
            0 /*pos x*/,
            app->line_height * i + app->font->ascent /*pos y*/,
            (unsigned char*)line.buffer,
            line.length);
    }
    XftColorFree(
        app->display,
        DefaultVisual(app->display, 0),
        DefaultColormap(app->display, 0),
        &xft_color);
}

static void draw_cursor(App *app) {
    auto &line = app->lines[app->text_cursor_row];
    XGlyphInfo glyph_info_all;
    XftTextExtents8(
        app->display/*Display*/,
        app->font /*xftfont*/,
        (XftChar8 *)line.buffer /*string*/,
        line.length /*int len*/,
        &glyph_info_all /*out glyph info*/);
    XGlyphInfo glyph_info_remaining;
    XftTextExtents8(
        app->display/*Display*/,
        app->font /*xftfont*/,
        (XftChar8 *)&line.buffer[app->text_cursor_col] /*string*/,
        line.length - app->text_cursor_col /*int len*/,
        &glyph_info_remaining /*out glyph info*/);

    const int x = glyph_info_all.width - glyph_info_remaining.width;
    const int y = app->line_height * app->text_cursor_row;
    const unsigned long white = WhitePixel(app->display, app->screen);
    XSetForeground(
        app->display /*display*/,
        app->gc,
        white /*color*/);

    XFillRectangle(
        app->display,
        app->window /*drawable*/,
        app->gc,
        x,
        y,
        3 /*width*/,
        app->line_height /*height*/);
}

static void redraw(App *app) {
    XClearWindow(app->display, app->window);
    draw_text(app);
    draw_cursor(app);
}

static int run(App *app) {
    const int input_buffer_size = 32;
    char input_buffer[input_buffer_size];

    XEvent event;
    while(!XNextEvent(app->display, &event)) {
        unsigned int key_code = 0;
        switch(event.type) {
        case Expose:
            if(event.xexpose.count == 0) {
                redraw(app);
            }
            break;
        case KeyPress:
            key_code = event.xkey.keycode;
            if(key_code == 9 /*esc*/) {
                return 0;
            } else if(key_code == 22 /*delete*/) {
                auto &line = app->lines.back();
                if(line.length == 0 && app->lines.size() > 1) {
                    app->lines.pop_back();
                    --app->text_cursor_row;
                    app->text_cursor_col = app->lines.back().length - 1;
                    redraw(app);
                } else if(line.length > 0) {
                    --line.length;
                    --app->text_cursor_col;
                    redraw(app);
                }
            } else if(key_code == 36 /*enter*/) {
                app->lines.push_back(Line());
                ++app->text_cursor_row;
                app->text_cursor_col = 0;
                redraw(app);
            } else if(key_code == 111 /*down arrow key*/) {
                if(app->text_cursor_row > 0) {
                    --app->text_cursor_row;
                    app->text_cursor_col = std::min(
                        app->text_cursor_col,
                        app->lines[app->text_cursor_row].length);
                    redraw(app);
                }
            } else if(key_code == 116 /*up arrow key*/) {
                if(app->text_cursor_row < app->lines.size() - 1) {
                    ++app->text_cursor_row;
                    app->text_cursor_col = std::min(
                        app->text_cursor_col,
                        app->lines[app->text_cursor_row].length);
                    redraw(app);
                }
            } else if(key_code == 113 /*left arrow key*/) {
                if(app->text_cursor_col > 0) {
                    --app->text_cursor_col;
                    redraw(app);
                } else if(app->text_cursor_row > 0) {
                    --app->text_cursor_row;
                    app->text_cursor_col = app->lines[app->text_cursor_row].length;
                    redraw(app);
                }
            } else if(key_code == 114 /*right arrow key*/) {
                if(app->text_cursor_col < app->lines[app->text_cursor_row].length) {
                    ++app->text_cursor_col;
                    redraw(app);
                } else if(app->text_cursor_row < app->lines.size() - 1 ) {
                    std::cout << "!" << std::endl;
                    ++app->text_cursor_row;
                    app->text_cursor_col = 0;
                    redraw(app);
                }
            } else if(XLookupString(
                    &event.xkey,
                    input_buffer,
                    input_buffer_size,
                    nullptr,
                    nullptr) > 0) {
                /* normal text input*/
                Line &line = app->lines.back();
                line.buffer[line.length] = input_buffer[0];
                ++line.length;
                ++app->text_cursor_col;
                redraw(app);
            }
            break;
        case VisibilityNotify:
            if (event.xvisibility.state != VisibilityUnobscured) {
                XRaiseWindow(app->display, app->window);
            }
            break;
        }
    }
}

int main(int argc, const char* argv[]) {
    App *app = setup_x();
    setup_xft_font(app);
    app->lines.push_back(Line());
    set_window_height(app);
    run(app);
    close_x(app);
    return 0;
}
