#include <iostream>
#include <vector>

#include <cstring>

struct S
{
    std::string name;
    int32_t length;
    std::vector<double> data;
};

int main() {

    std::cout << sizeof(std::vector<int>) << "\n";
    std::cout << sizeof(std::string("And")) << "\n";
    std::cout << sizeof(std::string("Andsdpofsdpofdsjfpljdsfim").c_str()) << "\n";

    S s{"Andi", 180, {1.1,2.2,3.3, 4.4}};
    S t{"KAtzenbacher", 92, {1.1,2.2,3.3, 4.4}};

    std::cout << &s << "\n" << sizeof(s) << "\n";
    std::cout << &t << "\n" << sizeof(t) << "\n";  // they hav  e the same size
}
