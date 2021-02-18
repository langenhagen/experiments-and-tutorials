/** Shows usage of QML components.

you can nicely preview the the QML view via:

    qmlscene 02-main-components-hello.qml

based on: https://doc.qt.io/qt-5/qml-tutorial2.html

see:
  - https://doc.qt.io/qt-5/qtqml-syntax-signals.html

@author: andreasl
*/
import QtQuick 2.0

Rectangle {
    id: page
    width: 320;
    height: 480
    color: "lightgray"

    Text {
        id: helloText
        text: "Hello world!"
        y: 30
        anchors.horizontalCenter: page.horizontalCenter
        font.pointSize: 24; font.bold: true
    }

    Grid {
        id: colorPicker
        x: 4; anchors.bottom: page.bottom;
        anchors.bottomMargin: 4
        rows: 2
        columns: 3
        spacing: 3

        /* Instanciate the custom types and give them some event handlers.*/
        Cell { cellColor: "red"; onClicked: helloText.color = cellColor }
        Cell { cellColor: "green"; onClicked: helloText.color = cellColor }
        Cell { cellColor: "blue"; onClicked: helloText.color = cellColor }
        Cell { cellColor: "yellow"; onClicked: helloText.color = cellColor }
        Cell { cellColor: "steelblue"; onClicked: helloText.color = cellColor }
        Cell { cellColor: "black"; onClicked: helloText.color = cellColor }
    }
}