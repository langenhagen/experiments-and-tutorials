# IDS Camera Hello
A proof of concept for making the interaction with an IDS camera.


Disclaimer: I could not connect the IDS cam via the package `ids_peak` since `ids_peak` did not
discover the device. The according C++ implementation works fine, however. I have no explanation for
the issue.


Find the IDS SDK prerequisites here:
https://en.ids-imaging.com/download-details/AB12776.html?os=linux&version=&bus=64&floatcalc=

Specifically: `ids-peak-linux-x86-2.1.0.0-64.tgz`


First, unpack.
Then follow the SDKs README under `share/doc/ids-peak/readme.txt`.

You also need libpython to the used python version. Get it e,g. easily for your default OS via:
```
sudo apt install libpython3.10-dev
```


# IDS Peak Cockpit
A QT-based camera image viewer for reference.

Then, I could start the program, however, I did not see any output, window or log messages appear.
ran the program via:
```bash
/opt/ids-peak_2.1.0.0-14251_amd64/bin/ids_peak_cockpit
```
