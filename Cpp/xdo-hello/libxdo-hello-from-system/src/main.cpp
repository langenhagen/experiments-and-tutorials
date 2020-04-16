/*Showcase usage of the external library when it is installed via the system.

To learn the keysym names, use the cli tool `xev`.

- install requirements:
    - sudo apt install -y libxdo-dev

- build like:
    mkdir -p build; cd build; cmake .. && cmake --build .

- see:
    - https://github.com/jordansissel/xdotool
    - https://packages.ubuntu.com/xenial/libxdo-dev

author: andreasl
*/
#include "XCopyPaste.hpp"

#include <chrono>
#include <cstring>
#include <iostream>
#include <memory>
#include <thread>

extern "C" {
#include <xdo.h>
}

int main(int argc, const char* argv[]) {
    std::cout << "Hello!\n" << std::endl;

    sleep(2);  /*wait until user focuses on the right window*/

    std::unique_ptr<xdo_t, decltype(&xdo_free)> my_xdo(xdo_new(nullptr), &xdo_free);

    Window win;
    xdo_get_active_window(my_xdo.get(), &win);

    unsigned char* name;
    int len;
    int type;
    xdo_get_window_name(
        my_xdo.get(),
        win,
        &name,
        &len,
        &type);

    std::cout
        << "\nWindow:      " << win
        << "\nwindow_name: " << name
        << "\nlen:         " << len
        << "\ntype:        " << type
        << std::endl;

    unsigned char chrome_suffix[17] = " - Google Chrome";
    if(std::memcmp(name + len - 16, chrome_suffix, 16)) {
        std::cerr << "Window \"" << name << "\" is not a Google Chrome window." << std::endl;
        return 1;
    }

    /*focus the chrome address bar*/
    xdo_send_keysequence_window(
        my_xdo.get(),
        win,
        "Control_L+l"/*X11 KeySym names separated by '+'*/,
        0 /*delay in micro seconds*/);

    std::this_thread::sleep_for(std::chrono::milliseconds(10)); /*sleeping seems necessary*/

    /*copy the contents*/
    xdo_send_keysequence_window(
        my_xdo.get(),
        win,
        "Control_L+c",
        0);

    std::this_thread::sleep_for(std::chrono::milliseconds(20));
    const auto url(::barn::x11::cp::get_text_from_clipboard());

    std::cout << "\n---\n" << url << "\n---\n";

    std::cout << "\nBye!" << std::endl;
    return 0;
}
