# Kivy Hello
Showcase Kivy, an app development framework that can run on mobile devices, e.g. Android.

https://kivy.org/doc/stable/gettingstarted/installation.html

Kivy seems to have problems with Python 3.11

```bash
bash setup.sh  # builds a non-hidden venv because buildozer android build requires a non-hidden folder
source venv/bin/activate

cd venv/share/kivy-examples/tutorials/notes/final/

buildozer init


# *** connect a phone with USB debugging enabled ***

# requires the code to be under a non-hidden folder
# initially, takes ridiculuously long, ~30 minutes
time buildozer android debug deploy run
```

Deployment seems to sometimes work. However, app crashes on start for reasons yet unknown.
