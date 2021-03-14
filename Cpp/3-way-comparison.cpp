/** Showcase that 3 way comparison doesn't work in Cpp.
author: andreasl
*/

#include <iostream>

int main() {

    auto x = 3 < 4 < 6;  // = 1
    std::cout << "x: " << x << "\n";

    auto y = 3 < 7 < 6;  // = 1  doesnt work
    std::cout << "y: " << y << "\n";

    auto z = 3 < 2 < 6;  // = 1  doesnt work
    std::cout << "z: " << z << "\n";


    auto alpha = (3 < 2) && (3 < 6);  // = 0  that works
    std::cout << "alpha: " << alpha << "\n";
    return 0;
}
