"""Showcase the usage of the package synthesizer and pynput by making
a sound by moving the mouse.

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


def on_move(x: float, y: float) -> None:
    """Play a note."""
    freq_hz = x + y + 1.0
    duration_s = 30.0 / freq_hz
    wave = synth.generate_constant_wave(freq_hz, duration_s)
    player.play_wave(wave)


def on_click(
    x: float,
    y: float,
    button: pynput.mouse.Button,
    pressed: bool,  # noqa: FBT001 - callback signature is defined by pynput.
) -> None:
    """Print the click action and coordinates."""
    logger.info("%s %s at %s", button, "pressed" if pressed else "released", (x, y))


def on_scroll(x: float, y: float, dx: float, dy: float) -> None:
    """Print the scroll delta and coordinates."""
    logger.info("Scrolled %s", (x, y, dx, dy))


with (
    pynput.mouse.Listener(
        on_move=on_move,
        on_click=on_click,
        on_scroll=on_scroll,
    ) as listener,
    suppress(KeyboardInterrupt),
):
    listener.join()
