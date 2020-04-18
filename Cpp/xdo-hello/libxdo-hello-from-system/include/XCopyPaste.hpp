/* Utility functions for easy reading from and writing to the x window system clipboard.

Use the external utility `xclip` rather than implemting clipboard capabilities for the X
application. This serves 2 purposes. First, avoid the necessity for the caller to implement an x11
application and avoid the complex setup for clipboard capabilities in x window applications. Second,
allow access to the clipboard after the calling process terminated.

author: andreasl
*/
#pragma once

#include <array>
#include <cstdio>
#include <memory>
#include <stdexcept>
#include <string>

namespace barn {
namespace x11 {
namespace cp {

namespace detail {

/*Call popen() & pclose() and return its output.*/
std::string call_popen(const char* cmd) {
    std::string result;
    std::unique_ptr<FILE, decltype(&pclose)> pipe( popen(cmd, "r"), pclose);
    if(!pipe) {
        throw std::runtime_error("Error: call to popen() failed");
    }
    std::array<char, 128> buf;
    while(std::fgets(buf.data(), buf.size(), pipe.get()) != nullptr) {
        result += buf.data();
    }
    return result;
}

} // namespace detail

/*Write the given string to the x window clipboard.*/
void write_to_clipboard(const std::string& str) {
    const std::string cmd = "printf -- '" + str + "' | xclip -selection clipboard";
    system(cmd.c_str());
}

/*Return a string from the x window clipboard.*/
std::string get_text_from_clipboard() {
    return detail::call_popen("xclip -selection clipboard -o");
}

} // namespace cp
} // namespace x11
} // namespace barn
