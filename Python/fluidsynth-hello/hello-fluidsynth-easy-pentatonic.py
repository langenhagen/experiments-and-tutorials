#!/usr/bin/env python3
"""Plays a sound according to a random pentatonic scale for key press.
See:
https://github.com/nwhitehead/pyfluidsynth
"""
import math
import random

import fluidsynth
import pynput

synth = fluidsynth.Synth()
synth.start(driver="alsa")
soundfont = synth.sfload("full-grand-piano.sf2")
synth.program_select(0, soundfont, 0, 0)

note_names = [
    "C",
    "C#",
    "D",
    "D#",
    "E",
    "F",
    "F#",
    "G",
    "G#",
    "A",
    "A#",
    "B",
]
pentatonic_offsets = [
    0,
    2,
    4,
    7,
    9,
]
key_offset = random.randrange(0, 11)
counter = 0


def on_press(key):
    """Play a note."""
    if isinstance(key, pynput.keyboard.KeyCode):
        note = key.vk % 72 + 24
    elif isinstance(key, pynput.keyboard.Key):
        note = hash(key.value) % 96
    else:
        raise ValueError(f"key {key} type {type(key)} is unhandled!")

    global counter
    if counter == 0:
        global key_offset
        key_offset = random.randrange(0, 11)
        print(f"New Key: {note_names[key_offset]}")
    counter = (counter + 1) % 48

    octave_offset = note // 10 * 12
    pentatonic_offset = pentatonic_offsets[note % len(pentatonic_offsets)]
    note = octave_offset + key_offset + pentatonic_offset
    note_name = note_names[(octave_offset + key_offset + pentatonic_offset) % 12]
    print(f"{note_name}_{octave_offset // 12}  ({note})")
    synth.noteon(0, note, 100)


def on_release(key):
    """Always return true, i.e. keep listener running."""
    del key
    return True


Listener = pynput.keyboard.Listener
with Listener(on_press=on_press, on_release=on_release) as listener:
    listener.join()
