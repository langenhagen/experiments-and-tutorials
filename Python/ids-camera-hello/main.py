#!/usr/bin/env python3
"""Showcase the image grabbing from an IDS Camera.

Based on the product's sample under
`/opt/ids-peak_2.1.0.0-14251_amd64/local/src/ids/samples/peak/python/simple_live_qtwidgets/`.
"""

import logging
import sys
from typing import Any

from ids_peak import ids_peak

logging.basicConfig(
    format="%(asctime)s.%(msecs)d [%(levelname)s]: %(lineno)d: %(message)s",
    datefmt="%T",
    level=logging.DEBUG,
)
log = logging.getLogger(__name__)


def _get_device() -> Any:
    """Open the first IDS Camera or fail in case no camera could be found."""

    device_manager = ids_peak.DeviceManager.Instance()
    device_manager.Update()
    assert device_manager.Devices().empty() is False, "Oh no! No device found!"

    device: Any = None

    for device in device_manager.Devices():
        if device.IsOpenable():
            device = device.OpenDevice(ids_peak.DeviceAccessType_Control)
            break

    assert device is not None, "Oh no! Device is None!"

    return device


def _something_something_datastream(device: Any) -> Any:
    """TODO doc"""

    # open standard data stream
    datastreams = device.DataStreams()
    assert datastreams.empty() is False, "Oh no! Device has no datastream!"

    datastream = datastreams[0].OpenDataStream()

    # get nodemap of the remote device for all accesses to the genicam nodemap tree
    nodemap_remote_device = device.RemoteDevice().NodeMaps()[0]

    # To prepare for untriggered continuous image acquisition, load the default
    # user set if available and wait until execution is finished
    try:
        nodemap_remote_device.FindNode("UserSetSelector").SetCurrentEntry("Default")
        nodemap_remote_device.FindNode("UserSetLoad").Execute()
        nodemap_remote_device.FindNode("UserSetLoad").WaitUntilDone()
    except ids_peak.Exception:
        # Userset is not available
        log.exception("Oh no! What's this?")

    # get the payload size for correct buffer allocation
    payload_size = nodemap_remote_device.FindNode("PayloadSize").Value()
    log.debug("payload_size=%d", payload_size)

    # get minimum number of buffers that must be announced
    buffer_count_max = datastream.NumBuffersAnnouncedMinRequired()
    log.debug("buffer_count_max=%d", buffer_count_max)

    # allocate and announce image buffers and queue them
    for i in range(buffer_count_max):
        buffer = datastream.AllocAndAnnounceBuffer(payload_size)
        datastream.QueueBuffer(buffer)


def _start_aquisition():
    """TODO doc"""


def main():
    """Main program function."""

    ids_peak.Library.Initialize()

    device = _get_device()
    datastream = _something_something_datastream(device)

    _start_aquisition()


if __name__ == "__main__":
    sys.exit(main())
