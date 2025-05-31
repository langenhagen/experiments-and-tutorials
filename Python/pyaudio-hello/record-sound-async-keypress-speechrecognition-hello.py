"""Record a sound via pyyaudio on keypress,
then send it to Google for speech recognition
and use the resulting text as a search query for google.com.
"""

import functools
import io
import subprocess
import time

import pyaudio
import pynput
import speech_recognition as sr

KeyboardListener = pynput.keyboard.Listener


class AudioRecognitionClient:
    """An audio recorder that records as long as you press a button."""

    SAMPLE_FORMAT: int = pyaudio.paInt16  # 16 bits per sample
    TRIGGER_KEY = pynput.keyboard.Key.enter

    def __init__(
        self,
        channels: int = 1,
        chunk_size: int = 1024,
        framerate: int = 44100,
    ) -> None:
        """Initialize the audio recorder."""
        self.audio_interface = pyaudio.PyAudio()
        self.do_record: bool = False
        self.channels: int = channels
        self.chunk_size: int = chunk_size
        self.framerate: int = framerate

    def __del__(self) -> None:
        """Destructor."""
        self.audio_interface.terminate()

    def recognize(self) -> None:
        """Do record audio."""
        with KeyboardListener(
            on_press=self._on_key_press,
            on_release=self._on_key_release,
        ) as listener:
            while True:
                if self.do_record:
                    frames = self._record_audio()
                    response = self._recognize_audio(frames)
                    print(f"> {response}")

                    if response:
                        slug = response.replace(" ", "+").replace("'", r"\'")
                        print(f">> {slug}")
                        subprocess.run(
                            "/opt/google/chrome/chrome "
                            f"https://www.google.com/search?q={slug}",
                            shell=True,
                        )

                time.sleep(0.1)  # reduce the amount of cpu load like a lot

            listener.join()

    def _on_key_press(self, key) -> None:
        """Trigger recording on trigger key pess."""
        if key is self.TRIGGER_KEY and not self.do_record:
            print("Start Recording...")
            self.do_record = True

    def _on_key_release(self, key) -> None:
        """Stop recording on key up trigger key."""
        if key is self.TRIGGER_KEY:
            print("End Recording")
            self.do_record = False

    def _fill_audio_buffer(
        self,
        frames: io.BytesIO,
        in_data,
        frame_count,
        time_info,
        status_flags,
    ):
        """Continuously collect from the audio stream, into the buffer."""
        if self.do_record:
            frames.write(in_data)
            return None, pyaudio.paContinue
        return None, pyaudio.paComplete

    def _record_audio(self) -> io.BytesIO:
        """Opens a recording stream in a context manager."""
        frames = io.BytesIO()
        audio_stream = self.audio_interface.open(
            channels=self.channels,
            format=self.SAMPLE_FORMAT,
            frames_per_buffer=self.chunk_size,
            input=True,
            rate=self.framerate,
            stream_callback=functools.partial(
                self._fill_audio_buffer,
                frames,
            ),
        )

        while audio_stream.is_active():
            pass

        audio_stream.stop_stream()
        audio_stream.close()

        return frames

    def _recognize_audio(self, frames: io.BytesIO) -> str:
        """Send audio data to google in order to get the speech recognition."""
        audio = sr.AudioData(
            frames.getvalue(),
            self.framerate,
            pyaudio.get_sample_size(self.SAMPLE_FORMAT),
        )

        recognizer = sr.Recognizer()
        response = None
        try:
            response = recognizer.recognize_google(audio)
        except sr.UnknownValueError:
            print("UnknownValueError: Apparently unrecognizable")

        return response


client = AudioRecognitionClient()
client.recognize()
