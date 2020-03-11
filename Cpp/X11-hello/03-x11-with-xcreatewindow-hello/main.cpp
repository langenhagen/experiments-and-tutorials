/* Render an X11 window with some a look and feel that matches the behavior of dmenu by using X11
 XCreateWindow instead of XCreateSimpleWindow.

- based on my prior learnings and dmenu source code
- see: https://tronche.com/gui/x/xlib/window/XCreateWindow.html
- see: https://tronche.com/gui/x/xlib/window/attributes/
- see: https://tronche.com/gui/x/xlib/window/attributes/override-redirect.html
- see: https://tronche.com/gui/x/xlib/event-handling/XSelectInput.html
- see maybe: https://www.x.org/releases/current/doc/man/man3/XOpenIM.3.xhtml

author: andreasl
*/
#include <algorithm>
#include <cstring>
#include <iostream>
#include <thread>

/* order of X11 includes allegedlly important*/
#include <X11/Xlib.h>
#include <X11/Xutil.h>
#include <X11/Xos.h>
#include <X11/Xft/Xft.h>

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
    int n_redraws = 0;
    static const int text_buffer_size = 255;
    char text_buffer[text_buffer_size];
    int text_cursor_pos = 0;
};

static void grab_keyboard(App *app) {
    using namespace std::chrono_literals;
    /* try to grab keyboard. 1000 times.
       we may have to wait for another process to ungrab*/
    for (int i = 0; i < 1000; ++i) {
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

    unsigned long black = BlackPixel(app->display, app->screen);
    unsigned long white = WhitePixel(app->display, app->screen);


    XSetWindowAttributes set_window_attributes;
    set_window_attributes.override_redirect = True; /*if True, window manager doesn't mess with the window*/
    set_window_attributes.background_pixel = 0x0000FF;  /*rgb values*/
    set_window_attributes.event_mask = ExposureMask | KeyPressMask | VisibilityChangeMask;

    app->window = XCreateWindow(
        app->display /*display*/,
        app->root_window /*parent*/,
        100 /*pos x*/,
        100 /*pos y*/,
        400 /*width*/,
        200 /*height*/,
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
        nullptr );
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

    XftDrawString8(
        app->xft_drawable /*drawable*/,
        &xft_color /*color*/,
        app->font /*font*/,
        10 /*pos x*/,
        20 /*pos y*/,
        (unsigned char*)app->text_buffer,
        app->text_cursor_pos);

    XftColorFree(
        app->display,
        DefaultVisual(app->display, 0),
        DefaultColormap(app->display, 0),
        &xft_color);
}

static void redraw(App *app) {
    XClearWindow(app->display, app->window);
    draw_text(app);
}

static int run(App *app) {
    const int input_buffer_size = 32;
    char input_buffer[input_buffer_size];

    XEvent event;
    while(!XNextEvent(app->display, &event)) {
        // if (XFilterEvent(&event, app->window))
        //     continue;
        switch(event.type) {
        case Expose:
            if(event.xexpose.count == 0) {
                redraw(app);
            }
            break;
        case KeyPress:
            if(event.xkey.keycode == 9 /*esc*/) {
                return 0;
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
            break;
        case VisibilityNotify:
            if (event.xvisibility.state != VisibilityUnobscured)
                XRaiseWindow(app->display, app->window);
            break;
        }
    }
}

int main(int argc, const char* argv[]) {
    App *app = setup_x();
    setup_xft_font(app);
    run(app);
    close_x(app);
    return 0;
}
