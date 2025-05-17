#!/usr/bin/env python3
"""
Plays a pyaudio asynchronous callback that is fed with synthesizer ouput from
fluisynth when th user presses a key on the keyboard.
See:
https://github.com/nwhitehead/pyfluidsynth
https://github.com/nwhitehead/pyfluidsynth/blob/master/fluidsynth.py
"""

import fluidsynth
import numpy as np
import pyaudio
import pynput

audio = pyaudio.PyAudio()
synth = fluidsynth.Synth()
soundfont = synth.sfload("full-grand-piano.sf2")
synth.program_select(0, soundfont, 0, 0)
samples = np.append([], synth.get_samples(44100 * 1))
audio_string = fluidsynth.raw_audio_string(samples)


def pyaudio_callback(in_data, frame_count, time_info, status):
    global audio_string
    slice_size = 128 * 2 * 16
    next_slice = audio_string[:slice_size]
    audio_string = audio_string[slice_size:]
    if len(next_slice) < slice_size:
        samples = np.append([], synth.get_samples(44100 * 10))
        audio_string = fluidsynth.raw_audio_string(samples)
        next_slice = audio_string[:slice_size]
    return (next_slice, pyaudio.paContinue)


strm = audio.open(
    format=pyaudio.paInt16,
    channels=2,
    rate=44100,
    output=True,
    stream_callback=pyaudio_callback,
)
strm.start_stream()


def on_press(key) -> None:
    """Play a note."""
    if isinstance(key, pynput.keyboard.KeyCode):
        note = key.vk
        print(f"KeyCode: {note}")
    elif isinstance(key, pynput.keyboard.Key):
        note = hash(key.value) % 128
        print(f"Key: {key.value}     {note}")
    else:
        raise ValueError(f"key {key} type {type(key)} is unhandled!")

    synth.noteon(0, note, 100)

    synth_samples = np.append([], synth.get_samples(44100 * 1))
    global audio_string
    audio_string = fluidsynth.raw_audio_string(synth_samples)


def on_release(key) -> bool:
    """Always return true, i.e. keep listener running."""
    del key
    return True


Listener = pynput.keyboard.Listener
with Listener(on_press=on_press, on_release=on_release) as listener:
    listener.join()
    strm.stop_stream()
    strm.close()
    audio.terminate()
