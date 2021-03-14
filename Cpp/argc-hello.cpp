/*Showcase the usage of argc and argv

@author: andreasl
*/
#include <iostream>

int main(int argc, const char* argv[]) {
    using namespace std;

    cout << "argc: " << argc << '\n';
    cout << "argv:\n";
    for(int i = 0; i < argc; ++i) {
        cout << "  [" << i << "]: " << argv[i] << '\n';
    }
    return 0;
}
