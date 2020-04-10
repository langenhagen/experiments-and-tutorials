/*Showcase usage of the external library yaml-cpp.

- see: https://github.com/jbeder/yaml-cpp/

- build like:
    mkdir -p build; cd build; cmake .. && cmake --build .
- note: building with Ninja does not work atm, but with make works

author: andreasl*/
#include <iostream>

#include <yaml-cpp/yaml.h>

int main(int argc, const char* argv[]) {
    std::cout << "Hello!" << std::endl;

    YAML::Node primes = YAML::Load("[2, 3, 5, 7, 11]");
    for (std::size_t i=0;i<primes.size();i++) {
        std::cout << primes[i].as<int>() << "\n";
    }

    std::cout << "Bye!\n";
    return 0;
}
