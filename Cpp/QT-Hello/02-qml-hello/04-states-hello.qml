/** Showcases the use of states and transitions in QML.

based on: https://en.wikipedia.org/wiki/QML#States

@author: andreasl
*/

import QtQuick 2.0

Item {
  id: myItem
  width: 200; height: 200

  Rectangle {
    id: myRect
    width: 100; height: 100
    color: "red"
  }
  states: [
    State {
      name: "moved"
      PropertyChanges {
        target: myRect
        x: 50
        y: 50
      }
    }
  ]
  MouseArea {
    anchors.fill: parent
    onClicked: myItem.state = 'moved'
  }

  /* you can define transition animations between states. YOu can also leave them away :) */
  transitions: [
    Transition {
      /*from any state to state "moved"*/
      from: "*"
      to: "moved"
      NumberAnimation { properties: "x,y"; duration: 500 }
    }
  ]
}
