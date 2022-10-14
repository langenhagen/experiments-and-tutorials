#!/usr/bin/env python3
"""Showcase the usage of the package harvesters to get images from a GenICame
standard camera."""
from curses.ascii import isupper
from harvesters.core import Harvester, ImageAcquirer, RemoteDevice

import numpy as np
import sys
import cv2
import json

from genicam.genapi import NodeMap, AccessException


def _print_nodes_and_values(node_map: NodeMap):
    """Print the nodes and according values of the given NodeMap."""

    for name in dir(node_map):
        if not name[0].isupper():
            continue

        try:
            node = getattr(node_map, name)
            if hasattr(node, "value"):
                value = node.value
        except AccessException:
            value = "***AccessException***"

        print(f"{name}={value}")

    print("---")


def main() -> int:
    """Main program function."""
    h = Harvester()

    # load a suitable CTI file for your cam
    h.add_file("/opt/ids-peak_2.1.0.0-14251_amd64/lib/ids/cti/ids_u3vgentl.cti")
    h.update()

    print(f"{len(h.device_info_list)} devices:\n{h.device_info_list}")
    assert len(h.device_info_list) > 0, "Oh no! No device detected."

    ia: ImageAcquirer = h.create_image_acquirer(list_index=0)
    device: RemoteDevice = ia.remote_device
    node_map: NodeMap = device.node_map

    node_map.Gain.value = 2.0
    node_map.ExposureTime.value = 10_000
    node_map.Width.value = 480
    node_map.Height.value = 360
    node_map.PixelFormat.value = "BayerRG8"  # see `pfnc.py` for available formats

    _print_nodes_and_values(node_map)

    ia.start_acquisition()

    keep_grabbing = True
    while keep_grabbing is True:
        with ia.fetch_buffer() as buffer:
            component = buffer.payload.components[0]

            # note that the number of components can vary. If your
            # target remote device transmits a multi-part information, then
            # you'd get two or more components in the payload. However, now
            # we're working with a remote device that transmits only a 2D image.
            # So we manipulate only index 0 of the list object, components.

            img_data: np.ndarray = component.data
            img = img_data.reshape(component.height, component.width)

            key = cv2.waitKey(1)
            if key == ord("q"):
                keep_grabbing = False
            elif key == 82:  # up key
                pass
            elif key == 84:  # down key
                pass

            img2 = cv2.cvtColor(img, cv2.COLOR_BayerRG2RGB)
        cv2.imshow("image", img2)

    ia.stop_acquisition()

    ia.destroy()

    h.reset()


if __name__ == "__main__":
    sys.exit(main())
