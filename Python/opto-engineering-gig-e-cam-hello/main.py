""" Showcase the image grabbing from a Opto Engineering Gig E camera.

Based on the product's sample under `/opt/MVS/Samples/64/Python/GrabImage/`.
"""
from ctypes import cast, POINTER
import logging
import os
import sys
import termios
import threading
import ipaddress

from MvCameraControl_class import *

logging.basicConfig(
    # filename='myfile.log',  # reroutes the default logging stream to file instead of to stdout
    format="%(asctime)s [%(levelname)s]: %(lineno)d: %(message)s",
    datefmt="%H:%M:%S",
    level=logging.DEBUG,
)
logger = logging.getLogger(__name__)


g_bExit = False


def work_thread(cam=0, pData=0, nDataSize=0):
    stFrameInfo = MV_FRAME_OUT_INFO_EX()
    memset(byref(stFrameInfo), 0, sizeof(stFrameInfo))
    while True:
        ret = cam.MV_CC_GetOneFrameTimeout(pData, nDataSize, stFrameInfo, 1000)
        if ret == 0:
            print(
                "get one frame: Width[%d], Height[%d], PixelType[0x%x], nFrameNum[%d]"
                % (
                    stFrameInfo.nWidth,
                    stFrameInfo.nHeight,
                    stFrameInfo.enPixelType,
                    stFrameInfo.nFrameNum,
                )
            )
        else:
            print("no data[0x%x]" % ret)
        if g_bExit == True:
            break


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
    logger.info(f"SDKVersion={SDKVersion}  SDKVersion[0x{SDKVersion:x}]")

    devices = MV_CC_DEVICE_INFO_LIST()
    device_type: int = MV_GIGE_DEVICE | MV_USB_DEVICE  # bitmask
    res: int = MvCamera.MV_CC_EnumDevices(device_type, devices)
    if res != 0:
        logger.error(f"MV_CC_EnumDevices() failed! Error code: {res}")
        return res

    n_connected_devices = devices.nDeviceNum
    logger.debug(f"number of connected_devices: {n_connected_devices}")
    if n_connected_devices == 0:
        logger.error("Found no connected devices")
        return 1

    for device_index in range(n_connected_devices):
        device_info = cast(
            devices.pDeviceInfo[device_index], POINTER(MV_CC_DEVICE_INFO)
        ).contents
        if device_info.nTLayerType == MV_GIGE_DEVICE:
            logger.info(f"GigE device: {device_index}")

            mode_name = "".join(
                [chr(c) for c in device_info.SpecialInfo.stGigEInfo.chModelName]
            )
            logger.info("Device model name: %s", mode_name)

            device_ip_addr = ipaddress.IPv4Address(
                device_info.SpecialInfo.stGigEInfo.nCurrentIp
            )
            logger.info(f"Device IP address: {device_ip_addr}")

    selected_device_index = int(
        input("please input the 0-indexed number of the device to connect: ")
    )

    if selected_device_index >= n_connected_devices:
        logger.error("Entered a number outside of the range!")
        return 2

    cam = MvCamera()

    cam_info = cast(
        devices.pDeviceInfo[selected_device_index], POINTER(MV_CC_DEVICE_INFO)
    ).contents

    res = cam.MV_CC_CreateHandle(cam_info)
    if res != 0:
        logger.error(f"MV_CC_CreateHandle() failed! Error code: {res}")
        return res

    res = cam.MV_CC_OpenDevice(MV_ACCESS_Exclusive, 0)
    if res != 0:
        logger.error(f"MV_CC_OpenDevice() failed! Error code: {res}")
        return res

    if cam_info.nTLayerType == MV_GIGE_DEVICE:
        desired_packet_size: int = cam.MV_CC_GetOptimalPacketSize()
        if desired_packet_size <= 0:
            logger.warning(f"MV_CC_GetOptimalPacketSize() failed! Error code: {desired_packet_size}")
        else:
            res = cam.MV_CC_SetIntValue("GevSCPSPacketSize", desired_packet_size)
            if res != 0:
                logger.warning(f"Setting GevSCPSPacketSize failed! Error code: {res}")

    # TODO here

    # ch:设置触发模式为off | en:Set trigger mode as off
    res = cam.MV_CC_SetEnumValue("TriggerMode", MV_TRIGGER_MODE_OFF)
    if res != 0:
        print("set trigger mode fail! ret[0x%x]" % res)
        sys.exit()

    # ch:获取数据包大小 | en:Get payload size
    stParam = MVCC_INTVALUE()
    memset(byref(stParam), 0, sizeof(MVCC_INTVALUE))

    res = cam.MV_CC_GetIntValue("PayloadSize", stParam)
    if res != 0:
        print("get payload size fail! ret[0x%x]" % res)
        sys.exit()
    nPayloadSize = stParam.nCurValue

    # ch:开始取流 | en:Start grab image
    res = cam.MV_CC_StartGrabbing()
    if res != 0:
        print("start grabbing fail! ret[0x%x]" % res)
        sys.exit()

    data_buf = (c_ubyte * nPayloadSize)()

    try:
        hThreadHandle = threading.Thread(
            target=work_thread, args=(cam, byref(data_buf), nPayloadSize)
        )
        hThreadHandle.start()
    except:
        print("error: unable to start thread")

    print("press a key to stop grabbing.")
    press_any_key_exit()

    g_bExit = True
    hThreadHandle.join()

    # ch:停止取流 | en:Stop grab image
    res = cam.MV_CC_StopGrabbing()
    if res != 0:
        print("stop grabbing fail! ret[0x%x]" % res)
        del data_buf
        sys.exit()

    # ch:关闭设备 | Close device
    res = cam.MV_CC_CloseDevice()
    if res != 0:
        print("close deivce fail! ret[0x%x]" % res)
        del data_buf
        sys.exit()

    # ch:销毁句柄 | Destroy handle
    res = cam.MV_CC_DestroyHandle()
    if res != 0:
        print("destroy handle fail! ret[0x%x]" % res)
        del data_buf
        sys.exit()

    del data_buf


if __name__ == "__main__":
    sys.exit(main())
