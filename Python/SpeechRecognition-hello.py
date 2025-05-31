"""found here:
https://elementztechblog.wordpress.com/2015/01/22/using-speech-to-text-in-ubuntu/

Prior to that, I had to do:

pip install SpeechRecognition
sudo apt-get install portaudio19-dev python-pyaudio python3-pyaudio
pip install pyaudio  # for microphones
"""

# Sample code for speech recognition
# Uses google speech engine API
# Network should be connected to the system
import pprint

import pyaudio
import speech_recognition as sr

device_count = pyaudio.PyAudio().get_device_count()  # gives me 10 and prints somethings onto stdout
print(f"Device Count: {device_count}")

print(f"Devices: {device_count}")
pp = pprint.PrettyPrinter()
pp.pprint(sr.Microphone.list_microphone_names())


print("Start listening.")
r = sr.Recognizer()
with sr.Microphone() as source:
    r.adjust_for_ambient_noise(source)
    audio = r.listen(source)
print("End listening.")

try:
    response = r.recognize_google(audio)
except sr.UnknownValueError:
    print("UnknownValueError: Apparently unrecognizable")

print(type(response))
print(f"Response: {response}")
