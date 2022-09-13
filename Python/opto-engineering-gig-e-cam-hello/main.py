""" Showcase the image grabbing from a Opto Engineering Gig E camera.

Based on the product's sample under `/opt/MVS/Samples/64/Python/GrabImage/`.
"""
import datetime as dt
import logging
import os
import sys
import termios
from ctypes import POINTER, byref, c_ubyte, cast, memset, sizeof
from ipaddress import IPv4Address
from math import ceil

import cv2
import numpy as np
from MvCameraControl_class import *

logging.basicConfig(
    format="%(asctime)s.%(msecs)d [%(levelname)s]: %(lineno)d: %(message)s",
    datefmt="%H:%M:%S",
    level=logging.DEBUG,
)
log = logging.getLogger(__name__)


def press_any_key_exit():
    fd = sys.stdin.fileno()
    old_ttyinfo = termios.tcgetattr(fd)
    new_ttyinfo = old_ttyinfo[:]
    new_ttyinfo[3] &= ~termios.ICANON
    new_ttyinfo[3] &= ~termios.ECHO
    # sys.stdout.write(msg)
    # sys.stdout.flush()
    termios.tcsetattr(fd, termios.TCSANOW, new_ttyinfo)
    try:
        os.read(fd, 7)
    except:
        pass
    finally:
        termios.tcsetattr(fd, termios.TCSANOW, old_ttyinfo)


def main() -> int:
    """Main program function."""
    SDKVersion = MvCamera.MV_CC_GetSDKVersion()
    log.info(f"SDKVersion={SDKVersion}  SDKVersion[0x{SDKVersion:x}]")

    devices = MV_CC_DEVICE_INFO_LIST()
    device_type: int = MV_GIGE_DEVICE | MV_USB_DEVICE  # bitmask
    res: int = MvCamera.MV_CC_EnumDevices(device_type, devices)
    if res != 0:
        log.error(f"MV_CC_EnumDevices() failed! Error code: {res}")
        return res

    n_connected_devices = devices.nDeviceNum
    log.debug(f"number of connected_devices: {n_connected_devices}")
    if n_connected_devices == 0:
        log.error("Found no connected devices")
        return 1

    for device_index in range(n_connected_devices):
        device_info: MV_CC_DEVICE_INFO = cast(
            devices.pDeviceInfo[device_index], POINTER(MV_CC_DEVICE_INFO)
        ).contents
        if device_info.nTLayerType == MV_GIGE_DEVICE:
            gig_e_info: MV_CC_DEVICE_INFO = device_info.SpecialInfo.stGigEInfo
            model_name = "".join([chr(c) for c in gig_e_info.chModelName])
            device_ip_addr = IPv4Address(gig_e_info.nCurrentIp)
            log.info(f"GigE device: {device_index}  {model_name} @ {device_ip_addr}")

    selected_device_index = 0
    # selected_device_index = int(
    #     input("Please input the 0-indexed number of the device to connect to: ")
    # )

    if selected_device_index >= n_connected_devices:
        log.error("Entered a number outside of the range!")
        return 2

    cam = MvCamera()

    cam_info = cast(
        devices.pDeviceInfo[selected_device_index], POINTER(MV_CC_DEVICE_INFO)
    ).contents

    res: int = cam.MV_CC_CreateHandle(cam_info)
    if res != 0:
        log.error(f"MV_CC_CreateHandle() failed! Error code: {res}")
        return res

    res: int = cam.MV_CC_OpenDevice(MV_ACCESS_Exclusive, 0)
    if res != 0:
        log.error(f"MV_CC_OpenDevice() failed! Error code: {res}")
        return res

    desired_packet_size: int = cam.MV_CC_GetOptimalPacketSize()
    if desired_packet_size <= 0:
        log.warning(
            f"MV_CC_GetOptimalPacketSize() failed! Error code: {desired_packet_size}"
        )
    else:
        res: int = cam.MV_CC_SetIntValue("GevSCPSPacketSize", desired_packet_size)
        if res != 0:
            log.warning(f"Setting GevSCPSPacketSize failed! Error code: {res}")

    res: int = cam.MV_CC_SetEnumValue("TriggerMode", MV_TRIGGER_MODE_OFF)
    if res != 0:
        log.error(f"Setting TriggerMode failed! Error code: {res}")
        return res

    res: int = cam.MV_CC_SetEnumValue("ExposureAuto", MV_EXPOSURE_AUTO_MODE_OFF)
    if res != 0:
        log.error(f"Setting ExposureAuto failed! Error code: {res}")
        return res

    exposure: float = 10000.0
    res: int = cam.MV_CC_SetFloatValue("ExposureTime", exposure)
    if res != 0:
        log.error(f"Setting ExposureTime failed! Error code: {res}")
        return res

    mvcc_int_value = MVCC_INTVALUE()
    memset(byref(mvcc_int_value), 0, sizeof(MVCC_INTVALUE))
    res: int = cam.MV_CC_GetIntValue("PayloadSize", mvcc_int_value)
    if res != 0:
        log.error("Getting PayloadSize failed! Error code: {res}")
        return res

    payload_size = mvcc_int_value.nCurValue

    res: int = cam.MV_CC_StartGrabbing()
    if res != 0:
        log.error("MV_CC_StartGrabbing() failed! Error code: {res}")
        return res

    data_buf = (c_ubyte * payload_size)()  # ubyte array
    frame_info = MV_FRAME_OUT_INFO_EX()
    memset(byref(frame_info), 0, sizeof(frame_info))

    log.info("Press `q` to stop grabbing.")


    start = dt.datetime.now()
    grab_timeout_millis: int = 1000
    keep_grabbing = True
    while keep_grabbing is True:
        start_frame = dt.datetime.now()
        res: int = cam.MV_CC_GetOneFrameTimeout(
            data_buf,  # out ref
            payload_size,
            frame_info,  # out ref
            grab_timeout_millis,
        )
        if res != 0:
            log.warning("MV_CC_GetOneFrameTimeout() failed! Error code: {res}")
            continue

        arr: np.ndarray = np.asarray(data_buf, dtype=np.uint8)
        image = np.ndarray(
            shape=(frame_info.nHeight, frame_info.nWidth), dtype=np.uint8, buffer=arr
        )
        end_frame = dt.datetime.now()
        duration_ms: int = (end_frame - start_frame).total_seconds() * 1000
        fps = round(1000.0 / duration_ms, 1)
        total_duration_ms: int = (end_frame - start).total_seconds() * 1000
        net_fps = round(1000.0 / total_duration_ms * (frame_info.nFrameNum + 1), 1)

        log.debug(
            f"frame number {frame_info.nFrameNum}, took {ceil(duration_ms)}ms; net {fps} fps; total gross fps {net_fps}"
        )
        # log.debug(
        #     f"frame_info ({frame_info.nFrameNum}):"
        #     f"\n  enPixelType={frame_info.enPixelType}"
        #     f"\n  fExposureTime={frame_info.fExposureTime}"
        #     f"\n  fGain={frame_info.fGain}"
        #     f"\n  nAverageBrightness={frame_info.nAverageBrightness}"
        #     f"\n  nBlue={frame_info.nBlue}"
        #     f"\n  nChunkHeight={frame_info.nChunkHeight}"
        #     f"\n  nChunkWidth={frame_info.nChunkWidth}"
        #     f"\n  nCycleCount={frame_info.nCycleCount}"
        #     f"\n  nCycleOffset={frame_info.nCycleOffset}"
        #     f"\n  nDevTimeStampHigh={frame_info.nDevTimeStampHigh}"
        #     f"\n  nDevTimeStampLow={frame_info.nDevTimeStampLow}"
        #     f"\n  nFrameCounter={frame_info.nFrameCounter}"
        #     f"\n  nFrameLen={frame_info.nFrameLen}"
        #     f"\n  nFrameNum={frame_info.nFrameNum}"
        #     f"\n  nGreen={frame_info.nGreen}"
        #     f"\n  nHeight={frame_info.nHeight}"
        #     f"\n  nHostTimeStamp={frame_info.nHostTimeStamp}"
        #     f"\n  nInput={frame_info.nInput}"
        #     f"\n  nLostPacket={frame_info.nLostPacket}"
        #     f"\n  nOffsetX={frame_info.nOffsetX}"
        #     f"\n  nOffsetY={frame_info.nOffsetY}"
        #     f"\n  nOutput={frame_info.nOutput}"
        #     f"\n  nRed={frame_info.nRed}"
        #     f"\n  nReserved={frame_info.nReserved}"
        #     f"\n  nReserved0={frame_info.nReserved0}"
        #     f"\n  nSecondCount={frame_info.nSecondCount}"
        #     f"\n  nTriggerIndex={frame_info.nTriggerIndex}"
        #     f"\n  nWidth={frame_info.nWidth}"
        # )

        key = cv2.waitKey(1)
        if key == ord("q"):
            keep_grabbing = False
        elif key == 82:  # up key
            pass
        elif key == 84:  # down key
            pass

        cv2.imshow("image", image)

    del data_buf

    res: int = cam.MV_CC_StopGrabbing()
    if res != 0:
        log.error(f"MV_CC_StopGrabbing() failed! Error code: {res}")
        return res

    res: int = cam.MV_CC_CloseDevice()
    if res != 0:
        log.error(f"MV_CC_CloseDevice() failed! Error code: {res}")
        return res

    res: int = cam.MV_CC_DestroyHandle()
    if res != 0:
        log.error(f"MV_CC_DestroyHandle() failed! Error code: {res}")
        return res

    return 0


if __name__ == "__main__":
    sys.exit(main())
