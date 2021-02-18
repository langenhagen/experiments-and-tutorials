/** Shows basic usage of QML, the Qt Modeling Language.

Traits of QML:
- json-like syntax
- dynamic strong typing
- css-like capabilities
- javascript for logic


Good things:
- clean syntax
- convenient property binding



you can nicely preview the the QML view via:

    qmlscene 01-main-qml-hello-world.qml

based on: https://doc.qt.io/qt-5/qml-tutorial2.html

@author: andreasl
*/

/*Qt Quick is a 2D scene graph and the UI framework*/
import QtQuick 2.0

Rectangle {
    id: page
    width: 320; height: 480
    color: "lightgray"

    Text {
        id: helloText
        text: "Hello world!"
        y: 30
        anchors.horizontalCenter: page.horizontalCenter
        font.pointSize: 24; font.bold: true
    }
}