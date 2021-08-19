"""
PyAudio Example: Make a wire between input and output (i.e., record a
few samples and play them back immediately).

This is the callback (non-blocking) version.

Simple showcase for Input/Output at the same time.

found here:
http://people.csail.mit.edu/hubert/pyaudio/#wire-callback-example

"""
import time

import pyaudio

WIDTH = 2
CHANNELS = 2
RATE = 44100

p = pyaudio.PyAudio()


def callback(in_data, frame_count, time_info, status):
    """Very simple recording callback."""
    return in_data, pyaudio.paContinue


stream = p.open(
    format=p.get_format_from_width(WIDTH),
    channels=CHANNELS,
    rate=RATE,
    input=True,
    output=True,
    stream_callback=callback,
)

stream.start_stream()

while stream.is_active():
    pass
    # make it stop instead after 3 seconds
    # time.sleep(3)
    # stream.stop_stream()

stream.stop_stream()
stream.close()

p.terminate()
