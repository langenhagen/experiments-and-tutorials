#include <cstdlib>  // system
#include <iostream>

using namespace std;
int main () {
    cout << "Running command...\n";
    int return_value = system("uname -a");
    cout << "Command has been run with exit code: " << return_value << "\n";
    return 0;
}

