/* Showcase usage of aggregate initialization.

author: andreasl
*/
#include <iostream>

struct S {
    int i;
    float j;
};

int main() {
    std::cout << "Hello from aggregate-initialize-hello\n";

    S s{1,2.3};
}