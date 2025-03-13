#!/usr/bin/env python3
"""Showcase the usage of the package harvesters to get images from a GenICam
standard camera via stream.

Tested with a Baumer VCXG.2-15C.I.
"""

import datetime as dt
import logging
import sys
from itertools import count
from math import ceil
from pathlib import Path
from time import perf_counter
from typing import TYPE_CHECKING

import cv2
from genicam.genapi import AccessException, NodeMap
from genicam.gentl import InvalidAddressException, NotImplementedException
from harvesters.core import DeviceInfo, Harvester, ImageAcquirer, RemoteDevice

if TYPE_CHECKING:
    import numpy as np

log = logging.getLogger(__name__)


def _setup_log() -> None:
    """Set up logging for the application."""
    logging.basicConfig(
        format="%(asctime)s.%(msecs)d [%(levelname)s]: %(filename)s:%(lineno)d: %(message)s",
        datefmt="%T",
        level=logging.DEBUG,
    )


def _print_nodes_and_values(node_map: NodeMap) -> None:
    """Print the nodes and according values of the given NodeMap."""
    for name in dir(node_map):
        if not name[0].isupper():
            continue

        info = ""
        value = ""
        try:
            node = getattr(node_map, name)
            if hasattr(node, "value"):
                value = node.value
            if hasattr(node, "symbolics"):
                info += f"    {node.symbolics}"
            if hasattr(node, "execute"):
                info += "     \033[92m*EXECUTABLE*\033[0m"
        except AccessException:
            value = "***AccessException***"
        except InvalidAddressException:  # happened with the Allied Vision U-508c
            value = "\033[1;31m***InvalidAddressException***\033[0m"

        log.info(f"{name}={value}{info}")

    log.info("---")


def main(write_images_to_disk: bool) -> int:
    """Main program function."""
    _setup_log()

    h = Harvester(logger=log)

    cti_files = [
        "/opt/baumer-gapi-sdk-cpp/lib/libbgapi2_gige.cti",
    ]
    for cti_file in cti_files:
        h.add_file(cti_file)

    h.update()

    log.info(f"{len(h.device_info_list)} devices:\n{h.device_info_list}")
    assert len(h.device_info_list) > 0, "Oh no! No device detected."

    device_info: DeviceInfo = h.device_info_list[0]
    serial_number = int(device_info.serial_number)
    log.info(f"serial_number={serial_number}")

    if write_images_to_disk is True:
        now = dt.datetime.now().strftime("%Y-%m-%-d-%H:%M:%S")
        output_folder = Path.cwd() / f"images-{now}"
        output_folder.mkdir(exist_ok=False)

    ia: ImageAcquirer = h.create()
    device: RemoteDevice = ia.remote_device

    node_map: NodeMap = device.node_map
    node_map.BalanceWhiteAuto.value = "Off"
    node_map.GainAuto.value = "Off"
    node_map.Gain.value = 1.0
    node_map.ExposureTime.value = 15_000
    # OpenCV works in BGR; see `pfnc.py` for available formats
    node_map.PixelFormat.value = "BGR8"

    # _print_nodes_and_values(node_map)

    node_map.TriggerMode.value = "Off"
    binning_value = 2
    node_map.BinningHorizontal.value = binning_value
    node_map.BinningHorizontalMode.value = "Average"
    node_map.BinningVertical.value = binning_value
    node_map.BinningVerticalMode.value = "Average"

    ia.start()

    start_loop = perf_counter()
    infos = []
    for n_frame in count(1):
        start_frame: float = perf_counter()

        with ia.fetch(timeout=10) as buffer:
            component = buffer.payload.components[0]

            img_data: np.ndarray = component.data
            img = img_data.reshape(component.height, component.width, 3)

            end_frame: float = perf_counter()
            net_duration_ms: float = (end_frame - start_frame) * 1000
            gross_duration_ms: float = (end_frame - start_loop) * 1000
            net_fps: float = round(1000.0 / net_duration_ms, 1)
            gross_fps: float = round(n_frame * 1000.0 / gross_duration_ms, 1)

            key = cv2.waitKey(1)
            if key == ord("q"):
                break
            elif key == 81:  # left key
                node_map.Gain.value -= 0.1
                log.info(f"Gain set to: {node_map.Gain.value}")
            elif key == 83:  # right key
                node_map.Gain.value += 0.1
                log.info(f"Gain set to: {node_map.Gain.value}")
            elif key == 82:  # up key
                node_map.ExposureTime.value += 50
                log.info(f"Exposure set to: {node_map.ExposureTime.value}")
            elif key == 84:  # down key
                node_map.ExposureTime.value -= 50
                log.info(f"Exposure set to: {node_map.ExposureTime.value}")

            window_title = f"frame # {n_frame}, took {ceil(net_duration_ms)}ms; {net_fps} net fps; {gross_fps} gross fps"
            infos.append(window_title)
            cv2.imshow("image", img)
            cv2.setWindowTitle("image", window_title)

            if write_images_to_disk is True:
                cv2.imwrite(str(output_folder / f"img-{n_frame}.png"), img)

    ia.stop()
    ia.destroy()
    h.reset()

    return 0


if __name__ == "__main__":
    sys.exit(main(write_images_to_disk=False))
