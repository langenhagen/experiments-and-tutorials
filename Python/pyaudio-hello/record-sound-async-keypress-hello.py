"""
Record sound via pyyaudio on keypress.
"""
import functools
import time
import wave

import pyaudio
import pynput

KeyboardListener = pynput.keyboard.Listener


class AudioRecorder:
    """An audio recorder that records as long as you press a button."""
    SAMPLE_FORMAT: int = pyaudio.paInt16  # 16 bits per sample

    def __init__(
            self,
            channels: int = 1,
            chunk_size: int = 1024,
            framerate: int = 44100):
        """Initialize the audio recorder."""
        self.audio_interface = pyaudio.PyAudio()
        self.do_record: bool = False
        self.channels: int = channels
        self.chunk_size: int = chunk_size
        self.framerate: int = framerate

    def __del__(self):
        """Destructor."""
        self.audio_interface.terminate()

    def record(self):
        """Do record audio."""
        with KeyboardListener(
                on_press=self._on_key_press,
                on_release=self._on_key_release) as listener:
            while True:
                if self.do_record:
                    frames = self._record_audio()
                    self._write_to_file(frames)
                time.sleep(0.1)  # reduce the amount of cpu load like a lot

            listener.join()

    def _on_key_press(self, key):
        """Trigger recording on key pess Enter."""
        if key is pynput.keyboard.Key.enter and not self.do_record:
            print("Start Recording...")
            self.do_record = True

    def _on_key_release(self, key):
        """Stop recording on key up Enter."""
        if key is pynput.keyboard.Key.enter:
            print("End Recording")
            self.do_record = False

    def _fill_audio_buffer(
            self,
            frames,
            in_data,
            frame_count,
            time_info,
            status_flags):
        """Continuously collect from the audio stream, into the buffer."""
        if self.do_record:
            frames.append(in_data)
            return None, pyaudio.paContinue
        return None, pyaudio.paComplete

    def _record_audio(self):
        """Opens a recording stream in a context manager."""
        frames = []
        audio_stream = self.audio_interface.open(
            channels=self.channels,
            format=self.SAMPLE_FORMAT,
            frames_per_buffer=self.chunk_size,
            input=True,
            rate=self.framerate,
            stream_callback=functools.partial(
                self._fill_audio_buffer,
                frames))

        while audio_stream.is_active():
            pass

        audio_stream.stop_stream()
        audio_stream.close()

        return frames

    def _write_to_file(self, frames):
        """Write the given frames to file."""
        with wave.open(f"output-{time.time()}.wav", "wb") as file:
            file.setnchannels(self.channels)
            sample_size = pyaudio.get_sample_size(self.SAMPLE_FORMAT)
            file.setsampwidth(sample_size)
            file.setframerate(self.framerate)
            file.writeframes(b"".join(frames))


recorder = AudioRecorder()
recorder.record()
