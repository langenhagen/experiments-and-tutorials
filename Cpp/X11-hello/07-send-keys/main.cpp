/* Showcase sending of key events to the x window system.

A full list of available key codes can be found in /usr/include/X11/keysymdef.h

based on:
    - https://ubuntuforums.org/showthread.php?t=2190400
    - https://web.archive.org/web/20070712224859/http://www.doctort.org/adam/nerd-notes/x11-fake-keypress-event.html


see also:
    - https://www.x.org/releases/X11R7.5/doc/man/man3/XSync.3.html
    - https://stackoverflow.com/questions/30413316/sending-fake-keypress-event-to-a-window-using-xlib

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
    event.type = press == true ? KeyPress : KeyRelease;
    return event;
}

main() {
    Display* display = XOpenDisplay(nullptr);
    Window root = XDefaultRootWindow(display);

    /*Find the window which has the current keyboard focus*/
    Window focused_win;
    int revert;
    XGetInputFocus(display, &focused_win, &revert);

    /*Ctrl + l*/
    /*Send a fake key press event to the focused window*/
    XEvent focus_evt;
    focus_evt.xkey = create_key_event(display, focused_win, root, true, XK_l, ControlMask);
    XSendEvent(focus_evt.xkey.display, focus_evt.xkey.window, True, KeyPressMask, &focus_evt);

    /*Send a fake key release event to the window*/
    focus_evt.xkey = create_key_event(display, focused_win, root, false, XK_l, ControlMask);
    XSendEvent(focus_evt.xkey.display, focus_evt.xkey.window, True, KeyPressMask, &focus_evt);

    /*both flushing and sleeping a very short amount of time seems to be necessary.*/
    XFlush(display);
    std::this_thread::sleep_for(std::chrono::milliseconds(1));

    // /*Ctrl + c*/
    // /*Send a fake key press event to the focused window*/
    XEvent copy_evt;
    copy_evt.xkey = create_key_event(display, focused_win, root, true, XK_c, ControlMask);
    XSendEvent(copy_evt.xkey.display, copy_evt.xkey.window, True, KeyPressMask, &copy_evt);

    copy_evt.xkey = create_key_event(display, focused_win, root, false, XK_c, ControlMask);
    XSendEvent(copy_evt.xkey.display, copy_evt.xkey.window, True, KeyPressMask, &copy_evt);

    XFlush(display);
    std::this_thread::sleep_for(std::chrono::milliseconds(1));

    XCloseDisplay(display);
    return 0;
}
