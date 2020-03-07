/* Render an undecorated window that waits for keyboard input.
Close the program with ESC.

The Xft Approach for Text Rendering is incomplete.

author: andreasl

based on: http://mech.math.msu.su/~nap/2/GWindow/xintro.html
   window decorations displayabled based on:
   https://stackoverflow.com/questions/31361859/simple-window-without-titlebar
     - also helpful for possible program structure

- use the command line tool `xev` to find out x11 key codes
- use the command line tool `xlsfonts` to find out which fonts exist

- https://tronche.com/gui/x/xlib/events/structures.html
- https://tronche.com/gui/x/xlib/events/keyboard-pointer/keyboard-pointer.html#XKeyEvent

- https://tronche.com/gui/x/xlib/window-information/properties-and-atoms.html
*/
#include <algorithm>
#include <cstring>
#include <iostream>

/* order of X11 includes allegedlly important*/
#include <X11/Xlib.h>
#include <X11/Xutil.h>
#include <X11/Xos.h>
#include <X11/Xatom.h>
// #include <X11/Xft/Xft.h>

Display *display;
int screen;
Window window;
GC gc;
XFontStruct *font; /* classic approach*/
// XftFont *xfont; /*Xft approach*/

int n_redraws = 0;
const int text_buffer_size = 255;
char text_buffer[text_buffer_size];
int text_cursor_pos = 0;

static void setup_font() {
    /* found here: https://www.lemoda.net/c/xlib-text-box/
    most fonts I see via `xlsfonts` dont works*/
    const char *font_name = "-bitstream-courier 10 pitch-medium-r-normal--0-0-0-0-m-0-ascii-0";
    font = XLoadQueryFont(display, font_name);
    if (!font) {
       /* If the font could not be loaded, revert to the "fixed" font. */
        std::cerr << "unable to load font " << font_name << " using default font \"fixed\"\n";
        font = XLoadQueryFont(display, "fixed");
    }
    XSetFont(display, gc, font->fid);

    // what dmenu does; I have issues with loading transitive dependencies
    // const char *font_name = "monospace:size=10";
    // xfont = XftFontOpenName(display, screen, font_name);
    // if(!xfont) {
    //     std::cerr << "unable to load font " << font_name << " using default font \"fixed\"\n";
    // }
}

static void init_x() {
    /* use the information from the environment variable DISPLAY
       to create the X connection:*/
    display = XOpenDisplay(nullptr);
    screen = DefaultScreen(display);

    /* get the colors black and white*/
    unsigned long black = BlackPixel(display, screen);
    unsigned long white = WhitePixel(display, screen);

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

    /* here is where some properties of the window can be set.
       The third and fourth items indicate the name which appears
       at the top of the window and the name of the minimized window
       respectively.*/
    XSetStandardProperties(
        display,
        window,
        "My nice Window" /* window title, will be missing due to missing window decorations*/,
        "HI!" /*don't know what this is*/,
        None,
        nullptr,
        0,
        nullptr);

    /* disable window decorations
       based on https://stackoverflow.com/questions/31361859/simple-window-without-titlebar
       I had however to replace  _NET_WM_WINDOW_TYPE  with  _MOTIF_WM_HINTS*/
    Atom window_type = XInternAtom(
        display,
        "_MOTIF_WM_HINTS" /*atom name*/,
        False /*only if exists*/);
    Atom value = XInternAtom(
        display,
        "_NET_WM_WINDOW_TYPE_DOCK" /*atom name*/,
        False /*only if exists*/);
    XChangeProperty(
        display,
        window,
        window_type,
        XA_ATOM,
        32,
        PropModeAppend,
        (unsigned char*)&value,
        1);

    /* create the Graphics Context */
    gc = XCreateGC(display, window, 0, 0);


    /* here is another routine to set the foreground and background
       colors _currently_ in use in the window.*/
    // XSetBackground(display, gc, white);
    XSetForeground(display, gc, white);

    setup_font();

    /* clear the window and bring it on top of the other windows */
    XClearWindow(display, window);
    XMapRaised(display, window);
};

static void close_x() {
    /* it is good programming practice to return system resources to the system...*/
    XFreeGC(display, gc);
    XDestroyWindow(display, window);
    XCloseDisplay(display);
}

static void redraw() {
    // std::cout << "redrawing " << ++n_redraws << std::endl;
    XClearWindow(display, window);
    XDrawString(
        display,
        window,
        gc,
        10 /*pos x*/,
        20 /*pos y*/,
        text_buffer,
        text_cursor_pos);
}

int main(int argc, const char* argv[]) {
    init_x();

    /* listen to given event types*/
    XSelectInput(
        display,
        window,
        ExposureMask /*when portions of the window are exposed that were hidden by another window*/
        | ButtonPressMask
        | KeyPressMask);

    XEvent event;  /* the XEvent declaration */
    const int input_buffer_size = 32;
    char input_buffer[input_buffer_size];  /* buffer for KeyPress Events; size is arbitrary */

    while(true) {
        /* get the next event and stuff it into our event variable.
           Note: only events we set the mask for are detected!*/
        XNextEvent(display, &event);
        if (event.type == Expose && event.xexpose.count == 0) {
            /* the window was exposed redraw it!
               see: https://tronche.com/gui/x/xlib/events/exposure/expose.html*/
            redraw();
        }
        else if(event.type == KeyPress) {
            // std::cout << event.xkey.keycode;
            if( event.xkey.keycode == 9 /*esc*/) {
                break;
            }
            else if(event.xkey.keycode == 22 /*delete*/) {
                text_cursor_pos = std::max(--text_cursor_pos, 0);
            }
            else if(XLookupString(
                &event.xkey /*event struct*/,
                input_buffer /*input buffer*/,
                input_buffer_size /*buffer length*/,
                nullptr /*output keysym or nullptr*/,
                nullptr /*status_in_out or nullptr*/) > 0) {
                /* normal text input*/

                /* use XLookupString() to convert the KeyPress data into regular text.
                   see: https://tronche.com/gui/x/xlib/utilities/XLookupString.html*/
                text_buffer[text_cursor_pos] = input_buffer[0];
                ++text_cursor_pos;
            }
            redraw();
        }
        else if(event.type == ButtonPress) {
            /* tell where the mouse Button was pressed*/
            sprintf(
                text_buffer,
                "You pressed a mouse button at (%d. %d)",
                event.xbutton.x,
                event.xbutton.y);
            text_cursor_pos = strlen(text_buffer);
            redraw();
        }
    }
    close_x();
    return 0;
}
