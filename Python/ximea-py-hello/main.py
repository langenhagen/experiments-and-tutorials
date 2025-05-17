#!/usr/bin/env python3
"""Assert the viability of the library `ximea-py` for work with Ximea xIQ cams.

The package just repacks Python library files. However, those still call on
shared libraries that the XIMEA_LINUX_SP.tgz brings to a system. I.e.,
`ximea-py` is not a replacement for `XIMEA_LINUX_SP.tgz`.

See: https://pypi.org/project/ximea-py
"""

import datetime as dt
import logging
import sys
from math import ceil

import cv2
from ximea import xiapi

logging.basicConfig(
    format="%(asctime)s.%(msecs)d [%(levelname)s]: %(lineno)d: %(message)s",
    datefmt="%H:%M:%S",
    level=logging.DEBUG,
)
log = logging.getLogger(__name__)


def main() -> None:
    """Connect and trigger a ximea camera and show the image."""

    n_devices = xiapi.Camera().get_number_devices()
    print(f"{n_devices=}")

    device_id = 0

    cam = xiapi.Camera(device_id)
    cam.open_device()

    # cam.set_debug_level("XI_DL_DISABLED")

    cam_serial_number = cam.get_param("device_sn").decode("utf8")
    print(f"{cam_serial_number=}")

    gain = cam.get_gain()
    exposure = cam.get_exposure()
    print(f"{gain=}")
    print(f"{exposure=}")

    cam.set_gain(1.0)
    cam.set_exposure(15000)

    print(f"{gain=}")
    print(f"{exposure=}")

    cam.set_imgdataformat("XI_RGB24")
    cam.set_trigger_source("XI_TRG_SOFTWARE")
    cam.enable_recent_frame()

    cam.start_acquisition()

    n_frame = 0
    keep_grabbing = True
    while keep_grabbing is True:
        start_frame = dt.datetime.now()

        cam.set_trigger_software(1)
        img = xiapi.Image()
        cam.get_image(img)
        np_img = img.get_image_data_numpy()

        end_frame = dt.datetime.now()

        duration_ms: int = (end_frame - start_frame).total_seconds() * 1000
        fps = round(1000.0 / duration_ms, 1)
        n_frame += 1

        log.debug(f"frame # {n_frame}, took {ceil(duration_ms)}ms; net {fps} fps")

        key = cv2.waitKey(1)
        if key == ord("q"):
            keep_grabbing = False
        elif key == 82:  # up key
            pass
        elif key == 84:  # down key
            pass

        cv2.imshow("image", np_img)

    cam.stop_acquisition()
    cam.close_device()


if __name__ == "__main__":
    sys.exit(main())
