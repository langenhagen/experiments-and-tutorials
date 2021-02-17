/** A reusable QML component.
Note that QML component files usually start with a capital letter.

Preview the component via:

        qmlscene Cell.qml

see: https://doc.qt.io/qt-5/qml-tutorial2.html

@author: andreasl
*/
import QtQuick 2.0

Item {
    id: container
    property alias cellColor: rectangle.color
    signal clicked(color cellColor)   /*I had to change that from the original tutorial due to changed syntax*/

    width: 40; height: 25

    Rectangle {
        id: rectangle
        border.color: "white"
        anchors.fill: parent
    }

    MouseArea {
        anchors.fill: parent
        onClicked: container.clicked(container.cellColor)
    }
}