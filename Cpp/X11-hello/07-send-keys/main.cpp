/* TODO

A full list of available key codes can be found in /usr/include/X11/keysymdef.h


based on: https://ubuntuforums.org/showthread.php?t=2190400

author: andreasl
*/
#include <X11/Xlib.h>
#include <X11/keysym.h>

#include <chrono>
#include <thread>

/*Create x11 key event.*/
XKeyEvent create_key_event(
    Display* display,
    Window& win,
    Window& root,
    bool press,
    int keycode,
    int modifiers)
{
    XKeyEvent event;
    event.display = display;
    event.window = win;
    event.root = root;
    event.subwindow = None;
    event.time = CurrentTime;
    event.same_screen = True;
    event.keycode = XKeysymToKeycode(display, keycode);
    event.state = modifiers;
    event.type = press ? KeyPress : KeyRelease;
    return event;
}

main() {
    Display* display = XOpenDisplay(nullptr);
    Window root = XDefaultRootWindow(display);

    /*Find the window which has the current keyboard focus*/
    Window focused_win;
    int revert;
    XGetInputFocus(display, &focused_win, &revert);

    XEvent evt;

    /*Ctrl + l*/
    /*Send a fake key press event to the focused window*/
    evt.xkey = create_key_event(display, focused_win, root, true, XK_l, ControlMask);
    XSendEvent(evt.xkey.display, evt.xkey.window, True, KeyPressMask, &evt);

    std::this_thread::sleep_for(std::chrono::milliseconds(10));

    // /*Send a fake key release event to the window*/
    // evt.xkey= create_key_event(display, focused_win, root, false, XK_l, ControlMask);
    // XSendEvent(evt.xkey.display, evt.xkey.window, True, KeyPressMask, &evt);

    std::this_thread::sleep_for(std::chrono::milliseconds(20));

    /*Ctrl + c*/
    /*Send a fake key press event to the focused window*/
    evt.xkey = create_key_event(display, focused_win, root, true, XK_x, ControlMask);
    XSendEvent(evt.xkey.display, evt.xkey.window, True, KeyPressMask, &evt);

    // std::this_thread::sleep_for(std::chrono::milliseconds(10));

    // /*Send a fake key release event to the window*/
    // evt.xkey = create_key_event(display, focused_win, root, false, XK_c, ControlMask);
    // XSendEvent(evt.xkey.display, evt.xkey.window, True, KeyPressMask, &evt);

    // std::this_thread::sleep_for(std::chrono::milliseconds(20));

    XCloseDisplay(display);
    return 0;
}