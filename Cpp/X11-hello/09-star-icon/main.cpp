/* Render a star-shaped icon in an X11 window.

- based on my prior learnings

author: andreasl
*/
#include <X11/Xlib.h>
#include <X11/Xutil.h>

#include <array>
#include <cmath>

Display* display;
int screen;
Window window;
GC gc;
XFontStruct *font; /* classic approach*/

int n_redraws = 0;

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
        400 /*width*/,
        200 /*height*/,
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

    // std::array<XPoint, 10> points;
    // for (int i=0; i<points.max_size(); ++i) {
    //     // todo populate the points given the offsets y, x and the outer and inner radii
    // }

    XPoint points[] = {
      {0, 0},
      {15, 10},
      {0, 20},
      {-15, 10},
      {0, 0}
    };
    for (int i=0; i<5; ++i) {
        points[i].y += y;
        points[i].x += x;
    }

    const unsigned long white = WhitePixel(display, screen);
    XSetForeground(display, gc, white);
    XDrawLines(dpy, win, gc, points, 5, CoordModeOrigin);
}

static void draw_full_star(Display* dpy, Window win, GC gc, int radius, int y, int x) {
    // TODO
}

static void redraw() {
    // std::cout << "redrawing " << ++n_redraws << std::endl;
    XClearWindow(display, window);
    draw_star(display, window, gc, 90, 30, 50, 70);
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
                // TODO implement switching from hollow to full star
            }
            redraw();
        }
    }
    close_x();
    return 0;
}
