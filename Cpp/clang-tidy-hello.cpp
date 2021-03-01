/**Showcase the usage of the powerfull c++ linter `clang-tidy` on the example of adding `override`
keywords.

Use `clang-tidy` like:

  clang-tidy -checks='modernize-use-override' clang-tidy-hello.cpp -- -std=c++17

To fix stuff, use `-fix`:

  clang-tidy -checks='modernize-use-override' -fix clang-tidy-hello.cpp -- -std=c++17


List all available checks via:

  clang-tidy --list-checks -checks='*'

For several checks call clang tidy like:

  clang-tidy -checks=’modernize-use-override,modernize-use-auto’ -fix clang-tidy-hello.cpp -- -std=c++17

  or

  run-clang-tidy -header-filter='.*' -checks='-*,modernize-*' -fix

based on: https://www.kdab.com/clang-tidy-part-1-modernize-source-code-using-c11c14/

author: andreasl
*/

struct Base {
    virtual void reimplementMe(int a) {}
};
struct Derived : public Base {
    // calling clang-tidy will result in void reimplementMe(int a) override {}
    virtual void reimplementMe(int a) {}
};
