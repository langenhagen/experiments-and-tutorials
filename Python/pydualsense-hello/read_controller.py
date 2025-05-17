#!/usr/bin/env python3
from contextlib import suppress

from pydualsense import pydualsense


def cross_down(state) -> None:
    print(f"cross {state}")


def circle_down(state) -> None:
    print(f"circle {state}")


def dpad_down(state) -> None:
    print(f"dpad {state}")


def joystick(stateX, stateY) -> None:
    print(f"lj {stateX} {stateY}")


def gyro_changed(pitch, yaw, roll) -> None:
    print(f"{roll}")
    print(f"{pitch}")
    print(f"{yaw}")
    print(f"{pitch}, {yaw}, {roll}")


# create dualsense
dualsense = pydualsense()
# find device and initialize
dualsense.init()

# add events handler functions
# dualsense.cross_pressed += cross_down
# dualsense.circle_pressed += circle_down
# dualsense.dpad_down += dpad_down
# dualsense.left_joystick_changed += joystick
dualsense.gyro_changed += gyro_changed

# read controller state until R1 is pressed
with suppress(KeyboardInterrupt):
    while True:
        ...

# close device
dualsense.close()
