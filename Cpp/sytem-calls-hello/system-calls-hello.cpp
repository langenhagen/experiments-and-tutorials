/* Showcas the usage of the function system() to call host system programs.
see: https://en.cppreference.com/w/cpp/utility/program/system
see: https://stackoverflow.com/questions/478898/how-do-i-execute-a-command-and-get-the-output-of-the-command-within-c-using-po

note: popen() apparently catches stdout, but not stderr.
*/
#include <array>
#include <cstdlib>  // system
#include <cstdio> // FILE, popen
#include <iostream>
#include <memory>

int main () {
    std::cout << "Running command via c std lib's system()...\n";
    int return_value = std::system("uname -n");
    std::cout << "Command has been run with exit code: " << return_value << "\n";

    std::cout << "\n\nRunning command via unix's popen()...\n";
    std::array<char, 128> buffer;
    std::string result;
    std::unique_ptr<FILE, decltype(&pclose)> pipe(
        popen("for i in $(seq 3 9); do echo $i; sleep 1; done;", "r"),
        pclose /*deleter*/);
    if(!pipe) {
        throw std::runtime_error("Error: call to popen() failed");
    }
    while(std::fgets(buffer.data(), buffer.size(), pipe.get()) != nullptr) {
        std::cout << "> " << buffer.data();
        result += buffer.data();
    }
    std::cout << "Command has been run with output:\n" << result  << "\n";

    return 0;
}
