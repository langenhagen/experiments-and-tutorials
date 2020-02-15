#!/usr/env/bin python
# author: andreasl
"""Showcase the usage of the package synthesizer and pynput by making
a sound by hitting any key on the keyboard.
"""
import pynput
import synthesizer

player = synthesizer.Player(rate=44100)
player.open_stream()
synth = synthesizer.Synthesizer(
    osc1_waveform=synthesizer.Waveform.sine,
    osc1_volume=1.0,
    use_osc2=False)


def on_press(key):
    """Play a note."""
    freq_Hz = hash(key) % 500 + 100.0
    duration_s = 30.0 / freq_Hz
    wave = synth.generate_constant_wave(freq_Hz, duration_s)
    player.play_wave(wave)


def on_release(key):
    """Always return true, i.e. keep listener running."""
    del key
    return True


Listener = pynput.keyboard.Listener
with Listener(on_press=on_press, on_release=on_release) as listener:
    listener.join()
