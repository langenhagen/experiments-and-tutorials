/**Showcase the performance difference of passing shared pointers by value vs passing shared pointers
by reference.

@author: andreasl
*/
#include <chrono>
#include <iostream>
#include <memory>

using duration_type = std::chrono::milliseconds;
constexpr static const int n_iterations = 100000000;

using type = int;


void call_ptr_by_val(const std::shared_ptr<type> p) {/*empty*/}


/** test passing shared pointers by value.*/
void test_by_value() {
    using namespace ::std::chrono;
    auto p = std::make_shared<type>(42);

    const auto clock_start = steady_clock::now();
    for(auto i = 0; i < n_iterations; ++i) {
        call_ptr_by_val(p);
    }
    const auto duration = duration_cast<duration_type>(steady_clock::now() - clock_start);
    std::cout << "Calling by value took " << duration.count() << " ms\n";
}


void call_ptr_by_ref(const std::shared_ptr<type>& p) {/*empty*/}


/** test passing shared pointers by value.*/
void test_by_ref() {
    using namespace ::std::chrono;
    auto p = std::make_shared<type>(42);

    const auto clock_start = steady_clock::now();
    for(auto i = 0; i < n_iterations; ++i) {
        call_ptr_by_ref(p);
    }
    const auto duration = duration_cast<duration_type>(steady_clock::now() - clock_start);
    std::cout << "Calling by reference took " << duration.count() << " ms\n";
}


int main() {
    std::cout << "Hi from calling shared_ptr by value vs. calling shared_ptr by reference!\n"
        << "n_iterations: " << n_iterations << '\n';

    test_by_value();
    test_by_ref();

    return 0;
}
