/* Render an own X11 widget for interactive text IO.

- based on my prior learnings

author: andreasl
*/
#include "App.hpp"

int main(int argc, const char* argv[]) {
    barn::x11::App app;
    app.text_box.lines.emplace_back();
    return app.run();
}
