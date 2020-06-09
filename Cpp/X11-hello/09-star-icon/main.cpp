/* Render a star-shaped icon in an X11 window.

- based on my prior learnings

- see: https://tronche.com/gui/x/xlib/graphics/filling-areas/XFillPolygon.html

author: andreasl
*/
#include <X11/Xlib.h>
#include <X11/Xutil.h>

#include <array>
#include <cmath>
#include <iostream>

Display* display;
int screen;
Window window;
GC gc;
XFontStruct *font; /* classic approach*/

bool full_first = false;

static void init_x() {
    display = XOpenDisplay(nullptr);
    screen = DefaultScreen(display);

    /* get the colors black and white*/
    const unsigned long black = BlackPixel(display, screen);
    const unsigned long white = WhitePixel(display, screen);

    /* once the display is initialized, create the window.*/
    window = XCreateSimpleWindow(
        display,
        DefaultRootWindow(display),
        100 /*pos x*/,
        100 /*pos y*/,
        550 /*width*/,
        270 /*height*/,
        0 /*border width*/,
        white /*border*/,
        black /*background*/);

    XSetStandardProperties(
        display,
        window,
        "My nice Window" /* window title, will be missing due to missing window decorations*/,
        "HI!" /*don't know what this is*/,
        None,
        nullptr,
        0,
        nullptr);

    gc = XCreateGC(display, window, 0, 0);

    XClearWindow(display, window);
    XMapRaised(display, window);
};

static void close_x() {
    /* it is good programming practice to return system resources to the system...*/
    XFreeGC(display, gc);
    XDestroyWindow(display, window);
    XCloseDisplay(display);
}

static void draw_star(
    Display* dpy, Window win, GC gc, int outer_radius, int inner_radius, int y, int x) {

    static const double pi = std::acos(-1);
    std::array<XPoint, 11> points;
    for (int i=0; i<points.max_size(); ++i) {
        const double radius = i % 2 == 0 ? inner_radius : outer_radius;
        const short int point_x = std::sin(pi/5*i) * radius + x + outer_radius;
        const short int point_y = std::cos(pi/5*i) * radius + y + outer_radius;
        points[i] = {point_x, point_y};
    }

    const unsigned long white = WhitePixel(display, screen);
    XSetForeground(display, gc, white);
    XDrawLines(dpy, win, gc, points.data(), points.max_size(), CoordModeOrigin);
}

static void draw_full_star(
    Display* dpy, Window win, GC gc, int outer_radius, int inner_radius, int y, int x) {

    static const double pi = std::acos(-1);
    std::array<XPoint, 11> points;
    for (int i=0; i<points.max_size(); ++i) {
        const double radius = i % 2 == 0 ? inner_radius : outer_radius;
        const short int point_x = std::sin(pi/5*i) * radius + x + outer_radius;
        const short int point_y = std::cos(pi/5*i) * radius + y + outer_radius;
        points[i] = {point_x, point_y};
    }

    const unsigned long white = WhitePixel(display, screen);
    XSetForeground(display, gc, white);
    XFillPolygon(display, win, gc, points.data(), points.max_size(), Nonconvex, CoordModeOrigin);
}

static void redraw() {
    XClearWindow(display, window);
    constexpr const int outer_radius = 120;
    constexpr const int inner_radius = outer_radius * 0.375;
    if (full_first) {
        draw_full_star(display, window, gc, outer_radius, inner_radius, 10, 10);
        draw_star(display, window, gc, outer_radius, inner_radius, 10, 260);
    } else {
        draw_star(display, window, gc, outer_radius, inner_radius, 10, 10);
        draw_full_star(display, window, gc, outer_radius, inner_radius, 10, 260);
    }
}

int main(int argc, const char* argv[]) {
    init_x();

    XSelectInput(
        display,
        window,
        ExposureMask | KeyPressMask);

    XEvent event;
    const int input_buffer_size = 8;
    char input_buffer[input_buffer_size];  /* buffer for KeyPress Events; size is arbitrary */

    while(true) {
        XNextEvent(display, &event);
        if (event.type == Expose && event.xexpose.count == 0) {
            redraw();
        }
        else if(event.type == KeyPress) {
            // std::cout << event.xkey.keycode;
            if (event.xkey.keycode == 9 /*esc*/) {
                break;
            } else if (event.xkey.keycode == 23 /*tab*/) {
                full_first = !full_first;
            }
            redraw();
        }
    }
    close_x();
    return 0;
}
