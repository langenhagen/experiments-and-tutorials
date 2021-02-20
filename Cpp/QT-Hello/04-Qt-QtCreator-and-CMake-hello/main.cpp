/*Showcase a pretty default Qt5 UI application with CMake, created via QtCreator.
 * @author: andreasl
 */

#include "mainwindow.h"

#include <QApplication>

int main(int argc, char *argv[])
{
    QApplication a(argc, argv);
    MainWindow w;
    w.show();
    return a.exec();
}
