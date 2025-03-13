#!/usr/bin/env python3
"""Plays a sound according to a random pentatonic scale for key press.
See:
https://github.com/nwhitehead/pyfluidsynth
"""
import math
import random
import sys
import termios
import tty

import fluidsynth


def getch() -> str:
    """Get a single pressed character."""
    file_descriptor = sys.stdin.fileno()
    old_settings = termios.tcgetattr(file_descriptor)
    try:
        tty.setraw(sys.stdin.fileno())
        char = sys.stdin.read(1)

    finally:
        termios.tcsetattr(file_descriptor, termios.TCSADRAIN, old_settings)
    return char


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


def play_note():
    char = getch()  # blocks nicely
    note = ord(char) % 72 + 24

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


while True:
    play_note()
