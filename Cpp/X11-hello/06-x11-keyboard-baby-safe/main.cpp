/* Block grab the keyboard and hold its focus until ctrl l + ctrl r + esc + backspace are presset at
the same time.

author: andreasl
*/
#include <algorithm>
#include <cstring>
#include <iostream>
#include <thread>

/* order of X11 includes allegedly important*/
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

    bool ctrl_l_pressed = false;
    bool ctrl_r_pressed = false;
    bool esc_pressed = false;
    bool backspace_pressed = false;
};

static void grab_keyboard(App *app) {
    using namespace std::chrono_literals;
    /* try to grab keyboard 1000 times.
    we may have to wait for another process to ungrab*/
    for (int i = 0; i < 10000; ++i) {
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

static void grab_pointer(App* app) {
    using namespace std::chrono_literals;
    for (int i = 0; i < 10000; ++i) {
        int grab_result = XGrabPointer(
            app->display /*display*/,
            app->root_window /*grab-window*/,
            True /*owner events*/,
            0 /*event mask*/,
            GrabModeAsync /*pointer mode*/,
            GrabModeAsync /*keyboard mode*/,
            None /*confine to*/,
            None /*cursor*/,
            CurrentTime /*time*/);

        if(grab_result == GrabSuccess) {
            return;
        }
        std::this_thread::sleep_for(1ms);
    }
    std::cerr << "Could not grab pointer" << std::endl;
}

static App* setup_x() {
    App *app = new App();
    app->display = XOpenDisplay(nullptr);
    app->screen = DefaultScreen(app->display);
    app->root_window = RootWindow(app->display, app->screen);

    grab_keyboard(app);
    grab_pointer(app);

    unsigned long black = BlackPixel(app->display, app->screen);
    unsigned long white = WhitePixel(app->display, app->screen);

    XSetWindowAttributes set_window_attributes;
    set_window_attributes.override_redirect = True; /*if True, window manager doesn't mess with the window*/
    set_window_attributes.background_pixel = 0xFF0000;  /*rgb values*/
    set_window_attributes.event_mask = ExposureMask | KeyPressMask | VisibilityChangeMask;

    app->window = XCreateWindow(
        app->display /*display*/,
        app->root_window /*parent*/,
        0 /*pos x*/,
        0 /*pos y*/,
        7 /*width*/,
        7 /*height*/,
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

static void draw(App *app) {
    XClearWindow(app->display, app->window);
}

static int run(App *app) {
    const int input_buffer_size = 32;
    char input_buffer[input_buffer_size];

    XEvent event;
    while(!XNextEvent(app->display, &event)) {
        switch(event.type) {
        case Expose:
            if(event.xexpose.count == 0) {
                draw(app);
            }
            break;
        case KeyPress:
            if(event.xkey.keycode == 9 /*esc*/) {
                app->esc_pressed = true;
            } else if(event.xkey.keycode == 37 /*ctrl left*/) {
                app->ctrl_l_pressed = true;
            } else if(event.xkey.keycode == 105 /*ctrl right*/) {
                app->ctrl_r_pressed = true;
            } else if(event.xkey.keycode == 22 /*backspace*/) {
                app->backspace_pressed = true;
            }
            break;
        case KeyRelease:
            app->ctrl_l_pressed = false;
            app->ctrl_r_pressed = false;
            app->esc_pressed = false;
            app->backspace_pressed = false;
            break;
        case VisibilityNotify:
            if (event.xvisibility.state != VisibilityUnobscured)
                XRaiseWindow(app->display, app->window);
            break;
        }
        if(app->esc_pressed && app->ctrl_l_pressed && app->ctrl_r_pressed && app->backspace_pressed) {
            return 0;
        }
    }
}

int main(int argc, const char* argv[]) {
    App *app = setup_x();
    run(app);
    close_x(app);
    return 0;
}
