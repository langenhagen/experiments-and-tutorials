/*Showcase usage of the external library libxdo.a coming with the external project xdotool.

- see:
    - https://github.com/jordansissel/xdotool
        - make libxdo.a
            - requires:
                - sudo apt install -y libxtst-dev
                - sudo apt install libxkbcommon-dev
                - see .travis.yml for more dependencies

- build like:
    mkdir -p build; cd build; cmake .. && cmake --build .

author: andreasl*/
#include <iostream>
#include <memory>

extern "C" {
#include <libxdo/xdo.h>
}

int main(int argc, const char* argv[]) {
    std::cout << "Hello!\n" << std::endl;

    // I want that:
    // xdotool getactivewindow getwindowname | grep -q ' - Google Chrome$' || exit 1
    //
    // window_id="$(xdotool getactivewindow)"
    // xdotool key --window "$window_id" "ctrl+l"
    // xdotool key --window "$window_id" "ctrl+c"


    // what I see
    /*
    context_t context;
    context.xdo = xdo_new(NULL);
    context.prog = *argv;
    argv++; argc--;
    context.argc = argc;
    context.argv = argv;
    context.windows = NULL;
    context.nwindows = 0;
    context.have_last_mouse = False;
    context.debug = (getenv("DEBUG") != NULL);

    if (context.xdo == NULL) {
        fprintf(stderr, "Failed creating new xdo instance\n");
        return 1;
    }
    context.xdo->debug = context.debug;

    ret = context_execute(&context);

    xdo_free(context.xdo);
    if (context.windows != NULL) {
        free(context.windows);
    }
    */

    xdo_new(nullptr);

    // std::unique_ptr<xdo_t, decltype(&xdo_free)> my_xdo(xdo_new(nullptr), &xdo_free);


    std::cout << "\nBye!" << std::endl;
    return 0;
}
