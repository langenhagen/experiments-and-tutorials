/* A simple QT application from the command line via `qmake`.

1. Install QTs prerequisites:

    sudo apt update;
    sudo apt upgrade -y;

    sudo apt install -y qtcreator;
    sudo apt install -y qt5-default;  # set QT 5 as the default

    sudo apt install -y qt5-doc:  # Qt 5 API Documentation.
    sudo apt install -y qtbase5-examples;  # Qt Base 5 examples.
    sudo apt install -y qtbase5-doc-html;  # HTML documentation for the Qt 5 Base libraries

2. Create a *.pro file via:

    qmake -project

This creates a *.pro file.

3. Then add the line:

    QT += gui widgets  # added that manually

to the *.pro file.

4. Call:

    qmake

This creates a Makefile.

5. Call:

    make

This makes your application. Nice.

based on: https://vitux.com/compiling-your-first-qt-program-in-ubuntu/
author: andreasl
*/
#include <QApplication>
#include <QLabel>
#include <QWidget>

#include <iostream>

int main(int argc, char* argv[]) {
    using namespace std;

    cout << "Hello from QT!" << endl;

    QApplication app(argc, argv);
    QLabel hello("<center>Welcome to my first Qt program</center>");
    hello.setWindowTitle("My First Qt Program");
    hello.resize(700, 700);
    hello.show();
    return app.exec();
}
