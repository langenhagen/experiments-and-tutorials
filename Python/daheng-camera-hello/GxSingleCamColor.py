"""Modified Daheng camera MER2-302-56U3C sample program.

Based on `GxSingleCamColor.py` version:1.0.1905.9051

author: andreasl
"""
import traceback

import cv2
import gxipy as gx
import numpy as np


def main():
    # print the demo information
    print("")
    print("-------------------------------------------------------------")
    print(
        "Modified sample to show how to acquire color image continuously and show image."
    )
    print("-------------------------------------------------------------")
    print("")
    print("Initializing......")
    print("")

    # create a device manager
    device_manager = gx.DeviceManager()
    dev_num, dev_info_list = device_manager.update_device_list()
    if dev_num is 0:
        print("Number of enumerated devices is 0")
        return

    print(f"{dev_info_list=}\n")

    # open the first device
    cam = device_manager.open_device_by_index(1)

    # exit when the camera is a mono camera
    if cam.PixelColorFilter.is_implemented() is False:
        print("This sample does not support mono camera.")
        cam.close_device()
        return

    # set continuous acquisition
    cam.TriggerMode.set(gx.GxSwitchEntry.OFF)

    # set exposure
    cam.ExposureTime.set(10000.0)

    # set gain
    cam.Gain.set(10.0)

    # get param of improving image quality
    if cam.GammaParam.is_readable():
        gamma_value = cam.GammaParam.get()
        gamma_lut = gx.Utility.get_gamma_lut(gamma_value)
    else:
        gamma_lut = None
    if cam.ContrastParam.is_readable():
        contrast_value = cam.ContrastParam.get()
        contrast_lut = gx.Utility.get_contrast_lut(contrast_value)
    else:
        contrast_lut = None
    if cam.ColorCorrectionParam.is_readable():
        color_correction_param = cam.ColorCorrectionParam.get()
    else:
        color_correction_param = 0
    if cam.BalanceWhiteAuto.is_readable():
        balance_white_auto = cam.BalanceWhiteAuto.get()
    else:
        print("eh, BalanceWhiteAuto not readable")

    if cam.BalanceWhiteAuto.is_writable():
        # cam.BalanceWhiteAuto.set(gx.GxAutoEntry.CONTINUOUS)
        cam.BalanceWhiteAuto.set(gx.GxAutoEntry.OFF)
    else:
        print("eh, BalanceWhiteAuto not writable")

    print(f"{gamma_value=}\n{gamma_lut=}\n{contrast_value=}\n{contrast_lut=}")
    print(f"{color_correction_param=}\n{balance_white_auto=}\n")

    # start data acquisition
    cam.stream_on()

    # acquisition image: num is the image number
    keep_running = True
    while keep_running is True:
        # get raw image
        raw_image: gx.gxiapi.RawImage = cam.data_stream[0].get_image()
        if raw_image is None:
            print("Getting image failed.")
            continue

        # get RGB image from raw image
        rgb_image: gx.gxiapi.RGBImage = raw_image.convert("RGB")
        if rgb_image is None:
            print("rgb_image is None")
            continue

        # improve image quality
        rgb_image.image_improvement(color_correction_param, contrast_lut, gamma_lut)

        # create numpy array with data from raw image
        numpy_image: np.ndarray = rgb_image.get_numpy_array()
        if numpy_image is None:
            print("numpy_image is None")
            continue

        key = cv2.waitKey(1)
        if key == ord("q"):
            keep_running = False
        elif key == 82:  # up key
            color_correction_param += 1
            print(f"{color_correction_param=}")
            pass
        elif key == 84:  # down key
            color_correction_param -= 1
            print(f"{color_correction_param=}")
            pass

        # convert BGR to RGB bc OpenCV deals in BGR
        image: cv2.Mat = cv2.cvtColor(numpy_image, cv2.COLOR_BGR2RGB)
        # image = cv2.resize(image, (1024, 768))

        # show acquired image
        cv2.imshow("image", image)

        # print height, width, and frame ID of the acquisition image
        print(
            f"Frame ID: {raw_image.get_frame_id()}   "
            f"Height: {raw_image.get_height()}   "
            f"Width: {raw_image.get_width()}"
        )

    # stop data acquisition
    cam.stream_off()

    # close device
    cam.close_device()


if __name__ == "__main__":
    main()
