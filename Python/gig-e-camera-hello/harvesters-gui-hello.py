#!/usr/bin/env python3

import sys

from harvesters_gui.frontend.pyqt5 import Harvester
from PyQt5.QtWidgets import QApplication

if __name__ == '__main__':
    app = QApplication(sys.argv)
    h = Harvester()
    h.show()
    sys.exit(app.exec_())
