/* Render an X11 window that waits for keyboard input and has nice Xft fonts
Close the program with ESC.

author: andreasl
*/
#include <algorithm>
#include <cstring>
#include <iostream>

/* order of X11 includes allegedlly important*/
#include <X11/Xlib.h>
#include <X11/Xutil.h>
#include <X11/Xos.h>
#include <X11/Xatom.h>
#include <X11/Xft/Xft.h>

struct App {
    Display *display;
    int screen;
    Window window;
    GC gc;
    XftFont *xfont; /*Xft approach*/
    int n_redraws = 0;

    static const int text_buffer_size = 255;
    char text_buffer[text_buffer_size];
    int text_cursor_pos = 0;
};

static App* init_x() {
    App *app = new App();
    app->display = XOpenDisplay(nullptr);
    app->screen = DefaultScreen(app->display);

    unsigned long black = BlackPixel(app->display, app->screen);
    unsigned long white = WhitePixel(app->display, app->screen);

    app->window = XCreateSimpleWindow(
        app->display,
        DefaultRootWindow(app->display),
        100 /*pos x*/,
        100 /*pos y*/,
        400 /*width*/,
        200 /*height*/,
        0 /*border width*/,
        white /*border*/,
        black /*background*/);

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

static void setup_font() {

    // what dmenu does; I have issues with loading transitive dependencies
    // const char *font_name = "monospace:size=10";
    // xfont = XftFontOpenName(display, screen, font_name);
    // if(!xfont) {
    //     std::cerr << "unable to load font " << font_name << " using default font \"fixed\"\n";
    // }
}

static void redraw(App *app) {
    // std::cout << "redrawing " << ++n_redraws << std::endl;
    XClearWindow(app->display, app->window);
    XDrawString(
        app->display,
        app->window,
        app->gc,
        10 /*pos x*/,
        20 /*pos y*/,
        app->text_buffer,
        app->text_cursor_pos);
}

static int listen_for_events(App *app) {

    XSelectInput(
        app->display,
        app->window,
        ExposureMask
        | KeyPressMask);

    XEvent event;
    const int input_buffer_size = 32;
    char input_buffer[input_buffer_size];

    while(true) {
        XNextEvent(app->display, &event);
        if (event.type == Expose && event.xexpose.count == 0) {
            redraw(app);
        }
        else if(event.type == KeyPress) {
            if(event.xkey.keycode == 9 /*esc*/) {
                break;
            }
            else if(event.xkey.keycode == 22 /*delete*/) {
                app->text_cursor_pos = std::max(--app->text_cursor_pos, 0);
                redraw(app);
            }
            else if(XLookupString(
                &event.xkey,
                input_buffer,
                input_buffer_size,
                nullptr,
                nullptr) > 0) {
                /* normal text input*/
                app->text_buffer[app->text_cursor_pos] = input_buffer[0];
                ++app->text_cursor_pos;
                redraw(app);
            }
        }
    }

}

int main(int argc, const char* argv[]) {
    App *app = init_x();
    listen_for_events(app);
    close_x(app);
    return 0;
}
