# PyDualsense Hello

See: https://github.com/yesbotics/dualsense-controller-python?tab=readme-ov-file#hidapi-on-linux

## Preliminaries

You need the `hidapi` library installed on your system. E.g.:
```bash
sudo apt-get install libhidapi-dev
```

For use the controller in Python without root privileges add a `udev rule`.
Create a file `/etd/udev/rules.d/70-dualsense.rules` with following content:
```bash
# USB
KERNEL=="hidraw*", SUBSYSTEM=="hidraw", ATTRS{idVendor}=="054c", ATTRS{idProduct}=="0ce6", MODE="0666"
# Bluetooth
KERNEL=="hidraw*", SUBSYSTEM=="hidraw", KERNELS=="0005:054C:0CE6.*", MODE="0666"
```

Then you have to activate the rule:
```bash
sudo udevadm control --reload-rules
sudo udevadm trigger
```


## 