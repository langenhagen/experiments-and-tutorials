/** Showcase the usage and numeric limits of fixed size integers int32_t and int64_t.

@author: andreasl
*/
#include <iostream>
#include <limits>

int main() {

    using limits_int = std::numeric_limits<int>;
    std::cout << "min int is " << limits_int::min() << "\n"; // == -2147483648  but no guarantee
    std::cout << "max int is " << limits_int::max() << "\n"; // == 2147483647  but no guarantee

    using limits32 = std::numeric_limits<int32_t>;
    std::cout << "min int32_t is " << limits32::min() << "\n"; // == -2147483648
    std::cout << "max int32_t is " << limits32::max() << "\n"; // == 2147483647

    using limits64 = std::numeric_limits<int64_t>;
    std::cout << "min int64_t is " << limits64::min() << "\n"; // == -9223372036854775808
    std::cout << "max int64_t is " << limits64::max() << "\n"; // == 9223372036854775807


    return 0;
}