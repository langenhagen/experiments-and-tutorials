// Showcase something that causes a segfault
#include <iostream>

int main() {
    std::cout << "Hello from segault-hello.cpp...\n";
    int i = 1 / 0;  // cause a crashes
    std::cout << "END\n";
    return 0;
}

