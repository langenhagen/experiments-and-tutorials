/**Showcase a QT project built with CMake, my favorite C++ meta build system.

see:
  - https://doc.qt.io/qt-5/cmake-get-started.html

 @author: andreasl
 */
#include <QApplication>
#include <QLabel>

#include <iostream>

int main(int argc, char* argv[]) {
    using namespace std;

    cout << "Hello from QT via CMake!" << endl;

    QApplication app(argc, argv);
    QLabel hello("Welcome to Qt via CMake!");
    hello.setWindowTitle("Hello QT via CMake");
    hello.resize(400, 200);
    hello.show();
    return app.exec();
}
