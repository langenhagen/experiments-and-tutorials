# Gig-E Camera Hello
A proof of concept for making the interaction with the Opto Engineering Gig-E camera.

Get the MVS SDK files from a closed source.

Inspect and run `bash setup.sh` to get an automated setup.

Inspect and run `harvesters-gui-hello.py` to see the image. Basically taken from the
`harvesters_gui` website.


## The Cam
- The cam captures monochrome images.
- The cam gets power over ethernet, e.g. via a switch of that supports power over ethernet.
- I set up the LAN to be `manual` and set my IP to `192.168.100.101`. with a Netmask `24` aka
  `255.255.255.0`.
- The cam's IP was `192.168.100.42`. Thus, `ping 192.168.100.42` should work. I could configure the
  IP via the `MVS Viewer` app


## MVS Viewer
A camera image viewer application for reference. Run the setup, then call:

```bash
/opt/MVS/bin/MVS.sh
```

## Misc
- the package `harvesters` seems to require Python >=3.7.
- the package `harvesters_gui` seems to be incompatible with Python 3.9 onwards.
- https://en.wikipedia.org/wiki/GigE_Vision
- harvesters is Apache 2 license
- https://github.com/genicam/harvesters/blob/master/docs/TUTORIAL.rst
- the Opto Engineering cam does apparently not come with a `*.cti` file. Thus, I took `*.cti` file
  from Matrix vision as the `harvesters` doc suggests.
  https://github.com/genicam/harvesters/blob/83b77747bdd4024fb1f7ee1bd54d95ac08339fe2/docs/INSTALL.rst#installing-a-gentl-producer
