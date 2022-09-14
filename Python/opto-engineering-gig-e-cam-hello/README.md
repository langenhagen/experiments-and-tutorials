# Opto Engineering Gig-E Camera Hello
A proof of concept for making the interaction with the Opto Engineering Gig-E camera via their
shipped library.

Check out the `.envrc` file.  
Inspect and run `bash setup.sh --first` to get an initial automated setup.  
Inspect and run `main.py` to see the image.  

Get the MVS SDK files from a closed source.

## The Cam
- The cam captures monochrome images.
- The cam gets power over ethernet, e.g. via a switch of that supports power over ethernet.
- I set up the LAN to be `manual` and set my IP to `192.168.100.101`. with a Netmask `24` aka
  `255.255.255.0`.
- The cam's IP was `192.168.100.42`. Thus, `ping 192.168.100.42` should work. I could configure the
  IP via the `MVS Viewer` app.


## MVS Viewer
A camera image viewer application for reference. Run the setup, then call:
```bash
/opt/MVS/bin/MVS.sh
```


## Misc
- https://en.wikipedia.org/wiki/GigE_Vision
