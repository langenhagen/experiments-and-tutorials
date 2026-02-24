"""Showcase the usage of the package synthesizer and pynput by making
a sound by hitting any key on the keyboard.

author: andreasl
"""

import logging
from contextlib import suppress

import pynput  # pylint: disable=import-error  # third-party demo dependency.
import synthesizer  # pylint: disable=import-error  # third-party demo dependency.

logger = logging.getLogger(__name__)

player = synthesizer.Player(rate=44100)
player.open_stream()
synth = synthesizer.Synthesizer(
    osc1_waveform=synthesizer.Waveform.sine,
    osc1_volume=1.0,
    use_osc2=False,
)


def on_press(key: pynput.keyboard.Key | pynput.keyboard.KeyCode | None) -> None:
    """Play a note."""
    freq_hz = hash(key) % 500 + 100.0
    duration_s = 30.0 / freq_hz
    wave = synth.generate_constant_wave(freq_hz, duration_s)
    player.play_wave(wave)


def on_release(key: pynput.keyboard.Key | pynput.keyboard.KeyCode | None) -> None:
    """Handle key release events."""
    del key
    logger.debug("Key released")


Listener = pynput.keyboard.Listener
with (
    Listener(on_press=on_press, on_release=on_release) as listener,
    suppress(KeyboardInterrupt),
):
    listener.join()
