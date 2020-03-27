/* Render an X11 win for multiple lines of text input
and have a text cursor that you can control via (ctrl +) arrow keys, and home/end
as well as a selection via pressing shift.
Also copy/cut/paste works with ctrl+x, ctrl+c, and ctrl+v.

Apparently, x11 provides own cursors https://tronche.com/gui/x/xlib/pixmap-and-cursor/cursor.html
but they seem inferior. I use a simple filled rectangle as a cursor.

- based on my prior learnings and the source code of dmenu.

author: andreasl
*/
#include "App.hpp"

int main(int argc, const char* argv[]) {
    barn::x11::app::App app;
    app.lines.emplace_back();
    return app.run();
}
