#!/usr/bin/env python3
"""
See:
https://github.com/nwhitehead/pyfluidsynth
"""
import time
import fluidsynth

fs = fluidsynth.Synth()
fs.start(driver="alsa")  # letting the driver set to default did not work

sfid = fs.sfload("full-grand-piano.sf2")
fs.program_select(0, sfid, 0, 0)

fs.noteon(0, 60, 30)
# fs.noteon(0, 67, 30)
# fs.noteon(0, 76, 30)

time.sleep(1.0)

fs.noteoff(0, 60)
# fs.noteoff(0, 67)
# fs.noteoff(0, 76)

time.sleep(1.0)

fs.delete()
