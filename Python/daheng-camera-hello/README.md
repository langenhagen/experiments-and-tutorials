# Daheng Camera Hello
A proof of concept for making the interaction with the Daheng camera MER2-302-56U3C.

Find prerequisites here:
https://www.get-cameras.com/customerdownloads

Specifically:
1. https://dahengimaging.com/downloads/Galaxy_Linux%20x86_Gige%20U3_32bits%2064bits_1.3.2206.9161.tar.gz
2. https://dahengimaging.com/downloads/Galaxy_Linux_Python_2.0.2106.9041.tar_1.gz

`Galaxy_Linux%20x86_Gige%20U3_32bits%2064bits_1.3.2206.9161.tar.gz` contains the setup that installs
the `/usr/lib/libgxiapi.so`, `Galaxy_Linux_Python_2.0.2106.9041.tar_1.gz` contains the Python package.

First, unpack and run the setup script from
`Galaxy_Linux%20x86_Gige%20U3_32bits%2064bits_1.3.2206.9161.tar.gz` in order to install a required
shared library. Then, check out the script `setup.sh` and run it via `bash setup.sh --clean` in
order to build the Python package `gxipy` and create a suitable virtual env:

```bash
sudo apt-get install libffi-dev  # prerequisite for the Galaxy/Daheng stuff

wget https://dahengimaging.com/downloads/Galaxy_Linux%20x86_Gige%20U3_32bits%2064bits_1.3.2206.9161.tar.gz
tar xxf Galaxy_Linux\ x86_Gige\ U3_32bits\ 64bits_1.3.2206.9161.tar.gz
cd Galaxy_Linux-x86_Gige-U3_32bits-64bits_1.3.2206.9161/
./Galaxy_camera.run
cd ..

wget https://dahengimaging.com/downloads/Galaxy_Linux_Python_2.0.2106.9041.tar_1.gz

bash setup.sh --clean
```

I used the sample program`GxSingleCamColor.py` but modified it to use `OpenCV` to display a
continuous image stream instead of opening a separate image viewer for each image via `pillow`.


## Troubleshooting
I had issues with the tool because I tested against USB2 ports that I thought to be USB3 and because
I tested against a cable that I expected to be USB3 but that might have been USB2. I used the
following commands to troubleshoot a solution:
```bash
lsusb | grep -i daheng
lsusb -D /dev/bus/usb/004/010  # detailed info about a USB device
```


# GalaxyView Reference Application
A QT4-based camera image viewer for reference.

Requires old QT4 libraries. I installed them on Ubuntu 22.04 via:
```bash
sudo add-apt-repository ppa:ubuntuhandbook1/ppa
sudo apt install libqtgui4 libqt4-network
```

Then, I could start the program, however, I did not see any output, window or log messages appear. I
ran the program via:
```bash
cd Galaxy_Linux-x86_Gige-U3_32bits-64bits_1.3.2206.9161/Galaxy_camera/bin
./GalaxyView
```
