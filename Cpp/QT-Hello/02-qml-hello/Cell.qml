/** A reusable QML component.
Note that QML component files usually start with a capital letter.

Preview the component via:

        qmlscene Cell.qml

see:
  - https://doc.qt.io/qt-5/qml-tutorial2.html
  - https://doc.qt.io/qt-5/qtqml-syntax-signals.html

@author: andreasl
*/
import QtQuick 2.0

Item {
    id: container
    property alias cellColor: rectangle.color

    /*define a custom signal; signals are events*/
    signal clicked(color cellColor)   /*I had to change that from the original tutorial due to changed syntax*/

    width: 80; height: 60

    Rectangle {
        id: rectangle
        border.color: "white"
        anchors.fill: parent
    }

    MouseArea {
        anchors.fill: parent

        /* onClicked is an event handler, its function body is JavaScript
        This one emits the signal defined above.
        */
        onClicked: {
            container.clicked(container.cellColor);
            console.log("clicked the " + parent.cellColor + " one");
        }
    }
}