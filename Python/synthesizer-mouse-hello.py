#!/usr/bin/env python
# author: andreasl
"""Showcase the usage of the package synthesizer and pynput by making
a sound by moving the mouse.
"""
import pynput
import synthesizer

player = synthesizer.Player(rate=44100)
player.open_stream()
synth = synthesizer.Synthesizer(
    osc1_waveform=synthesizer.Waveform.sine,
    osc1_volume=1.0,
    use_osc2=False)


def on_move(x, y):
    """Play a note."""
    freq_Hz = x + y + 1.0
    duration_s = 30.0 / freq_Hz
    wave = synth.generate_constant_wave(freq_Hz, duration_s)
    player.play_wave(wave)


def on_click(x, y, button, pressed):
    print(f"{button} {'pressed' if pressed else 'released'} at {(x,y)}")
    # if not pressed:
    #     # Stop listener
    #     return False


def on_scroll(x, y, dx, dy):
    print(f"Scrolled {(x, y, dx, dy)}")


with pynput.mouse.Listener(
        on_move=on_move,
        on_click=on_click,
        on_scroll=on_scroll) as listener:
    listener.join()
