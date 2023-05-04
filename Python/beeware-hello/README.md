# Beeware Hello
Showcase of Beeware, a toolkit for cross-platform Python GUI apps, including Android and Web apps.

Beeware is nicely easy to use, simple and fast.


Sadly, crashes on my Samsung Galaxy S8, Android 9. I only see a splash screen, then the app dies.
Tested with both Python 3.7 and Python 3.11.


See: https://docs.beeware.org/en/latest/tutorial/tutorial-0.html


```bash
bash setup.sh
source .venv/bin/activate.fish

briefcase new  # interactively set up the app

cd helloworld

briefcase dev  # start the project in developer mode

briefcase create  # package for distribution for the host OS

briefcase update  # update the package

briefcase create android  # package for android debugging

briefcase run android  # run the stuff in an emulator

briefcase package android  # package for android distribution - creates an AAB file
```

Don't know if the following is necessary or whether the debug APK will suffice. Both APKs crash after seeing a splash screen

To make APK files from AAB files, get `Bundletool` from https://github.com/google/bundletool/releases.
This can create an `.apks` file which will contain the `.apk` file. Do e.g.:
```bash
cd ..
java -jar bundletool-all-1.14.1.jar build-apks --bundle='helloworld/dist/Hello World-0.0.1.aab' --output=hello_world_release.apks --mode=universal
```
