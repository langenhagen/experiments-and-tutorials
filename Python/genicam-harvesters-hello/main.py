#!/usr/bin/env python3
"""Showcase the usage of the package harvesters to get images from a GenICam
standard camera."""

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


def main(use_software_trigger: bool, write_images_to_disk: bool) -> int:
    """Main program function."""
    _setup_log()

    h = Harvester(logger=log)  # logger = None silences Harvester logs

    # Load suitable CTI files for your cam.
    # Although you can add sev. CTI files at the same time, I recommend avoiding
    # doing so. When I added both IDS and Allied Vision CTI files and connected
    # the Allied Vision cam, the program reported 2 Allied Vision Devices.
    # However, when I connected an IDS cam, adding both CTI files correctly
    # reported 1 IDS device. I suspect the issue is on the Allied Vision side,
    # also since Allied Vision has some other issues with Harvesters.
    cti_files = [
        # "/opt/Vimba_6_0/VimbaUSBTL/CTI/x86_64bit/VimbaUSBTL.cti",  # Allied VIsion
        # "/opt/ids-peak_2.1.0.0-14251_amd64/lib/ids/cti/ids_u3vgentl.cti",  # IDS
        # "/opt/SICKVisionSuite_1.1.6.10_x86_64/lib/sick/cti/sick_gevgentl.cti",
        # "/opt/pylon/lib/gentlproducer/gtl/ProducerGEV.cti",  # Basler
        # "/opt/spinnaker/lib/spinnaker-gentl/Spinnaker_GenTL.cti",  # Flir
        "/opt/baumer-gapi-sdk-cpp/lib/libbgapi2_gige.cti",
    ]
    for cti_file in cti_files:
        h.add_file(cti_file)

    h.update()

    log.info(f"{len(h.device_info_list)} devices:\n{h.device_info_list}")
    assert len(h.device_info_list) > 0, "Oh no! No device detected."

    device_info: DeviceInfo = h.device_info_list[0]
    try:
        serial_number = int(device_info.serial_number)
    except NotImplementedException:
        # AlliedVision doesn't give a serial number properly. It reports a
        # serial number against the IDS CTI file, but fails down the road.
        serial_number = "\033[1;31m***NotImplementedException***\033[0m"

    log.info(f"serial_number={serial_number}")

    if write_images_to_disk is True:
        now = dt.datetime.now().strftime("%Y-%m-%-d-%H:%M:%S")
        output_folder = Path.cwd() / f"images-{now}"
        output_folder.mkdir(exist_ok=False)

    ia: ImageAcquirer = h.create(search_key=0)
    # worked with harvesters 1.3:
    # ia: ImageAcquirer = h.create(vendor="IDS Imaging Development Systems GmbH", list_index=0)  # rather restrictive
    device: RemoteDevice = ia.remote_device
    node_map: NodeMap = device.node_map

    _print_nodes_and_values(node_map)

    # If you need to reset the device
    # node_map.DeviceReset()
    # exit(99)

    node_map.BalanceWhiteAuto = "Off"  # "Off" is default
    node_map.GainAuto = "Off"
    node_map.Gain.value = 2.0
    node_map.ExposureTime.value = 15_000
    node_map.PixelFormat.value = (
        "BGR8"  # OpenCV works in BGR; see `pfnc.py` for available formats
    )
    # node_map.PixelFormat.value = "Mono8"

    # these crop the image
    # node_map.Width.value = 480
    # node_map.Height.value = 360

    binning_value = 2
    node_map.BinningHorizontal.value = binning_value
    node_map.BinningHorizontalMode.value = "Average"
    node_map.BinningVertical.value = binning_value
    node_map.BinningVerticalMode.value = "Average"

    if use_software_trigger is True:
        node_map.TriggerMode.value = "On"
        node_map.TriggerSource.value = "Software"  # with IDS, "Software" is the default
    else:
        node_map.TriggerMode = "Off"  # streaming mode

    # TriggerActivation:
    #   - with the IDS cam, "RisingEdge" is the default and only option
    #   - with a Basler a2A1920-51gcPRO, I get an access exception, so this goes out
    # node_map.TriggerActivation.value = "RisingEdge"

    ia.start()

    start_loop = perf_counter()

    for n_frame in count(1):
        if use_software_trigger is True:
            node_map.TriggerSoftware.execute()

        start_frame: float = perf_counter()

        with ia.fetch() as buffer:
            component = buffer.payload.components[0]

            # note that the number of components can vary. If your
            # target remote device transmits a multi-part information, then
            # you'd get two or more components in the payload. However, now
            # we're working with a remote device that transmits only a 2D image.
            # So we manipulate only index 0 of the list object, components.

            img_data: np.ndarray = component.data  # 1 dimensional array
            img = img_data.reshape(component.height, component.width, 3)
            # img = img_data.reshape(component.height, component.width, 1)  # for mono colors

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
            cv2.imshow("image", img)
            cv2.setWindowTitle("image", window_title)

            if write_images_to_disk is True:
                cv2.imwrite(str(output_folder / f"img-{n_frame}.png"), img)

    ia.stop()

    # reset cam configuration to factory settings
    # node_map.UserSetSelector.value = "Default"
    # node_map.UserSetLoad.execute()
    # node_map.DeviceReset.execute()
    # node_map.DeviceResetToDeliveryState.execute()

    ia.destroy()
    h.reset()

    return 0


if __name__ == "__main__":
    sys.exit(main(use_software_trigger=True, write_images_to_disk=False))
