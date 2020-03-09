/* Render an X11 window with some a look and feel that matches the behavior of dmenu by using X11
 XCreateWindow instead of XCreateSimpleWindow.

- based on my prior learnings
- see: https://tronche.com/gui/x/xlib/window/XCreateWindow.html
- see: https://tronche.com/gui/x/xlib/window/attributes/
- see: https://tronche.com/gui/x/xlib/window/attributes/override-redirect.html
- see: https://www.x.org/releases/current/doc/man/man3/XOpenIM.3.xhtml

author: andreasl
*/
#include <algorithm>
#include <cstring>
#include <iostream>

/* order of X11 includes allegedlly important*/
#include <X11/Xlib.h>
#include <X11/Xutil.h>
#include <X11/Xos.h>
#include <X11/Xft/Xft.h>

struct App {
     /*x11 essentials*/
    Display *display;
    int screen;
    Window window;
    GC gc;

    XSetWindowAttributes set_window_attributes;

     /*Xft stuff*/
    XftDraw *xft_drawable;
    XftFont* font;

    /*application stuff*/
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

    app->set_window_attributes.override_redirect = True; /*disables input method*/
    app->set_window_attributes.background_pixel = black;
    app->set_window_attributes.event_mask = ExposureMask | KeyPressMask | VisibilityChangeMask;

    app->window = XCreateWindow(
        app->display /*display*/,
        DefaultRootWindow(app->display) /*parent*/,
        100 /*pos x*/,
        100 /*pos y*/,
        400 /*width*/,
        200 /*height*/,
        0 /*border width*/,
        CopyFromParent /*depth*/,
        CopyFromParent /*class*/,
        CopyFromParent /*visual*/,
        CWOverrideRedirect | CWBackPixel | CWEventMask /*valuemask; bitmask*/,
        &app->set_window_attributes /*attributes; values matching the valuemask*/);

    /* input methods */
    XIM input_method = XOpenIM(
        app->display /*display*/,
        nullptr /*db*/,
        nullptr /*res_name*/,
        nullptr /*res_class*/);
    if(input_method == nullptr) {
        std::cerr << "XOpenIM failed: could not open input device" << std::endl;
    }

    XIC input_context = XCreateIC(
        input_method /*input method*/,
        XNInputStyle,
        XIMPreeditNothing | XIMStatusNothing,
        XNClientWindow,
        app->window,
        XNFocusWindow,
        app->window,
        nullptr);

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
    // std::cout << "redrawing " << ++n_redraws << std::endl;
    XClearWindow(app->display, app->window);
    draw_text(app);
}

static int listen_for_events(App *app) {
    XSelectInput(
        app->display,
        app->window,
        ExposureMask
        | KeyPressMask
        | VisibilityChangeMask);

    const int input_buffer_size = 32;
    char input_buffer[input_buffer_size];

    XEvent event;
    while(!XNextEvent(app->display, &event)) {
        std::cout << "!";
        switch(event.type) {
        case Expose:
            if(event.xexpose.count == 0) {
                redraw(app);
                break;
            }
        case KeyPress:
            std::cout << "!";
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
    App *app = init_x();
    setup_xft_font(app);
    listen_for_events(app);
    close_x(app);
    return 0;
}
