/*Showcase the use of constexpr on containers.*

author: andreasl
*/
#include <array>
#include <iostream>
#include <vector>

int main() {

    std::cout << "With std::array:\n";

    constexpr std::array arr{1,2,3};
    std::cout
        << arr[0] << std::endl
        << arr[1] << std::endl
        << arr[2] << std::endl;

    // not possible with std::vector as of C++17
    // std::cout << "With std::vector\n";
    // constexpr std::vector<int> vec{5,8,11};
    // std::cout
    //     << vec[0] << std::endl
    //     << vec[1] << std::endl
    //     << vec[2] << std::endl;

    return 0;
}