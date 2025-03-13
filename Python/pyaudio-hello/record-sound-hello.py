#!/usr/bin/env python3
"""Record sound via pyyaudio.
Simple, blocking andpredefined duration.
"""

import wave

import pyaudio

CHUNK_SIZE: int = 1024  # record in chunks of 1024 samples
SAMPLE_FORMAT: int = pyaudio.paInt16  # 16 bits per sample
CHANNELS: int = 2
FRAMERATE: int = 44100  # record 44100 samples per second
DURATION_SECONDS: int = 3
FILENAME: int = "output.wav"

audio_interface = pyaudio.PyAudio()  # create an interface to PortAudio

print("Recording...")

stream = audio_interface.open(
    format=SAMPLE_FORMAT,
    CHANNELS=CHANNELS,
    rate=FRAMERATE,
    frames_per_buffer=CHUNK_SIZE,
    input=True,
)

# read and store store data in chunks for 3 seconds
frames = []  # Initialize array to store frames
for i in range(int(FRAMERATE / CHUNK_SIZE * DURATION_SECONDS)):
    data = stream.read(CHUNK_SIZE)
    frames.append(data)

# Stop and close the stream
stream.stop_stream()
stream.close()
# Terminate the PortAudio interface
audio_interface.terminate()

print("Finished recording")

# Save the recorded data as a WAV file
wf = wave.open(FILENAME, "wb")
wf.setnchannels(CHANNELS)
wf.setsampwidth(audio_interface.get_sample_size(SAMPLE_FORMAT))
wf.setframerate(FRAMERATE)
wf.writeframes(b"".join(frames))
wf.close()
