#include "H:\CUDA 6.0\Toolkit\include\thrust\version.h"
#include <conio.h>
#include <iostream>

using namespace std;

void main() {
    int major = THRUST_MAJOR_VERSION;
    int minor = THRUST_MINOR_VERSION;

    cout << "Thrust v" << major << "." << minor << std::endl;

    getch();
}