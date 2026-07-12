# vosk-hello

Speech-to-text demos using [Vosk](https://alphacephei.com/vosk/).

## Setup

```bash
sudo apt install portaudio19-dev   # required to build pyaudio
uv sync
```

Download a Vosk model ([list](https://alphacephei.com/vosk/models)):

```bash
model='vosk-model-en-us-0.42-gigaspeech'
wget "https://alphacephei.com/vosk/models/${model}.zip"
unzip "${model}.zip" && mv "${model}" model && rm "${model}.zip"
```

## Usage

Microphone transcription: `python vosk-microphone-to-text-hello.py`

File transcription: `bash vosk-file-to-text-hello.sh <audio-file> <language>`
